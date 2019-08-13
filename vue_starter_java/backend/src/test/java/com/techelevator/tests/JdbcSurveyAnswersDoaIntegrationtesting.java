package com.techelevator.tests;

import static org.junit.Assert.assertEquals;

import org.bouncycastle.asn1.x9.DHDomainParameters;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.model.JdbcSurveyAnswersDao;
import com.techelevator.model.SurveyAnswersDao;
import com.techelevator.model.SurveyAnswers;

public class JdbcSurveyAnswersDoaIntegrationtesting extends DAOIntegrationTesting{

	
	private SurveyAnswersDao dao;
	private JdbcTemplate jdbcTemplate;
	
	@Before
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		dao = new JdbcSurveyAnswersDao(dataSource);
		
		String sql = "INSERT INTO SurveyAnswers (answer_id, answer, event_id, id, question_id) "+
		"VALUES (2, 'first answer', 1, 1, 1), (1, 'second answer', 1, 1, 1);";
	
		
		
		
		jdbcTemplate.update(sql);
	}
	
	@Test
	public void getAnswersByEventAndUserIdTest() {
		
		assertEquals(2, dao.getAnswersByEventAndUserId(1, 1).size());
		assertEquals(0, dao.getAnswersByEventAndUserId(2, 2).size());
		assertEquals("first answer", dao.getAnswersByEventAndUserId(1, 1).get(0).getAnswer());
	}
	
	@Test
	public void saveAnswerTest() {
		SurveyAnswers surveyAnswers = new SurveyAnswers();
		surveyAnswers.setAnswer("new answer");
		surveyAnswers.setEventId(2);
		surveyAnswers.setQuestionId(2);
		surveyAnswers.setUserId(2);
		dao.saveAnswer(surveyAnswers);
		
		assertEquals(1, dao.getAnswersByEventAndUserId(2, 2).size());
		assertEquals("new answer", dao.getAnswersByEventAndUserId(2, 2).get(0).getAnswer());
	}

}
