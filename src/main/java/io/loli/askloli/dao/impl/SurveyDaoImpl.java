package io.loli.askloli.dao.impl;

import io.loli.askloli.dao.SurveyDao;
import io.loli.askloli.entity.Survey;

import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named("surveyDao")
public class SurveyDaoImpl implements SurveyDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Survey survey) {
        em.persist(survey);
    }

    @Override
    public List<Survey> listByUser(int u_id) {
        return em.createNamedQuery("listByUser", Survey.class).getResultList();
    }

    @Override
    public Survey findById(int id) {
        return em.find(Survey.class, id);
    }
}
