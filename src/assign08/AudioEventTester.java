package assign08;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class AudioEventTester {

	NoteEvent shortMiddleC;
	NoteEvent longMiddleC;
	NoteEvent shortHighC;
	VolumeEvent highVolume;
	VolumeEvent lowVolume;

	@BeforeEach
	public void setup() {
		shortMiddleC = new NoteEvent(0, 5, 1, 60);
		shortHighC = new NoteEvent(0, 5, 1, 120);
		longMiddleC = new NoteEvent(4, 5, 8, 60);
		highVolume = new VolumeEvent(6, 5, 120);
		lowVolume = new VolumeEvent(5, 5, 120);
	}

	/*
	 * Test cases for constructors
	 */
	@Test
	public void testNoteEventConstructorNegativeDuration() {
		assertThrows(IllegalArgumentException.class, () -> {
			new NoteEvent(0, 5, -1, 5);
		});
	}

	@Test
	public void testNoteEventConstructorPitchOutOfRange() {
		assertThrows(IllegalArgumentException.class, () -> {
			new NoteEvent(0, 5, 5, 128);
		});
	}

	@Test
	public void testNoteEventConstructorNegativeTime() {
		assertThrows(IllegalArgumentException.class, () -> {
			new NoteEvent(-1, 5, 5, 5);
		});
	}

	@Test
	public void testNoteEventConstructorTrackNumberOutOfRange() {
		assertThrows(IllegalArgumentException.class, () -> {
			new NoteEvent(0, 10, 5, 5);
		});
	}

	@Test
	public void testVolumeEventConstructorValueOutOfRange() {
		assertThrows(IllegalArgumentException.class, () -> {
			new VolumeEvent(0, 5, 128);
		});
	}

	@Test
	public void testVolumeEventConstructorNegativeTime() {
		assertThrows(IllegalArgumentException.class, () -> {
			new VolumeEvent(-1, 5, 120);
		});
	}

	@Test
	public void testVolumeConstructorTrackNumberOutOfRange() {
		assertThrows(IllegalArgumentException.class, () -> {
			new VolumeEvent(0, 10, 120);
		});
	}

	/*
	 * Test cases for toString methods.
	 */
	@Test
	public void testNoteEventToString() {
		assertEquals("Note[0, 5, 1, 60]", shortMiddleC.toString());
	}

	@Test
	public void testVolumeEventToString() {
		assertEquals("Volume[5, 5, 120]", lowVolume.toString());
	}

	/*
	 * Test cases for getters
	 */
	@Test
	public void testGetDuration() {
		assertEquals(1, shortMiddleC.getDuration());
	}

	@Test
	public void testGetPitch() {
		assertEquals(60, shortMiddleC.getPitch());
	}

	@Test
	public void testGetTimeNoteEvent() {
		assertEquals(0, shortMiddleC.getTime());
	}

	@Test
	public void testGetTrackNumber() {
		assertEquals(5, shortMiddleC.getTrackNumber());
	}

	@Test
	public void testGetValue() {
		assertEquals(120, lowVolume.getValue());
	}

	@Test
	public void testGetTimeVolumeEvent() {
		assertEquals(5, lowVolume.getTime());
	}

	@Test
	public void testGetTrackNumberVolumeEvent() {
		assertEquals(5, lowVolume.getTrackNumber());
	}

	/*
	 * Test cases for compareTo methods.
	 */
	@Test
	public void testCompareVolumeEventBeforeNoteEvent() {
		assertTrue(shortMiddleC.compareTo(highVolume) < 0);
	}

	@Test
	public void testCompareToNoteEventAfterVolumeEvent() {
		assertTrue(highVolume.compareTo(shortMiddleC) > 0);
	}

	@Test
	public void testCompareToNoteEventsDifferentPitchesComesAfter() {
		assertTrue(shortMiddleC.compareTo(shortHighC) > 0);
	}

	@Test
	public void testCompareToNoteEventsDifferentDuration() {
		assertTrue(shortMiddleC.compareTo(longMiddleC) == 0);
	}

	@Test
	public void testCompareToVolumeEventsLowBeforeHigh() {
		assertTrue(lowVolume.compareTo(highVolume) < 0);
	}
}