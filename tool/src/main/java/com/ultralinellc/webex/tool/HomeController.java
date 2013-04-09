package com.ultralinellc.webex.tool;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		
		Map<String, Object> model = new HashMap<String,Object>();
        List<Conference> conferences = conferenceManager.getConferencesBySiteId("a2679c1a-a7a6-498b-9395-28151de39292");
//		ArrayList<Conference> conferences = new ArrayList<Conference>();
//        Conference conference = new Conference();

/*        conference.setHostUserId("admin");
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

        conferences.add(conference);*/


		model.put("conferences", conferences);
		
		return new ModelAndView("home", model);
	}
}
