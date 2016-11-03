package _01.io;

import _01.control.PersonManager;

public class Start {

	public static void main(String[] args) {
		PersonManager pm = new PersonManager();
		pm.initPersons();
		pm.menu();
		System.out.println("\nCiao");
	}
}
