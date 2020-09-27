import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class FibonacciHeapTest {

    /*
     * insert();
     * deleteMin(); delete from empty heap; usual delete
     * getMin(): of empty ; usual get min
     * decreaseKey(): delete deleted node; usual
     * merge(); ??? no example
     *
     * */
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originOut = System.out;
    private final PrintStream originErr = System.err;

    @Before
    public void setUpStream() {
        FibonacciHeap heap = new FibonacciHeap();
        heap.reset();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originOut);
        System.setErr(originErr);
    }

    @Test
    public void deleteMinFromEmptyHeap() {
        FibonacciHeap heap = new FibonacciHeap();
        heap.deleteMin();
        String correctOut = "action: delete min; heap: empty\n";
        assertEquals("delete from empty heap", correctOut, outContent.toString());
    }

    @Test
    public void deleteMinUsual() {
        FibonacciHeap heap = new FibonacciHeap();
        for (int key = 0; key < 10; key += 2) {
            heap.insert(key);
        }
        heap.deleteMin();
        String correctOut = "action: insert; heap: 0\n" +
                "action: insert; heap: 0 2\n" +
                "action: insert; heap: 0 4 2\n" +
                "action: insert; heap: 0 6 4 2\n" +
                "action: insert; heap: 0 8 6 4 2\n" +
                "action: delete min; heap: 2 [(6(8))(4)]\n";
        assertEquals("usual 'delete min' method", correctOut, outContent.toString());
    }

    @Test
    public void insertUsualNodes() {
        FibonacciHeap heap = new FibonacciHeap();
        heap.insert(0);
        heap.insert(2);
        String correctOut = "action: insert; heap: 0\n" +
                "action: insert; heap: 0 2\n";
        assertEquals("usual insert", correctOut, outContent.toString());
    }

    @Test
    public void insertNodeWithNegativeKey() {
        FibonacciHeap heap = new FibonacciHeap();
        heap.insert(-5);
        String correctOut = "negative keys are prohibited";
        assertEquals("insert node with negative key", correctOut, errContent.toString());
    }

    @Test
    public void decreaseUsualNode() throws IllegalHeapNode {
        FibonacciHeap heap = new FibonacciHeap();
        for (int key = 0; key < 10; key += 2) {
            heap.insert(key);
        }
        FibonacciHeap.HeapNode node = heap.insert(3);
        heap.decreaseKey(node, 2);
        String correctOut = "action: insert; heap: 0\n" +
                "action: insert; heap: 0 2\n" +
                "action: insert; heap: 0 4 2\n" +
                "action: insert; heap: 0 6 4 2\n" +
                "action: insert; heap: 0 8 6 4 2\n" +
                "action: insert; heap: 0 3 8 6 4 2\n" +
                "action: decrease key; heap: 0 1 8 6 4 2\n";
        assertEquals("usual 'decrease key' method", correctOut, outContent.toString());
    }

    @Test(expected = IllegalHeapNode.class)
    public void decreaseUnknownNode() throws IllegalHeapNode {
        FibonacciHeap heap = new FibonacciHeap();
        FibonacciHeap.HeapNode unknownNode = new FibonacciHeap.HeapNode(7);
        heap.decreaseKey(unknownNode, 6);
    }

    @Test(expected = IllegalHeapNode.class)
    public void decreaseNullNode() throws IllegalHeapNode {
        FibonacciHeap heap = new FibonacciHeap();
        heap.decreaseKey(null, 6);
    }

    @Test(expected = IllegalHeapNode.class)
    public void decreaseNodeToNegativeKey() throws IllegalHeapNode {
        FibonacciHeap heap = new FibonacciHeap();
        FibonacciHeap.HeapNode unknownNode = heap.insert(8);
        heap.decreaseKey(unknownNode, 10);
    }

    @Test
    public void getMinOfEmptyHeap() {
        FibonacciHeap heap = new FibonacciHeap();
        assertNull("get min of empty heap", heap.getMin());
    }

    @Test
    public void merge() {
    }
}