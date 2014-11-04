package ch.unibe.ese.team1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ch.unibe.ese.team1.model.dao.MessageDao;

@Controller
public class MessageController {
	
	// TODO remove
	@Autowired
	private MessageDao messageDao;

	@RequestMapping(value="/profile/messages")
	public ModelAndView messages(){
		ModelAndView model = new ModelAndView("messages");
		model.addObject("messages", messageDao.findAll());
		return model;
	}
}
