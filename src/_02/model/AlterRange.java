package _02.model;

public enum AlterRange {
	// @formatter:off
	A19T24(19, 24, 19, 24),
	A25T34(25, 34, 20, 25),
	A35T44(35, 44, 21, 26),
	A45T54(45, 54, 22, 27),
	A55T64(55, 64, 23, 28),
	A65T150(65, 150, 24, 29);
	// @formatter:on
	AlterRange(int startAlter, int endAlter, int untergewicht, int uebergewicht) {
		showAlter = startAlter + " - " + endAlter;
		this.startAlter = startAlter;
		this.endAlter = endAlter;
		this.untergewicht = untergewicht;
		this.uebergewicht = uebergewicht;
	}

	@Override
	public String toString() {
		return showAlter;
	}

	public int getEndAlter() {
		return endAlter;
	}

	public int getStartAlter() {
		return startAlter;
	}

	public int getUntergewicht(boolean istMann) {
		return untergewicht + (istMann ? 0 : 1);
	}

	public int getUebergewicht(boolean istMann) {
		return uebergewicht + (istMann ? 0 : 1);
	}

	private final String showAlter;
	private final int startAlter, endAlter;
	private final int untergewicht, uebergewicht;

}
