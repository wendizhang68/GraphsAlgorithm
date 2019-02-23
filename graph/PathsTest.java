package graph;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Unit tests for the ShortestPaths class.
 *
 * @author Wendi Zhang
 */
public class PathsTest {
    private class TestPath extends SimpleShortestPaths {

        private TestPath(Graph G, int source, int dest) {
            super(G, source, dest);
        }

        private TestPath(Graph G, int source) {
            super(G, source, 0);
        }

        @Override
        protected double getWeight(int u, int v) {
            for (int[] e : _edgeWeight.keySet()) {
                if (e[0] == u && e[1] == v) {
                    return _edgeWeight.get(e);
                }
            }
            throw new IllegalArgumentException("does not set weight for this");
        }
    }

    @Test
    public void test1() {
        DirectedGraph a = new DirectedGraph();
        for (int i = 0; i < 7; i++) {
            a.add();
        }
        _edgeWeight = new HashMap<>();
        a.add(1, 2);
        a.add(2, 3);
        a.add(2, 5);
        a.add(2, 6);
        a.add(3, 4);
        a.add(5, 7);
        a.add(6, 5);
        a.add(6, 7);
        TestPath p = new TestPath(a, 1, 7);
        _edgeWeight.put(new int[]{1, 2}, 1.0);
        _edgeWeight.put(new int[]{2, 3}, 1.0);
        _edgeWeight.put(new int[]{3, 4}, 1.0);
        _edgeWeight.put(new int[]{2, 5}, 3.0);
        _edgeWeight.put(new int[]{2, 6}, 8.0);
        _edgeWeight.put(new int[]{5, 7}, 2.0);
        _edgeWeight.put(new int[]{6, 5}, 1.0);
        _edgeWeight.put(new int[]{6, 7}, 1.0);
        p.setPaths();
        List<Integer> path = p.pathTo(7);
        List<Integer> expectedPath = new ArrayList<>();
        expectedPath.add(1);
        expectedPath.add(2);
        expectedPath.add(5);
        expectedPath.add(7);
        assertEquals(expectedPath, path);

    }

    @Test
    public void test2() {
        DirectedGraph a = new DirectedGraph();
        for (int i = 0; i < 7; i++) {
            a.add();
        }
        _edgeWeight = new HashMap<>();
        a.add(1, 2);
        a.add(2, 3);
        a.add(2, 5);
        a.add(2, 6);
        a.add(3, 4);
        a.add(5, 7);
        a.add(6, 5);
        a.add(6, 7);
        TestPath p = new TestPath(a, 1, 6);
        _edgeWeight.put(new int[]{1, 2}, 1.0);
        _edgeWeight.put(new int[]{2, 3}, 1.0);
        _edgeWeight.put(new int[]{3, 4}, 1.0);
        _edgeWeight.put(new int[]{2, 5}, 3.0);
        _edgeWeight.put(new int[]{2, 6}, 8.0);
        _edgeWeight.put(new int[]{5, 7}, 2.0);
        _edgeWeight.put(new int[]{6, 5}, 1.0);
        _edgeWeight.put(new int[]{6, 7}, 1.0);
        p.setPaths();
        List<Integer> path = p.pathTo(6);
        List<Integer> expectedPath = new ArrayList<>();
        expectedPath.add(1);
        expectedPath.add(2);
        expectedPath.add(6);
        assertEquals(expectedPath, path);
    }

    @Test
    public void testDest() {
        DirectedGraph a = new DirectedGraph();
        for (int i = 0; i < 7; i++) {
            a.add();
        }
        _edgeWeight = new HashMap<>();
        a.add(1, 2);
        a.add(2, 3);
        a.add(2, 5);
        a.add(2, 6);
        a.add(3, 4);
        a.add(5, 7);
        a.add(6, 5);
        a.add(6, 7);
        TestPath p = new TestPath(a, 1, 7);
        _edgeWeight.put(new int[]{1, 2}, 1.0);
        _edgeWeight.put(new int[]{2, 3}, 1.0);
        _edgeWeight.put(new int[]{3, 4}, 1.0);
        _edgeWeight.put(new int[]{2, 5}, 3.0);
        _edgeWeight.put(new int[]{2, 6}, 8.0);
        _edgeWeight.put(new int[]{5, 7}, 2.0);
        _edgeWeight.put(new int[]{6, 5}, 1.0);
        _edgeWeight.put(new int[]{6, 7}, 1.0);
        p.setPaths();
        List<Integer> path = p.pathTo(0);
        List<Integer> expectedPath = new ArrayList<>();
        assertEquals(expectedPath, path);
    }

    @Test
    public void testnoDest() {
        DirectedGraph a = new DirectedGraph();
        for (int i = 0; i < 7; i++) {
            a.add();
        }
        _edgeWeight = new HashMap<>();
        a.add(1, 2);
        a.add(2, 3);
        a.add(2, 5);
        a.add(2, 6);
        a.add(3, 4);
        a.add(5, 7);
        a.add(6, 5);
        a.add(6, 7);
        TestPath p = new TestPath(a, 1, 0);
        _edgeWeight.put(new int[]{1, 2}, 1.0);
        _edgeWeight.put(new int[]{2, 3}, 1.0);
        _edgeWeight.put(new int[]{3, 4}, 1.0);
        _edgeWeight.put(new int[]{2, 5}, 3.0);
        _edgeWeight.put(new int[]{2, 6}, 8.0);
        _edgeWeight.put(new int[]{5, 7}, 2.0);
        _edgeWeight.put(new int[]{6, 5}, 1.0);
        _edgeWeight.put(new int[]{6, 7}, 1.0);
        p.setPaths();
        List<Integer> path = p.pathTo(7);
        List<Integer> expectedPath = new ArrayList<>();
        expectedPath.add(1);
        expectedPath.add(2);
        expectedPath.add(5);
        expectedPath.add(7);
        assertEquals(expectedPath, path);
    }


    /**
     * Map edge to edgeweight.
     */
    private HashMap<int[], Double> _edgeWeight;
}
