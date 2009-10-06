/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package articlesectionclassifier.beans.datastore;

import articlesectionclassifier.beans.TokenData;
import java.io.InputStream;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jameseales
 */
public class ClassifierDataStore {

    private double introductionClassProb, methodsClassProb, resultsClassProb, discussionClassProb;
    private HashMap<String, TokenData> tokenData;
    private final String resourceName = "datastore.dump";

    private ClassifierDataStore(boolean l) {
        tokenData = new HashMap<String, TokenData>();
        if (l) {
            try {
                loadData();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param loadData do you want to load the default datastore or not
     * @return Returns a ClassifierDataStore with or without default data loaded.
     */
    public static ClassifierDataStore getNewDataStore(boolean loadData) {
        ClassifierDataStore d = new ClassifierDataStore(loadData);
        return d;
    }

    private void loadData() throws Exception {
        InputStream is = getClass().getClassLoader().getResourceAsStream(resourceName);
        ClassifierDataStoreReader.readDataStore(is, this);
    }

    /**
     *
     * @return The probability of any section being a Discussion
     */
    public double getDiscussionClassProb() {
        return discussionClassProb;
    }

    /**
     *
     * @param discussionClassProb
     */
    public void setDiscussionClassProb(double discussionClassProb) {
        this.discussionClassProb = discussionClassProb;
    }

    /**
     *
     * @return The probability of any section being an Introduction
     */
    public double getIntroductionClassProb() {
        return introductionClassProb;
    }

    /**
     *
     * @param introductionClassProb
     */
    public void setIntroductionClassProb(double introductionClassProb) {
        this.introductionClassProb = introductionClassProb;
    }

    /**
     *
     * @return The probability of any section being a Methods section
     */
    public double getMethodsClassProb() {
        return methodsClassProb;
    }

    /**
     *
     * @param methodsClassProb
     */
    public void setMethodsClassProb(double methodsClassProb) {
        this.methodsClassProb = methodsClassProb;
    }

    /**
     *
     * @return The probability of any section being a Results section
     */
    public double getResultsClassProb() {
        return resultsClassProb;
    }

    /**
     *
     * @param resultsClassProb
     */
    public void setResultsClassProb(double resultsClassProb) {
        this.resultsClassProb = resultsClassProb;
    }

    /**
     *
     * @return Returns all TokenData objects stored in a HashMap organised by their token.
     */
    public HashMap<String, TokenData> getTokenData() {
        return tokenData;
    }

    /**
     *
     * @param tokenData
     */
    public void setTokenData(HashMap<String, TokenData> tokenData) {
        this.tokenData = tokenData;
    }

    /**
     *
     * @param token
     * @return Returns the TokenData object for the given token string.
     */
    public TokenData getTokenData(String token) {
        return tokenData.get(token);
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        long s = System.currentTimeMillis();
        try {
            ClassifierDataStore cd = ClassifierDataStore.getNewDataStore(true);

        } catch (Exception ex) {
            Logger.getLogger(ClassifierDataStore.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(System.currentTimeMillis() - s);
        System.out.println("");
    }
}
