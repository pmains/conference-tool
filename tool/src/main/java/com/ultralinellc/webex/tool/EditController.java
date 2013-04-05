package com.ultralinellc.webex.tool;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.ultralinellc.webex.model.Conference;

public class EditController implements Controller {

	/**
	 * Webex Tool View Conference Controller
	 * 
	 * @author Peter Mains (peter.mains@gmail.com)
	 * 
	 */
	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		
		Map<String, Object> modelMap = new HashMap<String,Object>();
        Conference conference = new Conference();

        conference.setHostUserId("admin");
        conference.setSiteId("a2679c1a-a7a6-498b-9395-28151de39292");
        conference.setConfName("Class Orientation");
        conference.setMeetingType("UNSURE");
        conference.setAgenda("Get to know your professor and fellow students.");
        conference.setMaxUserNumber(new Integer(4));
        conference.setChatEnabled(true);
        conference.setPollEnabled(false);
        conference.setAudioVideoEnabled(true);
        conference.setStartDate(new Date());
        conference.setDuration(new Integer(90));
        conference.setTimeZoneID(new Integer(4));
        conference.setTelephonySupport("INCALL");
        conference.setExtTelephonyDescription("Call in at 1-800-WEB-CALL");

        HashSet<String> attendeeIds = new HashSet<String>();
        conference.setAttendeeIds(attendeeIds);

		modelMap.put("conference", conference);
		
		return new ModelAndView("editConference", modelMap);
	}
}
