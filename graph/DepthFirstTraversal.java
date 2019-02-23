package graph;

/* See restrictions in Graph.java. */

import java.util.Queue;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Collection;

/**
 * Implements a depth-first traversal of a graph.  Generally, the
 * client will extend this class, overriding the visit and
 * postVisit methods, as desired (by default, they do nothing).
 *
 * @author Wendi Zhang
 */
public class DepthFirstTraversal extends Traversal {

    /**
     * A depth-first Traversal of G.
     */
    protected DepthFirstTraversal(Graph G) {
        super(G, _dffringe);
    }

    @Override
    protected boolean visit(int v) {
        return super.visit(v);
    }

    @Override
    protected boolean shouldPostVisit(int v) {
        return false;
    }

    @Override
    protected boolean postVisit(int v) {
        return super.postVisit(v);
    }

    /**
     * A Queue type StackQueue.
     */
    private static class StackQueue<E> implements Queue<E> {

        /**
         * A linkedList as the constructor.
         */
        private LinkedList<E> stackQueue;

        /**
         * The constructor of StackQueue.
         */
        private StackQueue() {
            stackQueue = new LinkedList<>();
        }

        @Override
        public boolean add(E e) {
            return stackQueue.add(e);
        }

        @Override
        public boolean offer(E e) {
            return stackQueue.offer(e);
        }

        @Override
        public E remove() {
            return stackQueue.removeLast();
        }

        @Override
        public E poll() {
            return stackQueue.pollLast();
        }

        @Override
        public E element() {
            return stackQueue.getLast();
        }

        @Override
        public E peek() {
            return stackQueue.peekLast();
        }

        @Override
        public int size() {
            return stackQueue.size();
        }

        @Override
        public boolean isEmpty() {
            return stackQueue.isEmpty();
        }

        @Override
        public boolean contains(Object o) {
            return stackQueue.contains(o);
        }

        @Override
        public Iterator<E> iterator() {
            return stackQueue.iterator();
        }

        @Override
        public Object[] toArray() {
            return stackQueue.toArray();
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return stackQueue.toArray(a);
        }

        @Override
        public boolean remove(Object o) {
            return stackQueue.remove(o);
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return stackQueue.containsAll(c);
        }

        @Override
        public boolean addAll(Collection<? extends E> c) {
            return stackQueue.addAll(c);
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return stackQueue.removeAll(c);
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return stackQueue.retainAll(c);
        }

        @Override
        public void clear() {
            stackQueue.clear();
        }

        @Override
        public String toString() {
            return stackQueue.toString();
        }
    }

    /**
     * The stack storing all nodes.
     */
    private static Queue<Integer> _dffringe = new StackQueue<Integer>();


}
