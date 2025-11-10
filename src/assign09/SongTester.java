package assign09;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class contains tests for the Song class.
 * 
 * @author Matthew Suggars
 * @version November 3 2025.
 */
class SongTester {
	private static Song newSong;
	private static Song newSongTwo;

	@BeforeEach
	public void setup() {
		newSong = new Song(1, 10);
		newSongTwo = new Song(1, 10);
		for (int index = 0; index < 10; index++)
			newSongTwo.addNoteEvent(0, index, 0, 0);
	}

	@Test
	public void testAddNoteEventTrackNumberInvalidTooHigh() {
		assertThrows(IllegalArgumentException.class, () -> {
			newSong.addNoteEvent(0, 11, 0, 0);
		}, "Does not throw an exception when trackNumber higher than 9");
	}

	@Test
	public void testAddNoteEventTrackNumberInvalidTooLow() {
		assertThrows(IllegalArgumentException.class, () -> {
			newSong.addNoteEvent(0, -1, 0, 0);
		}, "Does not throw an exception when trackNumber lower than 0");
	}

	@Test
	public void testAddNoteEventFirstTrackNumber() {
		newSong.addNoteEvent(0, 0, 0, 0);
		assertFalse(newSong.getTrack(0).isEmpty(), "NoteEvent not added to first track");
	}

	@Test
	public void testAddNoteEventMiddleTrackNumber() {
		newSong.addNoteEvent(0, 4, 0, 0);
		assertFalse(newSong.getTrack(4).isEmpty(), "NoteEvent not added to middle track");
	}

	@Test
	public void testAddNoteEventLastTrackNumber() {
		newSong.addNoteEvent(0, 9, 0, 0);
		assertFalse(newSong.getTrack(9).isEmpty(), "NoteEvent not added to last track");
	}

	@Test
	public void testAddVolumeEventTrackNumberInvalidTooHigh() {
		assertThrows(IllegalArgumentException.class, () -> {
			newSong.addVolumeEvent(0, 11, 0);
		}, "Does not throw an exception when trackNumber higher than 9");
	}

	@Test
	public void testAddVolumeEventTrackNumberInvalidTooLow() {
		assertThrows(IllegalArgumentException.class, () -> {
			newSong.addVolumeEvent(0, -1, 0);
		}, "Does not throw an exception when trackNumber lower than 0");
	}

	@Test
	public void testAddVolumeEventFirstTrackNumber() {
		newSong.addVolumeEvent(0, 0, 0);
		assertFalse(newSong.getTrack(0).isEmpty(), "VolumeEvent not added to first track");
	}

	@Test
	public void testAddVolumeEventMiddleTrackNumber() {
		newSong.addVolumeEvent(0, 4, 0);
		assertFalse(newSong.getTrack(4).isEmpty(), "VolumeEvent not added to middle track");
	}

	@Test
	public void testAddVolumeEventLastTrackNumber() {
		newSong.addVolumeEvent(0, 9, 0);
		assertFalse(newSong.getTrack(9).isEmpty(), "VolumeEvent not added to last track");
	}

	@Test
	public void testClearTrack() {
		newSongTwo.clearTrack(0);
		assertEquals(0, newSongTwo.getTrack(0).size(), "clearTrack does not clear events in track");
	}

	@Test
	public void testClearAll() {
		newSongTwo.clearAll();
		for (int index = 0; index < 10; index++) {
			assertEquals(0, newSongTwo.getTrack(index).size(), "clearTrack does not clear all events in track");
		}
	}
}
