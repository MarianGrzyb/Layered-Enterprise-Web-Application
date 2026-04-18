package handlingtasks;

import handlingresults.SharedResult;
import static operation.CalculationLogic.calculation;
import utilities.Constants;

import java.util.Objects;

public class WorkerThreadInstance implements Runnable{

    private final SharedResource resource;
    private final SharedResult results;
    public WorkerThreadInstance(SharedResource resource, SharedResult results) {
        this.resource = resource;
        this.results = results;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Integer task = resource.take();

                if (Objects.equals(task, Constants.POISON_PILL)) {
                    break;
                }
                else {
                    try {
                        Thread.sleep(Constants.SLEEP_TIME);
                    }  catch (InterruptedException ex) {
                        break;
                    }

                    Integer result = calculation(task);
                    results.put(task, result);
                }
            } catch (InterruptedException ex) {
                break;
            }
        }
    }
}
