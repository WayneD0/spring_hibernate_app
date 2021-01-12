package com.tariq.practice.personalDetails.personalInfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tariq.practice.personalDetails.personalInfo.bean.Concept_Understanding_CURD_Bean;
import com.tariq.practice.personalDetails.personalInfo.dao.Concept_Understanding_CURD_Controller_DAO;
import com.tariq.practice.personalDetails.personalInfo.model.Concept_Understanding_CURD;

@Service("curdService")
public class Concept_Understanding_CURD_ServiceImpl implements Concept_Understanding_CURD_Service{
	
	@Autowired
	private Concept_Understanding_CURD_Controller_DAO curdDao;
	
	// 1st  prac
	
	
	//void method of service called in controller with help of private method of controller
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addEmployee(Concept_Understanding_CURD curd) {
		/*Concept_Understanding_CURD_Bean bean = new Concept_Understanding_CURD_Bean();
		Concept_Understanding_CURD curd1 = perModel(bean);
		curdDao.addEmployee(curd1);*/
		
		curdDao.addEmployee(curd);
		
	}
	
	
	// service  private method for save
	private Concept_Understanding_CURD perModel(Concept_Understanding_CURD_Bean bean){
		Concept_Understanding_CURD saveCurd = new Concept_Understanding_CURD();
		saveCurd.setId(bean.getId());
		saveCurd.setName(bean.getName());
		saveCurd.setAge(bean.getAge());
		//curdDao.addEmployee(saveCurd);
		return saveCurd;
	}

	//Instance save method which is called in controller for save 
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Concept_Understanding_CURD saveCurdWithService(Concept_Understanding_CURD_Bean bean) {
		 Concept_Understanding_CURD saveCurd = new Concept_Understanding_CURD();
		 saveCurd = perModel(bean);
		 curdDao.addEmployee(saveCurd);
		return saveCurd;
	}

	
	
	////// here starts the second prac for save
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@Override
	public void addEmployeeSecondPrac(Concept_Understanding_CURD curd) {
		curdDao.addEmployee(curd);
	}

	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@Override
	public Concept_Understanding_CURD saveWithSecondPrac(Concept_Understanding_CURD_Bean bean) {
		Concept_Understanding_CURD save = new Concept_Understanding_CURD();
		save = saveSecondPracFromServiceSave(bean);
		curdDao.addEmployee(save);
		return save;
	}
	
	private Concept_Understanding_CURD saveSecondPracFromServiceSave(Concept_Understanding_CURD_Bean bean){
		Concept_Understanding_CURD saveCrd = new Concept_Understanding_CURD();
		saveCrd.setId(bean.getId());
		saveCrd.setAge(bean.getAge());
		saveCrd.setName(bean.getName());
		return saveCrd;
	}

	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@Override
	public String save(Concept_Understanding_CURD_Bean bean) {
		Concept_Understanding_CURD strCurd = new Concept_Understanding_CURD();
		strCurd = saveSecondPracFromServiceSave(bean);
		
		
		strCurd.setId(bean.getId());
		strCurd.setAge(bean.getAge());
		strCurd.setName(bean.getName());
		curdDao.addEmployee(strCurd);
		
		return new String("saved");
	}

	
	//list coding 
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@Override
	public List<Concept_Understanding_CURD> getAllCurdList() {
		List<Concept_Understanding_CURD> list = curdDao.getAllCurdList();
		return list;
	}

	
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@Override
	public Concept_Understanding_CURD getCurd(int id) {                 // 2nd type edit 
		Concept_Understanding_CURD getCurdId = curdDao.getCurd(id);
		return getCurdId;
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@Override
	public Concept_Understanding_CURD getCurd1(int id) {                 // 3rd type edit 
		Concept_Understanding_CURD_Bean bean = new Concept_Understanding_CURD_Bean();
		Concept_Understanding_CURD getCurdId = new Concept_Understanding_CURD();
		bean.setId(getCurdId.getId());
		bean.setAge(getCurdId.getAge());
		bean.setName(getCurdId.getName());
		
		/*Concept_Understanding_CURD getCurdId = curdDao.getCurd(id);*/
		return curdDao.getCurd(id);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@Override
	public Concept_Understanding_CURD_Bean getCurdIdByBeanInstance(int id) { // 4th type edit
		Concept_Understanding_CURD_Bean bean = getCurdBean(curdDao.getCurd(id));
		return bean;
	} 
	
	private Concept_Understanding_CURD_Bean getCurdBean(Concept_Understanding_CURD curd){
		Concept_Understanding_CURD_Bean bean = new Concept_Understanding_CURD_Bean();
		bean.setId(curd.getId());
		bean.setAge(curd.getAge());
		bean.setName(curd.getName());
		return bean;
	}

	
	/************************ Delete starts *****************************/
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@Override
	public void deleteCurd(Concept_Understanding_CURD curd) {
		curdDao.deleteCurd(curd);
		
	}

}
