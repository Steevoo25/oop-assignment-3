import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Location {

    /**
     * declare private final locationId, description, exits
     */
    private final int locationId;
    private final String description;
    private final LinkedHashMap<String, Integer> exits = new LinkedHashMap<>();

    public Location(int locationId, String description, Map<String, Integer> exits) {

        this.locationId = locationId;
        this.description = description;

        if (exits != null){
            this.exits.putAll(exits);
        }
        else this.exits.put("Q", 0);
        /**
         * if exits are not null, set the exit
         * otherwise, set the exit HashMap to (Q,0)
         */
    }

    protected void addExit(String direction, int location) {
        exits.put(direction,location);
    }

    public int getLocationId() {
        return this.locationId;
    }

    public String getDescription() {
        return this.description;
    }

    public Map<String, Integer> getExits() {
        HashMap<String,Integer> m = new HashMap<>();
        m.putAll(this.exits);
        
        //return a copy of exits
        return m;
    }
}
