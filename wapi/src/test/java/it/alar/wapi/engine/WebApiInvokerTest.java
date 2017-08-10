package it.alar.wapi.engine;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import it.alar.wapi.model.HttpResponse;

public class WebApiInvokerTest {

	private WebApiInvoker invoker;
	
	@Before
	public void init(){
		invoker = new WebApiInvoker();
	}
	
	@Test
	public void testGET() throws IOException {
		HttpResponse response = invoker
		.setBaseURL("https://en.wikipedia.org/")
		.setApi("wiki/Portal:Biography")
		.get();
		
		assertEquals(response.getCode(), 200);
	}
}
