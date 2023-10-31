import java.util.concurrent.Semaphore;

public class BusStop {
    private int waitingCount;
    private final Semaphore waitForBus;
    private final Semaphore readyToDeparture;
    private final Semaphore busLock;
    private Bus bus;

    public BusStop() {
        this.waitingCount = 0;
        this.waitForBus = new Semaphore(0);        //signal when bus arrive, wait on bus
        this.readyToDeparture = new Semaphore(0);        //bus wait on till all aboard
        this.busLock = new Semaphore(1);          //rider count protect, avoid new riders when bus is at stop
    }

    public int getWaitingCount() {
        return waitingCount;
    }

    public void setWaitingCount(int waitingCount) {
        this.waitingCount = waitingCount;
    }

    public Semaphore getWaitForBus() {
        return waitForBus;
    }


    public Semaphore getReadyToDeparture() {
        return readyToDeparture;
    }


    public Semaphore getBusLock() {
        return busLock;
    }


    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }
}