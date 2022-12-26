package com.career.mentor.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.career.constants.AppConstants;
import com.career.dto.ResponseDto;
import com.career.mentor.dto.MentorUpdateDto;
import com.career.mentor.dto.RegisterMentorDto;
import com.career.mentor.entity.Mentor;
import com.career.mentor.service.MentorService;

@RestController
@RequestMapping("mentor")
public class MentorController {
	
	@Autowired
	MentorService mentorService;
	
	@PostMapping("/register")
	public ResponseEntity<ResponseDto> registerMentor(@RequestBody RegisterMentorDto registerMentorDto){
		
		if(AppConstants.MENTOR_SAVE_FAIL.equals(mentorService.registerMentor(registerMentorDto)))
			return new ResponseEntity<> (new ResponseDto(AppConstants.MENTOR_SAVE_FAIL, HttpStatus.BAD_REQUEST.value(),
					LocalDateTime.now().toString()), HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<> (new ResponseDto(AppConstants.MENTOR_SAVE_SUCCESS, HttpStatus.OK.value(),
					LocalDateTime.now().toString()), HttpStatus.OK);
		
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Mentor>> getAllMentor(){
		return new ResponseEntity<List<Mentor>>(mentorService.getAllMentor(), HttpStatus.FOUND);
		
	}

	@GetMapping("/{id}")
	public ResponseEntity<Mentor> getMentorById(@PathVariable Long id){
		return new ResponseEntity<Mentor>(mentorService.getMentorById(id), HttpStatus.FOUND);
	}
	
	@PutMapping("/updateMentor")
	public ResponseEntity<ResponseDto> updateMentor(@RequestBody MentorUpdateDto mentorUpdateDto){
		if(AppConstants.MENTOR_NOT_FOUND.equals(mentorService.updateMentor(mentorUpdateDto)))
			return new ResponseEntity<>(new ResponseDto(AppConstants.MENTOR_NOT_FOUND, HttpStatus.NOT_FOUND.value(),
					LocalDateTime.now().toString()), HttpStatus.NOT_FOUND);
		else if(AppConstants.MENTOR_UPDATE_FAIL.equals(mentorService.updateMentor(mentorUpdateDto)))
			return new ResponseEntity<>(new ResponseDto(AppConstants.MENTOR_UPDATE_FAIL, HttpStatus.BAD_REQUEST.value(),
					LocalDateTime.now().toString()), HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(new ResponseDto(AppConstants.MENTOR_UPDATE_SUCCESS, HttpStatus.BAD_REQUEST.value(),
					LocalDateTime.now().toString()), HttpStatus.BAD_REQUEST);
		

	}
	
	@DeleteMapping("/{mentorId}")
	public ResponseEntity<ResponseDto> deleteMentor(@PathVariable Long mentorId){
		if(AppConstants.DELETE_FAILURE.equals(mentorService.deleteMentor(mentorId))) 
				return new ResponseEntity<>(new ResponseDto(AppConstants.DELETE_FAILURE, HttpStatus.NOT_FOUND.value(),
						LocalDateTime.now().toString()),HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(new ResponseDto(AppConstants.DELETE_SUCCESS, HttpStatus.OK.value(),
						LocalDateTime.now().toString()),HttpStatus.OK);
	}
	
	
}
