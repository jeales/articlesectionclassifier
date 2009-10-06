/*
 * TitleData.java
 *
 * Created on 28 August 2007, 19:34
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package articlesectionclassifier.util;

import java.util.HashSet;

/**
 *
 * @author James
 */
public class SectionData {

    private static String[] introtitles = {"Background", "Introduction", "Context"};
    private static String[] methodtitles = {"Methods", "Materials and methods", "Materials and Methods", "Implementation",
        "Patients and methods", "Method", "Material and Methods", "Material and methods", "Methods/design", "Methods/Design", "MATERIALS AND METHODS"};
    private static String[] resulttitles = {"Results"};
    private static String[] discussiontitles = {"Discussion", "Conclusion", "Conclusions", "Key messages", "Summary",
        "Findings", "Discussion and conclusion", "Significant findings", "DISCUSSION"};
    private static String[] introtypes = {"introduction"};
    private static String[] methodtypes = {"materials|methods", "materials", "methods"};
    private static String[] resulttypes = {"results"};
    private static String[] discussiontypes = {"discussion"};

    /**
     * Holds static strings for section titles and
     * types for each of the four section types.<br><br>
     * Use <code>containsTitle(String toFind)</code>
     * and <code>containsType(String toFind)</code> methods
     * to evaluate whether your section title or
     * type map to one of the section types.<br><br>
     * You can also use the <code>incrementCount()</code>
     * and <code>getCount()</code>
     * methods to keep track of numbers of
     * section types in your corpus.<br><br>
     *
     * Search data:<br><br>
     * Time in millis to search for a string 10000000 times.<br>
     * Binary Search: 875<br>
     * HashSet: 203<br>
     * Fori array: 734<br>
     * This class uses HashSets of titles and types.
     * <br><br>
     * Use this kind of code to iterate through the section types:
     * <pre>{@code for (TitleTypes elem : TitleData.TitleTypes.values()) {
     *  ..do something..
     *}
     *}</pre>
     *
     */
    public static enum SectionType {

        /**
         *
         */
        INTRODUCTION(introtitles, introtypes),
        /**
         *
         */
        METHODS(methodtitles, methodtypes),
        /**
         *
         */
        RESULTS(resulttitles, resulttypes),
        /**
         *
         */
        DISCUSSION(discussiontitles, discussiontypes);
        private int count;
        private HashSet<String> titles;
        private HashSet<String> types;

        SectionType(String[] ts, String[] tys) {

            //init hashsets
            titles = new HashSet<String>();
            types = new HashSet<String>();
            //populate hashsets
            for (int i = 0; i < ts.length; i++) {
                titles.add(ts[i]);
            }
            for (int i = 0; i < tys.length; i++) {
                types.add(tys[i]);
            }
            //set section type counter variable to 0
            count = 0;

        }

        /**
         *
         */
        public void incrementCount() {
            count++;
        }

        /**
         *
         * @return returns an int representing the count of this section type.
         */
        public int getCount() {
            return count;
        }

        /**
         *
         * @param toFind
         * @return returns a boolean of whether the title to find is linked to this section type
         */
        public boolean containsTitle(String toFind) {
            boolean answer = false;
            //use fast hashset contains method
            //quicker than for/array or Collection binary search
            answer = titles.contains(toFind);

            return answer;
        }

        /**
         *
         * @param toFind
         * @return returns a boolean of whether the type to find is linked to this section type
         */
        public boolean containsType(String toFind) {
            boolean answer = false;
            //use fast hashset contains method
            //quicker than for/array or Collection binary search
            answer = types.contains(toFind);

            return answer;
        }
    }
}
