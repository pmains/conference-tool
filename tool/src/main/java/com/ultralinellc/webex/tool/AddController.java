package com.ultralinellc.webex.tool;

import java.text.SimpleDateFormat;
import java.text.ParseException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webex.schemas.x2002.x06.common.TimeZoneType;
import com.webex.schemas.x2002.x06.common.MeetingTypeType;
import lombok.Getter;
import lombok.Setter;

import org.sakaiproject.authz.api.Member;
import org.sakaiproject.user.api.User;
import org.sakaiproject.site.api.SiteService;
import org.sakaiproject.user.api.UserDirectoryService;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.ultralinellc.webex.model.ConferenceForm;
import com.ultralinellc.webex.model.ConferenceManager;
import com.ultralinellc.webex.logic.SakaiProxy;
import wjlib.WebexClient;

public class AddController extends SimpleFormController {

	/**
	 * Webex Tool View Conference Controller
	 * 
	 * @author Peter Mains (peter.mains@gmail.com)
	 * 
	 */
    @Getter @Setter
    ConferenceManager conferenceManager;
    @Getter @Setter
    SakaiProxy sakaiProxy;
    @Getter @Setter
    SiteService siteService;
    @Getter @Setter
    UserDirectoryService userDirectoryService;

    public AddController(){
        setCommandClass(ConferenceForm.class);
        setCommandName("conferenceForm");
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response,
        Object command, BindException errors) throws Exception {
        ConferenceForm cf = null;
        try {
            cf = (ConferenceForm)command;
            SimpleDateFormat sdf = new SimpleDateFormat("M-d-y h:m a");

            Date startDate = sdf.parse(cf.getMonth() + "-" + cf.getDate() + "-" + cf.getYear() + " "
                + cf.getHour() + ":" + cf.getMinute() + " " + cf.getAmpm());
            cf.setStartDate(startDate);

            conferenceManager.createConference(cf);
        } catch(ParseException pe) {
            pe.printStackTrace();
        }
        
        return new ModelAndView("addSuccess", "conference", cf);
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) {
        ConferenceForm conferenceForm = new ConferenceForm();
        conferenceForm.setHostSakaiUserId(sakaiProxy.getCurrentUserId());
        conferenceForm.setSakaiSiteId(sakaiProxy.getCurrentSiteId());
        return conferenceForm;
    }

    @Override
    protected Map referenceData(HttpServletRequest request) throws Exception {
        Map referenceData = new HashMap();

        List<User> siteUsers = new ArrayList<User>();
        String siteId = sakaiProxy.getCurrentSiteId();
        for(Member member : siteService.getSite(siteId).getMembers()) {
            User user = userDirectoryService.getUser(member.getUserId());
            siteUsers.add(user);
        }
        referenceData.put("siteUsers", siteUsers);
        
        List<Integer> months = new ArrayList<Integer>();
        for(int i=0; i<12; i++) {
            months.add(new Integer(i+1));
        }
        referenceData.put("months", months);
        referenceData.put("hours", months);
        
        List<Integer> dates = new ArrayList<Integer>();
        for(int i=0; i<31; i++) {
            dates.add(new Integer(i+1));
        }
        referenceData.put("dates", dates);

        List<Integer> years = new ArrayList<Integer>();
        int currentYear = (new GregorianCalendar()).get(Calendar.YEAR);
        for(int i=0; i<12; i++) {
            years.add(new Integer(currentYear + i));
        }
        referenceData.put("years", years);

        List<Integer> minutes = new ArrayList<Integer>();
        for(int i=0; i<60; i++) {
            minutes.add(new Integer(i+1));
        }
        referenceData.put("minutes", minutes);

        referenceData.put("meetingTypes", MeetingTypeType.Enum.table);
        referenceData.put("timeZones", TimeZoneType.Enum.table);

        return referenceData;
    }
}
