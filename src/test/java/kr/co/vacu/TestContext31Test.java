package kr.co.vacu;

import javax.annotation.Resource;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import kr.co.vacu.config.AppConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
//@ActiveProfiles("korea")
public class TestContext31Test {
	@Resource
	String whiteship;
	@Resource
	String toby;

	@Test
	public void di() {
		assertThat(whiteship, is("Keesun"));
		assertThat(toby, is("toby"));
		//assertThat(toby, is("이일민"));
	}
}
