
public class Main {

	public static void main(String[] args) {
		
		String inputFile = args[1];
		String outputFile = args[2];
		int exeTime = Integer.parseInt(args[3]);
		int fuelConsump = Integer.parseInt(args[4]);
		int landTime = Integer.parseInt(args[5]);
		int takeOffTime = Integer.parseInt(args[6]);
		String queue_Type = args[7];
		
		FileParser.ReadingFile(inputFile, outputFile, exeTime, fuelConsump, landTime, takeOffTime, queue_Type);

	}

}
