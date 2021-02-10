package se.mns;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PointTest {

	Score point = new Score();
	
	@Test
	public void testConstructor() {
		assertEquals(0, point.getLatest());
	}
	
	@Test
	public void testGetLatestPoints() {
		assertEquals(0, point.getLatest());
	}
	
	@Test
	public void testPointIncrease() {
		point.increase();
		assertEquals(1, point.getLatest());
	}
	
	@Test
	public void testResetPoints() {
		point.increase();
		point.reset();
		assertEquals(0, point.getLatest());
	}

	@Test
	public void testSetHigestPoint() {
		point.increase();
		point.setHighest();
		assertEquals(1, point.getHighest());
	}

	

}
