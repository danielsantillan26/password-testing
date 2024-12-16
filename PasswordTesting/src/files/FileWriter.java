package files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URI;
import java.util.Scanner;

public class FileWriter extends File {

	private Scanner scanner;
	private PrintWriter writer;
	private static final long serialVersionUID = 1L;
	
	public FileWriter(String pathname) {
		super(pathname);
	}
	
	public FileWriter(String parent, String child) {
		super(parent, child);
	}
	
	
	public boolean openForReading() {
		try {
			scanner = new Scanner(this);
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + this.getName());
			return false;
		}
		
		return true;
	}
	
	
	public boolean openForWriting() {
		try {
			writer = new PrintWriter(this);
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + this.getName());
			return false;
		}
		
		return true;
	}
	
	
	public void close() {
		if (scanner != null) { scanner.close(); }
		if (writer != null) { writer.close(); }
	}
	
	
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

	
	
	public boolean writeToFile(String[] contents) {
		if (writer == null) openForWriting();
		if (writer == null) return false;
		for (String s : contents) {
			writer.println(s);
		}
		return true;
	}
}
