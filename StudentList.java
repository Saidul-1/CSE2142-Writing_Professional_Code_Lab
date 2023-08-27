import java.io.*;
import java.text.*;
import java.util.*;
public class StudentList {
	// global variables
	public static String names[];
	public static String line;
	// reader method
	public static void reader(){
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(Constants.FILE_NAME))); 
			line = reader.readLine();
			names = line.split(Constants.SEPARATOR);			
		} catch (Exception e){} 
	}
	// writer method
	public static void writer(String newName){
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.FILE_NAME, true));
			DateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
			writer.write(Constants.SEPARATOR + newName + "\nList last updated on " + dateFormat.format(new Date()));
			writer.close();
		} catch (Exception e){}
	}
	// main method
	public static void main(String[] args) {
		// check number of arguments
		if(args.length != 1){
			System.out.println(Constants.WRONG_ARGUMENT_COUNT);
		}
		// print all names
		else if(args[0].equals(Constants.ALL_NAMES)) {
			System.out.println(Constants.LOADING);		
			reader();	
			for(String name : names) { 
				System.out.println(name); 
			}
			System.out.println(Constants.LOADED);
		}
		// print a random name
		else if(args[0].equals(Constants.RANDOM_NAME)) {
			System.out.println(Constants.LOADING);			
			reader();
			Random random = new Random();
			System.out.println(names[random.nextInt(names.length)]);
			System.out.println(Constants.LOADED);			
		}
		// add a new name
		else if(args[0].contains(Constants.ADD_NAME)){
			System.out.println(Constants.LOADING);
			writer(args[0].substring(1));
			System.out.println(Constants.LOADED);	
		}
		// search for a name
		else if(args[0].contains(Constants.QUERY)) {
			System.out.println(Constants.LOADING);		
			reader();	
			boolean done = false;
			for(int index = 0; index<names.length && !done; index++) {
				if(names[index].equals(args[0].substring(1))) {
					System.out.println(Constants.FOUND);
					done=true;
				}
			}
			System.out.println(Constants.LOADED);			
		}
		// print the total number of names
		else if(args[0].equals(Constants.COUNT)) {
			System.out.println(Constants.LOADING);	
			reader();
			System.out.println(names.length + " word(s) found ");
			System.out.println(Constants.LOADED);						
		}
		// error handling for invalid argument
		else{
			System.out.println(Constants.INVALID_ARGUMENT);
		}
	}
}
