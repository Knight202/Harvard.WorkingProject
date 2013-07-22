package cscie160.hw3;

import java.util.*;

/**
 * Elevator Class will simulate the real world Elevator system. 
 * It has implementation to drop the passengers to their destined floors upon request.
 * 
 * @since - 10/28/2012
 * @author KALPAN
 * @version 3.0
 * 
 */
public class Elevator{

//----------------------------------INSTANCE VARIABLES-------------------------------------------------------------------------
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
	 * Array of requests for a particular floor (Y or N)
	 */
	private char destRequest[];
	/**
	 * total passengers boarded on the Elevator
	 */
	private int totalPassengers;
	/**
	 * This HASHMAP specifies no of passengers destined for every floor
	 */
	private HashMap<Integer, ArrayList<Passenger>> totalPassforEachFloor = new HashMap<Integer, ArrayList<Passenger>>(10);
	/**
	 * It creates an array of Floor objects
	 */
	private Floor F[]={new Floor(0),new Floor(1), new Floor(2), new Floor(3), new Floor(4), new Floor(5), new Floor(6), new Floor(7)};
//-----------------------------------------CONSTRUCTORS---------------------------------------------------------------------------
	/**
	 * 	It will construct an Elevator at the first floor with no passengers on board and direction set to UP
	 */
	public	Elevator(){						
		currentFloor = 1;
		currentDirection = "UP";
		destRequest =  new char[8];
		totalPassengers = 0;
		for (int i=1; i<8;i++){											// Creates a Hash map of Passenger arrays to maintain list
			totalPassforEachFloor.put(i, new ArrayList<Passenger>(10));	//of passengers destined for each floor
		}
	}
//-----------------------------------------METHODS---------------------------------------------------------------------------------
	/**
	 *  This method will move the elevator UP/DOWN each floor.
	 *   If any passengers, destined for particular floor or if passengers are waiting on floor,It will stop the elevator.
	 */
	public void move(){
		if (currentFloor == TOTALFLOORS) setCurrentDirection("DOWN"); else setCurrentDirection("UP");		//sets direction for the elevator.
		for (int x = 1; x <= TOTALFLOORS; x++){															//This loop will move the elevator through each floor. 
			if (totalPassforEachFloor.get(currentFloor).size()>0 || destRequest[currentFloor] == 'Y')this.stop(); 	//Checks for the destined passengers or registered request for the current floor.
			if (getCurrentDirection() == "UP") currentFloor++; else currentFloor--;							//Moves the elevator up/down based on the direction
		}
		if (currentFloor == 8) currentFloor--;															//Adjusts current floor value when elevator-
		if (currentFloor == 0) currentFloor++;															//-is at first or top most floor
	}
	/**
	 * This method will stop the Elevator at current floor.
	 * It will calculate total no of passengers on board and will call unloadPassenger method to unload passengers from elevator to the floor.
	 * Then Prints the status of the Elevator and Floor
	 */
	public	void stop(){									
		System.out.println("Elevator Stopping at floor: "+currentFloor);		
		totalPassengers = totalPassengers - totalPassforEachFloor.get(currentFloor).size() ;//set count for total passengers
		System.out.println("Total passengers on Elevator before boarding passengers waiting on the floor: " + totalPassengers);		
		F[currentFloor].unloadPassengers(this);					//Calls unloadPassenges method to handle the departing passengers 
		this.registerRequest();									//registers request for the appropriate floors to drop the passengers
		System.out.println(this);
		
		System.out.println(this.F[currentFloor]);
		System.out.println();
	}
	/**
	 * This Overridden method will print the status of the Elevator object.
	 * It will display its current floor, its moving direction and total Passengers on board along with their names.
	 */
	public String toString(){
		return 	"Elevator Details: "+"\n"+
				"Current Floor: " + currentFloor +"\n"+
				"Total passengers on board after boarding: " + totalPassengers +"\n"+
				"On Board Passengers "+totalPassforEachFloor.toString() +"\n"+
				"Elevator's Direction: " + getCurrentDirection();
	}	
	/** 
	 * This method will board the passengers to the elevator.
	 * If elevator is at its full capacity it throws an exception
	 * @param p Passenger object
	 * @throws ElevatorFullException - When Elevator reaches its max capacity
	 */
	public	void boardPassengers(Passenger p) throws ElevatorFullException{									 
		if (totalPassengers == CAP) throw new ElevatorFullException();	
		totalPassengers = totalPassengers + 1;					//Increase no of passengers on Elevator
		totalPassforEachFloor.get(p.getDestFloor()).add(p);		//Add the passenger to collection of floor
	}
	/**
	 * This method registers request to the elevator to stop at particular floor to board waiting passengers from the floor.
	 * Whenever this method is called it checks each floor for any waiting passengers and registers a request accordingly
	 */
	public void registerRequest(){
		for (Floor x: F)		//Checks for the waiting passengers on each floor and assigns Y or N based on that.
			if (x.getUPgoingPassengers().size()>0 || x.getDOWNgoingPassengers().size()>0)	destRequest[x.getFloorNumber()] = 'Y';	
			else destRequest[x.getFloorNumber()] = 'N';	
	}	

//----------------------------------------SETTER AND GETTER--------------------------------------------------------------------------
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
	 * @return the totalPassforEachFloor
	 */
	public HashMap<Integer, ArrayList<Passenger>> getTotalPassforEachFloor() {
		return totalPassforEachFloor;
	}
	/**
	 * @param totalPassforEachFloor the totalPassforEachFloor to set
	 */
	public void setTotalPassforEachFloor(HashMap<Integer, ArrayList<Passenger>> totalPassforEachFloor) {
		this.totalPassforEachFloor = totalPassforEachFloor;
	}
	/**
	 * @return the currentDirection
	 */
	public String getCurrentDirection() {
		return currentDirection;
	}
	/**
	 * @param currentDirection the currentDirection to set
	 */
	public void setCurrentDirection(String currentDirection) {
		this.currentDirection = currentDirection;
	}

//------------------------------------------------MAIN METHOD----------------------------------------------------------------------
	/**
	 * Executing this main method will create an Elevator object at 1st floor and array of Floor objects.
	 *  Take all the passengers from 1st floor. Move up going through each floor and then comes down.
	 *   It will continue until all the passengers are released at their requested destination.
	 */
	public static void main(String args[]){
			Elevator E1 = new Elevator();
			Passenger A = new Passenger(1,2,"A");
			Passenger B = new Passenger(1,2,"B");
			Passenger C = new Passenger(1,3,"C");
		
			E1.F[3].getUPgoingPassengers().add(new Passenger(3,5,"D"));
			E1.F[3].getUPgoingPassengers().add(new Passenger(3,4,"E"));
			E1.F[3].getDOWNgoingPassengers().add(new Passenger(3,1,"F"));
			E1.F[3].getDOWNgoingPassengers().add(new Passenger(3,2,"G"));
			
			E1.F[5].getUPgoingPassengers().add(new Passenger(5,7,"H"));
			E1.F[5].getUPgoingPassengers().add(new Passenger(5,7,"I"));
			E1.F[5].getDOWNgoingPassengers().add(new Passenger(5,3,"J"));
			
			E1.F[6].getDOWNgoingPassengers().add(new Passenger(6,1,"K"));
			E1.F[6].getDOWNgoingPassengers().add(new Passenger(6,1,"L"));
			E1.F[6].getDOWNgoingPassengers().add(new Passenger(6,1,"M"));
			E1.F[6].getDOWNgoingPassengers().add(new Passenger(6,1,"N"));
			
			E1.F[7].getDOWNgoingPassengers().add(new Passenger(7,2,"O"));
			E1.F[7].getDOWNgoingPassengers().add(new Passenger(7,2,"P"));
			E1.F[7].getDOWNgoingPassengers().add(new Passenger(7,1,"Q"));
			E1.F[7].getDOWNgoingPassengers().add(new Passenger(7,2,"R"));
			E1.F[7].getDOWNgoingPassengers().add(new Passenger(7,2,"S"));
			
			try {
			E1.boardPassengers(A);
			E1.boardPassengers(B);
			E1.boardPassengers(C);
			}
			catch (ElevatorFullException exc){
				System.out.println("ELEVATOR IS AT ITS FULL CAPACITY");
			}
			
			E1.registerRequest();
			
			E1.move();
			System.out.println("---------------------------------------------------------------------------------------------------------------");
			E1.move();
			System.out.println("---------------------------------------------------------------------------------------------------------------");
			E1.move();
			System.out.println("---------------------------------------------------------------------------------------------------------------");
			E1.move();
		}//Main
		
}//Class
