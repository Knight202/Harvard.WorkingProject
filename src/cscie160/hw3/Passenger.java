package cscie160.hw3;

/**
 * This adds real passengers to the building for the implemented Real World Elevator stimulation system
 * @since - 10/30/2012
 * @author KALPAN
 * @version 1.0
 */
public class Passenger{
//----------------------------------INSTANCE VARIABLES-------------------------------------------------------------------------
	/**
 	* This represents the current floor of the passenger bet. 1 to 7
 	*/
	private int currentFloor;
	/**
	 * This represents Floor where the passenger want to go.
	 */
	private int destFloor;
	/**
	 * This is the name of the passenger.
	 */
	private String name;
//-----------------------------------------CONSTRUCTORS---------------------------------------------------------------------------
	/**
	 * For creating a passenger object.
	 * @param name
	 * @param currentFloor
	 * @param destFloor
	 */
	public Passenger(int currentFloor, int destFloor, String name)
	{
		this.currentFloor = currentFloor;
		this.destFloor = destFloor;
		this.name = name;
	}
//-----------------------------------------METHODS---------------------------------------------------------------------------------
	/**
	 * Arrive method is to change the current floor of the Passenger when it reaches it destination floor.
	 * Changes its destination floor to 0.	
	 * @param destFloor
	 */
	public void arrive(int destFloor)
	{
		currentFloor = destFloor;
		this.destFloor = 0;
	}
	/**
	 * Prints the Name of the passenger.
	 */
	public String toString()
	{
    	return  name ;
    }

	
//----------------------------------------SETTER AND GETTER--------------------------------------------------------------------------
		/**
		 * @return the currentFloor
		 */
		public int getCurrentFloor(){
			return currentFloor;
		}
		/**
		 * @param currentFloor the currentFloor to set
		 */
		public void setCurrentFloor(int currentFloor){
			this.currentFloor = currentFloor;
		}
		/**
		 * @return the destFloor
		 */
		public int getDestFloor(){
			return destFloor;
		}
		/**
		 * @param destFloor the destFloor to set
		 */
		public void setDestFloor(int destFloor){
			this.destFloor = destFloor;
		}		
}
