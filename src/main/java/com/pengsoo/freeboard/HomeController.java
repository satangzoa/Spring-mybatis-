package com.pengsoo.freeboard;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pengsoo.freeboard.dao.ContentDao;
import com.pengsoo.freeboard.dto.ContentDto;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	ContentDao dao;
	
	@Autowired
	public void setDao(ContentDao dao) {
	this.dao=dao;
	}
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	

	@RequestMapping(value = "list")
	public String list(Model model) {
		ArrayList<ContentDto> dtos = dao.listDao();
		model.addAttribute("list",dtos);
		return "list";
		}
	
	@RequestMapping(value = "writeForm")
	public String writeForm() {
		return "writeForm";
		}
	
	@RequestMapping(value = "write")
	public String write(HttpServletRequest request, Model model) {
		dao.writeDao(request.getParameter("mWriter"),request.getParameter("mContent"));
		return "redirect:list";
		}
	@RequestMapping(value = "view")
	public String view() {
		return "view";
		}
	
	@RequestMapping(value = "delete")
	public String delete(HttpServletRequest request, Model model) {
		dao.deleteDao(request.getParameter("mid"));
		return "redirect:list";
		}
	
}
