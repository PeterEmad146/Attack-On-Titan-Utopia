package game.engine.lanes;

import java.util.ArrayList;
import java.util.PriorityQueue;

import game.engine.base.Wall;
import game.engine.titans.Titan;
import game.engine.weapons.PiercingCannon;
import game.engine.weapons.SniperCannon;
import game.engine.weapons.VolleySpreadCannon;
import game.engine.weapons.WallTrap;
import game.engine.weapons.Weapon;

/**
 * 4.12 Milestone 1 -- 2.3 Milestone 2
 * A class representing the Lanes in which a titan walk on to the wall.
 * The class is able to get compared based on it's danger level
 * @author Peter Emad, Mark Fahim, Ahmed Sheta
 *
 */
public class Lane implements Comparable<Lane>{

	// class attributes
	private final Wall laneWall; // a wall object found in the lane.
	private int dangerLevel; // an integer representing the danger level of a lane based on the number danger level of the titans on it
	private final PriorityQueue<Titan> titans; // a queue that stores all the titans in a given lane and is initially empty. Titans closer to the walls have a higher priority.
	private final ArrayList<Weapon> weapons; // a queue that stores all the weapons in a given lane and is initially empty.
	
	//constructors
	public Lane (Wall laneWall) {
		super();
		this.laneWall = laneWall;
		this.dangerLevel = 0;
		this.titans = new PriorityQueue<Titan>();
		this.weapons = new ArrayList<Weapon>();
	}
	
	// methods
	@Override
	public int compareTo(Lane o) {
		return this.dangerLevel - o.dangerLevel;
	}
	
	public Wall getLaneWall() {
		return this.laneWall;
	}
	
	public int getDangerLevel() {
		return this.dangerLevel;
	}
	
	public void setDangerLevel(int dangerLevel) {
		this.dangerLevel = dangerLevel;
	}

	public PriorityQueue<Titan> getTitans() {
		return this.titans;
	}
	
	public ArrayList<Weapon> getWeapons() {
		return this.weapons;
	}
	
	/**
	 * A method that adds the given titan in the lane.
	 * @param titan to be added.
	 */
	public void addTitan (Titan titan) {
		this.titans.add(titan);
	}
	
	/**
	 * A method that adds the given weapon in the lane.
	 * @param weapon to be added
	 */
	public void addWeapon (Weapon weapon) {
		this.weapons.add(weapon);
	}
	
	/**
	 * A method that moves all the titans present in a lane each turn if they have not reached the base/wall yet.
	 */
	public void moveLaneTitans () {
		PriorityQueue<Titan> temp = new PriorityQueue<>();
		while (!this.titans.isEmpty()) {
			Titan t = this.titans.remove();
			t.move();
			temp.add(t);
		}
		while (!temp.isEmpty())
			titans.add(temp.remove());
	}
	
	/**
	 * A method that performs the attacks of all the titans present in the lane that has reached the base/wall.
	 * @return the number of resources gathered.
	 */
	public int performLaneTitansAttacks () {
		int resourcesGained = 0;
		PriorityQueue<Titan> temp = new PriorityQueue<>();
		while(!this.titans.isEmpty()) {
			Titan t = this.titans.remove();
			if(t.hasReachedTarget())
				resourcesGained += t.attack(this.laneWall);
			temp.add(t);
		}
		while(!temp.isEmpty())
			this.titans.add(temp.remove());
		return resourcesGained;
	}
	
	/**
	 * A method that performs the attack of all weapons present in the lane to the titans present in the same lane.
	 * @return the number of resources gathered.
	 */
	public int performLaneWeaponsAttacks () {
		int resourcesGained = 0;
		for(int i = 0; i<this.weapons.size(); i++) {
			Weapon w = this.weapons.get(i);
			if(w instanceof PiercingCannon) {
				PiercingCannon p = (PiercingCannon) w;
				resourcesGained += p.turnAttack(this.titans);
			}
			else if (w instanceof SniperCannon) {
				SniperCannon s = (SniperCannon) w;
				resourcesGained += s.turnAttack(this.titans);
			}
			else if (w instanceof VolleySpreadCannon) {
				VolleySpreadCannon v = (VolleySpreadCannon) w;
				resourcesGained += v.turnAttack(this.titans);
			}
			else {
				WallTrap wt = (WallTrap) w;
				resourcesGained += wt.turnAttack(this.titans);
			}
		}
		return resourcesGained;
	}
	
	/**
	 * A method that checks if the lane's wall is destroyed.
	 * @return
	 */
	public boolean isLaneLost() {
		if(this.laneWall.isDefeated())
			return true;
		return false;
	}
	
	/**
	 * A method that updates the danger level of the lane based on the number of titans present and their danger level.
	 */
	public void updateLaneDangerLevel () {
		int dangerLevelInput = 0;
		PriorityQueue<Titan> temp = new PriorityQueue<>();
		while(!this.titans.isEmpty()) {
			Titan t = this.titans.remove();
			dangerLevelInput += t.getDangerLevel();
			temp.add(t);
		}
		while(!temp.isEmpty())
			this.titans.add(temp.remove());
		this.setDangerLevel(dangerLevelInput);
	}
	
}
