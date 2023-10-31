public class Bus implements Runnable {
    private final BusStop busStop;
    private static final int riderLimit = 50;
    private int onboardCount;
    private int id;

    public Bus(BusStop busStop, int id) {
        this.busStop = busStop;
        this.onboardCount = 0;
        this.id = id;
    }

    public int getOnboardCount() {
        return onboardCount;
    }

    public void setOnboardCount(int onboardCount) {
        this.onboardCount = onboardCount;
    }

    @Override
    public void run() {
        try {
            busStop.getBusLock().acquire();             //avoid new riders when bus is at stop
            System.out.println("The bus" + this.id + " is arrived at the bus stop");

            int waitingCount = busStop.getWaitingCount();
            int onboardCount = Math.min(waitingCount,Bus.riderLimit);

            if (busStop.getWaitingCount() > 0) {
                busStop.setBus(this);
                busStop.getWaitForBus().release(); // wake riders in waiting queue
                busStop. getReadyToDeparture().acquire();   //departure if all the riders are onboard
            }
            System.out.println("Bus" + this.id + " departed with " + onboardCount + " riders out of " + waitingCount + " riders");
            busStop.getBusLock().release();
        } catch (InterruptedException e) {
            System.out.println("Error in Bus");
        }
    }
}