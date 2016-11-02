/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.usf.cutr.ontimeperformance.gtfs;

import org.onebusaway.gtfs.impl.GtfsRelationalDaoImpl;
import org.onebusaway.gtfs.model.ServiceCalendar;

import java.util.Date;


/**
 * Retrieves a base set of statistics from the GTFS.
 * 
 */
public class GtfsStatisticsService {

	private GtfsRelationalDaoImpl gtfsDao = null;
	public GtfsStatisticsService(GtfsRelationalDaoImpl dao) {
		gtfsDao = dao;
        }
	
	public Date getCalendarServiceRangeStart() {

		Date startDate = null;

		for (ServiceCalendar serviceCalendar : gtfsDao.getAllCalendars()) {

			if (startDate == null
					|| serviceCalendar.getStartDate().getAsDate().before(startDate))
				startDate = serviceCalendar.getStartDate().getAsDate();
		}

		return startDate;

	}

	public Date getCalendarServiceRangeEnd() {

		Date endDate = null;

		for (ServiceCalendar serviceCalendar : gtfsDao.getAllCalendars()) {
			if (endDate == null
          || serviceCalendar.getEndDate().getAsDate().after(endDate))
        endDate = serviceCalendar.getEndDate().getAsDate();
		}

		return endDate;
	}
}
