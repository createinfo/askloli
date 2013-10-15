package io.loli.askloli.dao.impl;

import io.loli.askloli.entity.Question;

import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named("questionDao")
public class QuestionDaoImpl {
    @PersistenceContext
    private EntityManager em;

    public void save(Question question) {
        em.persist(question);
    }

    public void delete(Question question) {
        em.remove(question);
    }

    public Question findById(int id) {
        return em.find(Question.class, id);
    }

    public List<Question> listBySurveyId(int s_id) {
        return em.createNamedQuery("Question.listBySurveyId", Question.class)
                .getResultList();
    }
}
