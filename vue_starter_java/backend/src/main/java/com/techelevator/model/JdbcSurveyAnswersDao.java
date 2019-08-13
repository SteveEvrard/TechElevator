package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
@Component

public class JdbcSurveyAnswersDao implements SurveyAnswersDao {

	private JdbcTemplate jdbcTemplate;
	@Autowired
	 public JdbcSurveyAnswersDao(DataSource dataSource) {
	this.jdbcTemplate = new JdbcTemplate(dataSource);	 
	 }
	
		
	
	
	@Override
	public List<SurveyAnswers> getAnswersByEventAndUserId(Integer id, Integer eventId) {
		List<SurveyAnswers> surveyAnswers = new ArrayList<SurveyAnswers>();
		
		String sql = "SELECT answer, answer_id, question_id, id, event_id FROM surveyAnswers "
				+ "WHERE event_id = ? AND id = ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id, eventId);
		while(results.next()) {
			surveyAnswers.add(mapRowToSurveyAnswers(results));
		}
		return surveyAnswers;
	}

	@Override
	public void saveAnswer(SurveyAnswers surveyAnswers){
		
		String sql ="INSERT INTO SurveyAnswers (answer, event_id, id, question_id) "
				+ "VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(sql, surveyAnswers.getAnswer(), surveyAnswers.getEventId(), surveyAnswers.getUserId(), surveyAnswers.getQuestionId());
		
	}
	
	private SurveyAnswers mapRowToSurveyAnswers(SqlRowSet results) {
		SurveyAnswers surveyAnswers=new SurveyAnswers();
		
		surveyAnswers.setAnswer(results.getString("answer"));
		surveyAnswers.setAnswerId(results.getInt("answer_id"));
		surveyAnswers.setEventId(results.getInt("event_id"));
		surveyAnswers.setUserId(results.getInt("id"));
		surveyAnswers.setQuestionId(results.getInt("question_id"));
		
		return surveyAnswers;
		
	}

}
