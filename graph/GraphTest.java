package graph;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for the Graph class.
 *
 * @author Wendi Zhang
 */
public class GraphTest {

    @Test
    public void emptyGraph() {
        DirectedGraph g = new DirectedGraph();
        assertEquals(0, g.maxVertex());
        assertEquals("Initial graph has vertices", 0, g.vertexSize());
        assertEquals("Initial graph has edges", 0, g.edgeSize());
    }

    @Test
    public void testBasicVertices() {
        DirectedGraph d = new DirectedGraph();
        d.add();
        d.add();
        assertEquals(2, d.vertexSize());
        assertEquals(true, d.contains(2));
        assertEquals(false, d.contains(3));
        d.add();
        d.add();
        assertEquals(4, d.vertexSize());
        assertEquals(4, d.maxVertex());
    }

    @Test
    public void testRemoveVertices() {
        DirectedGraph a = new DirectedGraph();
        a.add();
        a.add();
        a.add();
        a.add();
        a.add();
        assertEquals(6, a.add());
        a.remove(2);
        a.remove(4);
        assertEquals(4, a.vertexSize());
        assertEquals(6, a.maxVertex());
    }

    @Test
    public void testBasicEdges() {
        DirectedGraph b = new DirectedGraph();
        for (int i = 0; i < 8; i++) {
            b.add();
        }
        b.add(1, 5);
        b.add(1, 2);
        b.add(1, 7);
        b.add(2, 8);
        b.add(3, 4);
        b.add(3, 6);
        b.add(4, 5);
        b.add(5, 3);
        b.add(5, 6);
        b.add(1, 7);
        b.add(3, 4);
        assertEquals(2, b.add(1, 2));
        b.add(6, 5);
        b.add(7, 6);
        b.add(8, 6);
        assertEquals(12, b.edgeSize());
        assertEquals(2, b.outDegree(3));
        assertEquals(4, b.inDegree(6));
        assertEquals(2, b.edgeId(1, 2));
        assertEquals(1, b.inDegree(7));
        b.remove(1, 7);
        assertEquals(11, b.edgeSize());
        assertEquals(0, b.inDegree(7));
        assertEquals(0, b.edgeId(1, 7));
        b.remove(6);
        assertEquals(6, b.edgeSize());
        assertEquals(0, b.inDegree(6));
        assertEquals(0, b.outDegree(6));
        assertEquals(1, b.outDegree(3));
        Iteration bSuccessor1 = b.successors(1);
        assertEquals(5, bSuccessor1.next());
        assertEquals(2, bSuccessor1.next());
        assertEquals(false, bSuccessor1.hasNext());
        Iteration bSuccessor2 = b.successors(1);
        assertEquals(5, bSuccessor2.next());
        Iteration bPredecessor = b.predecessors(5);
        assertEquals(1, bPredecessor.next());
        assertEquals(4, bPredecessor.next());
        assertEquals(false, bPredecessor.hasNext());
    }

    @Test
    public void testIteration() {
        UndirectedGraph u = new UndirectedGraph();
        for (int i = 0; i < 5; i++) {
            u.add();
        }
        u.add(1, 2);
        u.add(1, 3);
        u.add(1, 4);
        u.add(1, 5);
        u.add(2, 3);
        u.add(2, 5);
        u.add(3, 5);
        u.add(4, 5);
        assertEquals(8, u.edgeSize());
        assertEquals(4, u.outDegree(5));
        assertEquals(4, u.inDegree(5));
        Iteration uSuccessor = u.successors(1);
        assertEquals(2, uSuccessor.next());
        assertEquals(3, uSuccessor.next());
        Iteration uPredecessor = u.predecessors(4);
        assertEquals(1, uPredecessor.next());
        assertEquals(5, uPredecessor.next());
        assertEquals(false, uPredecessor.hasNext());
    }

    @Test
    public void testDirected() {
        DirectedGraph b = new DirectedGraph();
        for (int i = 0; i < 8; i++) {
            b.add();
        }
        assertEquals(false, b.add(1, 2) == b.add(2, 1));
        assertEquals(1, b.add(1, 2));
        assertEquals(2, b.add(2, 1));
        assertEquals(1, b.add(1, 2));
        b.add(1, 3);
        b.add(3, 1);
        b.add(2, 4);
        b.add(4, 2);
        assertEquals(0, b.edgeId(1, 1));
        assertEquals(0, b.edgeId(4, 5));
        b.remove(1, 3);
        assertEquals(0, b.edgeId(1, 3));
        assertEquals(4, b.edgeId(3, 1));
        b.add(1, 3);
        assertEquals(false, b.edgeId(1, 3) == 0);
        assertEquals(false, b.edgeId(1, 3) == b.edgeId(3, 1));
    }

    @Test
    public void testSuccessor() {
        UndirectedGraph u = new UndirectedGraph();
        for (int i = 0; i < 8; i++) {
            u.add();
        }
        u.add(1, 1);
        u.add(1, 1);
        Iteration oneSuccessor = u.successors(1);
        Iteration onePredecessor = u.predecessors(1);
        assertEquals(1, oneSuccessor.next());
        assertEquals(1, onePredecessor.next());
        assertEquals(false, oneSuccessor.hasNext());
        assertEquals(false, onePredecessor.hasNext());
        u.add(1, 2);
        u.add(1, 3);
        u.add(1, 4);
        u.add(1, 5);
        u.add(1, 6);
        u.add(2, 1);
        u.add(3, 1);
        u.add(4, 1);
        u.add(1, 1);
        u.add(5, 1);
        u.add(6, 1);
        u.add(2, 5);
        u.add(3, 1);
        u.add(3, 8);
        u.add(3, 4);
        u.add(3, 5);
        u.add(4, 1);
        u.add(4, 6);
        u.add(4, 8);
        u.add(5, 8);
        u.add(5, 6);
        u.add(8, 3);
        u.add(8, 4);
        u.add(8, 5);
        u.add(1, 1);
        u.add(4, 3);

    }

    @Test
    public void testPredecessor() {
        DirectedGraph d = new DirectedGraph();
        for (int i = 0; i < 8; i++) {
            d.add();
        }
        d.add(1, 1);
        Iteration twoPredecessor = d.predecessors(2);
        assertEquals(false, twoPredecessor.hasNext());
        d.add(1, 2);
        d.add(3, 2);
        Iteration twoPtwo = d.predecessors(2);
        assertEquals(1, twoPtwo.next());
        assertEquals(3, twoPtwo.next());
        assertEquals(false, twoPtwo.hasNext());
        d.remove(2);
        Iteration twoPSucc = d.successors(2);
        assertEquals(false, twoPSucc.hasNext());
        Iteration twoPred = d.predecessors(2);
        assertEquals(false, twoPred.hasNext());
    }

    @Test
    public void testMaxVertex() {
        UndirectedGraph u = new UndirectedGraph();
        for (int i = 0; i < 20; i++) {
            u.add();
        }
        u.remove(3);
        u.remove(6);
        u.remove(1);
        u.remove(10);
        assertEquals(16, u.vertexSize());
        assertEquals(false, u.contains(6));
        assertEquals(false, u.contains(1));
        assertEquals(1, u.add());
        assertEquals(3, u.add());
        assertEquals(6, u.add());
        assertEquals(10, u.add());
        assertEquals(20, u.vertexSize());
        assertEquals(true, u.contains(1));
        assertEquals(true, u.contains(10));
        u.remove(3);
        u.remove(6);
        assertEquals(18, u.vertexSize());
        assertEquals(false, u.contains(3));
        assertEquals(20, u.maxVertex());
        u.remove(10);
        assertEquals(false, u.contains(10));
        u.remove(20);
        assertEquals(false, u.contains(20));
        assertEquals(19, u.maxVertex());
        assertEquals(3, u.add());
        assertEquals(6, u.add());
        assertEquals(18, u.vertexSize());
        assertEquals(true, u.contains(19));
        assertEquals(true, u.contains(1));
        assertEquals(10, u.add());
        assertEquals(20, u.add());
        assertEquals(20, u.maxVertex());
        u.remove(20);
        u.add();
        assertEquals(true, u.contains(18));
        assertEquals(true, u.contains(20));
        u.remove(18);
        u.remove(20);
        assertEquals(19, u.maxVertex());
        u.remove(19);
        assertEquals(17, u.maxVertex());
    }

    @Test
    public void testUndirected() {
        UndirectedGraph u = new UndirectedGraph();
        for (int i = 0; i < 5; i++) {
            u.add();
        }
        assertEquals(u.add(1, 2), u.add(2, 1));
        assertEquals(u.add(1, 3), u.add(3, 1));
        u.add(1, 2);
        u.add(1, 2);
        assertEquals(u.add(1, 2), u.add(1, 2));

    }

    @Test
    public void testRemove() {
        UndirectedGraph u = new UndirectedGraph();
        for (int i = 0; i < 8; i++) {
            u.add();
        }
        u.add(1, 1);
        u.add(1, 2);
        u.add(1, 3);
        u.add(1, 4);
        u.add(1, 5);
        u.add(1, 6);
        u.add(2, 1);
        u.add(5, 1);
        u.add(6, 1);
        u.add(2, 5);
        u.add(3, 8);
        u.add(3, 4);
        u.add(3, 5);
        u.add(4, 1);
        u.add(4, 6);
        u.add(4, 8);
        u.add(5, 8);
        u.add(5, 6);
        u.add(8, 3);
        u.add(8, 4);
        u.add(8, 5);
        u.add(1, 1);
        u.add(4, 3);
        u.remove(1);
        Iteration one = u.successors(1);
        Iteration oneP = u.predecessors(1);
        assertEquals(false, one.hasNext());
        assertEquals(false, oneP.hasNext());
        assertEquals(0, u.inDegree(1));
        assertEquals(0, u.outDegree(1));
        Iteration seven = u.successors(7);
        assertEquals(false, seven.hasNext());
        Iteration five = u.predecessors(5);
        assertEquals(2, five.next());
        assertEquals(3, five.next());
        assertEquals(8, five.next());
        assertEquals(6, five.next());
        assertEquals(false, five.hasNext());
    }

    @Test
    public void testRemoveEdge() {
        UndirectedGraph u = new UndirectedGraph();
        for (int i = 0; i < 8; i++) {
            u.add();
        }
        u.add(1, 1);
        assertEquals(1, u.outDegree(1));
        assertEquals(1, u.inDegree(1));
        u.add(1, 1);
        u.add(1, 1);
        assertEquals(1, u.outDegree(1));
        assertEquals(1, u.edgeSize());
        assertEquals(1, u.edgeId(1, 1));
        u.add(1, 2);
        u.add(1, 3);
        assertEquals(true, u.contains(3, 1));
        u.add(1, 4);
        u.add(1, 5);
        u.add(1, 6);
        u.add(2, 1);
        u.add(3, 1);
        u.add(4, 1);
        u.add(1, 1);
        u.add(5, 1);
        u.add(6, 1);
        assertEquals(6, u.outDegree(1));
        assertEquals(6, u.inDegree(1));
        assertEquals(6, u.edgeSize());
        u.add(2, 5);
        u.add(3, 1);
        u.add(3, 8);
        u.add(3, 4);
        u.add(3, 5);
        u.add(4, 1);
        u.add(4, 6);
        u.add(4, 8);
        u.add(5, 8);
        u.add(5, 6);
        u.add(8, 3);
        u.add(8, 4);
        u.add(8, 5);
        u.add(1, 1);
        u.add(4, 3);
        assertEquals(14, u.edgeSize());
        u.remove(2, 1);
        u.remove(6, 1);
        assertEquals(12, u.edgeSize());
        assertEquals(false, u.contains(1, 2));
        u.remove(1, 2);
        assertEquals(12, u.edgeSize());
        assertEquals(4, u.inDegree(1));
        u.remove(1, 1);
        u.remove(1, 1);
        u.remove(2, 1);
        assertEquals(11, u.edgeSize());
        Iteration seven = u.successors(7);
        assertEquals(false, seven.hasNext());
    }

}
