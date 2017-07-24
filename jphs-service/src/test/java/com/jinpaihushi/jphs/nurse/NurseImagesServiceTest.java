package com.jinpaihushi.jphs.nurse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jinpaihushi.jphs.nurse.model.NurseImages;
import com.jinpaihushi.jphs.nurse.service.NurseImagesService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/spring.xml")
public class NurseImagesServiceTest {

	@Autowired
	private NurseImagesService nurseImagesService;
	
	@Test
	public void insert(){
		NurseImages nurseImages = new NurseImages();
		nurseImages.setId(""+System.currentTimeMillis());
		nurseImages.setUrl("http://sdfsdfjlsjf.com/sdlfjlsdjflsdf");
		
		nurseImagesService.insert(nurseImages);
		
	}
}
