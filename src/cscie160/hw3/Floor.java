package cscie160.hw3;
import java.util.*;
import cscie160.hw3.ElevatorFullException;
/**
 * This creates a Floor system in the implemented Real World Elevator stimulation system
 * 
 * @since - 10/08/2012
 * @author KALPAN
 * @version 1.0
 * 				
 */
public class Floor {

//----------------------------------INSTANCE VARIABLES-------------------------------------------------------------------------
	/**
	 * This represents Floor No. 1 through 7
	 */
	private int floorNumber;
	/**
	 * Collection of Passengers waiting to go in UP direction with initial capacity of 5
	 */
	private ArrayList<Passenger> UPgoingPassengers = new ArrayList<Passenger>(5);
	/**
	 * Collection of Passengers waiting to go in DOWN direction with initial capacity of 5
	 */
	private ArrayList<Passenger> DOWNgoingPassengers = new ArrayList<Passenger>(5);
	/**
	 * Collection of Passengers roaming on the floor with initial capacity of 10
	 */
	private ArrayList<Passenger> OnFloorPassengers = new ArrayList<Passenger>(10);
//-----------------------------------------CONSTRUCTORS---------------------------------------------------------------------------
	/**
	 * This will construct a Floor with onFloorPassengers set to 0
	 * @param floorNumber - represents Floor No
	 * 
	 */
	public Floor(int floorNumber){
		this.floorNumber = floorNumber;
	}
//-----------------------------------------METHODS---------------------------------------------------------------------------------
	/**
	 * This method unloads destined passengers from elevator to the floor and adds them to Onfloor collection.
	 * It then calls boardpassenger() method to board the passengers, waiting to go in elevator's moving direction. (UP/DOWN)
	 * If ElevatorFullExcepton is thrown it will stop boarding further 
	 * processing and remaining passengers will be picked in the next round.
	 * @param e - Represents object instance of class Elevator
	 */
	public void unloadPassengers(Elevator e){
		OnFloorPassengers.addAll(e.getTotalPassforEachFloor().get(e.getCurrentFloor()));	//Unloads passengers from the
			for (Passenger p : e.getTotalPassforEachFloor().get(e.getCurrentFloor())){		// elevator and adds them to Onfloor collection.
				p.arrive(e.getCurrentFloor());
			}
		e.getTotalPassforEachFloor().get(e.getCurrentFloor()).clear();		//adjusts no of passengers on elevator for that floor to 0
		try{
			while (e.getCurrentDirection() == "UP" && this.getUPgoingPassengers().size() >0){	//Tries to board all the waiting passengers of the floor to the
			e.boardPassengers(UPgoingPassengers.get(0));				//elevator one by one and if elevator gets full, remaining 
			UPgoingPassengers.remove(0);								//passengers will wait for the next round
			}
			while (e.getCurrentDirection() == "DOWN" && this.getDOWNgoingPassengers().size() >0){			//Tries to board all the waiting passengers of the floor to the
				e.boardPassengers(DOWNgoingPassengers.get(0));			//elevator one by one and if elevator gets full, remaining 
				DOWNgoingPassengers.remove(0);							//passengers will wait for the next round
				}
		}
		catch (ElevatorFullException exc){
			System.out.println("ELEVATOR IS AT ITS FULL CAPACITY");
			}
	}
	/**
	 * This Overridden method will print the status of the Floor object.
	 * It will display passengers roaming on the floor, its floor number and total Passengers waiting on the floor along with their names
	 */
	public String toString(){	
		
		int sum = (UPgoingPassengers.size()+DOWNgoingPassengers.size());
		return 	"Floor Details:"+ "\n"+
				"FLOOR NUMBER: " + floorNumber + " Total Passengers waiting after boarding: " + sum + "\n" +
				"Passengers moving on the floor: " + OnFloorPassengers.size() +"\n"+
				"On Floor Passengers : "+OnFloorPassengers;
	}
//----------------------------------------SETTER AND GETTER--------------------------------------------------------------------------
	/**
	 * @return the floorNumber
	 */
	public int getFloorNumber() {
		return floorNumber;
	}
	/**
	 * @param floorNumber the floorNumber to set
	 */
	public void setFloorNumber(int floorNumber) {
		this.floorNumber = floorNumber;
	}
	/**
	 * @return the uPgoingPassengers
	 */
	public ArrayList<Passenger> getUPgoingPassengers() {
		return UPgoingPassengers;
	}
	/**
	 * @param uPgoingPassengers the uPgoingPassengers to set
	 */
	public void setUPgoingPassengers(ArrayList<Passenger> uPgoingPassengers) {
		UPgoingPassengers = uPgoingPassengers;
	}
	/**
	 * @return the dOWNgoingPassengers
	 */
	public ArrayList<Passenger> getDOWNgoingPassengers() {
		return DOWNgoingPassengers;
	}
	/**
	 * @param dOWNgoingPassengers the dOWNgoingPassengers to set
	 */
	public void setDOWNgoingPassengers(ArrayList<Passenger> dOWNgoingPassengers) {
		DOWNgoingPassengers = dOWNgoingPassengers;
	}
}

