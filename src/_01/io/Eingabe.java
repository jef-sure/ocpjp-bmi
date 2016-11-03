package _01.io;

import _00.model.Person;

public class Eingabe {
	public static Person liesPerson() {
		String name;
		float gewicht;
		char geschlecht;
		int groesse;
		System.out.println("--- Neue Person ---");
		name = Tastatur.inputLine("Enter person name: ");
		gewicht = Tastatur.inputFloat("Enter weight in kg: ");
		groesse = Tastatur.inputInteger("Enter height in cm: ");
		geschlecht = Tastatur.inputChar("Enter sex (m/f): ");
		return new Person(name, (geschlecht == 'm' || geschlecht == 'M'), groesse, gewicht);
	}
}
