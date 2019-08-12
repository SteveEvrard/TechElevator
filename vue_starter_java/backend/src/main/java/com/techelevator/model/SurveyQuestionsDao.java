package com.techelevator.model;

import java.util.List;

public interface SurveyQuestionsDao {
	
List<SurveyQuestions> getAllQuestions();

List<SurveyQuestions> getQuestionsByEvent(Integer id);



}
