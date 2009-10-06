/*
 * TextTokeniser.java
 *
 * Created on 14 September 2006, 00:58
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package articlesectionclassifier.util;

import java.util.*;

/**
 *A utility class that provides a series of normalised tokens for analysis by the {@link articlesectionclassifier.classifying.ArticleSectionClassifier} class.
 *A new object must be created for each piece of text.
 *You can iterate through the tokens using:
 *{@code 
 *  TextTokeniser tokeniser = new TextTokeniser("these words will be tokenised");
 *
 * while ( tokeniser.hasMoreTokens() ) {
 *     String token = tokeniser.nextToken();
 *     ..do someting with token..
 * } 
 *}
 *Or you can use {@code getAllTokens()} or {@code getAllUniqueTokens()} to retrieve all the tokens at once as a {@code String} typed {@code LinkedList} (i.e. {@code LinkedList<String>}).
 * @author James Eales
 */
public class TextTokeniser {

    private StringTokenizer st;
    private String text;

    /** Creates a new instance of TextTokeniser
     * @param text
     */
    public TextTokeniser(String text) {
        text = text.replaceAll("[.,/\';:#~!\"�$%^&*()\\[\\]\r\b\t]", "");
        text = text.replaceAll("\n", " ");
        text = text.toLowerCase();
        text = text.trim();
        this.text = text;
        st = new StringTokenizer(text, " ");
    }

    /**
     *
     * @return the next String token from the tokeniser
     */
    public String nextToken() {
        String t = "";

        t = st.nextToken();
        t = t.trim();

        return t;
    }

    /**
     *
     * @return A HashSet<String> of all the unique tokens from the text
     */
    public HashSet<String> getAllUnqiueTokens() {
        HashSet<String> uniques = new HashSet<String>();

        StringTokenizer s = new StringTokenizer(text, " ");
        while (s.hasMoreTokens()) {
            String t = s.nextToken();
            t = t.trim();
            uniques.add(t);
        }

        return uniques;
    }

    /**
     *
     * @return a Vector<String> of all the tokens from the text
     */
    public Vector<String> getAllTokens() {
        Vector<String> all = new Vector<String>();

        StringTokenizer s = new StringTokenizer(text, " ");
        while (s.hasMoreTokens()) {
            String t = s.nextToken();
            t = t.trim();
            all.add(t);
        }

        return all;
    }

    /**
     *
     * @return if the tokeniser has more tokens
     */
    public boolean hasMore() {
        return st.hasMoreTokens();
    }
}
