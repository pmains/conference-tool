package com.ultralinellc.webex.model;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.HibernateException;

import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public interface ConferenceManager
{
  public HibernateTransactionManager getTransactionManager();
  
  public void setTransactionManager(HibernateTransactionManager transactionManager);
  
  public Long createConference(final String siteId, final String confName, final String meetingType,
    final String agenda, final String hostUserId, final Integer maxUserNumber,
    final Set<String> attendeeIds,final Boolean chatEnabled, final Boolean pollEnabled,
    final Boolean audioVideoEnabled, final Date startDate, final Integer duration,
    final Integer timeZoneID, final String telephonySupport, final String extTelephonyDescription);
  
  public Conference getConferenceById(final String id);
  
  public Set<Conference> getConferenceBySite(final String siteId);

  public void saveConference(Conference conference);
  
  public void addAttendeeToConference(final Conference conference, final String attendeeUserId);
  
  public void removeAttendeeFromConference(final Conference conference, final String attendeeUserId);
}
