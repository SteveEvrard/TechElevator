package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcSurveyQuestions implements SurveyQuestionsDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcSurveyQuestions(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	@Override
	public List<SurveyQuestions> getAllQuestions() {
		List<SurveyQuestions> allQuestions = new ArrayList<SurveyQuestions>();
		
		String sql = "SELECT question_id, question, question_type"
				+ "FROM SurveyQuestions";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while(results.next()) {
			allQuestions.add(mapRowToSurveyQuestions(results));
		}
		
		return allQuestions;
	}

	@Override
	public List<SurveyQuestions> getQuestionsByEvent(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	private SurveyQuestions mapRowToSurveyQuestions(SqlRowSet results) {
		SurveyQuestions surveyQuestions =new SurveyQuestions();
		surveyQuestions.setQuestionId(results.getInt("question_Id"));
		surveyQuestions.setQuestion(results.getString("question"));
		surveyQuestions.setQuestionType(results.getString("question_type"));
		
		return surveyQuestions;
	}

}
