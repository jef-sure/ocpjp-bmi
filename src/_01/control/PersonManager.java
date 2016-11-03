package _01.control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import _00.io.Ausgabe;
import _00.model.Person;
import _01.io.AusgabeInterface;
import _01.io.Eingabe;
import _01.io.PersonSpeicher;
import _01.io.Tastatur;

/**
 * 
 * @author Anton Petrusevich
 * 
 *         Main class to work with persons, BMI, files and so on.
 *
 */

public class PersonManager {
	LinkedList<Person> persons = new LinkedList<Person>();

	public void initPersons() {
		persons.add(new Person("Lemuel", true, 185, 100));
		persons.add(new Person("Ernie", true, 170, 80));
		persons.add(new Person("Jarred", true, 175, 90));
		persons.add(new Person("Bradford", true, 190, 120));
		persons.add(new Person("Emmanuelina", false, 165, 60));
		persons.add(new Person("Wilfredo", true, 180, 95));
		persons.add(new Person("Chuckie", false, 185, 70));
		persons.add(new Person("Wilson", true, 185, 80));
		persons.add(new Person("Rollandina", false, 175, 65));
		persons.add(new Person("Emilina", false, 165, 100));
	}

	private Person findPersonByName(String name) {
		for (Person person : persons) {
			if (person.getName().equalsIgnoreCase(name))
				return person;
		}
		return null;
	}

	public void menu() {

		for (;;) {
			int current_index = persons.size();
			System.out.println("Personen: " + current_index);
			System.out.println("Menu");
			System.out.println("1 - lies Person");
			System.out.println("2 - gib Person aus (Name)");
			System.out.println("3 - gib Person aus (Index)");
			System.out.println("4 - gib alle Person aus");
			System.out.println("5 - gib alle Person aus sortiert nach Name");
			System.out.println("6 - gib alle Person aus sortiert nach BMI");
			System.out.println("7 - speichern aller Personen in csv-Datei");
			System.out.println("8 - einlesen aller Personen aus csv-Datei");
			System.out.println("0 - Ende");
			int choice = Tastatur.inputInteger("Bitte w�hlen: ");
			switch (choice) {
			case 0:
				return;
			case 1:
				persons.add(Eingabe.liesPerson());
				break;
			case 2:
				String name = Tastatur.inputLine("Name: ");
				Person p = findPersonByName(name);
				if (p == null)
					System.out.println("Nicht gefunden");
				else
					System.out.println(p);
				break;
			case 3:
				int index = Tastatur.inputInteger("Index: ");
				if (index < 0 || index >= current_index)
					System.out.println("Index ist falsch");
				else
					System.out.println(persons.get(index));
				break;
			case 4:
				printPersons(new AusgabeInterface() {
					@Override
					public void print(Person person, PrintStream ps) {
						Ausgabe.gibAusPerson(person, ps);
					}
				});
				break;
			case 5:
				printPersonsByName();
				break;
			case 6:
				printPersonsByBMI();
				break;
			case 7:
				saveCSV();
				break;
			case 8:
				loadCSV();
				break;
			default:
				System.out.println("Ung�lige wahl");
			}
		}
	}

	private void printPersonsByBMI() {
		Collections.sort(persons, new Comparator<Person>() {
			@Override
			public int compare(Person a, Person b) {
				return Float.compare(a.getBMI().getBmiWert(), b.getBMI().getBmiWert());
			}
		});
		printPersons(new AusgabeInterface() {
			@Override
			public void print(Person person, PrintStream ps) {
				Ausgabe.gibAusPersonMitGewichtenUndBMI(person, ps);
			}
		});
	}

	private void printPersonsByName() {
		Collections.sort(persons, new Comparator<Person>() {
			@Override
			public int compare(Person a, Person b) {
				return a.getName().compareTo(b.getName());
			}
		});
		printPersons(new AusgabeInterface() {
			@Override
			public void print(Person person, PrintStream ps) {
				Ausgabe.gibAusPersonMitGewichten(person, ps);
			}
		});
	}

	private void saveCSV() {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter("bmi-persons.csv"));
			PersonSpeicher.saveCSV(persons, bw);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (bw != null)
				try {
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	private void loadCSV() {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("bmi-persons.csv"));
			LinkedList<Person> np = PersonSpeicher.loadCSV(br);
			persons.clear();
			persons.addAll(np);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	private void printPersons(AusgabeInterface ai) {
		for (Person person : persons) {
			if (person == null)
				break;
			ai.print(person, System.out);
			System.out.println();
		}
	}
}
