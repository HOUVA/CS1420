package practice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ScratchTest {

	@Test
	public void test() {
		int actual = 6;
		assertEquals(18, Scratch.triple(actual));
	}

}
