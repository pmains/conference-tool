package com.ultralinellc.webex.tool;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import lombok.Getter;
import lombok.Setter;

import com.ultralinellc.webex.model.Conference;
import com.ultralinellc.webex.model.ConferenceManager;

public class AddSuccessController implements Controller {
    @Getter @Setter
    ConferenceManager conferenceManager;

	/**
	 * Webex Tool View Conference Controller
	 * 
	 * @author Peter Mains (peter.mains@gmail.com)
	 * 
	 */
	public ModelAndView handleRequest(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
		
		Map<String, Object> modelMap = new HashMap<String,Object>();
        String id = request.getParameter("id");
        if(id != null) {
            Conference conf = conferenceManager.getConferenceById(id);
		    modelMap.put("conference", conf);
        }
		
		return new ModelAndView("viewConference", modelMap);
	}
}
