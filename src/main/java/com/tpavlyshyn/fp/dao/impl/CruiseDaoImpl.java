package com.tpavlyshyn.fp.dao.impl;

import com.tpavlyshyn.fp.dao.CruiseDao;
import com.tpavlyshyn.fp.dto.CruisePort;
import com.tpavlyshyn.fp.dto.CruisesNumberOfRows;
import com.tpavlyshyn.fp.entity.Port;
import com.tpavlyshyn.fp.entity.TranslationCruise;
import com.tpavlyshyn.fp.exceptions.DaoException;
import com.tpavlyshyn.fp.Fields;
import com.tpavlyshyn.fp.entity.Cruise;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CruiseDaoImpl implements CruiseDao {

    private final static Logger log = Logger.getLogger(CruiseDaoImpl.class);

    private static final String SQL__FIND_CRUISE_BY_ID =
            "SELECT * " +
                    "FROM cruise " +
                    "INNER JOIN liner l on cruise.liner_id = l.id " +
                    "INNER JOIN translation_cruise tc on cruise.id = tc.cruise_id " +
                    "INNER JOIN cruise_has_port chp on cruise.id = chp.cruise_id " +
                    "INNER JOIN port p on chp.port_id = p.id" +
                    " WHERE cruise.id = ? " +
                    " AND tc.lang = ? AND p.lang=? ";
    private static final String SQL__FIND_ALL_CRUISES_WITH_PARAMETERS =
            "SELECT *, COUNT(*) OVER() as number_of_rows FROM cruise " +
                    "INNER JOIN translation_cruise tc on cruise.id = tc.cruise_id ";

    private static final String SQL__FIND_ALL_CRUISES =
            "SELECT * " +
                    "FROM cruise " +
                    " INNER JOIN translation_cruise tc on cruise.id = tc.cruise_id " +
                    " INNER JOIN liner l on cruise.liner_id = l.id " +
                    " WHERE tc.lang = ?";
    private static final String SQL__DELETE_CRUISE =
            "DELETE FROM cruise WHERE id=?";

    private static final String SQL__ADD_CRUISE =
            "INSERT INTO cruise(id, number_of_ports, price, " +
                    "start_date, end_date, liner_id) VALUES " +
                    " (default, ?, ?, ?, ?, ?);";

    private static final String SQL__ADD_PHOTO =
            "UPDATE cruise SET cruise_photo =? " +
                    "WHERE id=?";

    private static final String SQL__ADD_TRANSLATION_CRUISE =
            "INSERT INTO translation_cruise(cruise_id, lang, cruise_name, description) " +
                    "VALUES (?, ?, ?, ?)";
    private static final String SQL__ADD_CRUISE1 =
            "INSERT INTO cruise(id, number_of_ports, price, " +
                    "start_date, end_date, liner_id) VALUES " +
                    " (default, ?, ?, ?, ?, ?);" +
                    "INSERT INTO translation_cruise(cruise_id, lang, cruise_name, description) VALUES(?, ?, ?, ?), (?, ?, ?, ?)";


    private static final String SQL__FIND_FREE_PLACES_BY_ID =
            "SELECT l.passenger_capacity - COALESCE(current_usage, 0) as free_places FROM cruise c " +
                    "JOIN liner l ON l.id = c.liner_id " +
                    "LEFT JOIN ( " +
                    "    SELECT r.cruise_id, sum(amount) as current_usage " +
                    "    FROM request r " +
                    "    GROUP BY 1 " +
                    ") r on c.id = r.cruise_id " +
                    "WHERE c.id = ?;";


    private static final String SQL__UPDATE_CRUISE =
            " UPDATE cruise SET cruise_name =?, description=? , number_of_ports=? , price=? ," +
                    " start_date=? , end_date=? " +
                    " WHERE id=?";

    private static final String SQL__ADD_PORT_TO_CRUISE =
            "INSERT INTO cruise_has_port(cruise_id, port_id, sequence_number ,arrival_time) " +
                    "VALUES (?, ?, ?, ?)";
    private final DataSource ds;

    public CruiseDaoImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public Optional<Cruise> findByIdAndLang(Integer id, String lang) throws DaoException {
        Cruise cruise = null;
        List<Port> ports = new ArrayList<>();
        try (Connection connection = ds.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(SQL__FIND_CRUISE_BY_ID)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, lang);
            pstmt.setString(3, lang);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    cruise = extractCruise(rs);
                    cruise.setLiner(LinerDaoImpl.extractLiner(rs));

                    Port port = PortDaoImpl.extractPort(rs);
                    ports.add(port);
                }
            }
            assert cruise != null;
            cruise.setPortList(ports);
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw new DaoException(ex.getMessage(), ex);
        }
        return Optional.ofNullable(cruise);
    }

    @Override
    public Optional<Cruise> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        boolean result;
        try (Connection connection = ds.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(SQL__DELETE_CRUISE)) {

            pstmt.setInt(1, id);
            result = pstmt.executeUpdate() > 0;

        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw new DaoException(ex.getMessage(), ex);
        }
        return result;
    }

    @Override
    public boolean create(Cruise cruise) throws DaoException {
        return false;
    }

    @Override
    public boolean createCruiseWithTranslations(Cruise cruise, TranslationCruise translationCruiseUa, TranslationCruise translationCruiseEn, List<CruisePort> cruisePorts) throws DaoException {
        boolean resultCruise;
        boolean resultTranslationEn;
        boolean resultTranslationUa;
        boolean resultPhoto;
        try {
            Connection connection = ds.getConnection();
            try (PreparedStatement pstmt = connection.prepareStatement(SQL__ADD_CRUISE, Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement pstmt1 = connection.prepareStatement(SQL__ADD_PHOTO);
                 PreparedStatement pstmt2 = connection.prepareStatement(SQL__ADD_TRANSLATION_CRUISE);
                 PreparedStatement pstmt3 = connection.prepareStatement(SQL__ADD_TRANSLATION_CRUISE);
                 PreparedStatement pstmt4 = connection.prepareStatement(SQL__ADD_PORT_TO_CRUISE)) {
                setCruise(pstmt, cruise);
                resultCruise = pstmt.executeUpdate() > 0;
                int cruiseId = 0;
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        cruiseId = generatedKeys.getInt(1);
                        cruise.setId(cruiseId);
                    }
                }
                pstmt1.setString(1, "/img/cruisePhoto/" + cruiseId % 20 + ".png");
                pstmt1.setInt(2, cruiseId);
                resultPhoto = pstmt1.executeUpdate() > 0;
                translationCruiseUa.setCruiseId(cruiseId);
                translationCruiseEn.setCruiseId(cruiseId);
                setTranslation(translationCruiseUa, pstmt2);
                setTranslation(translationCruiseEn, pstmt3);

                resultTranslationUa = pstmt2.executeUpdate() > 0;
                resultTranslationEn = pstmt3.executeUpdate() > 0;

                for (CruisePort cruisePort : cruisePorts) {
                    pstmt3.setInt(1, cruiseId);
                    pstmt3.setInt(2, cruisePort.getPortId());
                    pstmt3.setInt(3, cruisePort.getSequenceNumber());
                    pstmt3.setTimestamp(4, cruisePort.getArrivalTime());
                    pstmt3.addBatch();
                }

                pstmt4.executeBatch();
                connection.commit();

            } catch (SQLException ex) {
                try {
                    connection.rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                log.error(ex.getMessage(), ex);
                throw new DaoException(ex.getMessage(), ex);
            } finally {
                connection.close();
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw new DaoException(ex.getMessage(), ex);
        }
        return resultCruise && resultTranslationEn && resultTranslationUa && resultPhoto;
    }

    private void setTranslation(TranslationCruise translationCruise, PreparedStatement pstmt) throws SQLException {
        pstmt.setInt(1, translationCruise.getCruiseId());
        pstmt.setString(2, translationCruise.getLand());
        pstmt.setString(3, translationCruise.getCruiseNameLand());
        pstmt.setString(4, translationCruise.getDescriptionLand());
    }


    @Override
    public boolean update(Cruise cruise) throws DaoException {

        return false;
    }


    @Override
    public CruisesNumberOfRows findAllWithParam(String whereExpression,
                                                Integer from,
                                                Integer to,
                                                Optional<Integer> month,
                                                Optional<Integer> year,
                                                int currentPage,
                                                int recordsPerPage,
                                                String lang) throws DaoException {
        List<Cruise> cruises = new ArrayList<>();
        CruisesNumberOfRows cruisesNumberOfRows = new CruisesNumberOfRows();
        int numberOfRows = 0;

        try (Connection connection = ds.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(SQL__FIND_ALL_CRUISES_WITH_PARAMETERS + whereExpression)) {

            setFindAllPstmt(from, to, month, year, currentPage, recordsPerPage, lang, pstmt);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Cruise cruise = extractCruise(rs);
                cruises.add(cruise);
                numberOfRows = Integer.parseInt(rs.getString("number_of_rows"));
            }
            cruisesNumberOfRows.setNumberOfRows(numberOfRows);
            cruisesNumberOfRows.setCruises(cruises);
        } catch (SQLException ex) {

            log.error(ex.getMessage(), ex);
            throw new DaoException(ex.getMessage(), ex);
        }
        return cruisesNumberOfRows;
    }

    private void setFindAllPstmt(Integer from,
                                 Integer to,
                                 Optional<Integer> month,
                                 Optional<Integer> year,
                                 int currentPage,
                                 int recordsPerPage,
                                 String lang,
                                 PreparedStatement pstmt) throws SQLException {
        int i = 1;
        pstmt.setString(i++, lang);
        pstmt.setInt(i++, from);
        pstmt.setInt(i++, to);
        if (month.isPresent() && month.get() != 0) {
            pstmt.setInt(i++, month.get());
        }
        if (year.isPresent() && year.get() != 0) {
            pstmt.setInt(i++, year.get());
        }
        pstmt.setInt(i++, currentPage);
        pstmt.setInt(i, recordsPerPage);
    }

    @Override
    public List<Cruise> findAllByLang(String lang) throws DaoException {
        List<Cruise> cruiseList = new ArrayList<>();
        try (Connection connection = ds.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(SQL__FIND_ALL_CRUISES)) {
            pstmt.setString(1, lang);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Cruise cruise = extractCruise(rs);
                    cruise.setLiner(LinerDaoImpl.extractLiner(rs));
                    cruiseList.add(cruise);
                }
            }

        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw new DaoException(ex.getMessage(), ex);
        }
        return cruiseList;
    }

    @Override
    public int getFreePlaces(int cruise_id) throws DaoException {
        int freePlaces = 0;

        try (Connection connection = ds.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(SQL__FIND_FREE_PLACES_BY_ID);) {

            pstmt.setInt(1, cruise_id);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next())
                    freePlaces = rs.getInt("free_places");
            }
        } catch (SQLException ex) {

            log.error(ex.getMessage(), ex);
            throw new DaoException(ex.getMessage(), ex);
        }
        return freePlaces;
    }

    public void setCruise(PreparedStatement pstmt, Cruise cruise) throws SQLException {
        int k = 1;
        pstmt.setInt(k++, cruise.getNumberOfPorts());
        pstmt.setInt(k++, cruise.getPrice());
        pstmt.setDate(k++, (Date) cruise.getStartDate());
        pstmt.setDate(k++, (Date) cruise.getEndDate());
        pstmt.setInt(k, cruise.getLinerId());
    }

    public static Cruise extractCruise(ResultSet rs) throws DaoException {
        try {
            Cruise cruise = new Cruise();
            cruise.setId(rs.getInt(Fields.ENTITY__ID));
            cruise.setNumberOfPorts(rs.getInt(Fields.CRUISE__NUMBER_OF_PORTS));
            cruise.setPrice(rs.getInt(Fields.CRUISE__PRICE));
            cruise.setStartDate(rs.getDate(Fields.CRUISE__START_DATE));
            cruise.setEndDate(rs.getDate(Fields.CRUISE__END_DATE));
            cruise.setLinerId(rs.getInt(Fields.CRUISE__LINER_ID));
            cruise.setCruisePhoto(rs.getString(Fields.CRUISE__PHOTO));
            cruise.setCruiseName(rs.getString(Fields.CRUISE__NAME));
            cruise.setDescription(rs.getString(Fields.CRUISE__DESCRIPTION));
            return cruise;
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage(), ex);
        }
    }

}
