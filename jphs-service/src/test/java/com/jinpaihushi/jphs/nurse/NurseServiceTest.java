package com.jinpaihushi.jphs.nurse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jinpaihushi.jphs.nurse.model.Nurse;
import com.jinpaihushi.jphs.nurse.service.NurseService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/spring.xml")
public class NurseServiceTest {
	
	@Autowired
	private NurseService nurseService;
	
	@Test
	public void testInsert(){
		Nurse nurse = new Nurse();
		nurse.setId("123123123123");
		nurse.setCreatorName("冯睿智");		
		
		nurseService.insert(nurse);
	}


}
