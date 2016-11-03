package _01.io;

import java.io.PrintStream;
import _00.model.Person;

/**
 * 
 * @author Anton Petrusevich
 * 
 * Interface to use for printing persons
 *
 */

public interface AusgabeInterface {
	void print(Person person, PrintStream ps);
}
