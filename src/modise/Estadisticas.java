package modise;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Estadisticas {

	public static void main(String[] args) throws IOException {

		File f1 = new File("Feedback.log"); // Creation of File Descriptor for input file
		String[] words = null; // Intialize the word Array
		FileReader fr = new FileReader(f1); // Creation of File Reader object
		BufferedReader br = new BufferedReader(fr); // Creation of BufferedReader object
		String s;

		String si = "si"; // Input word to be searched
		float countSi = 0;

		String no = "no";
		float countNo = 0;

		float mediaSN = 0;

		String uno = "1";
		String dos = "2";
		String tres = "3";
		String cuatro = "4";
		String cinco = "5";
		float countNum = 0;
		float mediaNum = 0; // Intialize the word to zero

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

		System.out.println("Media de puntuacion: " + countNum / mediaNum + " (sobre 5).");
		System.out.println("Si: " + countSi / mediaSN + " %.");
		System.out.println("No: " + countNo / mediaSN + " %.");

		fr.close();
	}
}
