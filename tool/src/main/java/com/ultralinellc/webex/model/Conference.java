package com.ultralinellc.webex.model;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.apache.commons.beanutils.BeanUtils;

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
public class Conference {
    public Conference(ConferenceForm conferenceForm) {
        try {
            BeanUtils.copyProperties(this, conferenceForm);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

	private String id;
	private String sakaiSiteId;
	private String confName;
	private String meetingType;
	private String agenda;
    private String hostSakaiUserId;
	private String hostUserId;
	private Integer maxUserNumber;
	private Set<String> attendeeIds;
    private Boolean chatEnabled;
    private Boolean pollEnabled;
    private Boolean audioVideoEnabled;
	private Date startDate;
	private Integer duration;
	private Integer timeZoneID;
	private String telephonySupport;
	private String extTelephonyDescription;
}
