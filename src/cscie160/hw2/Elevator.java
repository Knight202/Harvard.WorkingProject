package cscie160.hw2;

/**
 * Elevator Class will simulate the realworld Elevator system. 
 * It has implementation to drop the passengers to their destined floors upon request.
 * 
 * @since - 10/08/2012
 * @author KALPAN
 * @version 2.0
 * 
 */
public class Elevator{
	/**
	 * Maximum Passengers for the Elevator
	 */
	public static final int CAP = 10;
	/**
	 *Maximum floors on the building
	 */
	public static final int TOTALFLOORS = 7;
	/**
	 * Current Floor of the Elevator
	 */
	private int currentFloor;
	/**
	 * Moving Direction of the Elevator
	 */
	private String currentDirection;
	/**
	 * Array of requests for a perticular floor (Y or N)
	 */
	private char destRequest[];
	/**
	 * total passengers boarded on the Elevator
	 */
	private int totalPassengers;
	/**
	 * This array specifies no of passengers destined for a perticular floor
	 */
	private int totalPassDestFloor[];
	/**
	 * It creates an array of Floor objects.
	 */
	private Floor F[]={new Floor(0),new Floor(1), new Floor(2), new Floor(3), new Floor(4), new Floor(5), new Floor(6), new Floor(7)};
	/**
	 * 	No argument constructor.
	 *  It will construct an Elevator at the first floor with no passangers on board and direction set to UP
	 */
	public	Elevator(){						
		currentFloor = 1;
		currentDirection = "UP";
		totalPassengers = 0;
		totalPassDestFloor = new int[8];
		destRequest =  new char[8];
		 
	}
	/**
	 *  This method will move the elevator UP/DOWN each floor.
	 *   If any passengers, destined for perticular floor or if passengers are waiting on floor,It will call stop Method.
	 */
	public void move(){
		if (currentFloor == TOTALFLOORS) currentDirection = "DOWN"; else currentDirection ="UP";		//sets inital direction for the elevator.
		for (int x = 1; x <= TOTALFLOORS; x++){															//This loop will move the elevator through each floor. 
			if (getTotalPassDestFloor()[currentFloor] >0 || destRequest[currentFloor] == 'Y')this.stop(); 	//Checks for the destined passengers or registered request for the current floor.
			if (currentDirection == "UP") currentFloor++; else currentFloor--;							//Moves the elevator up/down based on the direction
		}
		if (currentFloor == 8) currentFloor--;															//Adjusts currentfloor value when elevator-
		if (currentFloor == 0) currentFloor++;															//-is at first or top most floor
	}
	/**
	 * This method will stop the Elevator at current floor.
	 * It will decrease total no of passengers on board and will call unloadPassenger method to unload passengers from elevator to the floor.
	 * Then Prints the status of the Elevator and current Floor
	 */
	public	void stop()
	{									
		System.out.println("Stopping at floor: "+currentFloor);
		totalPassengers = totalPassengers - getTotalPassDestFloor()[currentFloor] ;
		System.out.println("Total passengers before boarding passengers waiting on the floor: " + totalPassengers);		
		F[currentFloor].unloadPassengers(this);												//Calls unloadPassenges method to handle the passengers 
		this.registerRequest();																//registers request for the appropriate flooors
		System.out.println(this);
	//	System.out.println();
		System.out.println(this.F[currentFloor]);
		System.out.println();
	}
	/**
	 * This Overridden method will print the status of the Elevator object.
	 * It will display its current floor, its moving direction and total Passengers on board.
	 */
	public String toString()
	{
		return "Current Floor: " + currentFloor +" Total passangers on board: " + totalPassengers + " Direction: " + currentDirection;
	}	
	/**
	 * This method will board the passengers to the elevator,
	 * by passing the requested destination floor in the argument for each passenger. If elevator is at its full capacity it throws an exception
	 * @param v destination floor - Separate the values by comma for each passenger
	 * @throws ElevatorFullException - When Elevator reaches its max cap it throws this exception
	 */
	public	void boardPassengers(int...v) throws ElevatorFullException{									 
		if (totalPassengers == CAP) throw new ElevatorFullException();		//If passengers tries to board at elevator's full capacity, throws an exception
		totalPassengers = totalPassengers + v.length;
		for (int x:v){								
			getTotalPassDestFloor()[x]=getTotalPassDestFloor()[x]+1;
		}
	}
	/**
	 * This method registers request to the elevator to stop at perticulat floor to board waiting passengers from the floor.
	 * Whenever this method is called it checks each floor for any waiting passengers and registers a request accordigly
	 */
	public void registerRequest(){
		for (Floor x: F)
			if (x.getPassengersWaiting()>0)	destRequest[x.getFloorNumber()] = 'Y';	//Checks for the waiting passengers on each floor and assigns Y or N
			else destRequest[x.getFloorNumber()] = 'N';							// based on that.
	}	
	/**
	 * Executing this main method will create an Elevator object at 1st floor and array of Floor objects.
	 *  Take all the passengers from 1st floor. Move up goingthrough each floor and then comes down.
	 *   It will continue untill all the passengers are released at their requsted destination.
	 */
public static void main(String args[]){
		Elevator E1 = new Elevator();
		E1.F[3].setPassengersWaiting(4);
		E1.F[5].setPassengersWaiting(3);
		E1.F[6].setPassengersWaiting(4);
		
		try {E1.boardPassengers(2,2,3);}
		catch (ElevatorFullException exc){
			System.out.println("ELEVATOR IS AT ITS FULL CAPACITY");
			}
		E1.registerRequest();

		E1.move();
		System.out.println("------------------------------------------------------------------------");
		E1.move();
		System.out.println("------------------------------------------------------------------------");
		E1.move();
		System.out.println("------------------------------------------------------------------------");
		E1.move();
	}

//setter and getter methods for the private variables. 
	/**
	 * @return the currentFloor
	 */
	public int getCurrentFloor() {
		return currentFloor;
	}
	/**
	 * @param currentFloor the currentFloor to set
	 */
	public void setCurrentFloor(int currentFloor) {
		this.currentFloor = currentFloor;
	}
	/**
	 * @return the totalPassDestFloor
	 */
	public int[] getTotalPassDestFloor() {
		return totalPassDestFloor;
	}
	/**
	 * @param totalPassDestFloor the totalPassDestFloor to set
	 */
	public void setTotalPassDestFloor(int totalPassDestFloor[]) {
		this.totalPassDestFloor = totalPassDestFloor;
	}
}

