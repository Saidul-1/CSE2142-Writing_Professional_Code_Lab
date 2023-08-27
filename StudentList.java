import java.io.*;
import java.text.*;
import java.util.*;
public class StudentList {
	public static String names[];
	public static String line;
	public static void reader(){
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("students.txt"))); 
			line = reader.readLine();
			names = line.split(", ");			
		} catch (Exception e){} 
	}
	public static void writer(String newName){
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("students.txt", true));
			Date datetime = new Date();
			String dateformat = "dd/mm/yyyy-hh:mm:ss a";
			DateFormat dateFormat = new SimpleDateFormat(dateformat);
			String formatdate = dateFormat.format(datetime);
			writer.write(", " + newName + "\nList last updated on " + formatdate);
			writer.close();
		} catch (Exception e){}
	}
	public static void main(String[] args) {
		if(args.length != 1){
			System.out.println("Number of Argument should be 1");
		}
//		Check arguments
		else if(args[0].equals("a")) {
			System.out.println("Loading data ...");		
			reader();	
			for(String name : names) { 
				System.out.println(name); 
			}
			System.out.println("Data Loaded.");
		}
		else if(args[0].equals("r")) {
			System.out.println("Loading data ...");			
			reader();
			Random random = new Random();
			int randomIndx = random.nextInt(names.length);
			System.out.println(names[randomIndx]);
			System.out.println("Data Loaded.");			
		}
		else if(args[0].contains("+")){
			System.out.println("Loading data ...");			
			String newName = args[0].substring(1);
			writer(newName);
			System.out.println("Data Loaded.");	
		}
		else if(args[0].contains("?")) {
			System.out.println("Loading data ...");		
			reader();	
			boolean done = false;
			String query = args[0].substring(1);
			for(int index = 0; index<names.length && !done; index++) {
				if(names[index].equals(query)) {
					System.out.println("We found it!");
					done=true;
				}
			}
			System.out.println("Data Loaded.");			
		}
		else if(args[0].contains("c")) {
			System.out.println("Loading data ...");	
			reader();
			char characters[] = line.toCharArray();			
			boolean in_word = false;
			int count = 0;
			for(char character : characters) {
				if(character ==' ') {
					if (!in_word) {
						count++; 
						in_word = true;	
					}
					else { 
						in_word = false;
					}			
				}
			}
			System.out.println(count + " word(s) found ");
			System.out.println("Data Loaded.");						
		}
	}
}
