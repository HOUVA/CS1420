package assign10;

/**
 * This object is for controlling the value of the volume for SimpleSynthesizer.
 * 
 * @author Matthew Suggars
 * @version October 26, 2025
 */
public class VolumeEvent extends AudioEvent {
	private int value;

	/**
	 * Constructs an VolumeEvent object with a volume value. Calls the superclass
	 * constructor to initialize the track number and value.
	 * 
	 * @param time        - must be positive.
	 * @param trackNumber - must be between 0 and 9.
	 * @param value       - must be between 0 and 127.
	 */
	public VolumeEvent(int time, int trackNumber, int value) {
		super(time, trackNumber);
		if (value < 0 || value > 127)
			throw new IllegalArgumentException("volume value must be between 0 and 127");
		this.value = value;
	}

	/**
	 * Returns the current volume value of this VolumeEvent.
	 * 
	 * @return the volume value.
	 */
	public int getValue() {
		return this.value;
	}

	/**
	 * Sets the volume level for a specific track number.
	 * 
	 * @param synth - SimpleSynthesizer object.
	 */
	@Override
	public void execute(SimpleSynthesizer synth) {
		synth.setVolume(getTrackNumber(), value);
	}

	/**
	 * Returns a String describing the volume event. Includes the time, track
	 * number, and value.
	 * 
	 * @return a String formatted for the volume event.
	 */
	@Override
	public String toString() {
		return "Volume[" + getTime() + ", " + getTrackNumber() + ", " + this.value + "]";
	}

	/**
	 * Compares this VolumeEvent with other AudioEvent passed as argument. If other
	 * is a NoteEvent, returns a positive value. If other is also a VolumeEvent,
	 * then time is used as a tie breaker in which the smaller time comes first. If
	 * both objects are VolumeEvents with equal time, returns 0.
	 *
	 * Note: this class has a natural ordering that is inconsistent with equals."
	 * 
	 * @param other - AudioEvent Object that could either be a VolumeEvent or
	 *              NoteEvent.
	 * @return integer which is positive or negative depending on argument passed.
	 */
	@Override
	public int compareTo(AudioEvent other) {
		if (other instanceof NoteEvent)
			return 1;
		else if (this.getTime() != other.getTime())
			return this.getTime() - other.getTime();
		else
			return 0;
	}

}
