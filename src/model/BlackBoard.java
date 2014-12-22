package model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class BlackBoard {

	private static final BlackBoard instance = new BlackBoard();
	
	private final Set<Tactic> failedTactics = new HashSet<Tactic>();
	private final Set<Tactic> sucessFullTactics = new HashSet<Tactic>();
	
	public void writeSuccess(final Tactic tactic) {
		tactic.setWinner();
		sucessFullTactics.add(tactic);
		failedTactics.remove(tactic);
	}

	public void writeFailed(final Tactic tactic) {
		sucessFullTactics.remove(tactic);
		failedTactics.add(tactic);
	}
	
	public void delete(final Tactic tactic) {
		failedTactics.remove(tactic);
	}
	
	public boolean failedContains(final Tactic tactic) {
		return failedTactics.contains(tactic);
	}
	
	public Set<Tactic> getTacticsFailed() {
		return Collections.unmodifiableSet(failedTactics);
	}
	
	public Set<Tactic> getSuccessfulTactics() {
		return Collections.unmodifiableSet(sucessFullTactics);
	}
	
	public static BlackBoard getInstance() {
		return instance;
	}

}
