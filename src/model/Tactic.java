package model;

import java.util.HashSet;
import java.util.Set;

import agents.Creature;
import agents.hashtagCreature.HashtagCreature;
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
		int ans = 0;
		for (final Creature c : creatures) {
			ans += c.hashCode();
		}
		return ans + strategy.ordinal() * 1000;
	}
	
	@Override
	public String toString() {
		StringBuilder ans = new StringBuilder();
		int hashtagCreatures = 0;
		int starCreatures = 0;
		for (Creature c : creatures) {
			if (c instanceof HashtagCreature)
				hashtagCreatures++;
			else if (c instanceof StarCreature)
				starCreatures++;
		}
		ans.append("HASHTAG: ").append(hashtagCreatures);
		ans.append(" STAR: ").append(starCreatures);
		ans.append(" STRATEGY").append(strategy.toString());
		return ans.toString();
	}
	
}
