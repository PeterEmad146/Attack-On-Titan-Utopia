package game.engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

import game.engine.base.Wall;
import game.engine.dataloader.DataLoader;
import game.engine.exceptions.InsufficientResourcesException;
import game.engine.exceptions.InvalidLaneException;
import game.engine.lanes.Lane;
import game.engine.titans.Titan;
import game.engine.titans.TitanRegistry;
import game.engine.weapons.Weapon;
import game.engine.weapons.factory.FactoryResponse;
import game.engine.weapons.factory.WeaponFactory;
 
/**
 * 4.19 Milestone 1 -- 2.7 Milestone 2
 * A class representing the Game itself.
 * This class will represent the main engine that manages the flow of the game.
 * @author Peter Emad, Mark Fahim, Ahmed Sheta
 *
 */
public class Battle {

	// class attributes
	private static final int [][] PHASES_APPROACHING_TITANS = {{1, 1, 1, 2, 1, 3, 4}, {2, 2, 2, 1, 3, 3, 4}, {4, 4, 4, 4, 4, 4, 4}}; // a 2D array containing titans' codes, representing the order of titans during each phase.
	private static final int WALL_BASE_HEALTH = 10000; // an integer representing the original health of any walls created throughout the game.
	
	private int numberOfTurns; // an integer representing the number of turns during game play.
	private int resourcesGathered; // an integer representing number of available resources throughout the game.
	private BattlePhase battlePhase; // an enum representing the current phase.
	private int numberOfTitansPerTurn; // an integer representing number of titans in a turn throughout the game. 
	private int score; // an integer representing accumulated score throughout the game.
	private int titanSpawnDistance; // an integer representing the distance a titan would be spawned at.
	
	private final WeaponFactory weaponFactory; // A WeaponFactory object to store the available weapons to be bought. 
	private final HashMap<Integer, TitanRegistry> titansArchives; // A HashMap containing an archive of titans based on their codes. Initially has the data read from the dataloader.
	private final ArrayList<Titan> approachingTitans; // An arraylist representing the coming titans during a turn. Will be treated as a queue (FIFO).
	private final PriorityQueue<Lane> lanes; // a queue containing the active lanes based on their danger level. Least dangerous lanes will have the highest priority.
	private final ArrayList<Lane> originalLanes; // an arraylist containing all the lanes from the start of the game.
	
	// constructors
	/**
	 * Constructor that generates the main battle and initializes it with the parameters and the rest of attributes mentioned above.
	 * @param numberOfTurns
	 * @param score
	 * @param titanSpawnDistance
	 * @param initialNumOfLanes
	 * @param initialResourcesPerLane
	 * @throws IOException
	 */
	public Battle (int numberOfTurns, int score, int titanSpawnDistance, int initialNumOfLanes, int initialResourcesPerLane) throws IOException {
		
		super();
		this.numberOfTurns = numberOfTurns;
		this.resourcesGathered = initialResourcesPerLane*initialNumOfLanes;
		this.battlePhase = BattlePhase.EARLY;
		this.numberOfTitansPerTurn = 1;
		this.score = score;
		this.titanSpawnDistance = titanSpawnDistance;
		
		this.weaponFactory = new WeaponFactory();
		this.titansArchives = DataLoader.readTitanRegistry();
		this.approachingTitans = new ArrayList<Titan>();
		this.lanes = new PriorityQueue<Lane> (); // Create the lanes priority queue to be used in the initializeLanes method.
		this.originalLanes = new ArrayList<Lane> (); // Create the original lanes arraylist to be initialized in the initializeLanes method.
		this.initializeLanes(initialNumOfLanes);
				
	}
	
	// methods
	
	// getters and setters
	public static int[][] getPhasesApproachingTitans() {
		return PHASES_APPROACHING_TITANS;
	}

	public static int getWallBaseHealth() {
		return WALL_BASE_HEALTH;
	}
	
	public int getNumberOfTurns() {
		return numberOfTurns;
	}
	
	public void setNumberOfTurns(int numberOfTurns) {
		this.numberOfTurns = numberOfTurns;
	}
	
	public int getResourcesGathered() {
		return resourcesGathered;
	}
	
	public void setResourcesGathered(int resourcesGathered) {
		this.resourcesGathered = resourcesGathered;
	}
	
	public BattlePhase getBattlePhase() {
		return battlePhase;
	}
	
	public void setBattlePhase(BattlePhase battlePhase) {
		this.battlePhase = battlePhase;
	}
	
	public int getNumberOfTitansPerTurn() {
		return numberOfTitansPerTurn;
	}
	
	public void setNumberOfTitansPerTurn(int numberOfTitansPerTurn) {
		this.numberOfTitansPerTurn = numberOfTitansPerTurn;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getTitanSpawnDistance() {
		return titanSpawnDistance;
	}
	
	public void setTitanSpawnDistance(int titanSpawnDistance) {
		this.titanSpawnDistance = titanSpawnDistance;
	}

	public WeaponFactory getWeaponFactory() {
		return weaponFactory;
	}
	
	public HashMap<Integer, TitanRegistry> getTitansArchives() {
		return titansArchives;
	}
	
	public ArrayList<Titan> getApproachingTitans() {
		return approachingTitans;
	}
	
	public PriorityQueue<Lane> getLanes() {
		return lanes;
	}
	
	public ArrayList<Lane> getOriginalLanes() {
		return originalLanes;
	}
	
	/**
	 * Creates and adds a specified number of lanes to the game.
	 * Each lane is initialized with its own wall having the predefined WALL_BASE_HEALTH.
	 * These lanes are then stored in two different collections within the game, the originalLanes and lanes.
	 * @param numOfLanes
	 */
	private void initializeLanes (int numOfLanes) {
		for(int i = 0; i<numOfLanes; i++) {
			Wall newWall = new Wall(Battle.WALL_BASE_HEALTH);
			Lane newLane = new Lane(newWall);
			this.lanes.add(newLane);
			this.originalLanes.add(newLane);
		}
	}
	
	/**
	 * A method that refills approaching titans based on the codes of titans present in the current phase
	 */
	public void refillApproachingTitans() {
		
		switch (this.battlePhase) {
			case EARLY:
				for(int i = 0; i<Battle.PHASES_APPROACHING_TITANS[0].length; i++) {
					TitanRegistry tr = this.titansArchives.get(Battle.PHASES_APPROACHING_TITANS[0][i]);
					Titan t = tr.spawnTitan(this.titanSpawnDistance);
					this.approachingTitans.add(t);
				}
				break;
			case INTENSE:
				for(int i = 0; i<Battle.PHASES_APPROACHING_TITANS[1].length; i++) {
					TitanRegistry tr = this.titansArchives.get(Battle.PHASES_APPROACHING_TITANS[1][i]);
					Titan t = tr.spawnTitan(this.titanSpawnDistance);
					this.approachingTitans.add(t);
				}
				break;
			case GRUMBLING:
				for(int i = 0; i<Battle.PHASES_APPROACHING_TITANS[2].length; i++) {
					TitanRegistry tr = this.titansArchives.get(Battle.PHASES_APPROACHING_TITANS[2][i]);
					Titan t = tr.spawnTitan(this.titanSpawnDistance);
					this.approachingTitans.add(t);
				}
				break;
			default: break;
		}
	}
	
	/**
	 * A method that deploys the specific weapon indicated by the input code and adds it to the input lane
	 * if it's not lost (Weapons can only be added to active lanes).
	 * Resources should be updated after a successful purchase.
	 * The rest of the turn actions should be performed afterwards.
	 * @param weaponCode of the weapon to be purchased
	 * @param lane to add the weapon in
	 * @throws InsufficientResourcesException
	 * @throws InvalidLaneException
	 */
	public void purchaseWeapon (int weaponCode, Lane lane) throws InsufficientResourcesException, InvalidLaneException{
		if (lane.isLaneLost())
			throw new InvalidLaneException();
		if(!this.lanes.contains(lane))
			throw new InvalidLaneException();
		FactoryResponse fr = this.weaponFactory.buyWeapon(this.getResourcesGathered(), weaponCode);
		int remainingResources = fr.getRemainingResources();
		Weapon w = fr.getWeapon();
		//if this part of code is reached, a successful purchase occurred.
		this.setResourcesGathered(remainingResources);
		lane.addWeapon(w);
		this.performTurn();
	}
	
	/**
	 * A method that skips buying weapon action. The rest of the turn actions should be performed afterwards.
	 */
	public void passTurn () {
		this.performTurn();
	}
	
	/**
	 * A method that adds Titans from approachingTitans to the least dangerous active lane based on the numberOfTitansPerTurn.
	 * If approachingTitans is empty, it should be refilled based on the phase.
	 */
	private void addTurnTitansToLane() {
		// checks if there exists active lanes before adding any titans
		if(!this.lanes.isEmpty()) {
			for(int i = 0; i<this.numberOfTitansPerTurn; i++) {
				if (this.approachingTitans.isEmpty()) {
					this.refillApproachingTitans();
				}
				Titan t = this.approachingTitans.remove(0);
				this.lanes.peek().addTitan(t);
			}
		}
	}
	
	/**
	 * A method that performs the move action for all titans in all of the active lanes present in the game.
	 */
	private void moveTitans() {
		PriorityQueue<Lane> temp = new PriorityQueue<>();
		while (!this.lanes.isEmpty()) {
			Lane l = this.lanes.remove();
			l.moveLaneTitans();
			temp.add(l);
		}
		while(!temp.isEmpty())
			this.lanes.add(temp.remove());
	}
	
	/**
	 * A method that performs weapon attacks for all of the active lanes present in the game.
	 * @return the accumulated resources gathered by all the defeated titans.
	 */
	private int performWeaponsAttacks() {
		int resourcesGained = 0;
		PriorityQueue<Lane> temp = new PriorityQueue<>();
		while(!this.lanes.isEmpty()) {
			Lane l = this.lanes.remove();
			resourcesGained += l.performLaneWeaponsAttacks();
			temp.add(l);
		}
		while(!temp.isEmpty())
			this.lanes.add(temp.remove());
		this.score += resourcesGained;
		this.resourcesGathered += resourcesGained;
		return resourcesGained;
	}
	
	/**
	 * A method that performs titan attacks for all of the lanes present in the game.
	 * @return the attack's result (Wall's resource value in case of its destruction).
	 */
	private int performTitansAttacks() {
		int resourcesGained = 0;
		PriorityQueue<Lane> temp = new PriorityQueue<>();
		while (!this.lanes.isEmpty()) {
			Lane l = this.lanes.remove();
			resourcesGained += l.performLaneTitansAttacks();
			if(!l.isLaneLost())
				temp.add(l);
		}
		while(!temp.isEmpty())
			this.lanes.add(temp.remove());
		return resourcesGained;
	}
	
	/**
	 * A method that updates the danger level of all of the active lanes present in the game.
	 */
	private void updateLanesDangerLevels() {
		PriorityQueue<Lane> temp = new PriorityQueue<>();
		while (!this.lanes.isEmpty()) {
			Lane l = this.lanes.remove();
			if(!l.isLaneLost()) {
				l.updateLaneDangerLevel();
				temp.add(l);
			}
		}
		while(!temp.isEmpty())
			this.lanes.add(temp.remove());
	}
	
	/**
	 * A method whose main functionality is to update the number of turns and switch the phases according to the following table:
	 * --------------------------------------------------------------------------------
	 * |         Number of Turns         |                   Phase                    |
	 * --------------------------------------------------------------------------------
	 * |            Below 15             |                   EARLY                    |
	 * |            Below 30             |                  INTENSE                   |
	 * |      More than or equal 30      |                 GRUMBLING                  |
	 * | More than 30 and divisible by 5 | GRUMBLING and doubles the number of titans |
	 * --------------------------------------------------------------------------------
	 */
	private void finalizeTurns() {
		this.setNumberOfTurns(this.getNumberOfTurns()+1);
		if(this.getNumberOfTurns() < 15) {
			this.setBattlePhase(BattlePhase.EARLY);
		}
		if(this.getNumberOfTurns() >= 15 && this.getNumberOfTurns() < 30) {
			this.setBattlePhase(BattlePhase.INTENSE);
		}
		if (this.getNumberOfTurns() >= 30) {
			this.setBattlePhase(BattlePhase.GRUMBLING);
			if (this.getNumberOfTurns()%5 == 0 && this.getNumberOfTurns() >= 35)
				this.setNumberOfTitansPerTurn(this.getNumberOfTitansPerTurn()*2);
		}
	}
	
	/**
	 * A method that performs the main functionalities throughout each turn. 
	 * The turn cycle encompasses several key actions: titans in all lanes execute movement and attacks, 
	 * weapons in all lanes carry out their attacks, new titans are introduced, the danger level is adjusted,
	 * and the turn is subsequently updated to proceed. Check the game description document for the order of these steps.
	 */
	private void performTurn() {
		this.moveTitans();
		this.performWeaponsAttacks();
		this.performTitansAttacks();
		this.addTurnTitansToLane();
		this.updateLanesDangerLevels();
		this.finalizeTurns();
	}
	
	/**
	 * A method that checks the game over condition of the game.
	 * @return true if game is over (All the lanes are lost) and false otherwise.
	 */
	public boolean isGameOver() {
		if(this.lanes.isEmpty())
			return true;
		return false;
	}
	
}
