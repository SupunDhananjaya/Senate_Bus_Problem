import java.util.concurrent.TimeUnit;
import java.util.Random;

class BusSpawner implements Runnable {
    private BusStop busStop;
    private int busIdCounter = 1;
    private double meanBusArrivalTimeMillis;
    private Random random = new Random();

    public BusSpawner(BusStop busStop, double meanBusArrivalTimeMinutes) {
        this.busStop = busStop;
        this.meanBusArrivalTimeMillis = meanBusArrivalTimeMinutes * 100; // Convert minutes to milliseconds
                                                                         // (assume 1s mean 10 minuites)
    }

    @Override
    public void run() {
        while (true) {

            Thread bus = new Thread(new Bus(busStop, busIdCounter)); // create a thread for each bus.
            bus.start();

            // calculate exponentially distributed arrival time for the next bus
            double nextArrivalTimeMillis = -meanBusArrivalTimeMillis * Math.log(random.nextDouble());
            this.busIdCounter += 1; // increase bus id

            try {
                TimeUnit.MILLISECONDS.sleep((long) nextArrivalTimeMillis);
            } catch (InterruptedException e) {
                System.out.println("Error in busSwawn class");
                Thread.currentThread().interrupt();
            }
        }
    }
}
