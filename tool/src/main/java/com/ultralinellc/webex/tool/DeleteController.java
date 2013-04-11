package com.ultralinellc.webex.tool;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.Getter;
import lombok.Setter;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.ultralinellc.webex.model.Conference;
import com.ultralinellc.webex.model.ConferenceManager;

public class DeleteController implements Controller {

	/**
	 * Webex Tool Delete Conference Controller
	 * 
	 * @author Peter Mains (peter.mains@gmail.com)
	 * 
	 */
    @Getter @Setter
    ConferenceManager conferenceManager;

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
        String id = request.getParameter("id");
        Conference conference = conferenceManager.getConferenceById(id);
        conferenceManager.deleteConference(conference);
		
		return new ModelAndView("redirect:index.htm");
	}
}
