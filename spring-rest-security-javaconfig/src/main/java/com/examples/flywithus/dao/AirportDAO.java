package com.examples.flywithus.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.examples.flywithus.entity.Airport;
import com.examples.flywithus.entity.Flight;
import com.examples.flywithus.entity.UserInfo;

@Transactional(readOnly = true)
@Repository
public class AirportDAO implements IAirportDAO{

    @Autowired
    private HibernateTemplate hibernateTemplate;
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Airport> getAllAirports() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Airport.class);
        List<?> list = hibernateTemplate.findByCriteria(detachedCriteria);
        return (List<Airport>) list;
    }

    @Override
    public Airport getAirport(int airportId) {
        Airport airport =  new Airport();
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Airport.class);
        detachedCriteria.add(Restrictions.eq("airportId", airportId));
        List<?> list = hibernateTemplate.findByCriteria(detachedCriteria);
        if(!list.isEmpty()) {
            airport = (Airport)list.get(0);
        }
        return airport;
    }

}
