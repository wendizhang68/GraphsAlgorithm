package graph;

import org.junit.Test;


/**
 * Unit tests for the Traversal class.
 *
 * @author Wendi Zhang
 */
public class TraversalTest {
    @Test
    public void setUp() {
        DirectedGraph b = new DirectedGraph();
        for (int i = 0; i < 8; i++) {
            b.add();
        }
        b.add(1, 7);
        b.add(1, 5);
        b.add(1, 2);
        b.add(2, 8);
        b.add(3, 6);
        b.add(3, 4);
        b.add(4, 5);
        b.add(5, 6);
        b.add(5, 3);
        b.add(1, 7);
        b.add(3, 4);
        b.add(6, 5);
        b.add(7, 6);
        b.add(8, 6);
        DepthFirstTraversal dfs = new DepthFirstTraversal(b);
        dfs.traverse(1);
        BreadthFirstTraversal bfs = new BreadthFirstTraversal(b);
        bfs.traverse(1);
    }

    @Test
    public void postOrderTest() {
        DirectedGraph a = new DirectedGraph();
        for (int i = 0; i < 5; i++) {
            a.add();
        }
        a.add(1, 2);
        a.add(1, 3);
        a.add(4, 1);
        a.add(2, 5);
        DepthFirstTraversal dfsA = new DepthFirstTraversal(a);
        dfsA.traverse(4);
    }

    @Test
    public void postOrderTest2() {
        DirectedGraph c = new DirectedGraph();
        for (int i = 0; i < 6; i++) {
            c.add();
        }
        c.add(1, 4);
        c.add(1, 2);
        c.add(2, 5);
        c.add(3, 5);
        c.add(3, 6);
        c.add(4, 2);
        c.add(5, 4);
        c.add(5, 6);
        DepthFirstTraversal dfsC = new DepthFirstTraversal(c);
        dfsC.traverse(1);
    }

    @Test
    public void postOrderTest3() {
        UndirectedGraph u = new UndirectedGraph();
        for (int i = 0; i < 5; i++) {
            u.add();
        }
        u.add(1, 1);
        u.add(1, 2);
        u.add(1, 3);
        u.add(1, 4);
        u.add(1, 5);
        u.add(2, 3);
        u.add(2, 5);
        u.add(3, 5);
        u.add(4, 5);
        DepthFirstTraversal dfsU = new DepthFirstTraversal(u);
        dfsU.traverse(1);
    }

    @Test
    public void bfs1() {
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
        BreadthFirstTraversal bfsU = new BreadthFirstTraversal(u);
        bfsU.traverse(1);
    }

    @Test
    public void dfs1() {
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
        b.add(6, 5);
        b.add(7, 6);
        b.add(8, 6);
        DepthFirstTraversal dfsB = new DepthFirstTraversal(b);
        dfsB.traverse(1);
    }

    @Test
    public void dfs2() {
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
        DepthFirstTraversal dfsU = new DepthFirstTraversal(u);
        dfsU.traverse(2);
    }

    @Test
    public void dfs3() {
        UndirectedGraph u = new UndirectedGraph();
        for (int i = 0; i < 8; i++) {
            u.add();
        }
        u.add(1, 1);
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
        DepthFirstTraversal dfsU = new DepthFirstTraversal(u);
        dfsU.traverse(2);
    }
}
