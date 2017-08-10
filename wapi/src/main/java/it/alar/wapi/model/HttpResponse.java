/*
 * Copyright 2017 the original author or authors.
 * 
 * This file is part of Alar-Wapi
 * 
 * Alar-Wapi is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Alar-Wapi is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser Public License for more details.
 *
 * You should have received a copy of the GNU Lesser Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package it.alar.wapi.model;

public class HttpResponse {

	private int code;
	private String body;
	
	public HttpResponse(int code, String body) {
		super();
		this.code = code;
		this.body = body;
	}
	public int getCode() {
		return code;
	}

	public String getBody() {
		return body;
	}
}
