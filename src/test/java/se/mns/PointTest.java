package se.mns;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PointTest {

	Point point = new Point();
	
	@Test
	public void testConstructor() {
		assertEquals(0, point.getLatestPoints());
	}
	
	@Test
	public void testGetLatestPoints() {
		assertEquals(0, point.getLatestPoints());
	}
	
	@Test
	public void testPointIncrease() {
		point.pointIncrease();
		assertEquals(1, point.getLatestPoints());
	}
	
	@Test
	public void testResetPoints() {
		point.pointIncrease();
		point.resetPoints();
		assertEquals(0, point.getLatestPoints());
	}

	@Test
	public void testSetHigestPoint() {
		point.pointIncrease();
		point.setHighestPoint();
		assertEquals(1, point.getHighestPoints());
	}

	

}
