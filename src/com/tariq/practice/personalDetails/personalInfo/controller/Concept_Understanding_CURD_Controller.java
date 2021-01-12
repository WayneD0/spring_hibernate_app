package com.tariq.practice.personalDetails.personalInfo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tariq.practice.personalDetails.personalInfo.bean.Concept_Understanding_CURD_Bean;
import com.tariq.practice.personalDetails.personalInfo.model.Concept_Understanding_CURD;
import com.tariq.practice.personalDetails.personalInfo.service.Concept_Understanding_CURD_Service;

@RequestMapping(value="/personalDetails/personalInfo")
@Controller
public class Concept_Understanding_CURD_Controller {
	static Logger logger = Logger.getLogger(Concept_Understanding_CURD_Controller.class);
	@Autowired
	private Concept_Understanding_CURD_Service serviceDao;
	
	
	@RequestMapping(value = "/addConcept_Understanding_CURD", method = RequestMethod.GET)
	public ModelAndView addEmployee(@ModelAttribute("command")  Concept_Understanding_CURD_Bean conceptBean,BindingResult result) {
		logger.info("Enter Concept_Understanding_CURD_Controller addEmployee");
		System.out.println("inside add concept understanding curd");
		Map<String, Object> model = new HashMap<String, Object>();
		System.out.println("inside add concept understanding curd...12");
		model.put("curdList",  listOfAllExistingCurd(serviceDao.getAllCurdList()));
		System.out.println("inside add concept understanding curd...23");
		logger.info("Enter Concept_Understanding_CURD_Controller addEmployee");
		return new ModelAndView("/personalDetails/personalInfo/addConcept_Understanding_CURD", model);
	}

	
	
	// 1st time prac
	/****** save controller with Void and Instance Method **********************/
	@RequestMapping(value = "/saveCURD", method = RequestMethod.POST)
	public ModelAndView saveEmployee(@ModelAttribute("command") Concept_Understanding_CURD_Bean conceptBean, BindingResult result) {
		System.out.println("inside saveCURD controller of Concept_Understanding_CURD ");
			 
		/*********This is with instance method************/

		serviceDao.saveCurdWithService(conceptBean);
		 
		
		  /*************this is with void method *************/
		/*Concept_Understanding_CURD curd1 = perModel(conceptBean);
		 serviceDao.addEmployee(curd1);	*/
		
		return new ModelAndView("redirect:/personalDetails/personalInfo/addConcept_Understanding_CURD.html");
	}
	
	
	// this method is used to save the mthd with refrence to viod method of service class 
		private Concept_Understanding_CURD perModel(Concept_Understanding_CURD_Bean bean){
			Concept_Understanding_CURD saveCurd = new Concept_Understanding_CURD();
			saveCurd.setId(bean.getId());
			saveCurd.setName(bean.getName());
			saveCurd.setAge(bean.getAge());
			return saveCurd;
		}
	
	
	
	
	
	// 2nd time prac of save method 
	
	@RequestMapping(value = "/saveCURDSecondPrac", method = RequestMethod.POST)
	public ModelAndView saveEmployeeSecondPrac(@ModelAttribute("command") Concept_Understanding_CURD_Bean conceptBean, BindingResult result) {
		System.out.println("inside saveCURD controller of Concept_Understanding_CURD SecondPrac 1 ....");
			 
		/*Concept_Understanding_CURD curdSaving = saveSecondPrac(conceptBean);
		serviceDao.addEmployeeSecondPrac(curdSaving);*/
			
		serviceDao.saveWithSecondPrac(conceptBean);
		return new ModelAndView("redirect:/personalDetails/personalInfo/addConcept_Understanding_CURD.html");
	}
	
	
	private Concept_Understanding_CURD saveSecondPrac(Concept_Understanding_CURD_Bean bean){
		Concept_Understanding_CURD saveCurd = new Concept_Understanding_CURD();
		saveCurd.setId(bean.getId());
		saveCurd.setAge(bean.getAge());
		saveCurd.setName(bean.getName());
		return saveCurd;
	}
	
	
	//3rd practice of save
	
	@RequestMapping(value = "/saveWithStringMethodThirdPrac", method = RequestMethod.POST)
	public String  saveWithStringMethod(@ModelAttribute("command") Concept_Understanding_CURD_Bean conceptBean, BindingResult result) {
		System.out.println("inside saveCURD controller of Concept_Understanding_CURD SecondPrac String Method 2 ....");
		Concept_Understanding_CURD save =  perModel(conceptBean);
		serviceDao.addEmployee(save);// 2nd type
		
		//serviceDao.save(conceptBean);// 1st type
		return "redirect:/personalDetails/personalInfo/addConcept_Understanding_CURD.html";
	}
	
	
	/********************************** list Method Starts ***********************************************/
	
	
	// list code  & edit code
	
	// this method is to get a list of curd
		private List<Concept_Understanding_CURD_Bean>  listOfAllExistingCurd(List<Concept_Understanding_CURD> curd){
			List<Concept_Understanding_CURD_Bean> beans = null;
			if(curd != null && !curd.isEmpty()){
				beans = new ArrayList<Concept_Understanding_CURD_Bean>();
				Concept_Understanding_CURD_Bean curdBeanObj = null;
				for(Concept_Understanding_CURD curdObj : curd){
					curdBeanObj = new Concept_Understanding_CURD_Bean();
					curdBeanObj.setId(curdObj.getId());
					curdBeanObj.setAge(curdObj.getAge());
					curdBeanObj.setName(curdObj.getName());
					beans.add(curdBeanObj);
				}
			}
			return beans;
		}
	
	
		/********************************** list Method Ends ***********************************************/
		
		/********************************** Edit Controller &  Method Starts ***********************************************/
	@RequestMapping(value = "/editCurd", method = RequestMethod.GET)
	public ModelAndView editCurd(@ModelAttribute("command")  Concept_Understanding_CURD_Bean conceptBean,
			BindingResult result) {
		System.out.println("inside eidt controller");
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("curdList",listOfAllExistingCurd(serviceDao.getAllCurdList()));
		//model.put("curd", getCurdForEdit(serviceDao.getCurd(conceptBean.getId()))); // 1st type
		
		//model.put("curd", serviceDao.getCurd(conceptBean.getId())); // 2nd type
		
		//model.put("curd", serviceDao.getCurd1(conceptBean.getId())); // 3rd type
		
		model.put("curd", serviceDao.getCurdIdByBeanInstance(conceptBean.getId())); // 4th type

		return new ModelAndView("/personalDetails/personalInfo/addConcept_Understanding_CURD", model);
	}
		
	private Concept_Understanding_CURD_Bean getCurdForEdit(Concept_Understanding_CURD curd){
		Concept_Understanding_CURD_Bean bean = new Concept_Understanding_CURD_Bean();
		bean.setId(curd.getId());
		bean.setAge(curd.getAge());
		bean.setName(curd.getName());
		return bean;
	}

	/********************************** Edit  Ends ***********************************************/
	
	/********************************** Delete Controller Starts ***********************************************/
	
	@RequestMapping(value = "/deleteCurd", method = RequestMethod.GET)
	public ModelAndView deleteCurd(@ModelAttribute("command")  Concept_Understanding_CURD_Bean conceptBean,BindingResult result) {
		System.out.println("inside delete curd controller");
		
		serviceDao.deleteCurd(perModel(conceptBean));
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("curd", null);
		model.put("curdList",  listOfAllExistingCurd(serviceDao.getAllCurdList()));
		return new ModelAndView("/personalDetails/personalInfo/addConcept_Understanding_CURD", model);
	}
	
	/********************************** Delete Ends ***********************************************/
}
