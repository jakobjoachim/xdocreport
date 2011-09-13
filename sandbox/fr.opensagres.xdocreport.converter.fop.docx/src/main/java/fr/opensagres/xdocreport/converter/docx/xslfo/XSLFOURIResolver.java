/**
 * Copyright (C) 2011 Angelo Zerr <angelo.zerr@gmail.com>, Pascal Leclercq <pascal.leclercq@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.opensagres.xdocreport.converter.docx.xslfo;

import java.io.InputStream;

import javax.xml.transform.URIResolver;

import fr.opensagres.xdocreport.converter.internal.xslt.AbstractURIResolver;

public class XSLFOURIResolver extends AbstractURIResolver {

	public static final URIResolver INSTANCE = new XSLFOURIResolver();

	@Override
	protected InputStream getInputStream(String href, String base) {
		return XSLFOURIResolver.class.getResourceAsStream(href);
	}

}