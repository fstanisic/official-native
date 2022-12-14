/*
 * Copyright 2019-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.nativex.substitutions.tomcat;

import com.oracle.svm.core.annotate.Alias;
import com.oracle.svm.core.annotate.Substitute;
import com.oracle.svm.core.annotate.TargetClass;
import org.apache.tomcat.util.modeler.Registry;

import org.springframework.nativex.substitutions.OnlyIfPresent;
import org.springframework.nativex.substitutions.RemoveJmxSupport;

// To avoid Registry instantiation and JmxMBeanServer usage
@TargetClass(className = "org.apache.tomcat.util.modeler.Registry", onlyWith = { OnlyIfPresent.class, RemoveJmxSupport.class })
final class Target_Registry {

	@Alias
	private static Registry registry = null;

	@Alias
	private Object guard;

	@Substitute
	public static synchronized Registry getRegistry(Object key, Object guard) {
		if (registry == null) {
			disableRegistry();
		}
		return registry;
	}

	@Alias
	public static synchronized void disableRegistry() {
	}
}
