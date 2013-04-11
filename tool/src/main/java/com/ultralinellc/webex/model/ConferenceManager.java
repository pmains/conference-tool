package com.ultralinellc.webex.model;

import java.util.List;
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
  
  public String createConference(final String siteId, final String confName, final String meetingType,
    final String agenda, final String hostUserId, final Integer maxUserNumber,
    final Set<String> attendeeIds,final Boolean chatEnabled, final Boolean pollEnabled,
    final Boolean audioVideoEnabled, final Date startDate, final Integer duration,
    final Integer timeZoneID, final String telephonySupport, final String extTelephonyDescription);

  public String createConference(Conference conference);
  
  public Conference getConferenceById(final String id);
  
  public List<Conference> getConferencesBySiteId(final String siteId);

  public void updateConference(Conference conference);

  public void deleteConference(final Conference conference);
  
  public void addAttendeeToConference(final Conference conference, final String attendeeUserId);
  
  public void removeAttendeeFromConference(final Conference conference, final String attendeeUserId);
}
