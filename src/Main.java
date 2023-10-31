// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {

                System.out.println("we assumed a 10 minuites as 1 second:");
                System.out.println("Enter the average Arrival time of a bus in minuites:");
                String input1 = scanner.nextLine(); // Read user input

                System.out.println("Enter the average Arrival time of a rider in minuites:");
                String input2 = scanner.nextLine(); // Read user input

                System.out.println("Type exit and Enter to Exit.");
                System.out.println("Press Enter to start.");
                System.out.println("Press Enter to restart after start application.");
                String command = scanner.nextLine(); // Read user input

                if (command.equals("exit")) { // exit from program
                    System.exit(0);
                } else if (command.equals("")) {

                    Double averageBusArrivalTime = Double.parseDouble(input1);
                    Double averageRiderArrivalTime = Double.parseDouble(input2);
                    long approxbusval = (int) Math.round(averageBusArrivalTime);
                    BusStop busStop = new BusStop();

                    // create busSpqwn and riderSpawn threads
                    Thread busSpawnThread = new Thread(new BusSpawner(busStop, averageBusArrivalTime));
                    Thread riderSpawnThread = new Thread(new RiderSpawner(busStop, averageRiderArrivalTime));

                    // start threads
                    busSpawnThread.start();
                    riderSpawnThread.start();

                    // restart after pressing enter
                    String input = scanner.nextLine();

                    if (input.equals("")) {
                        riderSpawnThread.stop();
                        Thread.sleep(approxbusval * 2 * 100);
                        busSpawnThread.stop();
                        Thread.sleep(100);
                    }
                }
            }
        } catch (InterruptedException e) {
            System.out.println("error in main class");
        }
    }
}