package com.examples.flywithus.dao;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.examples.flywithus.entity.Flight;

@Repository
@Transactional(readOnly = true)
public class FlightDAO implements IFlightDAO {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd");

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @SuppressWarnings("boxing")
    @Override
    public List<Flight> getMatchedFlights(Date startTime, int numberOfAdults, int fromAirport, int toAirport) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Flight.class);
        detachedCriteria.add(Restrictions.and(Restrictions.ge("availableAdultTickets", numberOfAdults),
                Restrictions.eq("fromLocation", fromAirport), Restrictions.eq("toLocation", toAirport),
                Restrictions.eq("startDate", startTime)));
        detachedCriteria.addOrder(Order.desc("startDate"));
        List<?> list = hibernateTemplate.findByCriteria(detachedCriteria);

        return (List<Flight>) list;
    }

    @Override
    public Flight getFlight(int flightId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Flight.class);
        detachedCriteria.add(Restrictions.eq("flightId", flightId));
        List<?> list = hibernateTemplate.findByCriteria(detachedCriteria);
        if (!list.isEmpty()) {
            return (Flight) list.get(0);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = false)
    public void addFlight(Flight flight) throws DataAccessException {
        hibernateTemplate.save(flight);

    }

    @Transactional(readOnly = false)
    public void updateFlight(Flight flight) {
        hibernateTemplate.update(flight);

    }

    @Transactional(readOnly = false)
    public void deleteFlight(int flightId) {
        Flight flight = getFlight(flightId);
        hibernateTemplate.delete(flight);
    }

    @Override
    public List<Flight> getAll() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Flight.class);
        List<?> list = hibernateTemplate.findByCriteria(detachedCriteria);

        return (List<Flight>) list;
    }

}
