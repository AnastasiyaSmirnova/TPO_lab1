/**
 * FibonacciHeap
 * from https://github.com/danablanc/FibonacciHeap/blob/master/FibonacciHeap.java
 * An implementation of fibonacci heap over non-negative integers.
 */
public class FibonacciHeap {
    public static HeapNode minNode;
    public static int size;
    public static int markedCnt;
    public static int totalLinks;
    public static int numOfTrees;
    public static int totalCuts;
    public static final double PHI = (1 + Math.sqrt(5)) / 2;

    public FibonacciHeap() {
        minNode = null;
        numOfTrees = 0;
        size = 0;
    }

    /**
     * public int getSize()
     * returns the size of the FibonacciHeap
     * Complexity- O(1)
     */
    private int getSize() {
        return size;
    }

    /**
     * public int getMarked_cnt()
     * returns the number of the marked nodes in the FibonacciHeap
     * Complexity- O(1)
     */
    private int getMarkedCnt() {
        return markedCnt;
    }

    /**
     * public int getNum_of_trees()
     * returns the number of the trees (number of roots) in the FibonacciHeap
     * Complexity- O(1)
     */
    private int getNumOfTrees() {
        return numOfTrees;
    }

    /**
     * public static int totalLinks()
     * <p>
     * This static function returns the total number of link operations made during the run-time of the program.
     * A link operation is the operation which gets as input two trees of the same rank, and generates a tree of
     * rank bigger by one, by hanging the tree which has larger value in its root on the tree which has smaller value
     * in its root.
     * <p>
     * O(1) complexity
     */
    public static int getTotalLinks() {
        return totalLinks;
    }

    /**
     * public boolean empty()
     * <p>
     * precondition: none
     * <p>
     * The method returns true if and only if the heap
     * is empty.
     * <p>
     * Complexity- O(1)
     */
    private static boolean isHeapEmpty() {
        return size == 0;
    }

    /**
     * public HeapNode insert(int key)
     * <p>
     * Creates a node (of type HeapNode) which contains the given key, and inserts it into the heap.
     * <p>
     * Complexity- O(1) using functions connect and empty which are also O(1) complexity
     */
    public HeapNode insert(int key) {
        if (key < 0) {
            System.err.print("negative keys are prohibited");
        }
        HeapNode node = new HeapNode(key);
        if (isHeapEmpty()) {
            minNode = node;
        } else {
            connect(minNode, node);
            if (key < minNode.key)
                minNode = node;
        }
        size++;
        numOfTrees++;
        log("insert", getNumOfTrees());
        return node;
    }

    /**
     * public void deleteMin()
     * <p>
     * Delete the node containing the minimum key.
     * <p>
     * O(n) complexity w.c - using the functions reset, empty, connect  (which are all O(1) and consolidating
     * which is O(n) complexity w.c) and amortized O(logn)
     */
    public void deleteMin() {
        if (isHeapEmpty()) { // no HeapNodes in Heap - nothing to delete
            log("delete min", getNumOfTrees());
            return;
        }
        numOfTrees += minNode.rank - 1;
        size--;
        if (minNode.next == minNode && size == 0) { // one HeapNode in Heap - delete it
            reset();
            log("delete min", getNumOfTrees());
            return;
        }
        if (minNode.next == minNode) { // one tree in heap, this tree has at least one child
            HeapNode x = minNode.child;
            int count = minNode.rank;
            minNode = x;
            for (int i = 1; i <= count; i++) { // all children become roots by itself
                x.parent = null;
                if (x.mark == 1) {
                    x.mark = 0;
                    markedCnt--;
                }
                if (x.key < minNode.key) // updating new minimum
                    minNode = x;
                x = x.next;
            }
            log("delete min", getNumOfTrees());
            return;
        }
        HeapNode x = minNode.next;  // more then one tree in Heap
        minNode.prev.next = x;
        x.prev = minNode.prev;
        if (minNode.child != null) { // min_node has children
            HeapNode y = minNode.child;
            for (int i = 1; i <= minNode.rank; i++) { // while there are still children to connect to root list
                y.parent = null;
                if (y.mark == 1) {
                    y.mark = 0;
                    markedCnt--;
                }
                y = y.next;
            }
            connect(y, x); // connect children to root list
        }
        consolidating(x);
        log("delete min", getNumOfTrees());
    }

    /**
     * public HeapNode getMin_node()
     * returns this node with the minimun key of the FibonacciHeap
     * Complexity- O(1)
     */
    public HeapNode getMin() {
        log("get min", getNumOfTrees());
        return minNode;
    }

    /**
     * public void meld (FibonacciHeap heap2)
     * <p>
     * Meld the heap with heap2
     * <p>
     * O(1) complexity using connect, findMin and empty - O(1)
     */
    public void merge(FibonacciHeap heap2) {
        if (heap2 == null || heap2.getSize() == 0)
            return;
        if (isHeapEmpty())
            minNode = heap2.getMin();
        else {
            connect(minNode, heap2.getMin());
            if (minNode.key > heap2.getMin().key)
                minNode = heap2.getMin();
        }
        size += heap2.getSize();
        numOfTrees += heap2.getNumOfTrees();
        markedCnt += heap2.getMarkedCnt();
        log("merge", getNumOfTrees());
    }

    /**
     * public void reset()
     * <p>
     * resets the heap to an empty heap
     * <p>
     * O(1) complexity
     */
    public void reset() {
        minNode = null;
        size = 0;
        numOfTrees = 0;
        markedCnt = 0;
        log("reset", getNumOfTrees());
    }

    /**
     * public void decreaseKey(HeapNode x, int delta)
     * <p>
     * The function decreases the key of the node x by delta. The structure of the heap should be updated
     * to reflect this chage (for example, the cascading cuts procedure should be applied if needed).
     * <p>
     * O(n) complexity w.c - using CascadingCut - O(n) complexity w.c
     */
    public void decreaseKey(HeapNode x, int delta) throws IllegalHeapNode {
        if (x == null) {
            throw new IllegalHeapNode("Unable to decrease null element");
        }
        if (x.key - delta < 0) {
            throw new IllegalHeapNode("Negative result key");
        }
        if (x.getNext().getKey() == x.getKey() && (minNode == null || x.getKey() != getMin().getKey())) {
            throw new IllegalHeapNode("Unknown heap node. Unable to decrease");
        }
        x.key = x.key - delta;
        if (x.parent != null) {
            if (x.key < x.parent.key)
                cascadingCut(x, x.parent);
        }
        if (x.key < minNode.key)
            minNode = x;
        log("decrease key", getNumOfTrees());
    }

    /* non-usual methods: delete(), potential(), countersRep(), getTotalCuts(),  */

    /**
     * public void delete(HeapNode x)
     * <p>
     * Deletes the node x from the heap.
     * <p>
     * O(n) complexity w.c - using decreaseKey - O(n) w.c and deleteMin O(n) w.c
     */
    public void delete(HeapNode x) throws IllegalHeapNode {
        if (x == null)
            return;
        decreaseKey(x, Integer.MAX_VALUE);
        deleteMin();
    }

    /**
     * public int potential()
     * <p>
     * This function returns the current potential of the heap, which is:
     * Potential = #trees + 2*#marked
     * The potential equals to the number of trees in the heap plus twice the number of marked nodes in the heap.
     * <p>
     * O(1) complexity
     */
    public int potential() {
        return numOfTrees + 2 * markedCnt;
    }

    /**
     * public int[] countersRep()
     * <p>
     * Return a counters array, where the value of the i-th entry is the number of trees of order i in the heap.
     */
    public int[] countersRep() {
        int[] arr = new int[42];
        if (isHeapEmpty())
            return arr;
        HeapNode tmp = minNode;
        arr[minNode.rank]++;
        while (tmp.next != minNode) {
            tmp = tmp.next;
            arr[tmp.rank]++;
        }
        return arr;
    }

    /**
     * public static int totalCuts()
     * <p>
     * This static function returns the total number of cut operations made during the run-time of the program.
     * A cut operation is the operation which diconnects a subtree from its parent (during decreaseKey/delete methods).
     * <p>
     * O(1) complexity
     */
    public static int getTotalCuts() {
        return totalCuts;
    }

    /* private methods for inside processing */

    /**
     * public static void connect(HeapNode n1, HeapNode n2)
     * n1 - min_node of the heap
     * n2 - other node
     * <p>
     * O(1) complexity
     */
    private static void connect(HeapNode n1, HeapNode n2) {
        HeapNode temp = n1.next;
        n1.next = n2.next;
        n1.next.prev = n1;
        n2.next = temp;
        n2.next.prev = n2;
    }

    /**
     * private void cascadingCut(HeapNode x,HeapNode y)
     * gets a node x and it's parent y and cuts the connection between them two and makes x a root
     * if needed the function runs recursively according to the marked nodes and cuts some more connections
     * <p>
     * O(n) complexity w.c - using cut - O(1) complexity
     */
    private static void cascadingCut(HeapNode x, HeapNode y) {
        cut(x, y);
        if (y.parent != null) {
            if (y.mark == 0) {
                y.mark = 1;
                markedCnt++;
            } else
                cascadingCut(y, y.parent);
        }
    }

    /**
     * private void cut(HeapNode x,HeapNode y)
     * help function to cascading cut - gets two nodes x and y and cuts the connection between them two
     * <p>
     * O(1) complexity - uses the function connect - O(1) complexity
     */
    private static void cut(HeapNode x, HeapNode y) {
        totalCuts++;
        numOfTrees++;
        x.parent = null;
        if (x.mark == 1) {
            x.mark = 0;
            markedCnt--;
        }
        y.rank--;
        if (x.next == x)
            y.child = null;
        else {
            y.child = x.next;
            x.prev.next = x.next;
            x.next.prev = x.prev;
            x.next = x;
            x.prev = x;
        }
        connect(x, minNode);
    }

    /**
     * private void Consolidating()
     * the function makes the successive linking / consolidating progress
     * turns the FibonacciHeap into a heap, which contains at most
     * one tree of each rank
     * Complexity - w.c. - O(n) , amortized - O(logn)
     * using the functions toBuckets(O(n)) and fromBuckets(O(logn)), removeFromRootList (O(1)), connect (O(1))
     */
    private static void consolidating(HeapNode node) {
        toBuckets(node);
    }

    /**
     * private void toBuckets()
     * the function puts the trees into an array in order to make
     * the successive linking progress
     * O(n) complexity w.c when n represents the nodes in the heap.
     * using the functions fromBuckets(O(logn)), removeFromRootList (O(1)), connect (O(1))
     */
    private static void toBuckets(HeapNode node) {
        HeapNode[] buckets = new HeapNode[(int) (Math.ceil((Math.log(size) / Math.log(PHI))) + 1)]; // maximal rank of heap with size - size
        HeapNode x = node;
        HeapNode y;
        int count = numOfTrees;
        for (int i = 1; i <= count; i++) { //  while there more roots
            y = x;
            x = x.next;
            removeFromRootList(y);
            while (buckets[y.rank] != null) { // there is already one tree of same rank
                int check = 0;
                if (y.key >= buckets[y.rank].key)    // flag to know who will be the common root
                    check = 1;
                link(y, buckets[y.rank]); // making the linking progress
                if (check == 1)
                    y = buckets[y.rank];
                buckets[y.rank - 1] = null; // clear the cell
            }
            buckets[y.rank] = y; // put the linked tree in the next rank cell
        }
        fromBuckets(buckets);
    }

    /**
     * private void fromBuckets(HeapNode [] buckets)
     * the function connects the linked trees into one heap
     * Complexity- O(logn)
     * using the functions removeFromRootList (O(1)), connect (O(1))
     */
    private static void fromBuckets(HeapNode[] buckets) {
        minNode = new HeapNode(Integer.MAX_VALUE); //to update new minimum
        HeapNode x = null;
        for (HeapNode node : buckets) { //running on all cells
            if (node != null) { // there is a tree
                if (node.key < minNode.key) // looking for new minimum
                    minNode = node;
                if (x == null) { //the first connected tree
                    x = node;
                    x.next = x;
                    x.prev = x;
                } else // there were be connected trees already, connect to them
                    connect(x, node);
            }
        }
    }

    /**
     * private void link(HeapNode h1, HeapNode h2)
     * the function links two trees with same rank
     * the tree with lower root key will be the father of the second tree root
     * Complexity- O(1), uses the function insertNext - O(1) complexity
     */
    private static void link(HeapNode h1, HeapNode h2) {
        totalLinks++;
        if (h1.key < h2.key) { // checks who will be the common father
            if (h1.child != null)
                insertNext(h1.child, h2); // now a child
            h2.parent = h1;
            h1.child = h2;
            h1.rank++; // fathers rank increased by one
        } else {
            if (h2.child != null)
                insertNext(h2.child, h1);
            h1.parent = h2;
            h2.child = h1;
            h2.rank++;
        }
        numOfTrees--; // two trees were linked into one
    }

    /**
     * private void insertNext(HeapNode h1, HeapNode h2)
     * the function inserts the most left child to the root
     * Complexity- O(1)
     */
    public static void insertNext(HeapNode h1, HeapNode h2) {
        h2.prev = h1.prev;
        h1.prev.next = h2;
        h2.next = h1;
        h1.prev = h2;
    }

    /**
     * private void removeFromRootList(HeapNode x)
     * the function removes the given node from the root list
     * Complexity- O(1)
     */
    private static void removeFromRootList(HeapNode x) {
        HeapNode prev = x.prev;
        HeapNode next = x.next;
        prev.next = next;
        next.prev = prev;
        x.next = x;
        x.prev = x;
    }

    /**
     * public class HeapNode
     * <p>
     * If you wish to implement classes other than FibonacciHeap
     * (for example HeapNode), do it in this file, not in
     * another file
     */
    public static class HeapNode {

        private Integer key;
        private int rank;
        private short mark;
        private HeapNode child;
        private HeapNode next;
        private HeapNode prev;
        private HeapNode parent;

        public HeapNode(int k) {
            this.key = k;
            this.prev = this;
            this.next = this;
        }

        public HeapNode getChild() {
            return child;
        }

        /**
         * public int getKey()
         * returns the node's key
         * Complexity- O(1)
         */
        public int getKey() {
            return this.key;
        }

        public HeapNode getNext() {
            return next;
        }

        public HeapNode getPrev() {
            return prev;
        }
    }

    private static void printChild(StringBuilder sb, HeapNode childNode) {
        int key = childNode.getKey();
        HeapNode next = childNode;
        do {
            sb.append("(").append(next.getKey());
            if (next.getChild() != null) {
                printChild(sb, next.getChild());
            }
            sb.append(")");
            next = next.getNext();

        } while (next.getKey() != key);
    }

    private static void log(String action, int heapSize) {
        StringBuilder sb = new StringBuilder("action: ");
        sb.append(action).append("; ")
                .append("heap:");
        if (heapSize == 0) {
            sb.append(" empty");
        } else {
            HeapNode next = minNode;
            int counter = 0;
            while (counter++ < heapSize) {
                sb.append(' ').append(next.getKey());
                if (next.getChild() != null) {
                    sb.append(" [");
                    printChild(sb, next.getChild());
                    sb.append("]");
                }
                next = next.getNext();
            }
        }
        sb.append("\n");
        System.out.print(sb.toString());
    }
}