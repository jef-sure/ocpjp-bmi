package _00.control;

import java.util.Arrays;
import java.util.Comparator;

import Prog1Tools.IOTools;
import _00.io.Ausgabe;
import _00.io.Eingabe;
import _00.model.Person;

public class PersonManager {
	private static void initPersons(Person[] persons) {
		persons[0] = new Person("Lemuel", true, 185, 100);
		persons[1] = new Person("Ernie", true, 170, 80);
		persons[2] = new Person("Jarred", true, 175, 90);
		persons[3] = new Person("Bradford", true, 190, 120);
		persons[4] = new Person("Emmanuelina", false, 165, 60);
		persons[5] = new Person("Wilfredo", true, 180, 95);
		persons[6] = new Person("Chuckie", false, 185, 70);
		persons[7] = new Person("Wilson", true, 185, 80);
		persons[8] = new Person("Rollandina", false, 175, 65);
		persons[9] = new Person("Emilina", false, 165, 100);
	}

	private static int filledPersons(Person[] persons) {
		int current_index;
		for (current_index = 0; current_index < persons.length; ++current_index)
			if (persons[current_index] == null)
				break;
		return current_index;
	}

	private static Person findPersonByName(Person[] persons, String name) {
		for (int i = 0; i < persons.length; i++) {
			if (persons[i].getName().equalsIgnoreCase(name)) {
				return persons[i];
			}
		}
		return null;
	}

	private static void menu(Person[] persons) {

		for (;;) {
			int current_index = filledPersons(persons);
			System.out.println("Personen: " + current_index);
			System.out.println("Menu");
			if (current_index != persons.length)
				System.out.println("1 - lies Person");
			System.out.println("2 - gib Person aus (Name)");
			System.out.println("3 - gib Person aus (Index)");
			System.out.println("4 - gib alle Person aus");
			System.out.println("5 - gib alle Person aus sortiert nach Name");
			System.out.println("6 - gib alle Person aus sortiert nach BMI");
			System.out.println("0 - Ende");
			int choice = IOTools.readInt("Bitte w�hlen: ");
			switch (choice) {
			case 0:
				return;
			case 1:
				if (current_index != persons.length)
					persons[current_index] = Eingabe.liesPerson();
				break;
			case 2:
				String name = IOTools.readLine("Name: ");
				Person p = findPersonByName(persons, name);
				if (p == null)
					System.out.println("Nicht gefunden");
				else
					System.out.println(p);
				break;
			case 3:
				int index = IOTools.readInt("Index: ");
				if (index < 0 || index >= current_index)
					System.out.println("Index ist falsch");
				else
					System.out.println(persons[index]);
				break;
			case 4:
				printPersons(persons);
				break;
			case 5:
				Arrays.sort(persons, 0, current_index, new Comparator<Person>() {
					@Override
					public int compare(Person a, Person b) {
						return a.getName().compareTo(b.getName());
					}
				});
				printPersons(persons);
				break;
			case 6:
				Arrays.sort(persons, 0, current_index, new Comparator<Person>() {
					@Override
					public int compare(Person a, Person b) {
						return Float.compare(a.getBMI().getBmiWert(), b.getBMI().getBmiWert());
					}
				});
				printPersons(persons);
				break;
			default:
				System.out.println("Ung�lige wahl");
			}
		}
	}

	private static void printPersons(Person[] persons) {
		for (Person person : persons) {
			if (person == null)
				break;
			Ausgabe.gibAusPerson(person, System.out);
			System.out.println();
		}
	}

	public static void main(String[] args) {
		Person[] persons = new Person[100];
		initPersons(persons);
		menu(persons);
		System.out.println("\nCiao");
	}
}
