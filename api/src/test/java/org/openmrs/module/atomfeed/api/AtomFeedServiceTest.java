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

import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.api.context.Context;
import org.openmrs.test.BaseModuleContextSensitiveTest;
import org.openmrs.test.Verifies;
import org.openmrs.module.atomfeed.DataPoint;

/**
 * Tests {@link AtomFeedService}.
 */
public class AtomFeedServiceTest extends BaseModuleContextSensitiveTest {
	
    private AtomFeedService service;

	@Test
	public void shouldSetupContext() {
		assertNotNull(Context.getService(AtomFeedService.class));
	}
	
    @Before
    public void before() throws Exception {
        service = Context.getService(AtomFeedService.class);
    }

    @Test
    @Verifies(value="should save the specified data point to the database", method="saveDataPoint(DataPoint)")
    public void saveDataPoint_shouldSaveTheSpecifiedDataPointToDatabase() throws Exception {
        int originalCount = service.getDataPoints(null).size();
        DataPoint dp = new DataPoint();
        dp.setAction("CREATED");
        dp.setObjectClass("Concept");
        dp.setDateCreated(new Date());
        dp.setUuid("9fb658a6-bff8-4ab6-a7c5-a2242c470597");
        DataPoint ret = service.saveDataPoint(dp);
        Assert.assertNotNull(ret);
        Assert.assertEquals(originalCount + 1, service.getDataPoints(null).size());
    }
}
