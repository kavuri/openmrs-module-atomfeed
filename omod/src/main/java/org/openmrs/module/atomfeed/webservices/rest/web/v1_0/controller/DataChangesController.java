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
package org.openmrs.module.atomfeed.webservices.rest.web.v1_0.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openmrs.module.atomfeed.webservices.rest.web.v1_0.resource.DataChangesResource;
import org.openmrs.module.webservices.rest.SimpleObject;
import org.openmrs.module.webservices.rest.web.annotation.WSDoc;
import org.openmrs.module.webservices.rest.web.response.ResponseException;
import org.openmrs.module.webservices.rest.web.v1_0.controller.BaseCrudController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.RestUtil;

@Controller
@RequestMapping(value = "/rest/" + RestConstants.VERSION_1 + "/changes")
public class DataChangesController extends BaseCrudController<DataChangesResource> {
    @RequestMapping(method=RequestMethod.GET, params="Date")
    @WSDoc("Fetch all the changes in the system since the date")
    @ResponseBody
    public SimpleObject searchChangesSince(@RequestParam("date") Date changesSince,
        HttpServletRequest request, HttpServletResponse response) throws ResponseException {
        RequestContext context = RestUtil.getRequestContext(request);
        return getResource().getChangesSince(changesSince, context);
    }
}
