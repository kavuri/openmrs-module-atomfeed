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
package org.openmrs.module.atomfeed.api.db;

import java.util.Date;
import java.util.List;

import org.openmrs.OpenmrsObject;
import org.openmrs.module.atomfeed.api.AtomFeedService;
import org.openmrs.module.atomfeed.DataPoint;

/**
 *  Database methods for {@link AtomFeedService}.
 */
public interface AtomFeedDAO {

	OpenmrsObject getObjectByUuid(String classname, String uuid);
	
    public List<DataPoint> getDataPoints(Date changesSince);

    public <T> T save(T object);

    public void delete(Object object);

    public void deleteDataPoints(Date since);
}