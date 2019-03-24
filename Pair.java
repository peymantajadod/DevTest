
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * This class is to be used as the object type of the elements of the list (the
 * list of key/value pairs).
 *
 *
 * @author peymantajadod
 */
public class Pair {

    private String key;
    private String value;

    /**
     * This is the constructor of the class. Makes sure the input is in the
     * correct format. Throws an exception if the format of the input is
     * incorrect.
     *
     * @param word the input to become an object type of pair
     */
    public Pair(String word) throws IllegalArgumentException {
        if (isInFormat(word)) {
            String[] key_Value = word.split("=");
            this.key = key_Value[0].trim();
            this.value = key_Value[1].trim();
        } else {
            throw new IllegalArgumentException("The input is not in the correct format.");
        }

    }

    /**
     *
     * @return the key of the pair
     */
    public String getKey() {
        return this.key;
    }

    /**
     *
     * @return the value of the pair
     */
    public String getValue() {
        return this.value;
    }

    /**
     * This method is to check if the input is in the defined format .
     *
     * @param word
     * @return true if the word is in the correct format. If not, returns false.
     */
    public boolean isInFormat(String word) {
        //Checking for the number and the existance of equal sign (=).
        if (Collections.frequency(word.chars().mapToObj(i -> (char) i).collect(Collectors.toList()), '=') == 1) {
            //Using the equal sign to delimit the pair
            String[] key_value = word.split("=");
            //Checking for the existance of key and value both
            if (key_value.length == 2) {
                //Keys and values can only contain alphya-numeric characters
                if (key_value[0].matches("[A-Za-z0-9 ]+") && key_value[1].matches("[A-Za-z0-9 ]+")) {
                    return true;
                }
            }

        }

        return false;
    }

    /**
     * This method overrides the hashCode method of Object and it is to be used
     * for the purpose of finding equal objects of type Pair by giving each
     * object a value of Integer as a hash code.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.key);
        hash = 41 * hash + Objects.hashCode(this.value);
        return hash;
    }

    /**
     * This method overrides the equal method of Object. it is to be used for
     * finding to equal objects of type Pair. Checks for the class type and the
     * keys and values.
     *
     * @param obj to be compared with
     * @return true if tow objects are equal.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pair other = (Pair) obj;
        if (!(key.equals(other.key))) {
            return false;
        }
        if (!(value.equals(other.value))) {
            return false;
        }
        return true;
    }

    /**
     * This method override the toString method of Object.
     *
     * @return a string format of the pair in the "<key>==<value>" format
     */
    @Override
    public String toString() {
        String s = "";
        return s += getKey() + "=" + getValue();
    }

}
