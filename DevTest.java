
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author peymantajadod
 */
public class DevTest {

    private final List<Pair> list;

    /**
     * The constructor of the class to instantiate an object of type
     * DevTest which is instantiating the list.
     */
    public DevTest() {
        this.list = new ArrayList<>();

    }

    /**
     *
     * @return the list
     */
    public List<Pair> getList() {
        return this.list;
    }

    /**
     * Creates and object of type Pair and adds it to the list if the input is
     * in the right format.
     *
     * @param word the input(pair) to be add to the list
     */
    public void addPair(String word) {
        try {
            this.list.add(new Pair(word));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Creates a pair of the input and removes it if the pair exist in the list.
     * the overridden methods of hashCode() and equal() in the class Pair will
     * be used here to find the equal Pair objects and removing it.
     *
     * @param word the input to be removed
     * @throws IllegalStateException if the list empty so there is no elements
     * to be removed
     */
    public void removePair(String word) throws IllegalStateException {
        if (this.list.isEmpty()) {
            throw new IllegalStateException("the list is empty");
        } else {
            this.list.remove(new Pair(word));
        }
    }

    /**
     * Clears the list.
     */
    public void clearList() {
        this.list.clear();

    }

    /**
     * Sorts the list by the keys (names) Uses an anonymous inner class of
     * Comparator for comparing the keys only
     */
    public void sortByKey() {
        this.list.sort(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                if (o1.getKey().compareTo(o2.getKey()) < 0) {
                    return -1;
                } else if (o1.getKey().compareTo(o2.getKey()) == 0) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
    }

    /**
     * Sorts the list by the values Uses an anonymous inner class of Comparator
     * for comparing the keys only
     */
    public void sortByValue() {
        this.list.sort(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                if (o1.getValue().compareTo(o2.getValue()) < 0) {
                    return -1;
                } else if (o1.getValue().compareTo(o2.getValue()) == 0) {
                    return 0; // You can change this to make it then look at the
                    //words alphabetical order
                } else {
                    return 1;
                }
            }
        });
    }

    /**
     * Overrides the toString method of OBject
     *
     * @return a string of the list. pair by pair in each line by using Pair
     * toString() method
     */
    @Override
    public String toString() {
        String s = "";
        for (Pair p : this.list) {
            s += p.toString() + "\n";
        }
        return s;
    }

    /**
     * Generates an XML file of the list The DevTest (list) is the root element
     * and each pair is its child. Keys and values are the child of each pair.
     */
    public void generateXML() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            doc.setXmlStandalone(true);
            Element rootElement = doc.createElement("DevTestList");
            doc.appendChild(rootElement);

            for (int i = 0; i < this.list.size(); i++) {

                Element pairElement = doc.createElement("pair");
                rootElement.appendChild(pairElement);

                Element keyElement = doc.createElement("key");
                keyElement.setTextContent(this.list.get(i).getKey());
                pairElement.appendChild(keyElement);

                Element valueElement = doc.createElement("value");
                valueElement.setTextContent(this.list.get(i).getValue());
                pairElement.appendChild(valueElement);

            }

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            StreamResult result = new StreamResult(new File("DevTest.xml"));
            DOMSource source = new DOMSource(doc);
            transformer.transform(source, result);

        } catch (ParserConfigurationException | TransformerException | DOMException e) {
            System.out.println(e.getMessage());
        }
    }
}
