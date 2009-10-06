/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package articlesectionclassifier.beans;

/**
 *
 * @author jameseales
 */
public class StringValuePair implements Comparable<StringValuePair> {

    private String string;
    private double value;

    /**
     *
     */
    public StringValuePair() {
    }

    /**
     *
     * @param string
     * @param value
     */
    public StringValuePair(String string, double value) {
        this.string = string;
        this.value = value;
    }

    /**
     *
     * @return Returns the String
     */
    public String getString() {
        return string;
    }

    /**
     *
     * @param string
     */
    public void setString(String string) {
        this.string = string;
    }

    /**
     *
     * @return Returns the double value
     */
    public double getValue() {
        return value;
    }

    /**
     *
     * @param value
     */
    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return getString() + "\t" + getValue();
    }

    @Override
    public int compareTo(StringValuePair o) {
        int r = 0;
        if (o instanceof StringValuePair) {
            StringValuePair other = (StringValuePair) o;
            if (other.getValue() > this.getValue()) {
                r = 1;
            } else if (other.getValue() < this.getValue()) {
                r = -1;
            }
        }

        return r;
    }
}
