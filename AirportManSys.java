public class AirportManSys{
	
	public static void executionProcess(int line_Counter, Queue<Plane> plane_Queue,
			int exeTime, int fuelConsump, int landTime, int takeOffTime, String queue_Type){
		
		Queue<Plane> landingQueue_1 = new Queue<Plane>();
		Queue<Plane> landingQueue_2 = new Queue<Plane>();
		Queue<Plane> takeOffQueue = new Queue<Plane>();
		Queue<Plane> crashed_PlanesQueue = new Queue<Plane>();
		Runway landingRunway_1 = new Runway(null, landTime);
		Runway landingRunway_2 = new Runway(null, landTime);
		Runway takeOffRunway = new Runway(null, takeOffTime);
		
		int current_Time = 0;
		int timeCounter_Landing = 0;
		int timeCounter_TakeOff = 0;
		int number_LeftPlanes = 0;
		int number_ArrivedPlanes = 0;

		
		
		while(exeTime != current_Time){
			
			settleTakeOffRunway(crashed_PlanesQueue, number_LeftPlanes, takeOffQueue, takeOffRunway, queue_Type, timeCounter_TakeOff, takeOffTime);
			
			settleLandingRunway(crashed_PlanesQueue, number_ArrivedPlanes, timeCounter_Landing, landingQueue_1, landingQueue_2, takeOffQueue, landTime, queue_Type, landingRunway_1, landingRunway_2);
			
			settleLandingQueues(landingQueue_1, landingQueue_2, plane_Queue, exeTime, queue_Type, current_Time);
			
			current_Time++;
			
			if(current_Time == exeTime){
				
				finalize(crashed_PlanesQueue, number_ArrivedPlanes, landingQueue_1, landingQueue_2, takeOffQueue);
			
			}
		
		}
			
			
	}
	
	
	
    public static void update(Queue<Plane> crashed_PlanesQueue, Queue<Plane> landingQueue_1, Queue<Plane> landingQueue_2,
    		Queue<Plane> takeOffQueue) {
    	
    	Queue<Plane> temp_PlaneQueue =  new Queue<Plane>();
    	int temp_Queue_Counter=0;
    	
    	//updating landingQueue_1
    	int counter = landingQueue_1.size();
    	while(counter != 0){
    		Plane temp_Plane_Update = landingQueue_1.dequeue();
    		if(temp_Plane_Update.getRemainFuel() > 0){
    			temp_Plane_Update.setRemainFuel(temp_Plane_Update.getRemainFuel() - temp_Plane_Update.getFuelConsump());
    			temp_PlaneQueue.enqueue(temp_Plane_Update);
    			counter--;
    			temp_Queue_Counter++;
    			temp_Plane_Update = null;
    			//Carbage Collector will clean that space
    		}
    		else{
    			crashed_PlanesQueue.enqueue(temp_Plane_Update);
    			temp_Plane_Update = null;
    		}		
		}
    	while(temp_Queue_Counter != 0){
    		landingQueue_1.enqueue(temp_PlaneQueue.dequeue());
    		temp_Queue_Counter--;
    	}
    	
    	//updating landingQueue_2
    	temp_Queue_Counter = 0;
    	counter = landingQueue_2.size();
    	while(counter != 0){
    		Plane temp_Plane_Update = landingQueue_2.dequeue();
    		if(temp_Plane_Update.getRemainFuel() > 0){
    			temp_Plane_Update.setRemainFuel(temp_Plane_Update.getRemainFuel() - temp_Plane_Update.getFuelConsump());
    			temp_PlaneQueue.enqueue(temp_Plane_Update);
    			counter--;
    			temp_Queue_Counter++;
    			temp_Plane_Update = null;
    			//Carbage Collector will clean that space
    		}
    		else{
    			crashed_PlanesQueue.enqueue(temp_Plane_Update);
    			temp_Plane_Update = null;
    		}		
		}
    	while(temp_Queue_Counter != 0){
    		landingQueue_2.enqueue(temp_PlaneQueue.dequeue());
    		temp_Queue_Counter--;
    	}
    	
    	//updating takeOffQueue
    	temp_Queue_Counter = 0;
    	counter = takeOffQueue.size();
    	while(counter != 0){
    		Plane temp_Plane_Update = takeOffQueue.dequeue();
    		if(temp_Plane_Update.getRemainFuel() > 0){
    			temp_Plane_Update.setRemainFuel(temp_Plane_Update.getRemainFuel() - temp_Plane_Update.getFuelConsump());
    			temp_PlaneQueue.enqueue(temp_Plane_Update);
    			counter--;
    			temp_Queue_Counter++;
    			temp_Plane_Update = null;
    			//Carbage Collector will clean that space
    		}
    		else{
    			crashed_PlanesQueue.enqueue(temp_Plane_Update);
    			temp_Plane_Update = null;
    		}		
		}
    	while(temp_Queue_Counter != 0){
    		takeOffQueue.enqueue(temp_PlaneQueue.dequeue());
    		temp_Queue_Counter--;
    	}
    	
	} 
    
    public static void finalize(Queue<Plane> crashed_PlanesQueue, int number_ArrivedPlanes, Queue<Plane> landingQueue_1, Queue<Plane> landingQueue_2, Queue<Plane> takeOffQueue){
    	System.out.println("Number of planes which are in the landing queue 1 : " + landingQueue_1.size());
    	System.out.println("Number of planes which are in the landing queue 2 : " + landingQueue_2.size());
    	System.out.println("Number of planes which are in the takeoff queue : " + takeOffQueue.size());
    	System.out.println("Total number of planes arrived : " + number_ArrivedPlanes);
    	System.out.print("The number of planes that have crashed : " + crashed_PlanesQueue.size() + ":");
    	int counter_Finalize = crashed_PlanesQueue.size();
    	while(counter_Finalize != 1){
    		System.out.print(crashed_PlanesQueue.dequeue().getName() + ":");
    		counter_Finalize--;
    	}
    	System.out.println(crashed_PlanesQueue.dequeue().getName());
    
    }
	
  
	public static void settleTakeOffRunway(Queue<Plane> crashed_PlanesQueue, int number_LeftPlanes, Queue<Plane> takeOffQueue, Runway takeOffRunway, String queue_Type,
			int timeCounter_TakeOff, int takeOffTime){

		if(queue_Type == "FIFO");{
			
			if(takeOffQueue != null){
				
				if(takeOffRunway.getPlane_On_Runway() == null){
					
					Plane temp_Plane = takeOffQueue.dequeue();
					if(temp_Plane.getRemainFuel() >= (temp_Plane.getFuelConsump() * takeOffTime) ){
						takeOffRunway.setPlane_On_Runway(temp_Plane);
						timeCounter_TakeOff = takeOffTime;
					
					}else{
						crashed_PlanesQueue.enqueue(temp_Plane);
						temp_Plane = null;
						temp_Plane = takeOffQueue.dequeue();
						if(temp_Plane.getRemainFuel() >= (temp_Plane.getFuelConsump() * takeOffTime) ){
							takeOffRunway.setPlane_On_Runway(temp_Plane);
							timeCounter_TakeOff = takeOffTime;
						
						}else{
							crashed_PlanesQueue.enqueue(temp_Plane);
							temp_Plane = null;
							temp_Plane = takeOffQueue.dequeue();
							if(temp_Plane.getRemainFuel() >= (temp_Plane.getFuelConsump() * takeOffTime) ){
								takeOffRunway.setPlane_On_Runway(temp_Plane);
								timeCounter_TakeOff = takeOffTime;
							}else{
								crashed_PlanesQueue.enqueue(temp_Plane);
								temp_Plane = null;
							
							}
						
						}
					
					}
				
				}else{
					
					if(timeCounter_TakeOff == 0){
						System.out.println(takeOffRunway.getPlane_On_Runway().getName() + "had takeoff");
						takeOffRunway.setPlane_On_Runway(null);
						number_LeftPlanes++;
						
					}else{
						
						timeCounter_TakeOff--;
					}
				}
			}
		}
		
	}
	
	public static void settleLandingRunway(Queue<Plane> crashed_PlanesQueue, int number_ArrivedPlanes, int timeCounter_Landing, Queue<Plane> landingQueue_1, Queue<Plane> landingQueue_2,
			Queue<Plane> takeOffQueue, int landTime, String queue_Type,
			Runway landingRunway_1, Runway landingRunway_2){
		
		if(queue_Type == "FIFO"){
			
			if(landingQueue_1 != null){
				
				if(landingRunway_1.getPlane_On_Runway() == null){
					landingRunway_1.setPlane_On_Runway(landingQueue_1.dequeue());
					timeCounter_Landing = landTime; 
				
				}else{
					
					//That's for checking crashed planes
					if(landingRunway_1.getPlane_On_Runway().getRemainFuel() > (landTime * landingRunway_1.getPlane_On_Runway().getFuelConsump()) ){
						
						if(timeCounter_Landing == 0){
							takeOffQueue.enqueue(landingRunway_1.getPlane_On_Runway());
							landingRunway_1.setPlane_On_Runway(null);
							number_ArrivedPlanes++;
						}else{
							
							timeCounter_Landing--;
						}
						
					}else{
						if(timeCounter_Landing == 0){
							crashed_PlanesQueue.enqueue(landingRunway_1.getPlane_On_Runway());
							landingRunway_1.setPlane_On_Runway(null);
						}else{
							timeCounter_Landing--;
						}
						
					}
				}
			}
			
			if(landingRunway_2.getPlane_On_Runway() == null){
				landingRunway_2.setPlane_On_Runway(landingQueue_2.dequeue());
				timeCounter_Landing = landTime; 
			
			}else{
				
				//That's for checking crashed planes
				if(landingRunway_2.getPlane_On_Runway().getRemainFuel() > (landTime * landingRunway_2.getPlane_On_Runway().getFuelConsump()) ){
					if(timeCounter_Landing == 0){
						takeOffQueue.enqueue(landingRunway_2.getPlane_On_Runway());
						landingRunway_2.setPlane_On_Runway(null);
						number_ArrivedPlanes++;
					}else{
						
						timeCounter_Landing--;
					}
					
				}else{
					if(timeCounter_Landing == 0){
						crashed_PlanesQueue.enqueue(landingRunway_2.getPlane_On_Runway());
						landingRunway_2.setPlane_On_Runway(null);
					}else{
						timeCounter_Landing--;
					}
					
				}
			}
		}
		
	}	
	
	public static void settleLandingQueues(Queue<Plane> landingQueue_1, Queue<Plane> landingQueue_2,
			Queue<Plane> plane_Queue, int exeTime, String queue_Type,int current_Time){
		
		
		if(queue_Type == "FIFO"){
			
			Plane temp_Plane1 = plane_Queue.dequeue();
			if(temp_Plane1.getComing_Second() == current_Time){
				landingQueue_1.enqueue(temp_Plane1);
				Plane temp_Plane2 = plane_Queue.dequeue();
				
				if(temp_Plane2.getComing_Second() == current_Time){
					landingQueue_2.enqueue(temp_Plane2);
					Plane temp_Plane3 = plane_Queue.dequeue();
					
					if(temp_Plane3.getComing_Second() == current_Time){
						landingQueue_1.enqueue(temp_Plane3);
						Plane tempPlane_4 = plane_Queue.dequeue();
						
						if(tempPlane_4.getComing_Second() == current_Time){
							landingQueue_2.enqueue(tempPlane_4);
							Plane tempPlane_5 = plane_Queue.dequeue();
							
							if(tempPlane_5.getComing_Second() == current_Time){
								landingQueue_1.enqueue(tempPlane_5);
							}else{
								plane_Queue.enqueue(tempPlane_5);
								tempPlane_5 = null;
							}
							
						}else{
							plane_Queue.enqueue(tempPlane_4);
							tempPlane_4 = null;
						}	
					
					}else{
						plane_Queue.enqueue(temp_Plane3);
						temp_Plane3 = null;
					}	

				}else{
					plane_Queue.enqueue(temp_Plane2);
					temp_Plane2 = null;
				}	
					
			}else{
				plane_Queue.enqueue(temp_Plane1);
				temp_Plane1 = null;
			}	
		}
	
	}
	
	
	//update
	//checking the plane queue
	//checking the runways (takoff - landing) 
	//checking the takeoff queue
	//checking the landing queues
	//finalized function
	//output function

	
	public static void coming_Planes(int line_Counter, Queue<Plane> plane_Queue, int exeTime, int fuelConsump, int landTime, int takeOffTime){
		System.out.println("Hocam FIFO'yu da yazdým fakat kodda olusan hatayý düzeltemediðim icin bu cýktýyý vermek zorunda kaldým.");
		System.out.println();
		System.out.println("Planes are coming!!!");
		System.out.println("There are  " + plane_Queue.size() + "  planes on air.");
		while(line_Counter!=0){
			Plane current_Plane  = null;
			current_Plane = plane_Queue.dequeue();
			System.out.println(current_Plane.getComing_Second() + "  " +
					current_Plane.getName() + "  " + 
					current_Plane.getID() + "  " + 
					current_Plane.getRemainFuel() + "  ");
			line_Counter--;
		}
		
	}
	

}
