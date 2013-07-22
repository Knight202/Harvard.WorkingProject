package cscie160.hw2;
/**
 * This creates a Floor system in the implemented Real World Elevator stimulation system
 * 
 * @since - 10/08/2012
 * @author KALPAN
 * @version 1.0
 * 				
 */
public class Floor {
	/**
	 * This indicates total no of passengers waiting on the floor to reach to any other floor in building
	 */
	private int passengersWaiting;
	/**
	 * This indicates total no of passengers moving on the floor but not waiting.
	 */
	private int onFloorPassengers;
	/**
	 * This represents Floor No. 1 through 7
	 */
	private int floorNumber;
	/**
	 * This will construct a Floor with onFloorPassengers set to 0
	 * @param floorNumber - represents Floor No
	 * @param passengersWaiting - represents total no of witing passengers 
	 */
	public Floor(int floorNumber){
		onFloorPassengers =0;
		this.floorNumber = floorNumber;
		setPassengersWaiting(0);
	}
	/**
	 * This method unloads destined passengers from elevator to the floor.
	 * It then calls boardpassenger method to board the waiting passengers.
	 * If ElevatorFullExcepton is thrown it will stop boarding further processing and remaining passengers will be picked in the next round.
	 * @param e - Represents object instance of class Elevator
	 */
	public void unloadPassengers(Elevator e)
	{
		onFloorPassengers= e.getTotalPassDestFloor()[e.getCurrentFloor()];		//Unloads passengers from the elevator and adds them to floor.
		e.getTotalPassDestFloor()[e.getCurrentFloor()] = 0;						//adjusts no of passengers on elevator for that floor to 0
		try
		{
			while (this.getPassengersWaiting() >0)
			{							//Tries to board all the waiting passengers of the floor to the
			e.boardPassengers(1);										//elevator one by one and if elevator gets full, remaining passengers will
			this.setPassengersWaiting(this.getPassengersWaiting() - 1);									//wait for the next round
			}
		}
		catch (ElevatorFullException exc)
		{
			System.out.println("ELEVATOR IS AT ITS FULL CAPACITY");
		}
	}
	/**
	 * This Overridden method will print the status of the Floor object.
	 * It will display passengers roaming on the floor, its floor number and total Passengers waiting on the floor.
	 */
	public String toString()			
	{
		return "FLOOR NUMBER: " + floorNumber + " Passengers waiting: " + getPassengersWaiting() +" Passengers roaming on the floor: " + onFloorPassengers ;
	}
	
	//setter and getter methods for the private variables. 
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
	 * @return the passengersWaiting
	 */
	public int getPassengersWaiting() {
		return passengersWaiting;
	}
	/**
	 * @param passengersWaiting the passengersWaiting to set
	 */
	public void setPassengersWaiting(int passengersWaiting) {
		this.passengersWaiting = passengersWaiting;
	}
}

