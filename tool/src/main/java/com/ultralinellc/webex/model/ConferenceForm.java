package com.ultralinellc.webex.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;

/**
 * Webex Conference
 *
 * @author Peter Mains (peter.mains@gmail.com)
 *
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConferenceForm extends Conference {
    public ConferenceForm(Conference conference) {
        try {
            BeanUtils.copyProperties(this, conference);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        GregorianCalendar gcal = new GregorianCalendar();
        gcal.setTime(conference.getStartDate());
        this.setMonth(gcal.get(Calendar.MONTH));
        this.setDate(gcal.get(Calendar.DATE));
        this.setYear(gcal.get(Calendar.YEAR));
        this.setHour(gcal.get(Calendar.HOUR));
        this.setMinute(gcal.get(Calendar.MINUTE));
        if(gcal.get(Calendar.AM_PM) == 1)
            this.setAmpm("PM");
        else
            this.setAmpm("AM");
    }

    private Integer month;
    private Integer date;
    private Integer year;
    private Integer hour;
    private Integer minute;
    private String ampm;
}
