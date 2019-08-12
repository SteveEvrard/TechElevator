package com.techelevator.model;

import java.util.List;

public interface SurveyAnswersDao {
	
List<SurveyAnswers> getAnswersByEventAndUserId(Integer id, Integer eventId);

void saveAnswer(SurveyAnswers surveyAnswers);

	
}
