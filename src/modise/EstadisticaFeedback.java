package modise;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class EstadisticaFeedback {

	private static double ans;

	public static double mediaPuntuacion(double a, double b) {
		if (b == 0) {
			throw new ArithmeticException("No puedes dividir por cero");
		}
		ans = a / b;
		System.out.println("Media de puntuacion: " + String.format("%.2f", ans) + "/5,00");
		return ans;
	}

	public static /* double */ void siNo(double a, double b, double c) {
		if (c == 0) {
			throw new ArithmeticException("No puedes dividir por cero");
		}
		double ans1 = a / c;
		ans1 = ans1 * 100;
		System.out.println("Si: " + String.format("%.2f", ans1) + " %.");

		double ans2 = b / c;
		ans2 = ans2 * 100;
		System.out.println("No: " + String.format("%.2f", ans2) + " %.");
		/*
		 * ans = a+b; return ans;
		 */
	}

	public static void Read() throws RWException {
		File f1 = new File("Feedback.log"); // Creation of File Descriptor for input file
		String[] words = null; // Intialize the word Array
		FileReader fr;
		try {
			fr = new FileReader(f1);
		 // Creation of File Reader object
		BufferedReader br = new BufferedReader(fr); // Creation of BufferedReader object
		String s;

		String si = "si"; // Input word to be searched
		double countSi = 0;

		String no = "no";
		double countNo = 0;

		double mediaSN = 0;

		String uno = "1";
		String dos = "2";
		String tres = "3";
		String cuatro = "4";
		String cinco = "5";
		double countNum = 0;
		double mediaNum = 0; // Intialize the word to zero

		while ((s = br.readLine()) != null) { // Reading Content from the file
			words = s.split(" "); // Split the word using space
			for (String word : words) {
				// numero
				if (word.equals(uno) || word.equals(dos) || word.equals(tres) || word.equals(cuatro)
						|| word.equals(cinco)) { // Search for the given word
					mediaNum++; // If Present increase the count by one
				}

				if (word.equals(uno)) {
					countNum = countNum + 1;
				} else if (word.equals(dos)) {
					countNum = countNum + 2;
				} else if (word.equals(tres)) {
					countNum = countNum + 3;
				} else if (word.equals(cuatro)) {
					countNum = countNum + 4;
				} else if (word.equals(cinco)) {
					countNum = countNum + 5;
				}

				// si o no
				if (word.equals(si) || word.equals(no)) {
					mediaSN++;
				}

				if (word.equals(si)) {
					countSi++;
				} else if (word.equals(no)) {

					countNo++;
				}
			}
		}

		
		mediaPuntuacion(countNum, mediaNum);
		siNo(countSi, countNo, mediaSN);

		fr.close();
		
	} catch (FileNotFoundException e) {
		throw new RWException ("el archivo no fue encontrado", e);
	} catch (IOException e) {
		throw new RWException ("Error de input/output", e);
	}
	}

	
	  public static void main(String[] args) throws RWException {
		  
		  EstadisticaFeedback.Read(); // aqui o ponemos main en su clase??? AQUI NO?
	  }
	 
}
