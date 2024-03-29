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
package org.openmrs.module.atomfeed.api;

import java.util.Date;
import java.util.List;

import org.openmrs.OpenmrsObject;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.atomfeed.DataPoint;
import org.springframework.transaction.annotation.Transactional;

/**
 * Used to do custom db calls
 * 
 * @see org.openmrs.api.context.Context
 */
@Transactional
public interface AtomFeedService extends OpenmrsService {

	@Transactional(readOnly = true)
	public OpenmrsObject getObjectByUuid(String classname, String uuid);

    public DataPoint saveDataPoint(DataPoint dp);

    public List<DataPoint> getDataPoints(Date changesSince);

    public void deleteDataPoints(Date since);
}