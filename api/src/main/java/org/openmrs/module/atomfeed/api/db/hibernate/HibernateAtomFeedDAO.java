/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.atomfeed.api.db.hibernate;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import org.openmrs.OpenmrsObject;
import org.openmrs.module.atomfeed.api.db.AtomFeedDAO;
import org.openmrs.module.atomfeed.DataPoint;

/**
 * It is a default implementation of  {@link AtomFeedDAO}.
 */
public class HibernateAtomFeedDAO implements AtomFeedDAO {
	protected final Log log = LogFactory.getLog(this.getClass());
	
	private SessionFactory sessionFactory;
	
	/**
     * @param sessionFactory the sessionFactory to set
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
	    this.sessionFactory = sessionFactory;
    }
    
	/**
     * @return the sessionFactory
     */
    public SessionFactory getSessionFactory() {
	    return sessionFactory;
    }

	@Override
	public OpenmrsObject getObjectByUuid(String classname, String uuid) {
		return (OpenmrsObject)sessionFactory.getCurrentSession().createCriteria(classname).add(Expression.eq("uuid", uuid)).uniqueResult();
	}

    /* (non-Javadoc)
     * @see org.openmrs.module.changetracker.api.db.AtomFeedDAO#getDataPoints(java.util.Date)
     */
    @SuppressWarnings("unchecked")
    public List<DataPoint> getDataPoints(Date changesSince) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(DataPoint.class);
		if (changesSince != null) {
		    criteria.add(Restrictions.ge("date", changesSince));
		    return criteria.list();
		} else
		    return criteria.list();
    }

    /* (non-Javadoc)
     * @see org.openmrs.module.changetracker.api.db.ChangeTrackerDAO#save(java.lang.Object)
     */
    public <T> T save(T object) {
        Session session = sessionFactory.getCurrentSession();
        
		session.save(object);
		return object;
    }

    /* (non-Javadoc)
     * @see org.openmrs.module.changetracker.api.db.ChangeTrackerDAO#delete(java.lang.Object)
     */
    public void delete(Object object) {
		sessionFactory.getCurrentSession().delete(object);
    }

    /* (non-Javadoc)
     * @see org.openmrs.module.changetracker.api.db.ChangeTrackerDAO#deleteDataPoints(java.util.Date)
     */
    public void deleteDataPoints(Date since) {
        DataPoint dataPoint = (DataPoint)sessionFactory.getCurrentSession().createCriteria(DataPoint.class).add(Restrictions.le("date", since));
        Session session = sessionFactory.getCurrentSession();
        session.delete(dataPoint);
    }

}
