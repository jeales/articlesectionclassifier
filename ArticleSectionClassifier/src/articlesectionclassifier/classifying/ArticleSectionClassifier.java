/*
 * ArticleSectionClassifier.java
 *
 * Created on 29 October 2007, 14:04
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package articlesectionclassifier.classifying;

import articlesectionclassifier.beans.ClassificationInput;
import articlesectionclassifier.beans.ClassificationResult;
import articlesectionclassifier.beans.StringValuePair;
import articlesectionclassifier.beans.TokenData;
import articlesectionclassifier.beans.datastore.ClassifierDataStore;
import articlesectionclassifier.util.SectionData;
import articlesectionclassifier.util.SectionData.SectionType;
import articlesectionclassifier.util.TextTokeniser;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *This is the central classification class.
 *It can be used to classify text sections (with or without titles) with a variety of inputs and outputs.
 *The general way to use it is to create an instance of the class and then call one of the <code>classifyText</code> methods.
 *You can make repeated calls to these methods and the results will be provided by a <code>ClassificationResult</code> object as well as by the method <code>getLastClassificationResult()</code>.
 * @author James
 */
public class ArticleSectionClassifier {

    private ClassificationResult lastClassificationResult;
    private ClassificationInput lastClassificationInput;
    private static ClassifierDataStore dataStore;
    private int topTokens = 200;

    /** Creates a new instance of ArticleSectionClassifier */
    public ArticleSectionClassifier() {
        initialiseData();
        lastClassificationResult = new ClassificationResult();
        lastClassificationInput = new ClassificationInput();
    }

    /**
     *Sets the number of token to consider in the probability calculation
     * @param topTokens
     */
    public void setTopTokens(int topTokens) {
        this.topTokens = topTokens;
    }

    /**
     *
     * @return Gets the number of token to consider in the probability calculation
     */
    public int getTopTokens() {
        return topTokens;
    }

    private void setLastClassificationInput(ClassificationInput lastClassificationInput) {
        this.lastClassificationInput = lastClassificationInput;
    }

    private void setLastClassificationResult(ClassificationResult lastClassificationResult) {
        this.lastClassificationResult = lastClassificationResult;
    }

    /**
     *
     * @return Get the last submitted ClassificationInput or null
     */
    public ClassificationInput getLastClassificationInput() {
        return lastClassificationInput;
    }

    /**
     *
     * @return Get the last returned ClassificationResult or null
     */
    public ClassificationResult getLastClassificationResult() {
        return lastClassificationResult;
    }

    private void initialiseData() {
        if (dataStore == null) {
            try {
                dataStore = ClassifierDataStore.getNewDataStore(true);
            } catch (Exception ex) {
                Logger.getLogger(ArticleSectionClassifier.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     *
     * @param textToClassify
     * @param textTitle
     * @return A ClassificationResult representing the full output from the classifier.
     */
    public ClassificationResult classifyText(String textToClassify, String textTitle) {
        ClassificationInput i = new ClassificationInput();
        i.setTextToClassify(textToClassify);
        i.setTextTitle(textTitle);
        ClassificationResult r = classifyText(i);
        return r;
    }

    /**
     * @param input 
     * @return Returns a <code>ClassificationResult</code> object holding all the classification details from a <code>ClassificationInput</code> object.
     */
    public ClassificationResult classifyText(ClassificationInput input) {
        setLastClassificationInput(input);
        ClassificationResult result = null;

        result = doClassification(input);

        setLastClassificationResult(result);
        return result;
    }

    /**
     *
     * @param textToClassify 
     * @return Returns (as a <code>String</code>) the section the text passed to this method is most likely to have come from.
     */
    public String classifyText(String textToClassify) {
        ClassificationInput i = new ClassificationInput();
        i.setTextToClassify(textToClassify);
        ClassificationResult r = classifyText(i);
        String r2 = r.getClassifiedAs();
        return r2;
    }

    /**
     *This is the main classification method called by the public <code>classifyText</code> methods.
     */
    private ClassificationResult doClassification(ClassificationInput input) {
        ClassificationResult res = new ClassificationResult();
        res.setTextThatWasClassified(input.getTextToClassify());
        res.setTitleThatWasClassified(input.getTextTitle());

        //if we have a title to work with then look through the lists for it.
        if (!input.getTextTitle().equals("")) {
            String title = input.getTextTitle();
            for (SectionType elem : SectionData.SectionType.values()) {
                if (elem.containsType(title)) {
                    res.setClassifiedAs(elem.toString());
                    res.setClassificationMethod(ClassificationResult.BY_TITLE);
                } else if (elem.containsTitle(title)) {
                    res.setClassifiedAs(elem.toString());
                    res.setClassificationMethod(ClassificationResult.BY_TITLE);
                }
            }
        } else {
            res.setClassificationMethod(ClassificationResult.BY_BAYESIAN);
            getWordProbs(input, res);
        }

        return res;
    }

    private void getWordProbs(ClassificationInput input, ClassificationResult result) {
        String text = input.getTextToClassify();
        TextTokeniser bt = new TextTokeniser(text);
        HashSet<String> allToks = bt.getAllUnqiueTokens();
        LinkedList<TokenData> allTokenData = new LinkedList<TokenData>();

        for (String string : allToks) {
            TokenData datum = dataStore.getTokenData(string);
            if (datum != null) {
                allTokenData.add(datum);
            }
        }

        Collections.sort(allTokenData);

        List<TokenData> topToks = null;
        if (allTokenData.size() > topTokens) {
            topToks = allTokenData.subList(0, topTokens);
        } else {
            topToks = allTokenData.subList(0, allTokenData.size());
        }


        double iS = Math.log(dataStore.getIntroductionClassProb());
        double mS = Math.log(dataStore.getMethodsClassProb());
        double rS = Math.log(dataStore.getResultsClassProb());
        double dS = Math.log(dataStore.getDiscussionClassProb());


        for (TokenData tokenData : topToks) {
            iS += Math.log(tokenData.getIntroductionProb());
            mS += Math.log(tokenData.getMethodsProb());
            rS += Math.log(tokenData.getResultsProb());
            dS += Math.log(tokenData.getDiscussionProb());
        }

        LinkedList<StringValuePair> finalResults = new LinkedList<StringValuePair>();

        StringValuePair iP = new StringValuePair();
        iP.setString("INTRODUCTION");
        iP.setValue(iS);
        finalResults.add(iP);

        StringValuePair mP = new StringValuePair();
        mP.setString("METHODS");
        mP.setValue(mS);
        finalResults.add(mP);

        StringValuePair rP = new StringValuePair();
        rP.setString("RESULTS");
        rP.setValue(rS);
        finalResults.add(rP);

        StringValuePair dP = new StringValuePair();
        dP.setString("DISCUSSION");
        dP.setValue(dS);
        finalResults.add(dP);

        Collections.sort(finalResults);

        result.setAllLikelihoods(finalResults);
        String topClass = finalResults.getFirst().getString();
        result.setClassifiedAs(topClass);
        double topLl = finalResults.getFirst().getValue();
        result.setLogLikelihood(topLl);

        LinkedList<StringValuePair> usedForClassification = new LinkedList<StringValuePair>();
        for (TokenData tokenData : topToks) {
            StringValuePair p = new StringValuePair();
            p.setString(tokenData.getToken());
            if (topClass.equals("INTRODUCTION")) {
                p.setValue(Math.log(tokenData.getIntroductionProb()));
            } else if (topClass.equals("METHODS")) {
                p.setValue(Math.log(tokenData.getMethodsProb()));
            } else if (topClass.equals("RESULTS")) {
                p.setValue(Math.log(tokenData.getResultsProb()));
            } else if (topClass.equals("DISCUSSION")) {
                p.setValue(Math.log(tokenData.getDiscussionProb()));
            }
            usedForClassification.add(p);
        }

        result.setTokensUsedForClassification(usedForClassification);
    }
}
