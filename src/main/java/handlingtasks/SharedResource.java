package handlingtasks;

import java.util.LinkedList;
import java.util.Queue;

public class SharedResource {
    private final Queue<Integer> queue = new LinkedList<>();

    public synchronized void put(Integer value) {
        queue.add(value);
        notifyAll();
    }
    public synchronized Integer take() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }

        return queue.remove();
    }
}

