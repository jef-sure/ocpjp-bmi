package _01.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tastatur {
	public static int inputInteger(String message) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			try {
				System.out.print(message);
				return Integer.parseInt(br.readLine());
			} catch (IOException e) {
				System.err.println("System error!");
				System.exit(-1);
			} catch (NumberFormatException nfe) {
				System.err.println("Invalid Format!");
			}
		}
	}

	public static float inputFloat() {
		return inputInteger("Enter float: ");
	}

	public static float inputFloat(String message) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			try {
				System.out.print(message);
				return Float.parseFloat(br.readLine());
			} catch (IOException e) {
				System.err.println("System error!");
				System.exit(-1);
			} catch (NumberFormatException nfe) {
				System.err.println("Invalid Format!");
			}
		}
	}

	public static int inputInteger() {
		return inputInteger("Enter integer: ");
	}

	public static String inputLine(String message) {
		System.out.print(message);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			return br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}
	public static String inputLine() {
		return inputLine("Enter phrase: ");
	}
	
	public static char inputChar(String message) {
		System.out.print(message);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			int c = br.read();
			if(c == -1)
				throw new IOException("EOF of STDIN");
			return (char)c;
		} catch (IOException e) {
			e.printStackTrace();
			return (char)-1;
		}
	}
	public static char inputChar() {
		return inputChar("Enter phrase: ");
	}
}
