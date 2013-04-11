package com.ultralinellc.webex.model;

import java.sql.SQLException;
import java.util.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.HibernateException;

import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class ConferenceManagerImpl extends HibernateDaoSupport implements ConferenceManager
{
  HibernateTransactionManager transactionManager;
  public HibernateTransactionManager getTransactionManager() {
    return transactionManager;
  }
  public void setTransactionManager(HibernateTransactionManager transactionManager) {
    this.transactionManager = transactionManager;
  }
  
  public String createConference(final String siteId, final String confName, final String meetingType,
    final String agenda, final String hostUserId, final Integer maxUserNumber,
    final Set<String> attendeeIds,final Boolean chatEnabled, final Boolean pollEnabled,
    final Boolean audioVideoEnabled, final Date startDate, final Integer duration,
    final Integer timeZoneID, final String telephonySupport, final String extTelephonyDescription) {

    HibernateCallback hc = new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException {
            Conference conf =
                new Conference(null, siteId, confName, meetingType, agenda, hostUserId, maxUserNumber,
                attendeeIds, chatEnabled, pollEnabled, audioVideoEnabled, startDate, duration,
                timeZoneID, telephonySupport, extTelephonyDescription);

            // Save the new assignment
            String id = (String)session.save(conf);

            return id;
        }
    };
    return (String)getHibernateTemplate().execute(hc);
  }

  public String createConference(Conference conf) {
    return createConference(conf.getSiteId(), conf.getConfName(), conf.getMeetingType(),
        conf.getAgenda(), conf.getHostUserId(), conf.getMaxUserNumber(),
        conf.getAttendeeIds(), conf.getChatEnabled(), conf.getPollEnabled(),
        conf.getAudioVideoEnabled(), conf.getStartDate(), conf.getDuration(),
        conf.getTimeZoneID(), conf.getTelephonySupport(), conf.getExtTelephonyDescription());
  }

  public Conference getConferenceById(final String id) {
    return (Conference)getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException {
            Conference conf = (Conference)session.load(Conference.class, id);
            // Hibernate.initialize(gradebook.getGradeMappings());
            return conf;
        }
    });
  }

  public List<Conference> getConferencesBySiteId(final String siteId) {
      return (List<Conference>)getHibernateTemplate().execute(new HibernateCallback() {
          public Object doInHibernate(Session session) throws HibernateException {
              Query q = session.createQuery("from Conference as conf where conf.siteId=:siteId");
              q.setString("siteId", siteId);
              return q.list();
          }
      });
  }

  public void updateConference(final Conference conference) {
      getHibernateTemplate().execute(new HibernateCallback() {
          public Object doInHibernate(Session session) throws HibernateException, SQLException {
              session.saveOrUpdate(conference);
              return null;
          }
      });
  }

  public void deleteConference(final Conference conference) {
      getHibernateTemplate().execute(new HibernateCallback() {
          public Object doInHibernate(Session session) throws HibernateException, SQLException {
              session.delete(conference);
              return null;
          }
      });
  }
  
  public void addAttendeeToConference(final Conference conference, final String attendeeUserId) {
  }
  
  public void removeAttendeeFromConference(final Conference conference, final String attendeeUserId) {
  }
}
