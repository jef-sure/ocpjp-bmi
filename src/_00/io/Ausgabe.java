package _00.io;

import java.io.PrintStream;
import java.text.NumberFormat;

import _00.model.BMI;
import _00.model.Person;

public class Ausgabe {
	public static void gibAusPerson(Person person, PrintStream out) {
		out.print(person.toString());
	}

	public static void gibAusPersonMitGewichten(Person person, PrintStream pw) {
		String ret = person.toString();
		BMI bmi;
		bmi = person.getBMI();
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(1);
		ret += " normal Gewicht " + nf.format(bmi.getNormalGewicht()) + " kg";
		ret += " ideal Gewicht " + nf.format(bmi.getIdealGewicht()) + " kg";
		pw.print(ret);
	}

	public static void gibAusPersonMitGewichtenUndBMI(Person person, PrintStream pw) {
		BMI bmi = person.getBMI();
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);
		gibAusPersonMitGewichten(person, pw);
		pw.print(" BMI Wert: " + nf.format(bmi.getBmiWert()) + " BMI");
	}

}
