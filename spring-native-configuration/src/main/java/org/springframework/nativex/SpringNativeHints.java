/*
 * Copyright 2002-2021 the original author or authors.
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

package org.springframework.nativex;

import org.springframework.nativex.hint.InitializationHint;
import org.springframework.nativex.hint.InitializationTime;
import org.springframework.nativex.hint.NativeHint;
import org.springframework.nativex.hint.TypeAccess;
import org.springframework.nativex.hint.TypeHint;
import org.springframework.nativex.type.NativeConfiguration;

@NativeHint(
		initialization = @InitializationHint(types = {
				org.springframework.nativex.AotModeDetector.class
		}, initTime = InitializationTime.BUILD)
)
// TODO To be removed when https://github.com/spring-projects/spring-framework/issues/27772 is fixed, use netty-kotlin or webflux-thymeleaf to check
@NativeHint(trigger = org.junit.jupiter.api.Test.class,
		types = @TypeHint(types = TypeAccess.class, access = TypeAccess.RESOURCE))
public class SpringNativeHints implements NativeConfiguration {
}
