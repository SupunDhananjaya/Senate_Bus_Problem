// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        BusStop busStop = new BusStop();



        for(int i = 0; i < 5; i++){
            Thread riderThread = new Thread(new Rider(busStop,i));
            riderThread.start();
        }

        Thread busThread1 = new Thread(new Bus(busStop,1));

        busThread1.start();

        for(int i = 0; i < 80; i++){
            Thread riderThread = new Thread(new Rider(busStop,i));
            riderThread.start();
        }

        Thread busThread2 = new Thread(new Bus(busStop,2));

        busThread2.start();

        Thread busThread3 = new Thread(new Bus(busStop,3));

        busThread3.start();

    }
}