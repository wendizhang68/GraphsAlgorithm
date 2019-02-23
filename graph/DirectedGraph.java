package graph;

/* See restrictions in Graph.java. */

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a general unlabeled directed graph whose vertices are denoted by
 * positive integers. Graphs may have self edges.
 *
 * @author Wendi Zhang
 */
public class DirectedGraph extends GraphObj {

    @Override
    public boolean isDirected() {
        return true;
    }

    @Override
    public int inDegree(int v) {
        Iteration<Integer> vPredecessors = this.predecessors(v);
        int inDegree = 0;
        while (vPredecessors.hasNext()) {
            vPredecessors.next();
            inDegree++;
        }
        return inDegree;
    }

    @Override
    public Iteration<Integer> predecessors(int v) {
        List<Integer> predecessor = new ArrayList<>();
        Iteration<int[]> edges = this.edges();
        if (contains(v)) {
            for (int[] e : edges) {
                if (e != null && e[1] == v) {
                    predecessor.add(e[0]);
                }
            }
        }
        return Iteration.iteration(predecessor.iterator());
    }

}
