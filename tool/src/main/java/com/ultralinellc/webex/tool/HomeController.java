package com.ultralinellc.webex.tool;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ultralinellc.webex.logic.SakaiProxy;
import com.ultralinellc.webex.model.ConferenceManager;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.ultralinellc.webex.model.Conference;

public class HomeController implements Controller {

	/**
	 * Webex Tool Home Page Controller
	 * 
	 * @author Peter Mains (peter.mains@gmail.com)
	 * 
	 */
    @Getter @Setter
    ConferenceManager conferenceManager;
    @Getter @Setter
    SakaiProxy sakaiProxy;

	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		
		Map<String, Object> model = new HashMap<String,Object>();
        List<Conference> conferences = conferenceManager.getConferencesBySiteId(sakaiProxy.getCurrentSiteId());

		model.put("conferences", conferences);
		
		return new ModelAndView("home", model);
	}
}
