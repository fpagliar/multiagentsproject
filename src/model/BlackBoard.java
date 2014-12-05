package model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class BlackBoard {

	private static final BlackBoard instance = new BlackBoard();
	
	private final Set<Tactic> failedTactics = new HashSet<Tactic>();
	
	public void write(final Tactic tactic) {
		failedTactics.add(tactic);
	}
	
	public void delete(final Tactic tactic) {
		failedTactics.remove(tactic);
	}
	
	public boolean contains(final Tactic tactic) {
		return failedTactics.contains(tactic);
	}
	
	public Set<Tactic> getTactics() {
		return Collections.unmodifiableSet(failedTactics);
	}
	
	public static BlackBoard getInstance() {
		return instance;
	}

}
