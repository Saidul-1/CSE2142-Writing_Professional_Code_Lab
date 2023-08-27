import java.io.*;
import java.text.*;
import java.util.*;
public class StudentList {
	public static void main(String[] args) {
		if(args.length != 1){
			System.out.println("Number of Argument should be 1");
		}
//		Check arguments
		else if(args[0].equals("a")) {
			System.out.println("Loading data ...");			
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("students.txt"))); 
				String line = reader.readLine();
				String names[] = line.split(", ");			
				for(String name : names) { 
					System.out.println(name); 
				}
			} catch (Exception e){} 
			System.out.println("Data Loaded.");
		}
		else if(args[0].equals("r")) {
			System.out.println("Loading data ...");			
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("students.txt"))); 
				String line = reader.readLine();
				String names[] = line.split(", ");	
				Random random = new Random();
				int randomIndx = random.nextInt(names.length);
				System.out.println(names[randomIndx]);
			} catch (Exception e){} 
			System.out.println("Data Loaded.");			
		}
		else if(args[0].contains("+")){
			System.out.println("Loading data ...");			
			try {
				BufferedWriter writter = new BufferedWriter(new FileWriter("students.txt", true));
				String newName = args[0].substring(1);
			    Date datetime = new Date();
			    String dateformat = "dd/mm/yyyy-hh:mm:ss a";
			    DateFormat dateFormat = new SimpleDateFormat(dateformat);
			    String formatdate = dateFormat.format(datetime);
				writter.write(", " + newName + "\nList last updated on " + formatdate);
				writter.close();
			} catch (Exception e){}							
			System.out.println("Data Loaded.");	
		}
		else if(args[0].contains("?")) {
			System.out.println("Loading data ...");			
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("students.txt"))); 
				String line = reader.readLine();
				String names[] = line.split(", ");	
				boolean done = false;
				String query = args[0].substring(1);
				for(int index = 0; index<names.length && !done; index++) {
					if(names[index].equals(query)) {
						System.out.println("We found it!");
						done=true;
					}
				}
			} catch (Exception e){} 
			System.out.println("Data Loaded.");	
		}
		else if(args[0].contains("c")) {
			System.out.println("Loading data ...");			
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("students.txt"))); 
				String line = reader.readLine();
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
			} catch (Exception e){} 
			System.out.println("Data Loaded.");						
		}
	}
}
