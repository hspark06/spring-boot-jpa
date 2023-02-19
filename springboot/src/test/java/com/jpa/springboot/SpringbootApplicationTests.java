package com.jpa.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@SpringBootTest
class SpringbootApplicationTests {

	@Test
	void contextLoads() {
		assertThat(1).isEqualTo(1);
	}

	@Test
	void test() {
		assertThat(1).isEqualTo(1);
	}

}
