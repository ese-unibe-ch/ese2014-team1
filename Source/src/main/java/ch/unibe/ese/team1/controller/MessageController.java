package ch.unibe.ese.team1.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ch.unibe.ese.team1.controller.pojos.forms.MessageForm;
import ch.unibe.ese.team1.controller.service.MessageService;
import ch.unibe.ese.team1.controller.service.UserService;
import ch.unibe.ese.team1.model.Message;
import ch.unibe.ese.team1.model.User;

@Controller
public class MessageController {

	@Autowired
	private MessageService messageService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/profile/messages", method = RequestMethod.GET)
	public ModelAndView messages(Principal principal) {
		ModelAndView model = new ModelAndView("messages");
		User user = userService.findUserByUsername(principal.getName());
		model.addObject("messageForm", new MessageForm());
		model.addObject("messages", messageService.getInboxForUser(user));
		return model;
	}

	@RequestMapping(value = "/profile/message/inbox", method = RequestMethod.POST)
	public @ResponseBody Iterable<Message> getInbox(Principal principal) {
		User user = userService.findUserByUsername(principal.getName());
		return messageService.getInboxForUser(user);
	}

	@RequestMapping(value = "/profile/message/sent", method = RequestMethod.POST)
	public @ResponseBody Iterable<Message> getSent(Principal principal) {
		User user = userService.findUserByUsername(principal.getName());
		return messageService.getSentForUser(user);
	}

	@RequestMapping(value = "/profile/messages/getMessage", method = RequestMethod.GET)
	public @ResponseBody Message getMessage(@RequestParam Long id) {
		return messageService.getMessage(id);
	}
}
