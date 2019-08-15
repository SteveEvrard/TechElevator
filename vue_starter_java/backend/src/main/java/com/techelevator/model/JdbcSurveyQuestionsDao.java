package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcSurveyQuestionsDao implements SurveyQuestionsDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcSurveyQuestionsDao(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	@Override
	public List<SurveyQuestions> getAllQuestions() {
		List<SurveyQuestions> allQuestions = new ArrayList<SurveyQuestions>();
		
		String sql = "SELECT question_id, question, question_type, event_id "
				+ "FROM SurveyQuestions";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while(results.next()) {
			allQuestions.add(mapRowToSurveyQuestions(results));
		}
		
		return allQuestions;
	}

	@Override
	public List<SurveyQuestions> getQuestionsByEvent(Integer id) {
	List<SurveyQuestions> eventQuestions = new ArrayList<>();
	
	String sql = "SELECT  question,  event_id "
			+ "FROM SurveyQuestions WHERE event_id = ?";
	SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
	while(results.next()) {
		eventQuestions.add(mapRowToSurveyQuestions(results));
	}
		return eventQuestions;
	}
	
	
	private SurveyQuestions mapRowToSurveyQuestions(SqlRowSet results) {
		SurveyQuestions surveyQuestions =new SurveyQuestions();

		surveyQuestions.setQuestion(results.getString("question"));

		surveyQuestions.setEventId(results.getInt("event_id"));
		
		return surveyQuestions;
	}

	@Override
	public void saveQuestions(SurveyQuestions surveyQuestions) {
		String sql = "INSERT INTO SurveyQuestions(question, event_id) "+
					"VALUES (?, ?)";
		
		jdbcTemplate.update(sql, surveyQuestions.getQuestion(), surveyQuestions.getEventId());
		
	}

}
