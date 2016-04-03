# CSCIE10b-Harvard


enum kindMainEngine { STEAM_ENGINE, STEAM_TURBINE, 
	GAS_TURBINE, DIESEL, ELECTRIC, WIND, HUMAN_FORCE } ;

/**
 * The Ship class is a basic abstract class to prototype different type of ships
 * 
 * @author 		Kal Stoykov 
 */
public abstract class Ship
{			
	// Private member variables name and year
	private String name;
	private int year;
	protected final kindMainEngine kind;
	/**
	 * 0-parameter constructor for the class Ship, which sets the ENUM values.
	 */
	public kindMainEngine kind() 
	{ 
		return kind; 
	}
	
	/**
	 * 3-argument constructor for the class Ship, which sets the name of the ship,
	 * the year, and the type of the engine.
	 * 
	 * @param       name		The name of the ship
	 * @param		year		The year the ship was built
	 * @param		kind		The type of the main engine the ship is using	
	 */
	public Ship(String name, int year, kindMainEngine kind) 
	{
	     this.name = name;
	     this.year = year;
	     this.kind = kind;
	}
	/**
	 * 0-parameter getter method for the private variable name, from the class Ship.
	 */
	public String getName() 
	{ 
	   return name; 
	}
	/**
	 * 1-argument setter method for the private variable name, from the class Ship
	 * 
	 * @param   name	 The name of the ship.
	 */
	public void setName(String 	name) 
	{ 
		this.name = name; 
	}
	/**
	 * 0-parameter getter method for the private variable year, from the class Ship.
	 */
	public int getYear() 
	{ 
		return year; 
	}
	/**
	 * 1-argument setter method for the private variable year, from the class Ship
	 * 
	 * @param   year	 The year the ship was built.
	 */
	public void setYear(int year) 
	{ 
		this.year = year; 
	}
	/**
	 * This toString() method is used to describe the ship information. 
	 * 
	 * @return    The string representation of information about the ship.
	 */
	public String toString() 
	{ 
		return "(Name: " + getName() + " Kind: " + kind + " Year: " + getYear() + ")"; 
	}
	/**
	 * Main method for the Ship class and its subclasses
	 */
	public static void main (String [] args)
	{		
		  Ship [] ships = { new Cruiseship("HMS Shannon", 1875, kindMainEngine.STEAM_ENGINE, 452, true),
		  					new Cruiseship("Aurora", 1886, kindMainEngine.STEAM_TURBINE, 484, false),
		  					new Cargoship("USNS Alan Shepard", 2006, kindMainEngine.DIESEL, 123),
		  					new Cargoship("USNS Benavidez", 1999, kindMainEngine.DIESEL,  50),
		  					new Tanker("Thomas W. Lawson", 1902, kindMainEngine.STEAM_ENGINE,  13860, 18),
		  					new Tanker("Maersk Peary", 2004, kindMainEngine.ELECTRIC, 25487, 21)
		  };
		  for (int counter = 0; counter < ships.length; counter++)
		  {
			  System.out.println(ships[counter].toString());
		  }
		
	}		
}
/**
 * The Cruiseship class extends the Ship class
 *  
 * @author Kal Stoykov
 */
class Cruiseship extends Ship 
{
	// Private instance variables maxNumPassengers and norovirus
	private int maxNumPassengers;
	private boolean norovirus;
	/**
	 * 5-argument constructor for the class Cruiseship, which sets the name of
	 * the ship, the year, the type of the engine, the max number of passengers
	 * and whether the norovirus is currently infecting passengers.
	 * 
	 * @param   name			The name of the ship
	 * @param	year			The year the ship was built
	 * @param	kind			The type of the main engine the ship is using
	 * @param	maxNumPassengers The maximum number of passengers
	 * @param	norovirus		A Boolean type indicating whether the dreaded 
	 * 	norovirus is currently infecting passengers and causing illness		
	 */
	public Cruiseship(String name, int year, kindMainEngine kind,
			int maxNumPassengers, boolean norovirus) 
	{    
		 // invoke superclass' constructor Ship()
		super(name, year, kind);             
		this.maxNumPassengers = maxNumPassengers;
		this.norovirus = norovirus;
	}
	/**
	 * 0-parameter getter method for the private variable maxNumPassengers, 
	 * from the class Cruiseship.
	 */
	public int getMaxNumPassengers()
	{
		return maxNumPassengers;
	}
	/**
	 * 1-argument setter method for the private variable maxNumPassengers, 
	 * from the class Cruiseship.
	 * 
	 * @param   maxNumPassengers	 The maximum number of passengers.
	 */
	public void setMaxNumPassengers(int maxNumPassengers)
	{
		 this.maxNumPassengers = maxNumPassengers;
	}
	/**
	 * 1-argument constructor for the class Cruiseship, which checks whether
	 * there is a virus on the ship or not.
	 * 
	 * @param   currPassengers	 The number of current passengers on the ship	
	 */
	public boolean virusCheck(int currPassengers) 
	{
		if (currPassengers == maxNumPassengers) 
		{
			return false;
		}
		return true;
	}
	/**
	 * This toString() method overrides the toString() in the Ship class and 
	 * uses it to describe the ship information. 
	 * 
	 * @return    The string representation of information about the ship
	 */
	public String toString() 
	{ 
		return "Cruiseship name: " + getName() + ", Max number of passengers: " + getMaxNumPassengers() + ")"; 
	}
	
}
/**
 * The Cargoship class extends the Ship class
 *  
 * @author Kal Stoykov
 */
class Cargoship extends Ship 
{
	// Private instance variable cargoCapacity
	private double cargoCapacity;

	/**
	 * 4-argument constructor for the class Cargoship, which sets the name of
	 * the ship, the year it was built,the type of the engine and the Cargo capacity.
	 * 
	 * @param   name			The name of the ship
	 * @param	year			The year the ship was built
	 * @param	kind			The type of the main engine the ship is using
	 * @param	cargoCapacity 	The cargo capacity of the ship
	 */
	public Cargoship(String name, int year, kindMainEngine kind, double cargoCapacity) 
	{      
		 // invoke superclass' constructor Ship()
		super(name, year, kind);            
		this.cargoCapacity = cargoCapacity;
	}
	/**
	 * 0-parameter getter method for the private variable cargoCapacity, 
	 * from the class Cargoship.
	 */
	public double getCargoCapacity()
	{
		return cargoCapacity;
	}
	/**
	 * 1-parameter setter method for the private variable cargoCapacity, 
	 * from the class Cargoship.
	 * 
	 * @param   cargoCapacity	 The cargo capacity of the Cargoship.
	 */
	public void setCargoCapacity(int cargoCapacity)
	{
		 this.cargoCapacity = cargoCapacity;
	}
	/**
	 * This toString() method overrides the toString() in the Ship class and 
	 * uses it to describe the ship information. 
	 * 
	 * @return    The string representation of information about the ship.
	 */
	public String toString() 
	{ 
		return "Cargoship name: " + getName() + ", Cargo Capacity: " + getCargoCapacity() + ")"; 
	}
}
/**
 * The Tanker class extends the Ship class
 *  
 * @author Kal Stoykov
 */
class Tanker extends Cargoship  
{
	// Private instance variables tankerCapacity
	private double tankerCapacity;
	private double maxNumPassengers;
	/**
	 * 5-argument constructor for the class Tanker, which sets the name of
	 * the ship, the year it was built,the type of the engine, the Tanker capacity
	 * and the max number of passengers.
	 * 
	 * @param   name			The name of the ship
	 * @param	year			The year the ship was built
	 * @param	kind			The type of the main engine the ship is using
	 * @param	tankerCapacity 	The tanker capacity of the ship
	 * @param	maxNumPassengers The max number of passengers for the ship
	 */
	public Tanker(String name, int year, kindMainEngine kind, double tankerCapacity, double maxNumPassengers) 
	{     
		 // invoke superclass' constructor CargoShip()
		super(name, year, kind, tankerCapacity);             
		this.maxNumPassengers = maxNumPassengers;
		this.tankerCapacity = tankerCapacity;
	}
	/**
	 * 0-parameter getter method for the private variable tankerCapacity, 
	 * from the class Tanker.
	 */
	public double getTankerCapacity()
	{
		return tankerCapacity;
	}
	/**
	 * 1-argument setter method for the private variable tankerCapacity, 
	 * from the class Tanker
	 * 
	 * @param   tankerCapacity	 The capacity of the tanker.
	 */
	public void setTankerCapacity(int tankerCapacity)
	{
		 this.tankerCapacity = tankerCapacity;
	}
	/**
	 * This toString() method overrides the toString() in the Cargoship class and 
	 * uses it to describe the ship information. 
	 * 
	 * @return    The string representation of information about the ship.
	 */	
	public String toString() 
	{ 
		return "Tanker name: " + getName() + ", Tanker Capacity: " + getTankerCapacity() + ")"; 
	}
}
