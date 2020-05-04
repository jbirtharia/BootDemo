package com.example.demo.repository;

import com.example.demo.entity.RoomEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/*This Repository Class is for Hibernate Implementation. Uncomment all lines when wants run using hibernate*/
/*@Repository*/
public class RoomRepositoryImpl implements RoomRepositoryHiber{

    //@Autowired
    private SessionFactory sessionFactory;

    @Override
    public Optional<RoomEntity> findById(Integer id) {
        Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(RoomEntity.class).add(Restrictions.eq("id", id));
        RoomEntity roomEntity = (RoomEntity) criteria.uniqueResult();
        if (roomEntity != null)
            return Optional.of(roomEntity);
        else
            return null;
    }

    @Override
    public RoomEntity save(RoomEntity roomEntity) {
        return (RoomEntity) sessionFactory.getCurrentSession().merge(roomEntity);
    }

    @Override
    public RoomEntity updateRoom(Integer id, String name, String number, String bed) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(RoomEntity.class).add(Restrictions.eq("id", id));
        RoomEntity roomEntity = (RoomEntity) criteria.uniqueResult();
        roomEntity.setBed(bed);
        roomEntity.setName(name);
        roomEntity.setNumber(number);
        return (RoomEntity) session.merge(roomEntity);
    }

    @Override
    public RoomEntity updateRoomNumber(Integer id, String number) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(RoomEntity.class).add(Restrictions.eq("id", id));
        RoomEntity roomEntity = (RoomEntity) criteria.uniqueResult();
        roomEntity.setNumber(number);
        return (RoomEntity) session.merge(roomEntity);
    }

    @Override
    public RoomEntity updateRoomName(Integer id, String name) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(RoomEntity.class).add(Restrictions.eq("id", id));
        RoomEntity roomEntity = (RoomEntity) criteria.uniqueResult();
        roomEntity.setName(name);
        return (RoomEntity) session.merge(roomEntity);
    }

    @Override
    public RoomEntity updateRoomBed(Integer id, String bed) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(RoomEntity.class).add(Restrictions.eq("id", id));
        RoomEntity roomEntity = (RoomEntity) criteria.uniqueResult();
        roomEntity.setBed(bed);
        return (RoomEntity) session.merge(roomEntity);
    }

    @Override
    public void delete(RoomEntity roomEntity) {
        sessionFactory.getCurrentSession().delete(roomEntity);
    }

    @Override
    public Iterable<RoomEntity> findAlls() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(RoomEntity.class);
        return (Iterable<RoomEntity>) criteria.list();
    }
}
