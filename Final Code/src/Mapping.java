import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Mapping {

    public static final int INITIAL_LOCATION = 95;

    static LocationMap locMap = new LocationMap();
    HashMap<String, String> vocabulary = new HashMap<>();

    FileLogger fileLog = new FileLogger();
    ConsoleLogger consoleLog = new ConsoleLogger();

    public Mapping() {
        vocabulary.put("QUIT", "Q"); //all possible inputs for directions
        vocabulary.put("NORTH", "N");
        vocabulary.put("NORTHEAST", "NE");
        vocabulary.put("EAST", "E");
        vocabulary.put("SOUTHEAST", "SE");
        vocabulary.put("SOUTH", "S");
        vocabulary.put("SOUTHWEST", "SW");
        vocabulary.put("WEST", "W");
        vocabulary.put("NORTHWEST", "NW");
        vocabulary.put("UP", "U");
        vocabulary.put("DOWN", "D");
    }

    public void mapping() {

        Scanner sc = new Scanner(System.in);
        Location tempLoc = locMap.get(INITIAL_LOCATION);

        boolean isFinished = false;
        while (!isFinished) {
            //print current location to console and file
            consoleLog.log(tempLoc.getDescription());
            fileLog.log(tempLoc.getDescription());
            //if the user has chosen to quit, quit
            if (tempLoc.getLocationId() != 0) {
                // get a map of the exits for the location
                LinkedHashMap<String, Integer> exitsMap;
                exitsMap = new LinkedHashMap<>(tempLoc.getExits());
                //Print available exits to file and console
                StringBuilder sb = new StringBuilder("Available exits are ");

                for (Map.Entry exit : exitsMap.entrySet()) {
                    sb.append(exit.getKey()).append(", ");
                }
                consoleLog.log(sb.toString());
                fileLog.log(sb.toString());

                //Input direction and make it upper case
                String directionInput = sc.nextLine().toUpperCase();

                /**
                 * are we dealing with a letter / word for the direction to go to?
                 * available inputs are: a letter(the HashMap value), a word (the HashMap key), a string of words that contains the key
                 * crosscheck with the ExpectedInput and ExpectedOutput files for examples of inputs
                 * if the input contains multiple words, extract each word
                 * find the direction to go to using the vocabulary mapping
                 * if multiple viable directions are specified in the input, choose the last one
                 */

                String[] dirArr = directionInput.split(" ");
                String nextDir = "";

                for (String s : dirArr) { //check each word in the sentence - if it is already in short form of id it is in long form

                    if (vocabulary.containsKey(s)){ //checking for word
                        nextDir = vocabulary.get(s);
                    } else if (vocabulary.containsValue(s) && dirArr.length ==1){ //checking for key - if it is it cannot be part of a sentence
                        nextDir = s;
                    }

                }
                if (tempLoc.getExits().containsKey(nextDir)) {
                    int nextLoc = tempLoc.getExits().get(nextDir);
                    tempLoc = locMap.get(nextLoc);
                } else {
                    consoleLog.log("You cannot go in that direction");
                    fileLog.log("You cannot go in that direction");
                }
                /**
                 * if user can go in that direction, then set the location to that direction
                 * otherwise print an error message (to both console and file)
                 * check the ExpectedOutput files
                 */
            } else {
                isFinished = true;
            }
        }
    }

    public static void main(String[] args) {
        Mapping game = new Mapping();
        game.mapping();
    }
}