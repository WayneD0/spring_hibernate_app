package com.tariq.practice.personalDetails.personalInfo.dao;

import java.util.List;

import com.tariq.practice.personalDetails.personalInfo.model.Concept_Understanding_CURD;

public interface Concept_Understanding_CURD_Controller_DAO {
	
	public void addEmployee(Concept_Understanding_CURD curd);
	
	
	
	// edit code
	
	public Concept_Understanding_CURD getCurd(int id);
	
	// list coding 
	
	public List<Concept_Understanding_CURD> getAllCurdList();
	
	// delete
	
	public void deleteCurd(Concept_Understanding_CURD curd);
}
