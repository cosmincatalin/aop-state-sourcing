package com.repositories;

import com.entities.CompanyEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CompanyRepositoryHibernate implements CompanyRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public CompanyRepositoryHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void save(CompanyEntity company) {
        sessionFactory.getCurrentSession().saveOrUpdate(company);
        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().clear();
    }

}
