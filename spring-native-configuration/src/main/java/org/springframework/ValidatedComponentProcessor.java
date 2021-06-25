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

package org.springframework;

import org.springframework.nativex.hint.ProxyBits;
import org.springframework.nativex.type.ComponentProcessor;
import org.springframework.nativex.type.NativeContext;
import org.springframework.nativex.type.Type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Recognize spring.components that need validate proxies and register them.
 *
 * @author Petr Hejl
 */
public class ValidatedComponentProcessor implements ComponentProcessor {

	@Override
	public boolean handle(NativeContext imageContext, String componentType, List<String> classifiers) {
		Type type = imageContext.getTypeSystem().resolveName(componentType);
		return type.isAtValidated(true);
	}

	@Override
	public void process(NativeContext imageContext, String componentType, List<String> classifiers) {
		Type type = imageContext.getTypeSystem().resolveName(componentType);
		List<String> transactionalInterfaces = new ArrayList<>();
		boolean hasInterfaceMethods = false;
		for (Type intface: type.getAllInterfaces()) {
			transactionalInterfaces.add(intface.getDottedName());
			if (!intface.getMethods().isEmpty()) {
				hasInterfaceMethods = true;
			}
		}
		if (!transactionalInterfaces.isEmpty() && hasInterfaceMethods) {
			transactionalInterfaces.add("org.springframework.aop.SpringProxy");
			transactionalInterfaces.add("org.springframework.aop.framework.Advised");
			transactionalInterfaces.add("org.springframework.core.DecoratingProxy");
			imageContext.addProxy(transactionalInterfaces);
			imageContext.log(ValidatedComponentProcessor.class.getSimpleName() + ": creating proxy for these interfaces: " + transactionalInterfaces);
		} else if (!type.isInterface()) {
			// TODO is IS_STATIC always right here?
			imageContext.addAotProxy(type.getDottedName(), Collections.emptyList(), ProxyBits.IS_STATIC);
			imageContext.log(ValidatedComponentProcessor.class.getSimpleName() + ": creating proxy for this class: " + type.getDottedName());
		}
	}

}
