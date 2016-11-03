package _00.model;

/**
 * 
 * @author Anton Petrusevich
 * 
 *         Class BMI calculates and stores Body Mass Index
 *
 */

public class BMI {
	private float normalGewicht;
	private float idealGewicht;
	private float bmiWert;
	private int koerperGroesse;
	private float actualGewicht;
	private boolean istMann;
	private byte filled;
	public final static int GESCHLECHT = 4;
	public final static int KOERPERGROESSE = 2;
	public final static int GEWICHT = 1;

	private void calculate() {
		if (filled != (GESCHLECHT | KOERPERGROESSE | GEWICHT))
			return;
		normalGewicht = (float) koerperGroesse - 100.0f;
		if (normalGewicht < 0)
			normalGewicht = 0;
		idealGewicht = normalGewicht * (istMann ? 0.9f : 0.85f);
		float kginm = koerperGroesse / 100.0f;
		if (kginm == 0)
			kginm = 0.000001f;
		bmiWert = (float) (actualGewicht / Math.pow(kginm, 2));

	}

	public void setKoerperGroesse(int koerperGroesse) {
		this.koerperGroesse = koerperGroesse;
		filled |= KOERPERGROESSE;
		calculate();
	}

	public void setActualGewicht(float actualGewicht) {
		this.actualGewicht = actualGewicht;
		filled |= GEWICHT;
		calculate();
	}

	public void setMann(boolean istMann) {
		this.istMann = istMann;
		filled |= GESCHLECHT;
		calculate();
	}

	public boolean istMann() {
		return istMann;
	}

	public BMI() {
		normalGewicht = idealGewicht = bmiWert = 0;
		filled = 0;
	}

	public BMI(int koerperGroesse, float actualGewicht, boolean istMann) {
		this.koerperGroesse = koerperGroesse;
		this.actualGewicht = actualGewicht;
		this.istMann = istMann;
		filled = GESCHLECHT | KOERPERGROESSE | GEWICHT;
		calculate();
	}

	public float getNormalGewicht() {
		return normalGewicht;
	}

	public float getIdealGewicht() {
		return idealGewicht;
	}

	public float getBmiWert() {
		return bmiWert;
	}

}
