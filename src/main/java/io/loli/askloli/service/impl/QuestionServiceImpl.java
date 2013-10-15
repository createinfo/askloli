package io.loli.askloli.service.impl;

import io.loli.askloli.dao.impl.QuestionDaoImpl;
import io.loli.askloli.entity.Question;

import javax.inject.Inject;
import javax.inject.Named;

@Named("questionservice")
public class QuestionServiceImpl {
    @Inject
    @Named("questionDao")
    private QuestionDaoImpl qd;

    public void save(Question question) {
        qd.save(question);
    }

    public Question findById(int id) {
        return qd.findById(id);
    }

    public void delete(Question question) {
        qd.delete(question);
    }

    public void deleteById(int id) {
        this.delete(qd.findById(id));
    }
}
