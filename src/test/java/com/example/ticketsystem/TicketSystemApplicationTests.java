package com.example.ticketsystem;

import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TicketSystemApplicationTests {

	@Test
	void contextLoads() {
		System.out.println("In test server");
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			System.out.println(new Random().nextInt(10));
		}

	}

}
