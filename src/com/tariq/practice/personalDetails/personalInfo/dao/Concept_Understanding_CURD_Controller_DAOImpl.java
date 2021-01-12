package com.tariq.practice.personalDetails.personalInfo.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tariq.practice.personalDetails.personalInfo.model.Concept_Understanding_CURD;

@Repository("curdDao")
public class Concept_Understanding_CURD_Controller_DAOImpl implements Concept_Understanding_CURD_Controller_DAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addEmployee(Concept_Understanding_CURD curd) {
		sessionFactory.getCurrentSession().saveOrUpdate(curd);
	}

	
	//list coding
	@SuppressWarnings("unchecked")
	@Override
	public List<Concept_Understanding_CURD> getAllCurdList() {
		return (List<Concept_Understanding_CURD>) sessionFactory.getCurrentSession().createCriteria(Concept_Understanding_CURD.class).list();
	}


	@Override
	public Concept_Understanding_CURD getCurd(int id) {
		return (Concept_Understanding_CURD) sessionFactory.getCurrentSession().get(Concept_Understanding_CURD.class, id);
	}


	@Override
	public void deleteCurd(Concept_Understanding_CURD curd) {
		sessionFactory.getCurrentSession().createQuery("DELETE FROM Concept_Understanding_CURD WHERE id = "+curd.getId()).executeUpdate();
	}
}
