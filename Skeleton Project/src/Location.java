import java.util.LinkedHashMap;
import java.util.Map;

public class Location {

    /** TODO
     * declare private final locationId, description, exits
     */



    public Location(int locationId, String description, Map<String, Integer> exits) {
        /** TODO
         * set the locationId and the description
         */

        /** TODO
         * if exits are not null, set the exit
         * Note that exits should be of type LinkedHashMap to maintain the insertion order
         * otherwise, set the exits LinkedHashMap to (Q,0)
         */
    }

    protected void addExit(String direction, int location) {
        /** TODO
         * put the direction and the location in the exits LinkedHashMap
         */
    }

    public int getLocationId() {
        /** TODO
         * complete getter to return the location id
         */
        return 0;
    }

    public String getDescription() {
        /** TODO
         * complete getter to return the description
         */
        return null;
    }

    public Map<String, Integer> getExits() {
        /** TODO
         * complete getter to return a copy of exits
         * (preventing modification of exits from outside the Location instance)
         */
        return null;
    }
}
