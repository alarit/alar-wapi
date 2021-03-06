package com.github.alarit.engine;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.github.alarit.model.HttpResponse;

public class WebApiInvoker {
	public enum ContentType {
		JSON("application/json"),
		FORM_URL_ENCODED("application/x-www-form-urlencoded"),
		PLAIN("text/plain"),
		XML("application/xml"),
		HTML("text/html");
		
		private String value;
		
		private ContentType(String value) {
			this.value = value;
		}
	}
	
	private static final String HTTP_GET	= "GET";
	private static final String HTTP_POST	= "POST";
	private static final String HTTP_PUT	= "PUT";
	private static final String HTTP_PATCH	= "PATCH";
	private static final String HTTP_DELETE	= "DELETE";
	
	private Map<String,String> header = new HashMap<String, String>();
	private String baseURL;
	private String api;
	private String body;
	private ContentType contentType;
	
	/*
	 * Setting default body content type as JSON
	 */
	public WebApiInvoker() {
		super();
		this.contentType = ContentType.JSON;
	}
	
	public String getBaseURL() {
		return baseURL;
	}

	public WebApiInvoker setBaseURL(String baseURL) {
		this.baseURL = baseURL;
		return this;
	}

	public String getApi() {
		return api;
	}

	public WebApiInvoker setApi(String api) {
		this.api = api;
		return this;
	}

	public String getBody() {
		return body;
	}

	public WebApiInvoker setBody(String body) {
		this.body = body;
		return this;
	}

	public Map<String, String> getHeader() {
		return header;
	}

	public WebApiInvoker setHeader(Map<String, String> header) {
		this.header = header;
		return this;
	}
	
	public ContentType getContentType() {
		return contentType;
	}

	public WebApiInvoker setContentType(ContentType contentType) {
		this.contentType = contentType;
		return this;
	}

	public HttpResponse post() throws IOException{
		return invokeWebApi(HTTP_POST);
	}
	
	public HttpResponse get() throws IOException{
		return invokeWebApi(HTTP_GET);
	}
	
	public HttpResponse put() throws IOException{
		return invokeWebApi(HTTP_PUT);
	}
	
	public HttpResponse patch() throws IOException{
		return invokeWebApi(HTTP_PATCH);
	}
	
	public HttpResponse delete() throws IOException{
		return invokeWebApi(HTTP_DELETE);
	}

	private HttpResponse invokeWebApi(String verb) throws IOException {
		URL url = new URL(baseURL + api);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod(verb);
		
		for(Entry<String, String> entry : header.entrySet()){
			con.setRequestProperty(entry.getKey(), entry.getValue());	
		}
		
		if(body != null){
			con.setRequestProperty("Content-Type", contentType.value);
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(body);
			wr.flush();
			wr.close();
		}
		return getHttpResponse(con);
	}
	
	private HttpResponse getHttpResponse(HttpURLConnection c) throws IOException{
		int code = c.getResponseCode();
		StringBuilder response = new StringBuilder();
		
		BufferedReader in = null;
		try{
			in = new BufferedReader(new InputStreamReader(c.getInputStream()));
			String inputLine;
			
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
		}catch(IOException e){
			response.append("");
		}
		return new HttpResponse(code, response.toString());
	}
}
