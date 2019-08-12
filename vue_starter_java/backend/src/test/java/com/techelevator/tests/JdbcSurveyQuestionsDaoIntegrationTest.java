package com.techelevator.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.model.JdbcSurveyQuestionsDao;
import com.techelevator.model.SurveyQuestions;
import com.techelevator.model.SurveyQuestionsDao;

import io.jsonwebtoken.lang.Assert;


public class JdbcSurveyQuestionsDaoIntegrationTest extends DAOIntegrationTesting{

	private SurveyQuestionsDao dao;
	private JdbcTemplate jdbcTemplate;
	
	@Before
public void setup() {
	jdbcTemplate = new JdbcTemplate(dataSource);
	dao = new JdbcSurveyQuestionsDao(dataSource);
	String sql = "INSERT INTO SurveyQuestions(question, event_id) " +
			"VALUES ('Tech Elevator Whiskey', 1), ('Does this', 1);";
	
	jdbcTemplate.update(sql);
	
}
	@Test
	public void getAllQuestionsTest() {
		assertEquals(2, dao.getAllQuestions().size());
		
		assertEquals("Tech Elevator Whiskey", dao.getAllQuestions().get(0).getQuestion());
		
		assertEquals( new Integer(1), dao.getAllQuestions().get(0).getEventId());
	
}
	@Test
	public void saveQuestionsTest() {
		SurveyQuestions surveyQuestion = new SurveyQuestions();
		surveyQuestion.setEventId(1);
		surveyQuestion.setQuestion("is this working");
		surveyQuestion.setQuestionId(3);
		surveyQuestion.setQuestionType("false");
		dao.saveQuestions(surveyQuestion);
		
		assertEquals(3, dao.getAllQuestions().size());
		
		assertEquals("is this working", dao.getAllQuestions().get(2).getQuestion());
	}
	
	@Test
	public void getQuestionsByEventTest() {
		assertEquals(2, dao.getQuestionsByEvent(1).size());
		assertEquals(0, dao.getQuestionsByEvent(2).size());
	}
}