package assign11;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JPanel;

/**
 * A TrackEditor is the interactive GUI component for drawing a sequence of note
 * events or volume changes in a track.
 * 
 * @author CS 1420 course staff and ADD YOUR NAME
 * @version ADD THE DATE
 */
public class TrackEditor extends JPanel implements MouseListener, MouseMotionListener {

	public static enum Mode {
		NOTE, VOLUME, COPY
	};

	private Mode mode;

	private int trackNumber;
	private SimpleSynthesizer synth;
	private ArrayList<AudioEvent> events; // The AudioEvents for this track
	private ArrayList<NoteEvent> notesToCopy; // Stores notes during a copy operation

	private int columns, rows; // The number of columns and rows in the grid
	private boolean drawing; // Set to true during drawing operations
	private int currentRow, currentColumn; // Used by drawing operations
	private int noteDuration; // The duration of a note being drawn

	// For defining an area of the grid to copy
	private int copyFromRow, copyFromColumn; // Top and left of area
	private int copyToRow, copyToColumn; // Bottom and right of area

	// This pitch range matches a piano. You can change these values if desired.
	private static final int lowestPitch = 21;
	private static final int highestPitch = 108;

	/**
	 * Create a new TrackEditor with the default configuration.
	 * 
	 * @param trackNumber assigned to this track in the midi system
	 * @param synthesizer for making sounds
	 * @param sequencer   for sequencing note events
	 */
	public TrackEditor(int trackNumber, int songLength, ArrayList<AudioEvent> events, SimpleSynthesizer synth) {
		columns = songLength;
		rows = highestPitch - lowestPitch + 1;

		this.trackNumber = trackNumber;
		this.synth = synth;
		this.events = events;
		notesToCopy = new ArrayList<NoteEvent>();
		drawing = false;
		mode = Mode.NOTE;

		setBackground(Color.WHITE);

		addMouseListener(this);
		addMouseMotionListener(this);
	}

	/**
	 * Removes all events from the track.
	 */
	public void clearTrack() {
		events.clear();
		repaint();
	}

	/**
	 * Set the song duration in ticks.
	 * 
	 * @param songLength in ticks
	 */
	public void setSongLength(int songLength) {
		columns = songLength;
		if (columns < 1)
			columns = 1;
		repaint();
	}

	/**
	 * Set the editor to the specified mode.
	 * 
	 * @param mode - either Mode.NOTE, Mode.VOLUME, or Mode.COPY
	 */
	public void setMode(Mode mode) {
		this.mode = mode;
		// Set volume back to default in case it was changed in volume mode
		synth.setVolume(trackNumber, 100);
	}

	/**
	 * This method is called by the system when a component needs to be painted.
	 * Which can be at one of three times: --when the component first appears --when
	 * the size of the component changes (including resizing by the user) --when
	 * repaint() is called
	 * 
	 * Partially overrides the paintComponent method of JPanel.
	 * 
	 * @param g -- graphics context onto which we can draw
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Color lightBlue = new Color(173, 243, 255, 128);

		int previousVolume = 100; // the volume begins with value 100 by default
		int previousTime = 0; // the beginning of the song
		g.setColor(lightBlue);
		
		for (AudioEvent event : events) {
			if (event instanceof VolumeEvent) {
				VolumeEvent volEvent = (VolumeEvent) event;

				int rectX = colToPixel(previousTime);
				int rectY = rowToPixel(volumeToRow(previousVolume));

				int width = colToPixel(volEvent.getTime()) - rectX;
				int height = getHeight() - rectY;

				g.drawRect(rectX, rectY, width, height);
				g.fillRect(rectX, rectY, width, height);

				previousVolume = volEvent.getValue();
				previousTime = volEvent.getTime();
			}
		}

		// final column in pane
		g.fillRect(colToPixel(previousTime), rowToPixel(volumeToRow(previousVolume)),
				colToPixel(columns) - colToPixel(previousTime), getHeight() - rowToPixel(volumeToRow(previousVolume)));

		g.setColor(Color.black);
		for (int index = 0; index < this.columns; index++) {
			g.drawLine(colToPixel(index), 0, colToPixel(index), getHeight());
			if (index % 4 == 0)
				g.fillRect(colToPixel(index), 0, 2, getHeight());
		}

		for (int index = 0; index < this.rows; index++) {
			g.drawLine(0, rowToPixel(index), getWidth(), rowToPixel(index));
			if (index % 12 == 0)
				g.fillRect(0, rowToPixel(index), getWidth(), 2);
		}

		// Draw preview only if something is currently being drawn by the mouse
		if (drawing) {
			if (mode == Mode.VOLUME) {
				g.setColor(lightBlue);
				g.fillRect(colToPixel(this.currentColumn), rowToPixel(this.currentRow),
						colToPixel(this.currentColumn + 1) - colToPixel(this.currentColumn),
						getHeight() - rowToPixel(this.currentRow));

			} else if (mode == Mode.NOTE && noteDuration > 0) {
				g.setColor(Color.red);
				g.fillRect(colToPixel(this.currentColumn), rowToPixel(this.currentRow),
						colToPixel(this.currentColumn + noteDuration) - colToPixel(this.currentColumn),
						rowToPixel(this.currentRow + 1) - rowToPixel(this.currentRow));

			} else if (mode == Mode.COPY) {
				Color transparentYellow = new Color(252, 236, 3, 128);
				// TODO Set the color to a new color for copy selection. To make the background
				// visible
				// behind this rectangle, set the transparency of this color using the fourth
				// argument
				// to the Color constructor, alpha in "new Color(red, green, blue, alpha)".
				// Adjust this
				// transparency value as needed. A good starting point is 100.
				// Draw a rectangle that covers all cells from (copyFromColumn, copyFromRow) to
				// (copyToColumn, copyToRow)
				g.setColor(transparentYellow);
				g.fillRect(colToPixel(this.copyFromColumn), rowToPixel(this.copyFromRow), 
						colToPixel(this.copyToColumn) - colToPixel(this.copyFromColumn), 
						rowToPixel(this.copyToRow) - rowToPixel(this.copyFromRow));
				
				
			}
		}

		// Draw note events
		g.setColor(Color.red);

		for (AudioEvent event : events) {
			if (event instanceof NoteEvent) {
				NoteEvent note = (NoteEvent) event;
				g.fillRect(colToPixel(note.getTime()), rowToPixel(pitchToRow(note.getPitch())),
						colToPixel(note.getTime() + note.getDuration()) - colToPixel(note.getTime()),
						rowToPixel(pitchToRow(note.getPitch())) - rowToPixel(pitchToRow(note.getPitch() + 1)));

			}
		}
	} // end of paintComponent

	@Override
	public void mouseDragged(MouseEvent e) {
		int mouseRow = pixelToRow(e.getY());
		int mouseCol = pixelToCol(e.getX());
		if (drawing) {
			if (mode == Mode.NOTE) {
				noteDuration = mouseCol - this.currentColumn + 1;
				if (noteDuration < 1) noteDuration = 1;
				if (mouseRow != this.currentRow) {
					synth.noteOff(trackNumber, rowToPitch(this.currentRow));
					synth.noteOn(trackNumber, rowToPitch(mouseRow));
					this.currentRow = mouseRow;
				}
			} else if (mode == Mode.VOLUME && mouseRow != this.currentRow) {
				synth.setVolume(trackNumber, rowToVolume(mouseRow));
				synth.noteOn(trackNumber, 60);
				this.currentRow = mouseRow;
			} else if (mode == Mode.COPY) {
				this.copyToRow = mouseRow;
				this.copyToColumn = mouseCol;
			}

			repaint();
		}

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// unused inherited method.
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int mouseRow = pixelToRow(e.getY());
		int mouseCol = pixelToCol(e.getX());

		if (e.getButton() == MouseEvent.BUTTON2) {
			if (mode == Mode.NOTE) {
				for (int index = 0; index < events.size(); index++) {
					if (events.get(index) instanceof NoteEvent) {
						NoteEvent noteEvent = (NoteEvent) events.get(index);
						if (pitchToRow(noteEvent.getPitch()) == mouseRow && noteEvent.getTime() == mouseCol) {
							events.remove(index);
						}
					}

				}
			} else if (mode == Mode.COPY) {
				int timeOffset = mouseCol - this.copyFromColumn;
				int pitchOffset = mouseRow - this.copyFromRow;

				for (int index = 0; index < notesToCopy.size(); index++) {

					NoteEvent copyFromEvent = notesToCopy.get(index);
					int pitch = rowToPitch(pitchToRow(copyFromEvent.getPitch()) + pitchOffset);

					NoteEvent copyToEvent = new NoteEvent(copyFromEvent.getTime() + timeOffset, trackNumber, pitch,
							copyFromEvent.getDuration());
					events.add(copyToEvent);
				}
			}
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.drawing = true;
		if (e.getButton() == MouseEvent.BUTTON1) {
			this.currentRow = pixelToRow(e.getY());
			this.currentColumn = pixelToCol(e.getX());

			if (mode == Mode.NOTE) {
				synth.noteOn(trackNumber, rowToPitch(this.currentRow));
				this.noteDuration = 1;
			} else if (mode == Mode.VOLUME) {
				synth.setVolume(trackNumber, rowToVolume(this.currentRow));
				synth.noteOn(trackNumber, 60);
			} else if (mode == Mode.COPY) {
				this.copyFromRow = this.currentRow;
				this.copyFromColumn = this.currentColumn;

				this.copyToRow = this.currentRow;
				this.copyToColumn = this.currentColumn;
			}
		}
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (drawing) {
			if (mode == Mode.NOTE && noteDuration > 0) {
				synth.noteOff(trackNumber, rowToPitch(this.currentRow));
				events.add(new NoteEvent(this.currentColumn, this.trackNumber, noteDuration, rowToPitch(this.currentRow)));
			} else if (mode == Mode.VOLUME) {
				synth.noteOff(trackNumber, 60);
				events.add(new VolumeEvent(this.currentColumn, trackNumber, rowToVolume(this.currentRow)));
			} else if (mode == Mode.COPY) {
				
				for (AudioEvent event : events) {
					
					if (event instanceof NoteEvent) {
						NoteEvent copyNote = (NoteEvent) event;
						int copyTime = copyNote.getTime();
						int copyPitch = pitchToRow(copyNote.getPitch());
						
						if ((copyTime >= this.copyFromColumn && copyTime <= this.copyToColumn)
								&& (copyPitch >= this.copyFromRow && copyPitch <= this.copyToRow)) {
							notesToCopy.add(copyNote);
							Collections.sort(notesToCopy);
						}
					}
				}
			}
		}
		drawing = false;
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// unused inherited method.
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// unused inherited method.
	}

	//////////////////////////////////////////////////////////////////////
	// Private helper methods
	//////////////////////////////////////////////////////////////////////

	/**
	 * Convert a row index in the grid to a pitch number.
	 * 
	 * @param rowNumber - to convert
	 * @return pitch corresponding to that row
	 */
	private int rowToPitch(int rowNumber) {
		return highestPitch - rowNumber;
	}

	/**
	 * Convert a pitch number to a row index in the grid.
	 * 
	 * @param pitch - to convert
	 * @return row index corresponding to that pitch
	 */
	private int pitchToRow(int pitch) {
		return highestPitch - pitch;
	}

	/**
	 * Convert a row index in the grid to a volume value.
	 * 
	 * @param rowNumber - to convert
	 * @return volume value corresponding to that row
	 */
	private int rowToVolume(int rowNumber) {
		return 127 - rowNumber * 127 / rows;
	}

	/**
	 * Convert a volume value to a row index in the grid.
	 * 
	 * @param volume - to convert
	 * @return row index corresponding to that volume
	 */
	private int volumeToRow(int volume) {
		return rows - volume * rows / 127;
	}

	/**
	 * Converts a row index to pixel y value of the TOP edge of the row.
	 * 
	 * @param row - index
	 * @return pixel y value of the top edge
	 */
	private int rowToPixel(int row) {
		return row * getHeight() / rows;
	}

	/**
	 * Converts a column index to pixel x value of the LEFT edge of the column.
	 * 
	 * @param col - column index
	 * @return pixel x value of the left side
	 */
	private int colToPixel(int col) {
		return col * getWidth() / columns;
	}

	/**
	 * Converts a pixel y value to a row index.
	 * 
	 * @param pixelY - pixel y value
	 * @return index of row containing that pixel
	 */
	private int pixelToRow(int pixelY) {
		return rows * pixelY / getHeight();
	}

	/**
	 * Converts a pixel x value to a column index.
	 * 
	 * @param pixelX - pixel x value
	 * @return index of column containing that pixel
	 */
	private int pixelToCol(int pixelX) {
		return columns * pixelX / getWidth();
	}

	// Required by a serializable class (ignore for now)
	private static final long serialVersionUID = 1L;
}