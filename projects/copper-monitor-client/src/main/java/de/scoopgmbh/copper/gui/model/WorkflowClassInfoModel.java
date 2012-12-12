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
package de.scoopgmbh.copper.gui.model;

import javafx.beans.property.SimpleStringProperty;

public class WorkflowClassInfoModel {
	
	private final SimpleStringProperty classname;
	private final  SimpleStringProperty version;
	private final  SimpleStringProperty alias;
	
	public WorkflowClassInfoModel(String classname, String version, String alias) {
		super();
		this.classname=new SimpleStringProperty(classname);
		this.version = new SimpleStringProperty(version);
		this.alias = new SimpleStringProperty(alias);
	}

	public SimpleStringProperty getClassname() {
		return classname;
	}

	public SimpleStringProperty getVersion() {
		return version;
	}

	public SimpleStringProperty getAlias() {
		return alias;
	}

}