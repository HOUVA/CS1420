package assign12;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

/**
 * This class manages any files created or read by the SoundSketcher application.
 * Includes the ability to read and write .song files, as well as filter out any duplicate events.
 * 
 * @author Matthew Suggars
 * @version 11-28-2025
 */
public class SongFiles {

	public static class ChronologicalOrder implements Comparator<AudioEvent> {
		@Override
		public int compare(AudioEvent o1, AudioEvent o2) {
			if (o1.getTime() != o2.getTime())
				return o1.getTime() - o2.getTime();
			return o1.compareTo(o2);
		}

	}

	/**
	 * This method takes data from Song, and writes to a new .song file containing all of the data for each track and 
	 * AudioEvent.
	 * 
	 * @param file - file to write data from Song
	 * @param song - contains data being written to file
	 */
	public static void writeFile(File file, Song song) {
		FileWriter writer = null;
		ArrayList<AudioEvent> track;
		StringBuilder stringBuilder = new StringBuilder();

		try {
			writer = new FileWriter(file);

			stringBuilder.append(song.getTempo() + "\n");
			stringBuilder.append(song.getSongLength() + "\n");

			for (int index = 0; index < 10; index++) {
				// filtering and sorting an ArrayList to write
				track = filterEvents(song.getTrack(index));
				Collections.sort(track, new SongFiles.ChronologicalOrder());

				stringBuilder.append("Track" + index + "\n");
				stringBuilder.append(index + "\n");
				stringBuilder.append(song.getSynthesizer().getInstrument(index) + "\n");
				stringBuilder.append(track.size() + "\n");

				// data for AudioEvent blocks
				for (AudioEvent event : track) {
					if (event instanceof NoteEvent) {
						NoteEvent noteEvent = (NoteEvent) event;

						stringBuilder.append("note\n");
						stringBuilder.append(noteEvent.getTime() + "\n");
						stringBuilder.append(noteEvent.getDuration() + "\n");
						stringBuilder.append(noteEvent.getPitch() + "\n");

					} else {
						VolumeEvent volumeEvent = (VolumeEvent) event;

						stringBuilder.append("volume\n");
						stringBuilder.append(volumeEvent.getTime() + "\n");
						stringBuilder.append(volumeEvent.getValue() + "\n");
						stringBuilder.append(0 + "\n");
					}

				}

			}
			writer.write(stringBuilder.toString());
			writer.close();
		} catch (IOException e) {
			System.out.println("Unable to locate file");
		}

	}
	
	/**
	 * This method reads data from a .song file, and changes the Song Object with that data.
	 * 
	 * @param file - file to read data from
	 * @param song - Song object to manipulate data, being ready from file.
	 */
	public static void readFile(File file, Song song) {
		Scanner fileInput = null;
		try {
			fileInput = new Scanner(file);

			song.setTempo(fileInput.nextInt());
			song.setSongLength(fileInput.nextInt());

			for (int index = 0; index < 10; index++) {
				fileInput.next(); // track0-9
				fileInput.nextInt(); // trackNumber not needed since it's equal to the index

				int instrumentNum = fileInput.nextInt();
				int eventQuantity = fileInput.nextInt();

				song.getSynthesizer().setInstrument(index, instrumentNum);

				for (int eventIndex = 0; eventIndex < eventQuantity; eventIndex++) {

					String eventType = fileInput.next();
					int eventTime = fileInput.nextInt();

					if (eventType.equals("note")) {
						int eventDuration = fileInput.nextInt();
						int eventPitch = fileInput.nextInt();
						song.addNoteEvent(eventTime, index, eventDuration, eventPitch);

					} else {
						int eventValue = fileInput.nextInt();
						fileInput.nextInt();
						song.addVolumeEvent(eventTime, index, eventValue);
					}
				}
			}

		} catch (FileNotFoundException | InputMismatchException | IllegalStateException e) {
			if (e instanceof FileNotFoundException)
				System.out.println("Unable to locate file");
			else
				System.out.println("There is data in this file that is invalid");
		}
		fileInput.close();

	}

	/**
	 * This method filters any potential duplicate AudioEvents within a Song object.
	 * 
	 * @param events - ArrayList of AudioEvents to check for duplicates.
	 * @return a new ArrayList of AudioEvents containing no duplicates.
	 */
	public static ArrayList<AudioEvent> filterEvents(ArrayList<AudioEvent> events) {
		ArrayList<AudioEvent> filteredEvents = new ArrayList<AudioEvent>();
		HashMap<Integer, ArrayList<AudioEvent>> mappedEvents = new HashMap<>();
		

		for (AudioEvent argEvent : events) {
			Integer ArgEventTime = argEvent.getTime();
			boolean isDuplicate = false;
			if (!(mappedEvents.containsKey(ArgEventTime))) {
				mappedEvents.put(ArgEventTime, new ArrayList<AudioEvent>());
				mappedEvents.get(ArgEventTime).add(argEvent);
				filteredEvents.add(argEvent);
			} else {
				ArrayList<AudioEvent> entry = mappedEvents.get(ArgEventTime);
				for (AudioEvent mappedEvent : entry) {
					if (argEvent instanceof NoteEvent && mappedEvent instanceof NoteEvent) {
						NoteEvent argNoteEvent = (NoteEvent) argEvent;
						NoteEvent mapNoteEvent = (NoteEvent) mappedEvent;
						if (argNoteEvent.getPitch() == mapNoteEvent.getPitch()) {
							isDuplicate = true;
							break;
						}
					}else if (argEvent instanceof VolumeEvent && mappedEvent instanceof VolumeEvent) {
						isDuplicate = true;
						break;
					}
				}
				if (!isDuplicate) {
					mappedEvents.get(ArgEventTime).add(argEvent);
					filteredEvents.add(argEvent);
				}
			}
		}

		Collections.sort(filteredEvents);
		return filteredEvents;
	}

}
