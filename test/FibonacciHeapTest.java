import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class FibonacciHeapTest {

    /*
     * insert();
     * deleteMin(); delete from empty heap;
     * getMin(): of empty ;
     * decreaseKey(): delete deleted node; from empty;
     * merge();
     * reset();
     * */
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originOut = System.out;

    @Before
    public void setUpStream() {
        FibonacciHeap heap = new FibonacciHeap();
        heap.reset();
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originOut);
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
        String correctOut = "action: insert; heap: 0" +
                "action: insert\nheap: 0 2";
    }

    public void decreaseUsualNode() {
        FibonacciHeap heap = new FibonacciHeap();
        for (int key = 0; key < 10; key += 2) {
            heap.insert(key);
        }
        FibonacciHeap.HeapNode node = heap.insert(3);
        heap.decreaseKey(node, 2);
        String correctOut = "action: insert\n heap: 0";
        assertEquals("usual 'decrease key' method", correctOut, outContent.toString());
    }

    @Test
    public void getMinOfEmptyHeap() {
        FibonacciHeap heap = new FibonacciHeap();
        assertNotNull("get min of empty heap", heap.getMin());
    }

    @Test
    public void insert() {
    }

    @Test
    public void merge() {
    }

    @Test
    public void decreaseKey() {
    }
}