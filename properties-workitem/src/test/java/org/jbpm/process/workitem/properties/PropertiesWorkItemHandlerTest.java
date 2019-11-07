/*
 * Copyright 2018 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jbpm.process.workitem.properties;

import java.io.File;
import java.util.Map;

import org.jbpm.process.workitem.properties.PropertiesWorkItemHandler;
import org.drools.core.process.instance.impl.WorkItemImpl;
import org.jbpm.bpmn2.handler.WorkItemHandlerRuntimeException;
import org.jbpm.process.workitem.core.TestWorkItemManager;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.runtime.process.WorkItemManager;

import static org.junit.Assert.*;

public class PropertiesWorkItemHandlerTest {

    final int PROP1=12345;
    final String PROP2 = "properties@example.com";   
    final String fileName = "test.properties";

    // Returns absolute class path (directory) of the test.properties file.
    final ClassLoader classLoader = getClass().getClassLoader();
    final File file = new File(classLoader.getResource(fileName).getFile());
    final String dir = file.getParent();

    PropertiesWorkItemHandler handler;

    @Before
    public void init() {
        System.out.println("dir: "+dir);

        handler = new PropertiesWorkItemHandler(dir);
    }

    @Test
    public void testLoadFile() {
        WorkItemManager manager = new TestWorkItemManager();
        WorkItemImpl workItem = new WorkItemImpl();
        workItem.setParameter("fileName",fileName);
        handler.executeWorkItem(workItem,
                                manager);
        Map<String, Object> results = ((TestWorkItemManager) manager).getResults(workItem.getId());
        Map result = (Map) results.get("properties");
        assertEquals(PROP1,
                     Integer.parseInt(result.get("PROP1").toString()));
        assertEquals(PROP2,
                     result.get("PROP2"));
    }
}

