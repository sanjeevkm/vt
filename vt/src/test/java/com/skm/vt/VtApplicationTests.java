package com.skm.vt;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VtApplicationTests {

	@Value("${csv.file.path}")
	private String fileUploadFolder;
	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;

	@Before
	public void setup() {
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(webApplicationContext);
		mockMvc = builder.build();
	}

	@Test
	public void test_HTMLPage() throws Exception {
		MvcResult result = mockMvc.perform(get("/upload.html")).andExpect(status().isOk()).andReturn();

		MockHttpServletResponse mockResponse = result.getResponse();
		assertThat(mockResponse.getContentType()).isEqualTo("text/html");
	}

	@Test
	public void test_GetPortMonthlyTrafficEndpoint() throws Exception {
		MvcResult result = mockMvc.perform(get("/port/2/year/2015/month/05")).andExpect(status().isOk()).andReturn();

		MockHttpServletResponse mockResponse = result.getResponse();
		assertThat(mockResponse.getContentType()).isEqualTo("application/json;charset=UTF-8");
		assertThat(mockResponse.getContentAsString().contains("totalArrivedVesselCount"));
	}

	@Test
	public void test_DataLoad_GetPortMonthlyTrafficEndpoint() throws Exception {
		String fileName = "data.csv";

		MockMultipartFile mockMultipartFile = new MockMultipartFile("file", fileName, "text/csv",
				"name,imo,length,port_id,time_started,time_finished\nhamburg express,123,304.00,2,2016-01-01 06:15:07.838,2016-01-03 07:07:00.109".getBytes());

		File file = new File(fileUploadFolder + fileName);
		// delete if exits
		file.delete();

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.multipart("/upload").file(mockMultipartFile);
		this.mockMvc.perform(builder).andDo(MockMvcResultHandlers.print());
		Assert.assertTrue(file.exists());
		
		MvcResult result = mockMvc.perform(get("/port/2/year/2016/month/01")).andExpect(status().isOk()).andReturn();

		MockHttpServletResponse mockResponse = result.getResponse();
		assertThat(mockResponse.getContentType()).isEqualTo("application/json;charset=UTF-8");
		assertThat(mockResponse.getContentAsString().contains("totalArrivedVesselCount"));
	}
}
