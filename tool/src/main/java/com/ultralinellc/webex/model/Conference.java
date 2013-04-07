package com.ultralinellc.webex.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
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
public class Conference {
	private String id;
	private String siteId;
	private String confName;
	private String meetingType;
	private String agenda;
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
