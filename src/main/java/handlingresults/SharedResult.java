package handlingresults;

import java.util.LinkedList;
import java.util.Queue;

public class SharedResult {

    private static class Result {
        public final Integer number;
        public final Integer primeFactorCount;

        public Result(Integer number, Integer primeFactorCount) {
            this.number = number;
            this.primeFactorCount = primeFactorCount;
        }
    }
    private final Queue<Result> queue = new LinkedList<>();

    public synchronized void put(Integer number, Integer primeFactorCount) {
        Result result = new Result(number, primeFactorCount);
        queue.add(result);
    }
}
