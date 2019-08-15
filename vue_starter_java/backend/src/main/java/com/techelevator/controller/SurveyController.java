package com.techelevator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import com.techelevator.authentication.AuthProvider;
import com.techelevator.model.Event;
import com.techelevator.model.SurveyQuestions;
import com.techelevator.model.SurveyQuestionsDao;

@RestController
public class SurveyController {

	@Autowired
	private AuthProvider auth;

	private final SurveyQuestionsDao surveyDao;

	public SurveyController(SurveyQuestionsDao surveyDao) {
		this.surveyDao = surveyDao;
	}

	@GetMapping(path = "/api/survey/{eventId}")
	public List<SurveyQuestions> getQuestionsByEvent(Integer eventId) {
		return surveyDao.getQuestionsByEvent(eventId);
	}

	@PostMapping(path = "/api/save-survey")
	public void saveQuestions(@RequestBody SurveyQuestions surveyQuestions) {
		surveyDao.saveQuestions(surveyQuestions);
	}

}
