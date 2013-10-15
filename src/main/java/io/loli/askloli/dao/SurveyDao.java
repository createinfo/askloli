package io.loli.askloli.dao;

import io.loli.askloli.entity.Survey;

import java.util.List;

public interface SurveyDao {

    void save(Survey survey);

    List<Survey> listByUser(int u_id);

    Survey findById(int id);

}