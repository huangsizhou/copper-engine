/*
 * Copyright 2002-2012 SCOOP Software GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.scoopgmbh.copper.sample.orchestration;

import javax.xml.ws.Endpoint;

import org.apache.derby.jdbc.EmbeddedConnectionPoolDataSource40;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.scoopgmbh.copper.PersistentProcessingEngine;
import de.scoopgmbh.copper.util.PersistentEngineFactory;
import de.scoopgmbh.copper.util.PojoDependencyInjector;

public class OrchestrationServer {
	
	private static final Logger logger = LoggerFactory.getLogger(OrchestrationServer.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			EmbeddedConnectionPoolDataSource40 dataSource = new EmbeddedConnectionPoolDataSource40();
			dataSource.setDatabaseName("./build/derbydb/copperUnitTestDB;create=true");
			
			CustomerServiceAdapter customerServiceAdapter = new CustomerServiceAdapter();

			PojoDependencyInjector dependencyInjector = new PojoDependencyInjector();
			dependencyInjector.register("customerServiceAdapter", customerServiceAdapter);
			
			PersistentProcessingEngine engine = new PersistentEngineFactory().createEngine(dataSource, "./src/workflow/java", "./build/compiled_workflow", dependencyInjector);
			customerServiceAdapter.setEngine(engine);
			
			engine.startup();
			
			OrchestrationServiceInputChannel implementor = new OrchestrationServiceInputChannel(engine);

			logger.info("Starting Server");
			String address = "http://localhost:9090/OrchestrationServicePort";
			Endpoint.publish(address, implementor);
			logger.info("Server ready...");
		}
		catch(Exception e) {
			logger.error("startup failed",e);
			System.exit(-1);
		}
	}

}