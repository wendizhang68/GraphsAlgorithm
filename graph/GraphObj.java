package graph;

/* See restrictions in Graph.java. */

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * A partial implementation of Graph containing elements common to
 * directed and undirected graphs.
 *
 * @author Wendi Zhang
 */
abstract class GraphObj extends Graph {

    /**
     * A new, empty Graph.
     */
    GraphObj() {
        _verticesList = new ArrayList<>();
        _vertices = new ArrayList<>();
        _edgeList = new ArrayList<>();
        _deletevertices = new ArrayList<>();
    }

    @Override
    public int vertexSize() {
        return _vertex;
    }

    @Override
    public int maxVertex() {
        if (_vertices.isEmpty()) {
            return 0;
        }
        return Collections.max(_vertices);
    }

    @Override
    public int edgeSize() {
        int edgeCount = 0;
        for (int[] edge : _edgeList) {
            if (edge != null) {
                edgeCount++;
            }
        }
        return edgeCount;
    }

    @Override
    public abstract boolean isDirected();

    @Override
    public int outDegree(int v) {
        if (contains(v)) {
            return _verticesList.get(v - 1).size();
        }
        return 0;
    }

    @Override
    public abstract int inDegree(int v);

    @Override
    public boolean contains(int u) {
        return _vertices.contains(u);
    }

    @Override
    public boolean contains(int u, int v) {
        if (_verticesList.get(u - 1) != null) {
            return _verticesList.get(u - 1).contains(v);
        }
        return false;
    }

    @Override
    public int add() {
        _verticesList.add(new ArrayList<>());
        if (!_deletevertices.isEmpty()) {
            int addBack = Collections.min(_deletevertices);
            _vertices.add(addBack);
            for (int i = 0; i < _deletevertices.size(); i++) {
                if (_deletevertices.get(i) == addBack) {
                    _deletevertices.remove(i);
                }
            }
            _vertex += 1;
            return addBack;
        } else {
            _vertices.add(vertexSize() + 1);
            _vertex += 1;
        }
        return _vertex;
    }

    @Override
    public int add(int u, int v) {
        checkMyVertex(u);
        checkMyVertex(v);
        int[] edge = new int[]{u, v};
        if (!_verticesList.get(u - 1).contains(v)) {
            if (u == v || isDirected()) {
                _verticesList.get(u - 1).add(v);
            } else {
                _verticesList.get(u - 1).add(v);
                _verticesList.get(v - 1).add(u);
            }
            _edgeList.add(edge);
        }
        return edgeId(u, v);
    }

    @Override
    public void remove(int v) {
        if (contains(v)) {
            for (int k = 0; k < _edgeList.size(); k++) {
                int[] edge = _edgeList.get(k);
                if (edge != null && (edge[0] == v || edge[1] == v)) {
                    _edgeList.set(k, null);
                }
            }
            for (int r = 0; r < _vertices.size(); r++) {
                if (_vertices.get(r) == v) {
                    _vertices.remove(r);
                }
            }
            _deletevertices.add(v);
            if (_verticesList.get(v - 1) != null) {
                _verticesList.get(v - 1).clear();
            }
            for (int i = 0; i < _verticesList.size(); i++) {
                if (_verticesList.get(i) != null) {
                    for (int j = 0; j < _verticesList.get(i).size(); j++) {
                        if (_verticesList.get(i).get(j) == v) {
                            _verticesList.get(i).remove(j);
                        }
                    }
                }
            }
            _vertex--;
        }
    }

    @Override
    public void remove(int u, int v) {
        checkMyVertex(u);
        checkMyVertex(v);
        if (contains(u, v)) {
            _edgeList.set(edgeId(u, v) - 1, null);
            if (u == v || isDirected()) {
                for (int i = 0; i < _verticesList.get(u - 1).size(); i++) {
                    if (_verticesList.get(u - 1).get(i) == v) {
                        _verticesList.get(u - 1).remove(i);
                    }
                }
            } else {
                for (int i = 0; i < _verticesList.get(u - 1).size(); i++) {
                    if (_verticesList.get(u - 1).get(i) == v) {
                        _verticesList.get(u - 1).remove(i);
                    }
                }
                for (int j = 0; j < _verticesList.get(v - 1).size(); j++) {
                    if (_verticesList.get(v - 1).get(j) == u) {
                        _verticesList.get(v - 1).remove(j);
                    }
                }
            }
        }
    }

    @Override
    public Iteration<Integer> vertices() {
        List<Integer> allVertex = new ArrayList<>();
        for (Integer a : _vertices) {
            allVertex.add(a);
        }
        return Iteration.iteration(allVertex.iterator());
    }

    @Override
    public Iteration<Integer> successors(int v) {
        List<Integer> vList = new ArrayList<>();
        if (contains(v)) {
            for (Integer i : _verticesList.get(v - 1)) {
                vList.add(i);
            }
        }
        return Iteration.iteration(vList.iterator());
    }

    @Override
    public abstract Iteration<Integer> predecessors(int v);

    @Override
    public Iteration<int[]> edges() {
        List<int[]> edgeList = new ArrayList<>();
        for (int[] a : _edgeList) {
            if (a != null) {
                edgeList.add(a);
            }
        }
        return Iteration.iteration(edgeList.iterator());
    }

    @Override
    protected void checkMyVertex(int v) {
        if (!contains(v)) {
            throw new IllegalArgumentException("vertex not from Graph");
        }
    }

    @Override
    protected int edgeId(int u, int v) {
        int edgeId = 0;
        if (contains(u, v)) {
            for (int[] a : _edgeList) {
                if (isDirected()) {
                    if (a != null && a[0] == u && a[1] == v) {
                        edgeId = _edgeList.indexOf(a) + 1;
                    }
                } else {
                    if (a != null && ((a[0] == u && a[1] == v)
                            || (a[1] == u && a[0] == v))) {
                        edgeId = _edgeList.indexOf(a) + 1;
                    }
                }
            }
        }
        return edgeId;
    }

    /**
     * Vertex count.
     */
    private int _vertex = 0;

    /**
     * List of list storing all vertices and their going-to vertices.
     */
    private List<List<Integer>> _verticesList;

    /**
     * List storing all edges.
     */
    private List<int[]> _edgeList;

    /**
     * List storing all vertices.
     */
    private List<Integer> _vertices;

    /**
     * List storing all deleted vertices.
     */
    private List<Integer> _deletevertices;

}
