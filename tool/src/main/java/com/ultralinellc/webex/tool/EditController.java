package com.ultralinellc.webex.tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ultralinellc.webex.model.ConferenceForm;
import com.ultralinellc.webex.model.ConferenceManager;
import lombok.Getter;
import lombok.Setter;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.ultralinellc.webex.model.Conference;

public class EditController extends AddController {

	/**
	 * Webex Tool View Conference Controller
	 * 
	 * @author Peter Mains (peter.mains@gmail.com)
	 * 
	 */
    public EditController() {
        setCommandClass(ConferenceForm.class);
        setCommandName("conferenceForm");
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) {
        String id = request.getParameter("id");
        Conference conference = conferenceManager.getConferenceById(id);
        ConferenceForm conferenceForm = new ConferenceForm(conference);
        return conferenceForm;
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

            Conference conference = new Conference(cf);

            conferenceManager.updateConference(conference);
        } catch(ParseException pe) {
            pe.printStackTrace();
        }

        return new ModelAndView("redirect:view.htm?id=" + cf.getId());
    }
}
