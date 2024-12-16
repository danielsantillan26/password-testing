package files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * The FileWriter class has methods that work on the file, such as reading and
 * writing. Most of this was taken off Mrs. Kelly's provided code on the Github
 * group, with minor adjustments to fit my preferences.
 * 
 * @author Daniel Santillan
 */
public class FileWriter extends File {

	// Version
	private static final long serialVersionUID = 1L;
	
	// Scanner and writer
	private Scanner scanner;
	private PrintWriter writer;
	
	
	/**
	 * The FileWriter constructor creates a file with the file name, placing
	 * the file in the project folder.
	 * 
	 * @param pathname		file name
	 */
	public FileWriter(String pathname) {
		super(pathname);
	}
	
	
	/**
	 * The openForReading method tries to create a Scanner for the file. If
	 * the file does not exist, the Scanner cannot be created.
	 * 
	 * @return 		whether the Scanner was created
	 */
	public boolean openForReading() {
		try {
			scanner = new Scanner(this);
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + this.getName());
			return false;
		}
		
		return true;
	}
	
	
	/**
	 * The openForWriting method tries to create a PrintWriter for the file. If
	 * the file does not exist, the PrintWriter cannot be created.
	 * 
	 * @return		whether the PrintWriter was created
	 */
	public boolean openForWriting() {
		try {
			writer = new PrintWriter(this);
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + this.getName());
			return false;
		}
		
		return true;
	}
	
	
	/**
	 * The close method closes both the Scanner and the PrintWriter.
	 */
	public void close() {
		if (scanner != null) { scanner.close(); }
		if (writer != null) { writer.close(); }
	}
	
	
	/**
	 * The numLines method uses the Scanner to determine how many lines are
	 * present in the file, helping determine the number of users archived.
	 * 
	 * @return		the number of lines
	 */
	public int numLines() {
		close();
		if (!openForReading()) { return -1; }
		
		int lines = 0;
		while (scanner.hasNext()) {
			scanner.nextLine();
			lines++;
		};
		
		close();
		return lines;
	}
	
	
	/**
	 * The readToArray method reads all of the lines of the users.txt file
	 * into an array that will be used to determine usernames and passwords.
	 * 
	 * @return		String array of every line in users.txt
	 */
	public String[] readToArray() {
		int count = numLines();
		if (! openForReading()) return null;
		if (count < 0) return null;
		String[] lines = new String[count];
		for (int i = 0; i < count; i++) {
			lines[i] = scanner.nextLine();
		}
		return lines;
	}

	
	/**
	 * The writeToFile method prints an input String array (of usernames and
	 * passwords) into the users.txt file.
	 * 
	 * @param contents		String array of contents to print out
	 * @return				whether everything was printed
	 */
	public boolean writeToFile(String[] contents) {
		if (writer == null) openForWriting();
		if (writer == null) return false;
		for (String s : contents) {
			writer.println(s);
		}
		return true;
	}

	
	/**
	 * This is the toString method for this class. It prints the fields.
	 */
	@Override
	public String toString() {
		return "FileWriter [scanner=" + scanner + ", writer=" + writer + "]";
	}
	
	
	
}
