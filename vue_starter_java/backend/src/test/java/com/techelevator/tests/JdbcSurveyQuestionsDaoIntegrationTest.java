package com.techelevator.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.model.JdbcSurveyQuestionsDao;
import com.techelevator.model.SurveyQuestionsDao;

import io.jsonwebtoken.lang.Assert;


public class JdbcSurveyQuestionsDaoIntegrationTest extends DAOIntegrationTesting{

	private SurveyQuestionsDao dao;
	private JdbcTemplate jdbcTemplate;
	
	@Before
public void setup() {
	jdbcTemplate = new JdbcTemplate(dataSource);
	dao = new JdbcSurveyQuestionsDao(dataSource);
	String sql = "INSERT INTO SurveyQuestions(question) " +
			"VALUES ('Tech Elevator Whiskey'), ('Does this');";
	
	jdbcTemplate.update(sql);
	
}
	@Test
	public void getAllQuestionsTest() {
		assertEquals(2, dao.getAllQuestions().size());
		assertEquals("Tech Elevator Whiskey", dao.getAllQuestions().get(0).getQuestion());
	}
	
	@Test
	public void 

}
