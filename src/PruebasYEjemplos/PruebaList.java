package PruebasYEjemplos;

import java.util.ArrayList;
import java.util.List;

public class PruebaList {

	public static void main(String[] args) {
		List<guay> al = new ArrayList<guay>();
		al.add(new guay(1, "a"));
		al.add(new guay(3, "c"));
		al.add(new guay(2, "b"));

		for (int i = 0; i < al.size(); i++) {
			System.out.println(al.get(i).toString());
		}
	}
}
