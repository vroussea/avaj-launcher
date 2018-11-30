package tower;

import exception.IllegalInstructionInFileException;
import flyable.AircraftFactory;
import flyable.Flyable;

import java.io.*;
import java.util.ArrayList;

public class AvajLauncher {

    public static void main(final String[] args) {
        if (!(args.length > 0))
            System.out.println("No file specified.");
        else {
            for (int i = 0; i < args.length; i++) {
                createSimulationFile(i + 1, args[i]);
            }
        }
    }

    private static void createSimulationFile(int simulationNumber, String fileName) {
        if (!fileName.endsWith(".txt"))
            System.out.println("Error with " + fileName + " :\nOnly .txt files accepted.");
        else {
            final PrintStream originalOut = System.out;
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
                PrintWriter writer = new PrintWriter("simulation" + simulationNumber + ".txt", "UTF-8");
                System.setOut(outputFile("simulation" + simulationNumber + ".txt"));
                readScenario(bufferedReader);
                System.setOut(originalOut);
                writer.close();
            } catch (Exception e) {
                System.setOut(originalOut);
                e.printStackTrace();
            }
        }
    }

    private static void readScenario(BufferedReader bufferedReader) throws IOException {
        int runTimes = 0;
        ArrayList<Flyable> aircrafts = new ArrayList<>();
        for (String line; (line = bufferedReader.readLine()) != null; ) {
            if (line.matches("[0-9]+")) {
                runTimes = Integer.valueOf(line);
            } else if (line.matches("^(Baloon|Helicopter|JetPlane) [a-zA-z0-9]+ [0-9]+ [0-9]+ [0-9]+")) {
                String[] aircraftValues = (line.split(" "));
                aircrafts.add(AircraftFactory.newAircraft(aircraftValues[0].toLowerCase(),
                        aircraftValues[1],
                        Integer.valueOf(aircraftValues[2]),
                        Integer.valueOf(aircraftValues[3]),
                        Integer.valueOf(aircraftValues[4])));
            } else {
                throw new IllegalInstructionInFileException(line);
            }
        }

        runSimulation(runTimes, aircrafts);
    }

    private static void runSimulation(int runTimes, ArrayList<Flyable> aircrafts) {
        WeatherTower tower = new WeatherTower();

        for (Flyable aircraft : aircrafts) {
            tower.register(aircraft);
        }

        for (int i = 0; i < runTimes; i++) {
            tower.changeWeather();
        }
    }

    private static PrintStream outputFile(String name) throws FileNotFoundException {
        return new PrintStream(new BufferedOutputStream(new FileOutputStream(name)), true);
    }
}
