public class Runway {
	
	//Fields
	private Plane plane_On_Runway;
	private int time;

	//Constructer
	public Runway(Plane plane_On_Runway, int time) {
		super();
		this.plane_On_Runway = plane_On_Runway;
		this.time = time;
	}

	//Getter Setter Methods
	public Plane getPlane_On_Runway() {
		return plane_On_Runway;
	}

	public void setPlane_On_Runway(Plane plane_On_Runway) {
		this.plane_On_Runway = plane_On_Runway;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	//public Plane is_Runway_Empty(){
	//	while(time != 0){
	//		if(time == 0){
	//			return plane_On_Runway;
	//			plane_On_Runway = null;
	//		}
			
	//		time--;
	//	}
	//}

}

