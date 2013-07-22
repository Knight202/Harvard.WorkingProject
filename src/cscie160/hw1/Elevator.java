package cscie160.hw1;
 
/**
 * Elevator Class will simulate the realworld Elevator system. 
 * It has implementation to drop the passengers to their destined floors upon request.
 * 
 * @since - 09/18/2012
 * @author KALPAN
 * @version 1.0
 * 				
 */
public class Elevator
{	
static final int CAP = 10;							//Maximum Passengers for the Elevator
static final int TOTALFLOORS = 7;					//Maximum floors on the building
	private int currentFloor;						//Current Floor of the Elevator
	private String currentDirection;				//Moving Direction of the Elevator
//	private int destRequest;						//Requests for a perticular floor
	private int totalPassengers;					//total passengers boarded on the Elevator
	private int totalPassDestFloor[] = new int[8];	//This array specifies the 	
/**
 * 	No argument constructor.
 *  It will construct an Elevator at the first floor with no passangers on board and direction set to UP
 */
public	Elevator()
	{						
		currentFloor = 1;
		currentDirection = "UP";
		totalPassengers = 0;
	}
	/**
	 *  This method will move the elevator UP each floor.
	 *   If any passengers, destined for that floor,It will call stop Method.
	 *   After reaching top floor it will return to the first floor.
	 *   Prints status of elevator at each floor
	 */
public	void move()														
	{																
		for (int x = 1; x <= TOTALFLOORS; x++)						//This loop will move the elevator through each floor. 
		{	if (totalPassDestFloor[currentFloor] >0) 	//"IF" condition will check IF there are any passengers destined for the current floor 			
				{stop();
				if (currentFloor == TOTALFLOORS) currentDirection = "DOWN"; else currentDirection ="UP";		//Sets the Elevator direction
				 if (currentDirection == "UP") currentFloor++; else currentFloor--;		//Else moves the elevator to the next floor based on the direction
				}
			else if (currentFloor == TOTALFLOORS)		//"ELSE IF" -means Elevator is on the top floor, but no passengers to drop there.
				{										//	it will change the direction and print its status
				currentDirection ="DOWN";
				}
				else if (currentDirection == "UP") currentFloor++; else currentFloor--;
		
		}
	}
	/**
	 * This method will stop the Elevator at current floor.
	 * It will decrease total no of passengers on board.
	 * Prints the status of the Elevator
	 */
public	void stop()							
	{									
		System.out.println("Stopping at floor: "+currentFloor);
		totalPassengers = totalPassengers - totalPassDestFloor[currentFloor] ;
		totalPassDestFloor[currentFloor] =0;
		System.out.println(this);
		System.out.println();
	}

	/**
	 * This Overridden method will print the status of the Elevator object.
	 * It will display its current floor, its moving direction and total Passengers on board.
	 */
public String toString()			
	{
		return "Current Floor: " + currentFloor +" Total passangers on board: " + totalPassengers;
	}
	
	/**
	 * This method will board the passengers to the elevator.
	 * by passing the requested destination floor in the argument for each passenger
	 * @param v destination floor - Separate the values by comma for each passenger
	 */
public	void boardPassengers(int...v)		
	{									 
	totalPassengers = v.length;	
		/*this loop will create an array such that index of the array will denote the floor
		 * and value of that index will be total no of passengers destined for that floor.
		 */
		  for (int x:v)
		 {								
			  totalPassDestFloor[x]=totalPassDestFloor[x]+1;
		}	
	}
	
	/**
	 * Executing this main method will create an Elevator object at 1st floor. Take all the passengers from 1st floor. Move up going
	 * through each floor and then comes down.
	 * Elevator status will be displayed at each floor
	 */
public static void main(String args[])
	{
		Elevator E1 = new Elevator();
		E1.boardPassengers(2,2,3);		
		System.out.println(E1);
		System.out.println();
		E1.move();
	}
}

