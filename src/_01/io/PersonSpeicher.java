package _01.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import _00.model.Person;

public class PersonSpeicher {
	/**
	 * Saves list of person (persons) to CSV-file (bw)
	 * 
	 * @param persons
	 * @param bw
	 */
	public static void saveCSV(List<Person> persons, BufferedWriter bw) {
		Locale ldef = Locale.getDefault(); // save current locale to restore it later
		Locale.setDefault(Locale.US); // to have point as decimal separator
		try {
			for (Person person : persons) {
				String name = person.getName().replaceAll("(:+)", ":$1").replaceAll(",", " : ");
				// every ':' in the name will be prepended with ':', every comma will be replaced with " : "
				bw.write(String.format("%s,%d,%s,%s%n", name, person.getKoerpergroesse(), person.getGewicht(),
						person.istMann()));
				// String.format to have platform-independent CRLF - %n
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Locale.setDefault(ldef);
	}

	/**
	 * Loads list of persons from CSV-file (br)
	 * 
	 * @param br
	 * @return list of persons
	 */
	public static LinkedList<Person> loadCSV(BufferedReader br) {
		LinkedList<Person> ret = new LinkedList<Person>();
		String line;
		Locale ldef = Locale.getDefault(); // save current locale to restore it later
		Locale.setDefault(Locale.US); // to have point as decimal separator
		try {
			while ((line = br.readLine()) != null) {
				String[] csv = line.split(",");
				String name = csv[0].replaceAll(" : ", ",").replaceAll(":(:+)", "$1");
				ret.add(new Person(name, Boolean.parseBoolean(csv[3]), Integer.parseInt(csv[1]),
						Float.parseFloat(csv[2])));
			}
		} catch (NumberFormatException e) {
			System.err.println("Bad file format");
		} catch (IOException e) {
			e.printStackTrace();
		}
		Locale.setDefault(ldef);
		return ret;
	}
}
