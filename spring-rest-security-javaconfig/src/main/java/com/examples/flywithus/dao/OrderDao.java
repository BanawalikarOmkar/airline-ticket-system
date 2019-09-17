package com.examples.flywithus.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.examples.flywithus.entity.Order;

@Transactional(readOnly = true)
@Repository
public class OrderDao implements IOrderDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Transactional(readOnly = false)
    @Override
    public void placeOrder(Order order) {
        hibernateTemplate.save(order);
    }

    @Override
    public Order getOrder(int orderId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Order.class);
        detachedCriteria.add(Restrictions.eq("orderId", orderId));
        List<?> list = hibernateTemplate.findByCriteria(detachedCriteria);
        if (list.isEmpty()) {
            return null;
        }
        return ((List<Order>) list).get(0);
    }

    @Override
    public List<Order> getAllOrders(String username) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Order.class);
        detachedCriteria.add(Restrictions.eq("username", username));
        detachedCriteria.addOrder(org.hibernate.criterion.Order.desc("orderDate"));
        List<?> list = hibernateTemplate.findByCriteria(detachedCriteria);
        return (List<Order>) list;
    }

    @Transactional(readOnly = false)
    @Override
    public int deleteOrder(int orderId) {
        hibernateTemplate.delete(getOrder(orderId));
        return 1;

    }

}
