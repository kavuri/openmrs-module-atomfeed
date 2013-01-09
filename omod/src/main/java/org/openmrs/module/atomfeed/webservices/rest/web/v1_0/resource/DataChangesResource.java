/**
 *===================================================================
 * Mobilitas Technologies CONFIDENTIAL
 * -----------------------------------
 *
 * Copyright 2012 Mobilitas Technologies Pvt. Ltd.
 * All Rights Reserved.
 *
 * Copyright: Sateesh Kavuri <sateesh@mobilitas.co>
 * 
 * 
 * NOTICE:  All information contained herein is, and remains
 * the property of Mobilitas Technologies Pvt. Ltd. and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Mobilitas Technologies Pvt. Ltd.
 * and its suppliers and may be covered by India, U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Mobilitas Technologies Pvt. Ltd.
 *=======================================================================
 */
package org.openmrs.module.atomfeed.webservices.rest.web.v1_0.resource;

import java.util.Date;

import org.openmrs.api.context.Context;
import org.openmrs.module.atomfeed.DataPoint;
import org.openmrs.module.atomfeed.api.AtomFeedService;
import org.openmrs.module.webservices.rest.SimpleObject;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.resource.impl.NeedsPaging;
import org.openmrs.module.webservices.rest.web.response.ResponseException;

public class DataChangesResource extends DelegatingCrudResource<DataPoint> {

    /* (non-Javadoc)
     * @see org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceHandler#getRepresentationDescription(org.openmrs.module.webservices.rest.web.representation.Representation)
     */
    @Override
    public DelegatingResourceDescription getRepresentationDescription(
            Representation rep) {
        DelegatingResourceDescription description = new DelegatingResourceDescription();
        description.addProperty("uuid");
        description.addProperty("action");
        description.addProperty("objectClass");
        description.addProperty("dateCreated");
        description.addSelfLink();
        description.addLink("full", ".?v=" + RestConstants.REPRESENTATION_FULL);

        return description;
    }

    /* (non-Javadoc)
     * @see org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceHandler#newDelegate()
     */
    @Override
    public DataPoint newDelegate() {
        return new DataPoint();
    }

    /* (non-Javadoc)
     * @see org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceHandler#save(java.lang.Object)
     */
    @Override
    public DataPoint save(DataPoint dp) {
        // We will not provide any save API to client apps
        return null;
    }

    /* (non-Javadoc)
     * @see org.openmrs.module.webservices.rest.web.resource.impl.BaseDelegatingResource#delete(java.lang.Object, java.lang.String, org.openmrs.module.webservices.rest.web.RequestContext)
     */
    @Override
    protected void delete(DataPoint arg0, String arg1, RequestContext arg2)
            throws ResponseException {
        // We will not provide delete API to the client applications
        // Another mechanism has to be devised to delete already sync'd changes
    }

    /* (non-Javadoc)
     * @see org.openmrs.module.webservices.rest.web.resource.impl.BaseDelegatingResource#getByUniqueId(java.lang.String)
     */
    @Override
    public DataPoint getByUniqueId(String uuid) {
        return (DataPoint)Context.getService(AtomFeedService.class).getObjectByUuid("DataPoint", uuid);
    }

    /* (non-Javadoc)
     * @see org.openmrs.module.webservices.rest.web.resource.impl.BaseDelegatingResource#purge(java.lang.Object, org.openmrs.module.webservices.rest.web.RequestContext)
     */
    @Override
    public void purge(DataPoint arg0, RequestContext arg1)
            throws ResponseException {
        // We will not provide any purge method to client apps
    }

    public SimpleObject getChangesSince(Date since, RequestContext context) throws ResponseException {
        if (since.compareTo(new Date()) > 0)
            throw new IllegalArgumentException("Invalid date value");
        AtomFeedService feedService = Context.getService(AtomFeedService.class);
        return new NeedsPaging<DataPoint>(feedService.getDataPoints(since), context).toSimpleObject();
    }
}
