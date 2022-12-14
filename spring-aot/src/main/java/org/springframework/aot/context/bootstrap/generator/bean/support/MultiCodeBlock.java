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

package org.springframework.aot.context.bootstrap.generator.bean.support;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.CodeBlock.Builder;

/**
 * A {@link CodeBlock} wrapper for joining multiple elements.
 *
 * @author Stephane Nicoll
 * @see MultiStatement
 */
public class MultiCodeBlock {

	private final List<CodeBlock> elements = new ArrayList<>();

	/**
	 * Add an element using the specified callback
	 * @param code the callback to use
	 */
	public void add(Consumer<Builder> code) {
		Builder builder = CodeBlock.builder();
		code.accept(builder);
		add(builder.build());
	}

	/**
	 * Add the specified element.
	 * @param code the element to add
	 */
	public void add(CodeBlock code) {
		if (code.isEmpty()) {
			throw new IllegalArgumentException("Code should not be empty");
		}
		this.elements.add(code);
	}

	/**
	 * Add an element.
	 * @param code the code
	 * @param arguments the arguments
	 * @see Builder#add(String, Object...)
	 */
	public void add(String code, Object... arguments) {
		add(CodeBlock.of(code, arguments));
	}

	/**
	 * Return a {@link CodeBlock} that joins the different elements registered in this
	 * instance with the specified delimiter.
	 * @param delimiter the delimiter to use (not {@code null})
	 * @return a {@link CodeBlock} joining the elements of this instance with the
	 * specified {@code delimiter}
	 * @see String#join(CharSequence, Iterable)
	 */
	public CodeBlock join(String delimiter) {
		Builder all = CodeBlock.builder();
		Iterator<CodeBlock> it = this.elements.iterator();
		while (it.hasNext()) {
			all.add(it.next());
			if (it.hasNext()) {
				all.add(delimiter);
			}
		}
		return all.build();
	}

}
