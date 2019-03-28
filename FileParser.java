import java.util.Random;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;


public class FileParser {

	public static void ReadingFile(String inputFile, String outputFile, int exeTime, int fuelConsump, int landTime, int takeOffTime, String queue_Type)
	{
		File input = new File(inputFile);
		String[] line = new String[150];
		Scanner scanner = null;
		
		try{
			scanner = new Scanner(input);
			if(scanner.hasNext() == false){
				System.out.println("File is Empty.");
			} else{
				int line_Counter=0;
				while (scanner.hasNextLine()){
					line[line_Counter] = scanner.nextLine();
					line_Counter++;
				}
				ParsingFile(line_Counter, line, inputFile, outputFile, exeTime, fuelConsump, landTime, takeOffTime, queue_Type);
			}	
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} finally {
			scanner.close();
		}
		
	}
	
	public static void ParsingFile(int line_Counter, String[] line, String inputFile, String outputFile, int exeTime, int fuelConsump, int landTime, int takeOffTime, String queue_Type)
	{
		Queue<Plane> plane_Queue = new Queue<Plane>();
		Random randomGenerator = new Random();
		
		for (int i = 0; i < line_Counter; i++) {
			String lineArray[] = line[i].split("\\s+");
			
			int coming_Second = Integer.parseInt(lineArray[0]);
			String name = lineArray[1];
			int randomInt = randomGenerator.nextInt(300);
			int fuel = Integer.parseInt(lineArray[2]);
			Plane plane = new Plane(randomInt, name, fuel, coming_Second, fuelConsump);
			
			plane_Queue.enqueue(plane);
	    }
		
		//AirportManSys.executionProcess(line_Counter, plane_Queue, exeTime, fuelConsump, landTime, takeOffTime, queue_Type);
		
		AirportManSys.coming_Planes(line_Counter, plane_Queue, exeTime, fuelConsump, landTime, takeOffTime);
		
	}

}
