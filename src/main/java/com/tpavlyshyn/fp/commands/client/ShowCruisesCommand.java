package com.tpavlyshyn.fp.commands.client;


import com.tpavlyshyn.fp.DurationRanges;
import com.tpavlyshyn.fp.commands.Path;
import com.tpavlyshyn.fp.commands.action.Redirect;
import com.tpavlyshyn.fp.dto.CruisesNumberOfRows;
import com.tpavlyshyn.fp.entity.Cruise;
import com.tpavlyshyn.fp.exceptions.ServiceException;
import com.tpavlyshyn.fp.services.CruiseService;
import com.tpavlyshyn.fp.services.impl.CruiseServiceImpl;
import com.tpavlyshyn.fp.commands.Command;
import com.tpavlyshyn.fp.commands.action.Dispatcher;
import com.tpavlyshyn.fp.commands.action.Forward;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

public class ShowCruisesCommand implements Command {
    private final static Logger log = Logger.getLogger(ShowCruisesCommand.class);

    private final CruiseService cruiseService;

    public ShowCruisesCommand(CruiseService cruiseService) {
        this.cruiseService = cruiseService;
    }
    @Override
    public Dispatcher execute(HttpServletRequest request, HttpServletResponse response) {

        Optional<YearMonth> yearMonth = getYearMonth(request);
        Optional<Integer> month = Optional.empty();
        Optional<Integer> year = Optional.empty();
        if (yearMonth.isPresent()) {
            year = Optional.of(yearMonth.get().getYear());
            month = Optional.of(yearMonth.get().getMonthValue());
        }

        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
/*
        int recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));
*/
        int recordsPerPage = 2;
        int start = currentPage * recordsPerPage - recordsPerPage;


        CruisesNumberOfRows cruisesNumberOfRows;
        DurationRanges durationR = getDurationRanges(request);
        List<Cruise> cruises;

        String land = (String) request.getSession().getAttribute("locale");

        try {
            cruisesNumberOfRows = cruiseService.showCruisesWithParam(durationR.getMinDay(), durationR.getMaxDay(), month, year, start, recordsPerPage, land);
            cruises = cruisesNumberOfRows.getCruises();
            request.setAttribute("cruises", cruises);

            int numberOfPages = getNumberOfPages(recordsPerPage, cruisesNumberOfRows.getNumberOfRows());
            request.setAttribute("numberOfPages", numberOfPages);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("recordsPerPage", recordsPerPage);

        } catch (ServiceException ex) {
            log.error(ex.getMessage(), ex);
            return new Redirect("error page");
        }
        return new Forward(Path.PAGE__SHOW_CRUISES);
    }

    private DurationRanges getDurationRanges(HttpServletRequest request) {
        Optional<String> durationName = Optional.ofNullable(request.getParameter("duration"));

        Optional<DurationRanges> durationR = Optional.of(DurationRanges.ANY);
        if (durationName.isPresent()) {
            durationR = DurationRanges.findByName(durationName.get());
            if (durationR.isEmpty()) {
                durationR = Optional.of(DurationRanges.ANY);
            }
        }
        return durationR.get();
    }

    private Optional<YearMonth> getYearMonth(HttpServletRequest request) {
        Optional<String> yearMonthOpt = Optional.ofNullable(request.getParameter("month"));
        Optional<YearMonth> yearMonth = Optional.empty();

        if (yearMonthOpt.isPresent() && (!yearMonthOpt.get().equals(""))) {
            yearMonth = yearMonthOpt.map(YearMonth::parse);
        }
        return yearMonth;
    }

    public int getNumberOfPages(int recordsPerPage, int numberOfRows) throws ServiceException {
        int numberOfPages;
        numberOfPages = numberOfRows / recordsPerPage;
        if (numberOfRows % recordsPerPage > 0) {
            numberOfPages++;
        }
        log.debug("Number of rows--> " + numberOfRows);
        log.debug("Records per page--> " + recordsPerPage);
        log.debug("Number of pages--> " + numberOfPages);
        return numberOfPages;
    }
}

