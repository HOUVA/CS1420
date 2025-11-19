package assign11;

/**
 * Represents a generic audio event.
 * 
 * This abstract class defines a common object for all audio event objects.
 * Including a super constructor for time and track number.
 * 
 * abstract method execute must be overridden by the sub classes
 * 
 * @author Matthew Suggars
 * @version October 26, 2025
 */
public abstract class AudioEvent implements Comparable<AudioEvent> {
	private int time;
	private int trackNumber;

	/**
	 * Super constructor for an AudioEvent with a value for time and track number.
	 * 
	 * @param time        - must be positive.
	 * @param trackNumber - must be between 0 and 9.
	 */
	public AudioEvent(int time, int trackNumber) {
		if (time < 0)
			throw new IllegalArgumentException("starting time must be positive");
		else if (trackNumber < 0 || trackNumber > 9)
			throw new IllegalArgumentException("track number must be between 0 and 9");
		this.time = time;
		this.trackNumber = trackNumber;
	}

	/**
	 * Returns the current time value of an AudioEvent.
	 * 
	 * @return the time value.
	 */
	public int getTime() {
		return this.time;
	}

	/**
	 * Returns the current track number of an AudioEvent.
	 * 
	 * @return the track number value.
	 */
	public int getTrackNumber() {
		return this.trackNumber;
	}

	/**
	 * Executes a method in the SimpleSynthesizer object.
	 * 
	 * @param synth SimpleSynthesizer object.
	 */
	public abstract void execute(SimpleSynthesizer synth);
}
