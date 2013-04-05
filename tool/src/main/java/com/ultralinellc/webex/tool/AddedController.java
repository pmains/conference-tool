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

import lombok.Getter;
import lombok.Setter;

import com.ultralinellc.webex.model.Conference;
import com.ultralinellc.webex.model.ConferenceManager;

public class AddedController implements Controller {
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
		
		Map<String, Object> modelMap = new HashMap<String,Object>();
        Conference conf = new Conference();

        conf.setHostUserId("admin");
        conf.setSiteId("a2679c1a-a7a6-498b-9395-28151de39292");
        conf.setConfName("Class Orientation");
        conf.setMeetingType("UNSURE");
        conf.setAgenda("Get to know your professor and fellow students.");
        conf.setMaxUserNumber(new Integer(4));
        conf.setChatEnabled(true);
        conf.setPollEnabled(false);
        conf.setAudioVideoEnabled(true);
        conf.setStartDate(new Date());
        conf.setDuration(new Integer(90));
        conf.setTimeZoneID(new Integer(4));
        conf.setTelephonySupport("INCALL");
        conf.setExtTelephonyDescription("Call in at 1-800-WEB-CALL");

        HashSet<String> attendeeIds = new HashSet<String>();
        conf.setAttendeeIds(attendeeIds);

        conferenceManager.createConference(conf.getSiteId(), conf.getConfName(), conf.getMeetingType(),
            conf.getAgenda(), conf.getHostUserId(), conf.getMaxUserNumber(),
            null , conf.getChatEnabled(), conf.getPollEnabled(),
            conf.getAudioVideoEnabled(), conf.getStartDate(), conf.getDuration(),
            conf.getTimeZoneID(), conf.getTelephonySupport(), conf.getExtTelephonyDescription());


		modelMap.put("conference", conf);
		
		return new ModelAndView("viewConference", modelMap);
	}
}
