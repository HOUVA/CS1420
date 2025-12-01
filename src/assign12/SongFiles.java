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
import java.util.Scanner;

public class SongFiles {

	public static class ChronologicalOrder implements Comparator<AudioEvent> {
		@Override
		public int compare(AudioEvent o1, AudioEvent o2) {
			if (o1.getTime() != o2.getTime())
				return o1.getTime() - o2.getTime();
			return o1.compareTo(o2);
		}

	}

	public static void writeFile(File file, Song song) {
		FileWriter writer = null;
		ArrayList<AudioEvent> track;
		StringBuilder stringBuilder = new StringBuilder();
		
		try {
			writer = new FileWriter(file);
			
			writer.write(song.getTempo() + "\n");
			writer.write(song.getSongLength() + "\n");
			
			for (int index = 0; index < 10; index++) {
				track = filterEvents(song.getTrack(index));
				Collections.sort(track, new SongFiles.ChronologicalOrder());
				writer.write("Track" + index + "\n");
				writer.write(index + "\n");
			}

			writer.close();
		} catch (IOException e) {
			System.out.println("Unable to locate file");
		}

	}

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

	public static ArrayList<AudioEvent> filterEvents(ArrayList<AudioEvent> events) {
		ArrayList<AudioEvent> filteredEvents = new ArrayList<AudioEvent>();
		HashMap<Integer, ArrayList<AudioEvent>> mappedEvents = new HashMap<>();

		for (AudioEvent event : events) {
			Integer eventTime = event.getTime();
			if (!(mappedEvents.containsKey(eventTime))) {
				mappedEvents.put(eventTime, new ArrayList<AudioEvent>());
				mappedEvents.get(eventTime).add(event);
				filteredEvents.add(event);
			} else {
				for (AudioEvent timeEvent : mappedEvents.get(eventTime)) {
					if (event instanceof NoteEvent && timeEvent instanceof NoteEvent) {
						NoteEvent argEvent= (NoteEvent) event;
						NoteEvent eventInMap = (NoteEvent) timeEvent;
						if (argEvent.getPitch() != eventInMap.getPitch()
								&& argEvent.getTime() != eventInMap.getTime()) {
							mappedEvents.get(eventTime).add(event);
							filteredEvents.add(event);
						}
					} else if (event instanceof VolumeEvent && timeEvent instanceof VolumeEvent) {
						VolumeEvent inputEvent = (VolumeEvent) event;
						VolumeEvent eventInMap = (VolumeEvent) timeEvent;
						if (inputEvent.getTime() != eventInMap.getTime()) {
							mappedEvents.get(eventTime).add(event);
							filteredEvents.add(event);
						}
					}
				}
			}
		}

		Collections.sort(filteredEvents);
		return filteredEvents;
	}

}
