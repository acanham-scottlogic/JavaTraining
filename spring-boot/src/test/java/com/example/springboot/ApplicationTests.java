package com.example.springboot;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {//basically use the tests from js matcher test
	Matcher matcher = new Matcher();

	@BeforeEach
	public void setUp(){
		matcher.addOrder(new Order("account",12.90,9,false));//sell goes lot to high
		matcher.addOrder(new Order("account",13.90,2,false));
		matcher.addOrder(new Order("account",14.90,2,false));

		matcher.addOrder(new Order("account",6.90,9,true));//buy goes high to low
		matcher.addOrder(new Order("account",5.90,2,true));
		matcher.addOrder(new Order("account",3.90,2,true));

		System.out.println("test set up");
	}

	@AfterEach
	public void tearDown(){
		matcher.buyList.clear();
		matcher.sellList.clear();
		matcher.tradesList.clear();
	} //this combined with the setUp function allows the entire thing to be reset to original conditions
	//this allows testing to be like that in jest.

	@Test
	void contextLoads() {
		System.out.println("testing");
	}

	@Test
	void checkSetUp(){
		assertEquals(12.9,matcher.sellList.get(0).getPrice());
		assertEquals(3, matcher.sellList.size());
		assertEquals(6.90, matcher.buyList.get(0).getPrice());
		assertEquals(5.9,matcher.buyList.get(1).getPrice());
		assertEquals(3,matcher.buyList.size());
	}

	@Test
	void tradeFromBuy(){
		matcher.addOrder(new Order("account", 13,3,true));
		assertEquals(1,matcher.tradesList.size());
		assertEquals(6,matcher.sellList.get(0).getQuantity());
	}

	@Test
	void partialTradeFromBuy(){
		matcher.addOrder(new Order("account",13,10,true));
		assertEquals(1,matcher.buyList.get(0).getQuantity());
		assertEquals(2, matcher.sellList.size());
		assertEquals(9, matcher.tradesList.get(0).getQuantity());
		assertEquals(1,matcher.tradesList.size());
	}

	@Test
	void unsuccessfulBuy(){
		matcher.addOrder(new Order("account",5,10,true));
		assertEquals(4,matcher.buyList.size());
		assertEquals(5,matcher.buyList.get(2).getPrice());
	}

	@Test
	void tradeFromSell(){
		assertEquals(0, matcher.tradesList.size());
		matcher.addOrder(new Order("account", 6,9,false));
		//this will fully match on both the buy and sell side
		assertEquals(6.9,matcher.tradesList.get(0).getPrice());
		assertEquals(3, matcher.sellList.size());
		assertEquals(2,matcher.buyList.size());
		assertEquals(1, matcher.tradesList.size());
	}

	@Test
	void partialTradeFromSell(){
		matcher.addOrder(new Order("account",6,4,false));
		assertEquals(6.9,matcher.tradesList.get(0).getPrice());
	}

	@Test
	void unsuccessfulSell(){
		matcher.addOrder(new Order("account",8,10,false));
		assertEquals(4,matcher.sellList.size());
		assertEquals(8,matcher.sellList.get(0).getPrice());
	}


	@Test
	void tripleBuyTrade(){
		matcher.addOrder(new Order("account",15,14,true));
		assertEquals(3, matcher.tradesList.size());
		assertEquals(15,matcher.buyList.get(0).getPrice());
	}
}
