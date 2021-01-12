package com.tariq.practice.personalDetails.personalInfo.service;

import java.util.List;

import com.tariq.practice.personalDetails.personalInfo.bean.Concept_Understanding_CURD_Bean;
import com.tariq.practice.personalDetails.personalInfo.model.Concept_Understanding_CURD;

public interface Concept_Understanding_CURD_Service {

	/********* 1st time prac****************/
	public void addEmployee( Concept_Understanding_CURD employee);
	public Concept_Understanding_CURD saveCurdWithService(Concept_Understanding_CURD_Bean bean);
	
	/********* 2nd time prac****************/
	
	public void addEmployeeSecondPrac(Concept_Understanding_CURD curd);
	
	public Concept_Understanding_CURD saveWithSecondPrac(Concept_Understanding_CURD_Bean bean);
	
	/******** 3rd Practice********/
	
	public String save(Concept_Understanding_CURD_Bean strCurd);
	
	
	
	
	//edit coding
	
	public Concept_Understanding_CURD getCurd(int id);
	public Concept_Understanding_CURD getCurd1(int id);
	
	public Concept_Understanding_CURD_Bean getCurdIdByBeanInstance(int id);
	
	
	// list coding
	
	public List<Concept_Understanding_CURD> getAllCurdList();
	
	//delete
	
	public void deleteCurd(Concept_Understanding_CURD curd);
	
	
}
