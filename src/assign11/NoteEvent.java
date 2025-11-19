package assign11;

/**
 * This object is for controlling the duration and pitch for SimpleSynthesizer.
 * 
 * @author Matthew Suggars
 * @version October 26, 2025
 */
public class NoteEvent extends AudioEvent {
	private int duration;
	private int pitch;

	/**
	 * Constructs a NoteEvent object with a duration and pitch. Calls the superclass
	 * constructor to initialize the track number and value.
	 * 
	 * @param time        - must be positive.
	 * @param trackNumber - must be between 0 and 9.
	 * @param duration    - must be positive.
	 * @param pitch       - must be between 0 and 127.
	 */
	public NoteEvent(int time, int trackNumber, int duration, int pitch) {
		super(time, trackNumber);
		if (duration < 0)
			throw new IllegalArgumentException("duration must be positive");
		else if (pitch < 0 || pitch > 127)
			throw new IllegalArgumentException("pitch must be between 0 and 127");
		this.duration = duration;
		this.pitch = pitch;
	}

	/**
	 * Returns the current duration value of this NoteEvent.
	 * 
	 * @return the duration value.
	 */
	public int getDuration() {
		return this.duration;
	}

	/**
	 * Returns the current pitch value of this NoteEvent.
	 * 
	 * @return the pitch value.
	 */
	public int getPitch() {
		return this.pitch;
	}

	/**
	 * Stops playing the note for SimpleSynthesizer object.
	 * 
	 * @param synth - SimpleSynthesizer object.
	 */
	public void complete(SimpleSynthesizer synth) {
		synth.noteOff(getTrackNumber(), pitch);
	}

	/**
	 * Begins playing the note for SimpleSythesizer object.
	 * 
	 * @param synth - SimpleSynthesizer object.
	 */
	@Override
	public void execute(SimpleSynthesizer synth) {
		synth.noteOn(getTrackNumber(), pitch);
	}

	/**
	 * Returns a String describing the note event. Includes the time, track number,
	 * duration, and pitch.
	 * 
	 * @return a String formatted for the note event.
	 */
	@Override
	public String toString() {
		return "Note[" + getTime() + ", " + getTrackNumber() + ", " + this.duration + ", " + this.pitch + "]";
	}

	/**
	 * Compares this NoteEvent with other AudioEvent passed as argument. If other is
	 * a VolumeEvent, returns a negative value. If other is also a NoteEvent, then
	 * pitch is used as a tie breaker in which the smaller pitch comes first. If
	 * both objects are NoteEvent with equal pitch, returns 0.
	 *
	 * Note: this class has a natural ordering that is inconsistent with equals."
	 * 
	 * @param other - AudioEvent Object that could either be a VolumeEvent or
	 *                NoteEvent.
	 * @return integer which is positive or negative depending on argument passed.
	 */
	@Override
	public int compareTo(AudioEvent other) {
		if (other instanceof VolumeEvent)
			return -1;
		NoteEvent otherNoteEvent = (NoteEvent) other;
		if (this.pitch != otherNoteEvent.pitch)
			return otherNoteEvent.pitch - this.pitch;
		else
			return 0;

	}
}
