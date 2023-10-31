public class Rider implements Runnable {
    private final BusStop busStop;
    private final int id;

    public Rider(BusStop busStop, int id) {
        this.busStop = busStop;
        this.id = id;
    }

    @Override
    public void run() {
        try {

            busStop.getBusLock().acquire();
            busStop.setWaitingCount(busStop.getWaitingCount() + 1);
            System.out.println("The rider" +  id +  " is waiting for the bus");

            busStop.getBusLock().release(); // release the bus lock to avoid deadlock

            // waiting for bus. once the bus release this semaphore, rider can onboard
            busStop.getWaitForBus().acquire();

            System.out.println("Rider " + this.id + " board to the bus");

            busStop.getBus().setOnboardCount(busStop.getBus().getOnboardCount() + 1);
            if(busStop.getBus().getOnboardCount()==50 || busStop.getBus().getOnboardCount()==busStop.getWaitingCount()){
                busStop.setWaitingCount(Math.max(busStop.getWaitingCount() - 50, 0));
                busStop.getReadyToDeparture().release();
            }else {
                busStop.getWaitForBus().release();
            }
        } catch (InterruptedException e) {
            System.out.println("Error in rider class");
        }

    }
}