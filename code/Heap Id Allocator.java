import java.util.*;

/*
Id Allocator using heap data structure over bitset.
*/
public class Main {

    public static void main(String[] args) {
        HeapIdAllocator a = new HeapIdAllocator(3);
        System.out.println("alloc = " + a.allocate());
        System.out.println("alloc = " + a.allocate());
        System.out.println("isAllocated(1) = " + a.isAllocated(1));
        System.out.println("isAllocated(0) = " + a.isAllocated(0));
        System.out.println("free(1) = " + a.free(1));
        System.out.println("free(0) = " + a.free(0));
        System.out.println("isAllocated(1) = " + a.isAllocated(1));
        System.out.println("isAllocated(0) = " + a.isAllocated(0));
        System.out.println("alloc = " + a.allocate());
        System.out.println("alloc = " + a.allocate());
        System.out.println("alloc = " + a.allocate());
        System.out.println("alloc = " + a.allocate());
    }
}

class HeapIdAllocator {

    private final int numIds;
    private final int numNonLeafNodes;
    private final BitSet bits;

    HeapIdAllocator(int numIds) {
        if (numIds <= 0) {
            throw new IllegalArgumentException("Num ids must be at least 1");
        }

        this.numIds = numIds; // num leaf nodes
        this.numNonLeafNodes = numIds - 1;
        this.bits = new BitSet(numIds + numNonLeafNodes);
    }

    int allocate() {
        int i = 0;
        while (i < numNonLeafNodes) {
            int leftChild = leftChildOf(i);
            if (!bits.get(leftChild)) {
                i = leftChild;
                continue;
            }

            int rightChild = rightChildOf(i);
            if (!bits.get(rightChild)) {
                i = rightChild;
                continue;
            }

            // Out of ids.
            return -1;
        }

        // Handle special case of 1 id total so there is no tree structure at all.
        if (bits.get(i)) {
            return -1;
        }

        bits.set(i);
        updateParents(i);

        return i - numNonLeafNodes;
    }

    boolean free(int id) {
        checkRange(id);

        int i = id + numNonLeafNodes;

        if (bits.get(i)) {
            bits.clear(i);
            updateParents(i);
            return true;
        }

        return false;
    }

    boolean isAllocated(int id) {
        checkRange(id);
        int i = id + numNonLeafNodes;
        return bits.get(i);
    }

    private void updateParents(int i) {
        i = parentOf(i);

        while (i > 0) {
            boolean leftChildSet = bits.get(leftChildOf(i));
            boolean rightChildSet = bits.get(rightChildOf(i));

            if (leftChildSet && rightChildSet) {
                if (bits.get(i)) {
                    return;
                }
                bits.set(i);
            } else {
                if (!bits.get(i)) {
                    return;
                }
                bits.clear(i);
            }

            i = parentOf(i);
        }
    }

    private void checkRange(int i) {
        if (i < 0 || i >= numIds) {
            throw new IllegalArgumentException("Given id " + i + " out of range");
        }
    }

    private static int leftChildOf(int i) {
        return i * 2 + 1;
    }

    private static int rightChildOf(int i) {
        return i * 2 + 2;
    }

    private static int parentOf(int i) {
        return (i - 1) / 2;
    }
}
