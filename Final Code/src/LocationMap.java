import java.io.*;
import java.util.*;
//class that behaves like a map
public class LocationMap implements Map<Integer, Location> {

    private static final String LOCATIONS_FILE_NAME = "locations.txt";
    private static final String DIRECTIONS_FILE_NAME = "directions.txt";

    private static HashMap<Integer, Location> locations = new HashMap<>(); //map of all locations

    static {

        FileLogger fileLog = new FileLogger();
        ConsoleLogger consLog = new ConsoleLogger();

        /**
         * Read from LOCATIONS_FILE_NAME so that a user can navigate from one location to another
         * use try-with-resources/catch block for the FileReader
         * extract the location and the description on each line
         * print all locations and descriptions to both console and file
         * check the ExpectedOutput files
         * put each location in the locations HashMap using temporary empty hashmaps for exits
         */
        consLog.log("Available locations:");
        fileLog.log("Available locations:");
        try (BufferedReader bufferReader = new BufferedReader(new FileReader(LOCATIONS_FILE_NAME))) {
            int i = 0;
            String line;
            while ((line=bufferReader.readLine()) != null) {

                String[] locationDetails = line.split(","); //splits at comma

                //puts a new location object in the hashmap with details
                if (locationDetails.length >2){ //in the case where there are commas in the location descriptions
                    for (int j = 2; j<locationDetails.length;j++){ //loop from the 3rd element to the end
                        locationDetails[1] += ("," + locationDetails[j]); //concatenate the 2nd element with all other elements
                        //and a comma
                    }
                }
                locations.put(i, new Location(
                        Integer.parseInt(locationDetails[0]),
                        locationDetails[1],
                        new HashMap<>()));

                consLog.log(String.format("%s: %s",
                        locationDetails[0], locationDetails[1]));
                fileLog.log(String.format("%s: %s",
                        locationDetails[0], locationDetails[1]));
                i++;
            }
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
        /**
         * Read from DIRECTIONS_FILE_NAME so that a user can move from A to B, i.e. current location to next location
         * use try-with-resources/catch block for the FileReader
         * extract the 3 elements  on each line: location, direction, destination
         * print all locations, directions and destinations to both console and file
         * check the ExpectedOutput files
         * for each location, create a new location object and add its exit
         */
        consLog.log("Available directions:");
        fileLog.log("Available directions:");

        try (BufferedReader frd = new BufferedReader(new FileReader(DIRECTIONS_FILE_NAME))) {
            String line;
            while ((line = frd.readLine()) != null) {

                String[] lineDetails = line.split(",");

                locations.get(Integer.parseInt(lineDetails[0])).addExit
                        (lineDetails[1], Integer.parseInt(lineDetails[2]));
                //adds the next exit to the appropriate location object in the 'locations' map
                fileLog.log(String.format("%s: %s: %s",
                        lineDetails[0], lineDetails[1], lineDetails[2]));
                consLog.log(String.format("%s: %s: %s",
                        lineDetails[0], lineDetails[1], lineDetails[2]));
            }

        } catch (FileNotFoundException e) {

        } catch (IOException e) {
        }
    }

    /**
     * implement all methods for Map
     *
     * @return
     */
    @Override
    public int size() {
        //
        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        //
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        //
        return locations.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        //
        return locations.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        //

        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        //
        return locations.put(key, value);
    }

    @Override
    public Location remove(Object key) {
        //
        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {
        locations.putAll(m);
    }

    @Override
    public void clear() {
        locations.clear();
    }

    @Override
    public Set<Integer> keySet() {
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        //
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        //
        return locations.entrySet();
    }
}
