package model;

import java.util.HashSet;
import java.util.Set;

import agents.Creature;
import agents.starAgent.StarCreature;

public class Tactic {

	public static enum Strategy {
		TANKING, FREE_FOR_ALL
	};
	
	private final Set<Creature> creatures;
	private final Strategy strategy;
	
	public Tactic(final Set<Creature> creatures, final Strategy strategy) {
		this.creatures = new HashSet<>(creatures);
		this.strategy = strategy;
	}
	
	@Override
	public int hashCode() {
		return getHashtagAgents() + getStarAgents() * 100 + strategy.ordinal() * 10000;
	}
	
	@Override
	public boolean equals(final Object obj) {
		if(! (obj instanceof Tactic))
			return false;
		final Tactic other = (Tactic) obj;
		return this.getStarAgents() == other.getStarAgents() &&
				this.getHashtagAgents() == other.getHashtagAgents() &&
				this.strategy == other.strategy;
	}
	
	private int getStarAgents() {
		int starCreatures = 0;
		for (Creature c : creatures) {
			if (c instanceof StarCreature)
				starCreatures++;
		}
		return starCreatures;		
	}
	
	private int getHashtagAgents() {
		int hashtagCreatures = 0;
		for (Creature c : creatures) {
			if (c instanceof StarCreature)
				hashtagCreatures++;
		}
		return hashtagCreatures;		
	}

	@Override
	public String toString() {
		StringBuilder ans = new StringBuilder();
		ans.append("HASHTAG: ").append(getHashtagAgents());
		ans.append(" STAR: ").append(getStarAgents());
		ans.append(" STRATEGY").append(strategy.toString());
		return ans.toString();
	}
	
	private int getValue() {
		int value = 0;
		for(final Creature c : creatures)
			value += c.getHealth();
		return value;
	}
	
	private int getSpeed(final Creature creature) {
		if(strategy == Strategy.FREE_FOR_ALL)
			return creature.getMaxSpeed();
		int lowestSpeed = creature.getMaxSpeed();
		for(final Creature c : creatures)
			if(c.getMaxSpeed() < lowestSpeed)
				lowestSpeed = c.getMaxSpeed();
		return lowestSpeed;
	}
	
}
