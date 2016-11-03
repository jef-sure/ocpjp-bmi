package _00.model;

import java.text.NumberFormat;

public class Person {
	private String name;
	private boolean istMann;
	private int koerpergroesse;
	private float gewicht;
	private int filled;
	private BMI bmi;
	public final static int NAME = 8;
	public final static int GESCHLECHT = 4;
	public final static int KOERPERGROESSE = 2;
	public final static int GEWICHT = 1;

	public Person(String name, boolean istMann, int koerpergroesse, float gewicht) {
		this.name = name;
		this.istMann = istMann;
		this.koerpergroesse = koerpergroesse;
		this.gewicht = gewicht;
		filled = NAME | GESCHLECHT | KOERPERGROESSE | GEWICHT;
		bmi = new BMI(koerpergroesse, gewicht, istMann);
	}

	public Person(String name, boolean istMann, int koerpergroesse) {
		this(name, istMann, koerpergroesse, 80);
		filled = NAME | GESCHLECHT | KOERPERGROESSE;
	}

	public Person(String name, boolean istMann) {
		this(name, istMann, 170);
		filled = NAME | GESCHLECHT;
	}

	public Person(String name) {
		this(name, true);
		filled = NAME;
	}

	public Person() {
		this("Martin");
		filled = 0;
	}

	public boolean istMit(int flag) {
		return (filled & flag) == flag;
	}

	public String getName() {
		return name;
	}

	public boolean istMann() {
		return istMann;
	}

	public int getKoerpergroesse() {
		return koerpergroesse;
	}

	public float getGewicht() {
		return gewicht;
	}

	public void setName(String name) {
		this.name = name;
		filled |= NAME;
	}

	public void setMann(boolean istMann) {
		this.istMann = istMann;
		filled |= GESCHLECHT;
		berechneMeinGewicht();
	}

	public void setKoerpergroesse(int koerpergroesse) {
		this.koerpergroesse = koerpergroesse;
		filled |= KOERPERGROESSE;
		berechneMeinGewicht();
	}

	public void setGewicht(float gewicht) {
		this.gewicht = gewicht;
		filled |= GEWICHT;
		berechneMeinGewicht();
	}

	private void berechneMeinGewicht() {
		bmi = new BMI(koerpergroesse, gewicht, istMann);
	}

	public BMI getBMI() {
		return bmi;
	}

	@Override
	public String toString() {
		String ret = name;
		if (istMit(GESCHLECHT))
			ret += " " + (istMann ? " Mann" : "Frau");
		if (istMit(KOERPERGROESSE))
			ret += " " + koerpergroesse + " cm";
		if (istMit(GEWICHT)) {
			NumberFormat nf = NumberFormat.getInstance();
			nf.setMaximumFractionDigits(1);
			ret += " " + nf.format(gewicht) + " kg";
		}
		return ret;
	}
}
