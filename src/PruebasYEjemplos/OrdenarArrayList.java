package PruebasYEjemplos;

import java.util.ArrayList;

public class OrdenarArrayList {

	private static ArrayList<Integer> bucle(ArrayList<Integer> entrada, int a, ArrayList<Integer> salida) {

		ArrayList<Integer> entrada2 = entrada;
		int b = a;

		for (Integer i : entrada2) {
			if (i < b) {
				b = i;
			}
		}

		if (!entrada2.isEmpty()) {

			entrada2.remove(new Integer(b));
			salida.add(b);
			bucle(entrada2, 999, salida);

		}

		return salida;

	}

	private static ArrayList<Integer> OrdenarArrayList(ArrayList<Integer> entrada, Integer integer) {

		ArrayList<Integer> salida2 = new ArrayList<>();

		ArrayList<Integer> salida = bucle(entrada, entrada.get(0), salida2);

		return salida;
	}

	public static void main(String[] args) {

		ArrayList<Integer> salida = new ArrayList<>();

		salida.add(0);
		salida.add(5);
		salida.add(4);
		salida.add(3);
		salida.add(2);
		salida.add(1);

		System.out.println(OrdenarArrayList(salida, salida.get(0)));

	}

}
