package PruebasYEjemplos;

//Java program to illustrate Copying the file 
//and deleting the original file 
import java.io.File;

public class FileMover {
	public static void main(String[] args) {
		File file = new File("1.txt");

		// renaming the file and moving it to a new location
		if (file.renameTo(new File("C:\\Users\\Eneko\\Desktop\\dest\\2.txt"))) {
			// if file copied successfully then delete the original file
			file.delete();
			System.out.println("File moved successfully");
		} else {
			System.out.println("Failed to move the file");
		}
	}
}