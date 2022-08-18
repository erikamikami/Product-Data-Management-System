package com.example.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import com.example.entity.User;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("nameから特定できるか")
	public void findByNameTest() {
		// 引数
		String name = "Mikami";
		
		// 期待値
		User expected = new User("00", name, "TestPassword1", "ADMIN");
		
		// 実際
		User actual = userRepository.findByName(name);
		
		System.out.println(expected);
		System.out.println(actual);
		
		// 結果
		assertEquals(expected.toString(), actual.toString());
	}
	
	@Test
	@DisplayName("insertできているか")
	public void insertTest() {
		// 登録内容
		String name = "斎藤";
		User user = new User("03", name, "Password1", "USER");
		
		
		// 登録
		userRepository.insert(user);
		
		// 結果
		User actual = userRepository.findByName(name);
		assertEquals(user.toString(), actual.toString());
	}

}
