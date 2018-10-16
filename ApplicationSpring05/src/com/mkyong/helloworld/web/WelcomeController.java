package com.mkyong.helloworld.web;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mkyong.customer.model.customerForm;
import com.mkyong.helloworld.bean.myIndex;
import com.mkyong.helloworld.service.HelloWorldService;
import com.octo.captcha.service.CaptchaServiceException;

@Controller
public class WelcomeController {

	private final Logger logger = LoggerFactory.getLogger(WelcomeController.class);
	private final HelloWorldService helloWorldService;

	@Autowired
	public WelcomeController(HelloWorldService helloWorldService) {
		this.helloWorldService = helloWorldService;
	}
	


	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Map<String, Object> model) {

		logger.debug("index() is executed!");

		model.put("title", helloWorldService.getTitle(""));
		model.put("msg", helloWorldService.getDesc());
		
		return "captcha";
	}

	
	@RequestMapping(value = "/hello/{name:.+}", method = RequestMethod.GET)
	public ModelAndView hello(@PathVariable("name") String name) {

		logger.debug("hello() is executed - $name {}", name);
		
		
		

		ModelAndView model =new ModelAndView("index") ;
		//ModelAndView model = new ModelAndView("index", "customerForm", new customerForm()) ;
 
		//ModelAndView model = new ModelAndView("index", "customerForm", new customerForm());
		
		model.addObject("title", helloWorldService.getTitle(name));
		model.addObject("msg", helloWorldService.getDesc());
		myIndex mi = new myIndex() ;
		mi.setName("ongart");
		mi.setSurename("woongkeov");
		mi.setAddress("1233");
		model.addObject("person", mi) ;
		
		
		Map<String,String> country = new LinkedHashMap<String,String>();
		country.put("US", "United Stated");
		country.put("CHINA", "China");
		country.put("SG", "Singapore");
		country.put("MY", "Malaysia");
		model.addObject("countryList", country);
		
		return model;

	}
	
	@RequestMapping(value = "/captcha/{name:.+}", method = RequestMethod.GET)
	public ModelAndView captcha(@PathVariable("name") String name , HttpServletRequest req , HttpServletResponse res) {

		logger.debug("hello() is executed - $name {}", name);
		
		
		

		ModelAndView model =new ModelAndView("index") ;
		//ModelAndView model = new ModelAndView("index", "customerForm", new customerForm()) ;
 
		//ModelAndView model = new ModelAndView("index", "customerForm", new customerForm());
		
		model.addObject("title", helloWorldService.getTitle(name));
		model.addObject("msg", helloWorldService.getDesc());
		myIndex mi = new myIndex() ;
		mi.setName("ongart");
		mi.setSurename("woongkeov");
		mi.setAddress("1233");
		model.addObject("person", mi) ;
		
		
		Map<String,String> country = new LinkedHashMap<String,String>();
		country.put("US", "United Stated");
		country.put("CHINA", "China");
		country.put("SG", "Singapore");
		country.put("MY", "Malaysia");
		model.addObject("countryList", country);
		
		Boolean isResponseCorrect =Boolean.FALSE;
        //remenber that we need an id to validate!
        String captchaId = req.getSession().getId();
        //retrieve the response
        String response = req.getParameter("j_captcha_response");
        // Call the Service method
         try {
             isResponseCorrect = CaptchaServiceSingleton.getInstance().validateResponseForID(captchaId,response);
         } catch (CaptchaServiceException e) {
              //should not happen, may be thrown if the id is not valid
         }

         System.out.println("j_captcha_response :"+ isResponseCorrect);
		
		return model;

	}
	
	
}