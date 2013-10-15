package io.loli.askloli.service.impl;

import io.loli.askloli.dao.SurveyDao;
import io.loli.askloli.entity.Survey;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Named("surveyService")
public class SurveyServiceImpl {
    @Inject
    private SurveyDao surveyDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public void save(Survey survey) {
        surveyDao.save(survey);
    }

    @Transactional(propagation = Propagation.NEVER)
    public Survey findById(int id) {
        return surveyDao.findById(id);
    }
    
    @Transactional(propagation = Propagation.NEVER)
    public List<Survey> listByUser(int u_id){
        return surveyDao.listByUser(u_id);
    }
    
    public Survey update(Survey survey){
        Survey entity = surveyDao.findById(survey.getId());
        entity.setTitle(survey.getTitle());
        entity.setDescription(survey.getDescription());
        return entity;
    }
}
