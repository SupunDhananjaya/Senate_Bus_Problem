import java.util.concurrent.TimeUnit;
import java.util.Random;

class RiderSpawner implements Runnable {
    private BusStop busStop;
    private int riderIdCounter = 1;
    private double meanRiderArrivalTimeMillis;
    private Random random = new Random();

    public RiderSpawner(BusStop busStop, double meanRiderArrivalTimeMinutes) {
        this.busStop = busStop;
        this.meanRiderArrivalTimeMillis = meanRiderArrivalTimeMinutes * 100; // Convert minutes to milliseconds
    }

    @Override
    public void run() {
        while (true) {

            Thread rider = new Thread(new Rider(busStop, riderIdCounter)); // create a thread for each rider.
            rider.start();

            // calculate exponentially distributed arrival time for the next rider
            double nextArrivalTimeMillis = -meanRiderArrivalTimeMillis * Math.log(random.nextDouble());
            this.riderIdCounter += 1; // increase rider id

            try {
                TimeUnit.MILLISECONDS.sleep((long) nextArrivalTimeMillis);
            } catch (InterruptedException e) {
                System.out.println("Error in riderSwaw class");
                Thread.currentThread().interrupt();
            }
        }
    }
}