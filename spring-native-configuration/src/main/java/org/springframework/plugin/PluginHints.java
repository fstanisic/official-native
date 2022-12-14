/*
 * Copyright 2021 the original author or authors.
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
package org.springframework.plugin;

import org.springframework.nativex.hint.TypeAccess;
import org.springframework.nativex.hint.TypeHint;

@TypeHint(
		types = {
				org.springframework.plugin.core.support.PluginRegistryFactoryBean.class,
				org.springframework.plugin.core.OrderAwarePluginRegistry.class,
				org.springframework.plugin.core.Plugin.class,
				org.springframework.plugin.core.PluginRegistry.class,
				org.springframework.plugin.core.PluginRegistrySupport.class,
				org.springframework.plugin.core.SimplePluginRegistry.class,
				org.springframework.plugin.core.config.EnablePluginRegistries.class,
				org.springframework.plugin.core.config.PluginRegistriesBeanDefinitionRegistrar.class,
				org.springframework.plugin.core.support.AbstractTypeAwareSupport.class,
				org.springframework.plugin.core.support.PluginRegistryFactoryBean.class,
		},
		access = { TypeAccess.DECLARED_CONSTRUCTORS, TypeAccess.DECLARED_METHODS, TypeAccess.PUBLIC_METHODS })
public class PluginHints {

}
