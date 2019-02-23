package make;

import graph.Iteration;

import java.util.ArrayList;
import java.util.List;

import static make.Main.error;

/**
 * Represents the rules concerning a single target in a makefile.
 *
 * @author P. N. Hilfinger
 */

class Rule {
    /**
     * A new Rule for TARGET. Adds corresponding vertex to MAKER's dependence
     * graph.
     */
    Rule(Maker maker, String target) {
        _maker = maker;
        _depends = _maker.getGraph();
        _target = target;
        _vertex = _depends.add(this);
        _time = _maker.getInitialAge(target);
        _finished = false;
    }

    /**
     * Add the target of DEPENDENT to my dependencies.
     */
    void addDependency(Rule dependent) {
        _depends.add(_vertex, dependent.getVertex());
    }

    /**
     * Add COMMANDS to my command set.  Signals IllegalStateException if
     * COMMANDS is non-empty, but I already have a non-empty command set.
     */
    void addCommands(List<String> commands) {
        if (!_commands.isEmpty() && !commands.isEmpty()) {
            throw new IllegalStateException("cannot add non-empty command set");
        } else {
            _commands.addAll(commands);
        }
    }

    /**
     * Return the vertex representing me.
     */
    int getVertex() {
        return _vertex;
    }

    /**
     * Return my target.
     */
    String getTarget() {
        return _target;
    }

    /**
     * Return my target's current change time.
     */
    Integer getTime() {
        return _time;
    }

    /**
     * Return true iff I have not yet been brought up to date.
     */
    boolean isUnfinished() {
        return !_finished;
    }

    /**
     * Check that dependencies are in fact built before it's time to rebuild
     * a node.
     */
    private void checkFinishedDependencies() {
        Iteration<Integer> dependsNode = _depends.successors(_vertex);
        for (Integer i : dependsNode) {
            Rule r = _depends.getLabel(i);
            if (r.isUnfinished()) {
                error("error: dependencies are not built");
            }
        }
    }

    /**
     * Return true iff I am out of date and need to be rebuilt (including the
     * case where I do not exist).  Assumes that my dependencies are all
     * successfully rebuilt.
     */
    private boolean outOfDate() {
        Iteration<Integer> dependsNode = _depends.successors(_vertex);
        if (this.getTime() == null) {
            return true;
        }
        while (dependsNode.hasNext()) {
            Integer next = dependsNode.next();
            Rule r = _depends.getLabel(next);
            if (r.getTime() == null) {
                return true;
            } else if (this.getTime() < r.getTime()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Rebuild me, if needed, after checking that all dependencies are rebuilt
     * (error otherwise).
     */
    void rebuild() {
        checkFinishedDependencies();
        if (outOfDate()) {
            if (_commands.isEmpty()) {
                error("error: %s needs to be rebuilt, but has no commands",
                        _target);
            }
            _time = _maker.getCurrentTime();
            for (String command : _commands) {
                System.out.println(command);
            }
        }
        _finished = true;
    }

    /**
     * The Maker that created me.
     */
    private Maker _maker;
    /**
     * The Maker's dependency graph.
     */
    private Depends _depends;
    /**
     * My target.
     */
    private String _target;
    /**
     * The vertex corresponding to my target.
     */
    private int _vertex;
    /**
     * My command list.
     */
    private ArrayList<String> _commands = new ArrayList<>();
    /**
     * True iff I have been brought up to date.
     */
    private boolean _finished;
    /**
     * My change time, or null if I don't exist.
     */
    private Integer _time;
}
