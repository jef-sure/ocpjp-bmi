package _00.io;

import Prog1Tools.IOTools;
import _00.model.Person;

public class Eingabe {
	public static Person liesPerson() {
		String name;
		float gewicht;
		char geschlecht;
		int groesse;
		System.out.println("--- Neue Person ---");
		name = IOTools.readLine("Enter person name: ");
		gewicht = IOTools.readFloat("Enter weight in kg: ");
		groesse = IOTools.readInt("Enter height in cm: ");
		geschlecht = IOTools.readChar("Enter sex (m/f): ");
		return new Person(name, (geschlecht == 'm' || geschlecht == 'M'), groesse, gewicht);
	}
}
