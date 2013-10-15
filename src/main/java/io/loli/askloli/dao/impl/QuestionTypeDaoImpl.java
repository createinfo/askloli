package io.loli.askloli.dao.impl;

import io.loli.askloli.entity.QuestionType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class QuestionTypeDaoImpl {
    @PersistenceContext
    private EntityManager em;

    public QuestionType findByName(String name) {
        return em.createNamedQuery("QuestionType.findbyName",
                QuestionType.class).getSingleResult();
    }
}
