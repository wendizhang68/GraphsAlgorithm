package graph;

/* See restrictions in Graph.java. */

import java.util.Collections;
import java.util.TreeSet;
import java.util.List;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * The shortest paths through an edge-weighted graph.
 * By overrriding methods getWeight, setWeight, getPredecessor, and
 * setPredecessor, the client can determine how to represent the weighting
 * and the search results.  By overriding estimatedDistance, clients
 * can search for paths to specific destinations using A* search.
 *
 * @author Wendi Zhang
 */
public abstract class ShortestPaths {

    /**
     * The shortest paths in G from SOURCE.
     */
    public ShortestPaths(Graph G, int source) {
        this(G, source, 0);
    }

    /**
     * A shortest path in G from SOURCE to DEST.
     */
    public ShortestPaths(Graph G, int source, int dest) {
        _G = G;
        _source = source;
        _dest = dest;
        _edgeweight = new double[_G.maxVertex()];
        _predecessor = new int[_G.maxVertex()];
        _nodes = new TreeSet<Integer>(new WeightComp());
        _visited = new boolean[_G.maxVertex() + 1];
    }

    /**
     * Max number for infinity.
     */
    static final int MAXNUM = 100000000;

    /**
     * Initialize the shortest paths.  Must be called before using
     * getWeight, getPredecessor, and pathTo.
     */
    public void setPaths() {
        for (int i : _G.vertices()) {
            setWeight(i, MAXNUM);
            setPredecessor(i, 0);
        }
        setWeight(_source, 0);
        setPredecessor(_source, 0);
        _nodes.add(_source);
        while (!_nodes.isEmpty()) {
            int v = _nodes.pollFirst();
            _visited[v] = true;
            if (v == _dest) {
                return;
            }
            Iterator<Integer> vSuccessor = _G.successors(v);
            while (vSuccessor.hasNext()) {
                int n = vSuccessor.next();
                if (!_visited[n]) {
                    double currWeight = getWeight(v);
                    double neighborWeight = getWeight(n);
                    double currToNeighbor = getWeight(v, n);
                    if (currWeight + currToNeighbor
                            < neighborWeight) {
                        setWeight(n, currWeight + currToNeighbor);
                        setPredecessor(n, v);
                        _nodes.add(n);
                    }
                }
            }
        }
    }

    /**
     * Returns the starting vertex.
     */
    public int getSource() {
        return _source;
    }

    /**
     * Returns the target vertex, or 0 if there is none.
     */
    public int getDest() {
        return _dest;
    }

    /**
     * Returns the current weight of vertex V in the graph.  If V is
     * not in the graph, returns positive infinity.
     */
    public abstract double getWeight(int v);

    /**
     * Set getWeight(V) to W. Assumes V is in the graph.
     */
    protected abstract void setWeight(int v, double w);

    /**
     * Returns the current predecessor vertex of vertex V in the graph, or 0 if
     * V is not in the graph or has no predecessor.
     */
    public abstract int getPredecessor(int v);

    /**
     * Set getPredecessor(V) to U.
     */
    protected abstract void setPredecessor(int v, int u);

    /**
     * Returns an estimated heuristic weight of the shortest path from vertex
     * V to the destination vertex (if any).  This is assumed to be less
     * than the actual weight, and is 0 by default.
     */
    protected double estimatedDistance(int v) {
        return 0.0;
    }

    /**
     * Returns the current weight of edge (U, V) in the graph.  If (U, V) is
     * not in the graph, returns positive infinity.
     */
    protected abstract double getWeight(int u, int v);

    /**
     * Returns a list of vertices starting at _source and ending
     * at V that represents a shortest path to V.  Invalid if there is a
     * destination vertex other than V.
     */
    public List<Integer> pathTo(int v) {
        List<Integer> path = new ArrayList<>();
        if (_G.contains(v)) {
            if (_dest == 0 || _dest == v) {
                path.add(v);
                int a = getPredecessor(v);
                path.add(a);
                while (a != getSource()) {
                    a = getPredecessor(a);
                    path.add(a);
                }
            }
            Collections.reverse(path);
        }
        return path;
    }

    /**
     * Returns a list of vertices starting at the source and ending at the
     * destination vertex. Invalid if the destination is not specified.
     */
    public List<Integer> pathTo() {
        return pathTo(getDest());
    }


    /**
     * Class implements comparator to compare weights.
     */
    private class WeightComp implements Comparator<Integer> {
        @Override
        public int compare(Integer node1, Integer node2) {
            if (getWeight(node1) + estimatedDistance(node1)
                    > getWeight(node2) + estimatedDistance(node2)) {
                return 1;
            } else if (getWeight(node1) + estimatedDistance(node1)
                    == getWeight(node2) + estimatedDistance(node2)){
                if (node1 > node2){
                    return 1;
                } else {
                    return -1;
                }
            } else {
                return -1;
            }
        }
    }

    /**
     * The graph being searched.
     */
    protected final Graph _G;
    /**
     * The starting vertex.
     */
    private final int _source;
    /**
     * The target vertex.
     */
    private final int _dest;

    /**
     * Array storing all edgeWeight.
     */
    protected final double[] _edgeweight;

    /**
     * Array of predecessor of each node along the path
     * in the graph.
     */
    protected int[] _predecessor;

    /**
     * TreeSet storing all nodes in the path.
     */
    protected TreeSet<Integer> _nodes;

    /**
     * Array check if the node has been visited.
     */
    protected boolean[] _visited;

}
