public class Plane{
	
	private int ID;
	private String name;
	private int remainFuel;
	private int coming_Second;
	private int fuelConsump;
	
	
	
	public Plane(int iD, String name, int remainFuel, int coming_Second,
			int fuelConsump) {
		super();
		ID = iD;
		this.name = name;
		this.remainFuel = remainFuel;
		this.coming_Second = coming_Second;
		this.fuelConsump = fuelConsump;

	}


	public int getFuelConsump() {
		return fuelConsump;
	}


	public void setFuelConsump(int fuelConsump) {
		this.fuelConsump = fuelConsump;
	}

	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRemainFuel() {
		return remainFuel;
	}
	public void setRemainFuel(int remainFuel) {
		this.remainFuel = remainFuel;
	}

	public int getComing_Second() {
		return coming_Second;
	}

	public void setComing_Second(int coming_Second) {
		this.coming_Second = coming_Second;
	}
	
}	
