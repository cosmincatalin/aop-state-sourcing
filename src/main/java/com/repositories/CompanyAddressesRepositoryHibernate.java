package com.repositories;

import com.entities.CompanyAddressEntity;
import com.entities.CompanyEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CompanyAddressesRepositoryHibernate implements CompanyAddressesRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public CompanyAddressesRepositoryHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void save(CompanyAddressEntity companyAddress) {
        sessionFactory.getCurrentSession().saveOrUpdate(companyAddress);
        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().clear();
    }

}
