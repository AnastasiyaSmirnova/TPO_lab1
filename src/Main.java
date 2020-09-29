public class Main {
    public static void main(String[] args) throws IllegalHeapNode {
        FibonacciHeap heap = new FibonacciHeap();
//        heap.insert(0);
//        heap.insert(1);
//        heap.insert(5);
//        heap.insert(2);

//        for (int key = 0; key < 10; key += 2) {
//            heap.insert(key);
//        }// 2 4 6 8 0
//        heap.deleteMin();
//
//        for (int key = 0; key < 10; key += 2) {
//            heap.insert(key);
//        }
//        FibonacciHeap.HeapNode node = heap.insert(3);
//        heap.decreaseKey(node, 2);

        for (int i = 1; i < 17; i++){
            int log = log2(i);
            System.out.println("log(" + i + ") = " + log);
        }
    }

    public static int log2(int n){
        return  (int)(Math.log(n) / Math.log(2));
    }
}
