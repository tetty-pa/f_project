package com.tpavlyshyn.fp.dao.impl;

import com.tpavlyshyn.fp.dao.CruiseDao;
import com.tpavlyshyn.fp.dto.CruisesNumberOfRows;
import com.tpavlyshyn.fp.exceptions.DaoException;
import com.tpavlyshyn.fp.Fields;
import com.tpavlyshyn.fp.entity.Cruise;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
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
                    "WHERE cruise.id = ? " +
                    "  AND tc.lang = ? ";
    private static final String SQL__FIND_ALL_CRUISES =
            "SELECT *,  COUNT(*) OVER() as number_of_rows FROM cruise " +
                    "INNER JOIN translation_cruise tc on cruise.id = tc.cruise_id ";

    private static final String SQL__DELETE_CRUISE =
            "DELETE FROM cruise WHERE id=?";

    private static final String SQL__ADD_CRUISE =
            "INSERT INTO cruise(id, number_of_ports, price, " +
                    "start_date, end_date, liner_id) VALUES " +
                    " (default, ?, ?, ?, ?, ?);\n";


    private static final String SQL__FIND_FREE_PLACES_BY_ID =
            "SELECT l.passenger_capacity - COALESCE(current_usage, 0) as free_places FROM cruise c " +
                    "JOIN liner l ON l.id = c.liner_id " +
                    "LEFT JOIN ( " +
                    "    SELECT r.cruise_id, sum(amount) as current_usage " +
                    "    FROM request r " +
                    "    GROUP BY 1 " +
                    ") r on c.id = r.cruise_id " +
                    "WHERE c.id = ?;";

    private static final String SQL__GET_NUMBER_OF_PAGES =
            "SELECT COUNT(id) AS number_of_rows FROM cruise";

    private static final String SQL_UPDATE_CRUISE =
            " UPDATE cruise SET cruise_name =?, description=? , number_of_ports=? , price=? ," +
                    " start_date=? , end_date=? " +
                    " WHERE id=?";


    private final DataSource ds;

    public CruiseDaoImpl(DataSource ds) {
        this.ds = ds;
    }


    public Optional<Cruise> findByIdAndLang(Integer id, String lang) throws DaoException {
        Cruise cruise = null;

        try (Connection connection = ds.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(SQL__FIND_CRUISE_BY_ID);) {
            pstmt.setInt(1, id);
            pstmt.setString(2, lang);
            try (ResultSet rs = pstmt.executeQuery();) {
                if (rs.next()) {
                    cruise = extractCruise(rs);
                    cruise.setLiner(LinerDaoImpl.extractLiner(rs));
                }
            }

        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw new DaoException(ex.getMessage(), ex);
        }
        return Optional.ofNullable(cruise);
    }

    @Override
    public Optional<Cruise> findById(Integer id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        boolean result = false;
        try (Connection connection = ds.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(SQL__DELETE_CRUISE);) {

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
        boolean result = false;
        try (Connection connection = ds.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(SQL__ADD_CRUISE, Statement.RETURN_GENERATED_KEYS);) {

            setCruise(pstmt, cruise);
            result = pstmt.executeUpdate() > 0;

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    cruise.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw new DaoException(ex.getMessage(), ex);
        }
        return result;
    }

    @Override
    public boolean update(Cruise cruise) throws DaoException {
        boolean result = false;
        try (Connection connection = ds.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(SQL_UPDATE_CRUISE);) {

            setCruise(pstmt, cruise);
            pstmt.setInt(7, cruise.getId());
            result = pstmt.executeUpdate() > 0;

        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw new DaoException();
        }
        return result;
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
        List<Cruise> cruises = new ArrayList();
        CruisesNumberOfRows cruisesNumberOfRows = new CruisesNumberOfRows();
        int numberOfRows = 0;

        try (Connection connection = ds.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(SQL__FIND_ALL_CRUISES + whereExpression);) {

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
        List<Cruise> cruiseList = new ArrayList();
        try (Connection connection = ds.getConnection()) {
            String query = SQL__FIND_ALL_CRUISES + " WHERE tc.lang=?";
            try (PreparedStatement pstmt = connection.prepareStatement(query);) {
                pstmt.setString(1, lang);
                try (ResultSet rs = pstmt.executeQuery();) {
                    while (rs.next())
                        cruiseList.add(extractCruise(rs));
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
            try(ResultSet rs = pstmt.executeQuery();){
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
        pstmt.setString(k++, cruise.getCruiseName());/*
        pstmt.setString(k++, cruise.getCruisePhoto());*/
        pstmt.setString(k++, cruise.getDescription());
        pstmt.setInt(k++, cruise.getNumberOfPorts());
        pstmt.setInt(k++, cruise.getPrice());
/*
        pstmt.setString(k++, cruise.getRoute());
*/
        pstmt.setDate(k++, (Date) cruise.getStartDate());
        pstmt.setDate(k++, (Date) cruise.getEndDate());
/*
        pstmt.setInt(k, cruise.getLinerId());
*/
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
