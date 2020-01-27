import java.util.*;

public class Main {

    public static void main(String[] args) {
        try {
            ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
            lock.lockRead();
            lock.lockWrite();
            lock.lockRead();
            lock.lockWrite();
            lock.unlockWrite();
            lock.unlockRead();
            lock.unlockWrite();
            lock.unlockRead();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("hello");
    }
}

class ReentrantReadWriteLock {
    private final Map<Thread, Integer> readerThreads = new HashMap<>();
    private Thread writerThread = null;
    private int numWriteRequests = 0;
    private int numWriteLocks = 0;

    public synchronized void lockRead() throws InterruptedException {
        while (!canGrantRead()) {
            wait();
        }

        readerThreads.merge(Thread.currentThread(), 1, Integer::sum);
    }

    private boolean canGrantRead() {
        if (writerThread == Thread.currentThread()) {
            return true;
        }
        if (writerThread != null || numWriteRequests > 0) {
            return false;
        }
        if (readerThreads.containsKey(Thread.currentThread())) {
            return true;
        }
        return true;
    }

    public synchronized void unlockRead() {
        Integer readCount = readerThreads.get(Thread.currentThread());

        if (readCount == null) {
            throw new IllegalMonitorStateException("Not holding on to read lock");
        }

        if (readCount == 1) {
            readerThreads.remove(Thread.currentThread());
        } else {
            readerThreads.put(Thread.currentThread(), readCount - 1);
        }

        if (readerThreads.isEmpty()) {
            notifyAll();
        }
    }

    public synchronized void lockWrite() throws InterruptedException {
        numWriteRequests++;

        while (!canGrantWrite()) {
            wait();
        }

        writerThread = Thread.currentThread();
        numWriteLocks++;
        numWriteRequests--;
    }

    private boolean canGrantWrite() {
        if (Thread.currentThread() == writerThread) {
            return true;
        }
        if (writerThread != null) {
            return false;
        }
        if (readerThreads.size() == 1 && readerThreads.containsKey(Thread.currentThread())) {
            return true;
        }
        return readerThreads.isEmpty();
    }

    public synchronized void unlockWrite() {
        if (Thread.currentThread() != writerThread) {
            throw new IllegalMonitorStateException("Not holding on to write lock");
        }

        numWriteLocks--;
        if (numWriteLocks == 0) {
            writerThread = null;
            notifyAll();
        }
    }
}
