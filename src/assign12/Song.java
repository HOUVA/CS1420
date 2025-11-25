package assign12;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A Song is made of 10 tracks that contain AudioEvents.
 * 
 * @author CS 1420 course staff and Matthew Suggars.
 * @version November 3 2025.
 */
public class Song {
	// Each track is represented by an ArrayList of AudioEvents.
	// There are always 10 tracks, so a basic array is used to
	// store the 10 ArrayLists.

	private ArrayList<AudioEvent>[] tracks;
	private int tempo;
	private int songLength;
	private SimpleSynthesizer synth;
	private SimpleTimer timer;

	/**
	 * Construct a new Song with the given tempo and length. It starts with 10 empty
	 * tracks. This also initializes the synthesizer and timer.
	 * 
	 * @param tempo      - speed of the song in beats(ticks) per minute
	 * @param songLength - length of the song in ticks
	 */
	@SuppressWarnings("unchecked")
	public Song(int tempo, int songLength) {
		this.tempo = tempo;
		this.songLength = songLength;
		synth = new SimpleSynthesizer();
		timer = new SimpleTimer(synth);
		// Creating tracks requires casting because we can't allocate a new array
		// of a generic class with a specified generic type.
		tracks = (ArrayList<AudioEvent>[]) new ArrayList[10];
		for (int i = 0; i < tracks.length; i++)
			tracks[i] = new ArrayList<AudioEvent>();
	}

	/**
	 * Add one NoteEvent to the specified track. After adding, the track is sorted
	 * to ensure the events are always in sorted order.
	 * 
	 * @param time        - for the NoteEvent
	 * @param trackNumber - for the NoteEvent
	 * @param duration    - for the NoteEvent
	 * @param pitch       - for the NoteEvent
	 */
	public void addNoteEvent(int time, int trackNumber, int duration, int pitch) {
		validateTrackNumber(trackNumber);
		tracks[trackNumber].add(new NoteEvent(time, trackNumber, duration, pitch));

		Collections.sort(tracks[trackNumber]);
	}

	/**
	 * Add one VolumeEvent to the specified track. After adding, the track is sorted
	 * to ensure the events are always in sorted order.
	 * 
	 * @param time        - for the VolumeEvent
	 * @param trackNumber - for the VolumeEvent
	 * @param value       - for the VolumeEvent
	 */
	public void addVolumeEvent(int time, int trackNumber, int value) {
		validateTrackNumber(trackNumber);
		tracks[trackNumber].add(new VolumeEvent(time, trackNumber, value));

		Collections.sort(tracks[trackNumber]);

	}

	/**
	 * Remove all events from the specified track.
	 * 
	 * @param trackNumber - of track to clear
	 * @throws IllegalArgumentException if trackNumber is not between 0 and 9
	 */
	public void clearTrack(int trackNumber) {
		validateTrackNumber(trackNumber);
		tracks[trackNumber].clear();
	}

	/**
	 * Remove all events from all tracks.
	 */
	public void clearAll() {
		for (int index = 0; index < tracks.length; index++)
			tracks[index].clear();
	}

	/**
	 * Get the list of events for a given track.
	 * 
	 * @param trackNumber - of track to get
	 * @return list of events in that track
	 * @throws IllegalArgumentException if trackNumber is not between 0 and 9
	 */
	public ArrayList<AudioEvent> getTrack(int trackNumber) {
		validateTrackNumber(trackNumber);
		return tracks[trackNumber];
	}

	/**
	 * This helper methods validates that the track number provided is in range.
	 * (between 0 and 9)
	 * 
	 * Throws exception if not in range.
	 * 
	 * @param trackNumber - for either VolumeEvent or NoteEvent.
	 */
	private void validateTrackNumber(int trackNumber) {
		if (trackNumber < 0 || trackNumber > 9)
			throw new IllegalArgumentException("Track number is not in range");
	}

	//////////////////////////////////////////////////////////////////////
	// The methods below are complete. Do not modify them.
	// They will be used in the next stages of the project.
	//////////////////////////////////////////////////////////////////////

	/**
	 * Get the playing speed of the song.
	 * 
	 * @return tempo
	 */
	public int getTempo() {
		return tempo;
	}

	/**
	 * Set the playing speed of the song.
	 * 
	 * @param newTempo - to set
	 */
	public void setTempo(int newTempo) {
		tempo = newTempo;
	}

	/**
	 * Get the total time that the song will play in ticks.
	 * 
	 * @return length of the song in ticks
	 */
	public int getSongLength() {
		return songLength;
	}

	/**
	 * Set the total time that the song will play in ticks.
	 * 
	 * @param newLength - of the song in ticks
	 */
	public void setSongLength(int newLength) {
		songLength = newLength;
	}

	/**
	 * Get the synthesizer that this song uses to make sound.
	 * 
	 * @return synthesizer
	 */
	public SimpleSynthesizer getSynthesizer() {
		return synth;
	}

	/**
	 * Get the timer that this song uses to schedule events.
	 * 
	 * @return timer
	 */
	public SimpleTimer getTimer() {
		return timer;
	}

	/**
	 * Schedule all events in all tracks to begin playback. This first stops
	 * playback to avoid overlapping any currently playing song.
	 */
	public void play() {
		stop();
		timer.scheduleEvents(tracks, tempo, songLength);
	}

	/**
	 * Tell the timer to stop all scheduled events. This effectively stops playback.
	 */
	public void stop() {
		timer.stop();
	}

	/**
	 * Sets whether the song loops.
	 * 
	 * @param doLoop - true to loop the song, false to only play once
	 */
	public void enableLoop(boolean doLoop) {
		timer.enableLoop(doLoop);
	}
}
