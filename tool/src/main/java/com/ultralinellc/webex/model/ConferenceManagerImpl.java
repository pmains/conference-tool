package com.ultralinellc.webex.model;

import java.sql.SQLException;
import java.util.*;

import com.ultralinellc.webex.logic.SakaiProxy;
import com.webex.schemas.x2002.x06.common.TimeZoneType;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.HibernateException;

import org.sakaiproject.user.api.User;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import wjlib.WebexClient;

public class ConferenceManagerImpl extends HibernateDaoSupport implements ConferenceManager
{
  @Getter @Setter
  HibernateTransactionManager transactionManager;
  @Getter @Setter
  SakaiProxy sakaiProxy;
  
  public String createConference(final String sakaiSiteId, final String confName, final String meetingType,
    final String agenda, final String sakaiHostUserId, final String hostUserId, final Integer maxUserNumber,
    final Set<String> attendeeIds,final Boolean chatEnabled, final Boolean pollEnabled,
    final Boolean audioVideoEnabled, final Date startDate, final Integer duration,
    final Integer timeZoneID, final String telephonySupport, final String extTelephonyDescription) {

    final String webexSiteName     = sakaiProxy.getConfigurationProperty("webex.siteName");
    final String webexSiteId       = sakaiProxy.getConfigurationProperty("webex.siteId");
    final String webexPartnerId    = sakaiProxy.getConfigurationProperty("webex.partnerId");
    final String webexSiteUsername = sakaiProxy.getConfigurationProperty("webex.siteUsername");
    final String webexSitePassword = sakaiProxy.getConfigurationProperty("webex.sitePassword");

    /*WebexClient client = new WebexClient(
      webexSiteName,
      new Long(webexSiteId),
      webexPartnerId,
      webexSiteUsername,
      webexSitePassword);*/

    User host = sakaiProxy.getUser(sakaiHostUserId);

    HibernateCallback hc = new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException {
            Conference conf =
                new Conference(null, sakaiSiteId, confName, meetingType, agenda, hostUserId, hostUserId, maxUserNumber,
                attendeeIds, chatEnabled, pollEnabled, audioVideoEnabled, startDate, duration, timeZoneID,
                telephonySupport, extTelephonyDescription);

            // Save the new assignment
            String id = (String)session.save(conf);

            return id;
        }
    };
    return (String)getHibernateTemplate().execute(hc);
  }

  public String createConference(Conference conf) {
    return createConference(conf.getSakaiSiteId(), conf.getConfName(), conf.getMeetingType(),
        conf.getAgenda(), conf.getHostSakaiUserId(), conf.getHostUserId(), conf.getMaxUserNumber(),
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

  public List<Conference> getConferencesBySiteId(final String sakaiSiteId) {
      return (List<Conference>)getHibernateTemplate().execute(new HibernateCallback() {
          public Object doInHibernate(Session session) throws HibernateException {
              Query q = session.createQuery("from Conference as conf where conf.sakaiSiteId=:sakaiSiteId");
              q.setString("sakaiSiteId", sakaiSiteId);
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
