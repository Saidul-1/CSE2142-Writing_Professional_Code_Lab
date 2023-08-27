import java.io.*;
import java.text.*;
import java.util.*;
public class StudentList {
	public static String names[];
	public static String line;
	public static void reader(){
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(Constants.FILE_NAME))); 
			line = reader.readLine();
			names = line.split(Constants.SEPARATOR);			
		} catch (Exception e){} 
	}
	public static void writer(String newName){
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.FILE_NAME, true));
			Date datetime = new Date();
			String dateformat = Constants.DATE_FORMAT;
			DateFormat dateFormat = new SimpleDateFormat(dateformat);
			String formatdate = dateFormat.format(datetime);
			writer.write(Constants.SEPARATOR + newName + "\nList last updated on " + formatdate);
			writer.close();
		} catch (Exception e){}
	}
	public static void main(String[] args) {
		if(args.length != 1){
			System.out.println(Constants.WRONG_ARGUMENT_COUNT);
		}
//		Check arguments
		else if(args[0].equals(Constants.ALL_NAMES)) {
			System.out.println(Constants.LOADING);		
			reader();	
			for(String name : names) { 
				System.out.println(name); 
			}
			System.out.println(Constants.LOADED);
		}
		else if(args[0].equals(Constants.RANDOM_NAME)) {
			System.out.println(Constants.LOADING);			
			reader();
			Random random = new Random();
			int randomIndx = random.nextInt(names.length);
			System.out.println(names[randomIndx]);
			System.out.println(Constants.LOADED);			
		}
		else if(args[0].contains(Constants.ADD_NAME)){
			System.out.println(Constants.LOADING);			
			String newName = args[0].substring(1);
			writer(newName);
			System.out.println(Constants.LOADED);	
		}
		else if(args[0].contains(Constants.QUERY)) {
			System.out.println(Constants.LOADING);		
			reader();	
			boolean done = false;
			String query = args[0].substring(1);
			for(int index = 0; index<names.length && !done; index++) {
				if(names[index].equals(query)) {
					System.out.println(Constants.FOUND);
					done=true;
				}
			}
			System.out.println(Constants.LOADED);			
		}
		else if(args[0].contains(Constants.COUNT)) {
			System.out.println(Constants.LOADING);	
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
			System.out.println(Constants.LOADED);						
		}
	}
}
