package com.ultralinellc.webex.tool;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ultralinellc.webex.model.ConferenceManager;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.ultralinellc.webex.model.Conference;

public class ViewController implements Controller {
    @Getter @Setter
    ConferenceManager conferenceManager;

	/**
	 * Webex Tool View Conference Controller
	 * 
	 * @author Peter Mains (peter.mains@gmail.com)
	 * 
	 */
	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
        String confId = arg0.getParameter("id");
		
		Map<String, Object> modelMap = new HashMap<String,Object>();
        Conference conference = conferenceManager.getConferenceById(confId);

		modelMap.put("conference", conference);
		
		return new ModelAndView("viewConference", modelMap);
	}
}
