/*
 * ClassificationResult.java
 *
 * Created on 28 August 2007, 19:25
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package articlesectionclassifier.beans;

import java.util.LinkedList;

/**
 * A bean object that holds data provided by the {@link articlesectionclassifier.classifying.ArticleSectionClassifier} after classification.
 * @author James
 */
public class ClassificationResult {

    private String textThatWasClassified;
    private String titleThatWasClassified;
    private String classificationMethod;
    private String classifiedAs;
    private double logLikelihood;
    private LinkedList<StringValuePair> allLikelihoods;
    private LinkedList<StringValuePair> tokensUsedForClassification;
    //classification method fields
    /**
     *A static field used to represent text classification by the Bayesian method.
     */
    public static String BY_BAYESIAN = "Bayesian Method";
    /**
     *A static field used to represent text classification by the title recognition method.
     */
    public static String BY_TITLE = "Title Recognition Method";
    /**
     *A static field used to represent text classification when it has produced an error or has not been possible.
     */
    public static String NO_CLASSIFICATION = "Classification Not Performed";

    /**
     *
     * @return Returns the tokens chosen to classify the text as a {@link java.util.LinkedList} of {@link articlesectionclassifier.beans.StringValuePair}s.
     */
    public LinkedList<StringValuePair> getTokensUsedForClassification() {
        return tokensUsedForClassification;
    }

    /**
     *
     * @param tokensUsedForClassification
     */
    public void setTokensUsedForClassification(LinkedList<StringValuePair> tokensUsedForClassification) {
        this.tokensUsedForClassification = tokensUsedForClassification;
    }

    /**
     *
     * @return Returns the title (if a title was submitted) that was passed to the classifier.
     */
    public String getTitleThatWasClassified() {
        return titleThatWasClassified;
    }

    /**
     *
     * @param titleThatWasClassified
     */
    public void setTitleThatWasClassified(String titleThatWasClassified) {
        this.titleThatWasClassified = titleThatWasClassified;
    }

    /**
     *
     * @return Returns the classification method field corresponding to the method that was used to produce this object.
     */
    public String getClassificationMethod() {
        return classificationMethod;
    }

    /**
     *
     * @param classificationMethod
     */
    public void setClassificationMethod(String classificationMethod) {
        this.classificationMethod = classificationMethod;
    }

    /**
     *
     * @return Returns the log likelihoods of the text submitted being present in any of the four standard article section as a {@link java.util.LinkedList} of {@link articlesectionclassifier.beans.StringValuePair}s.
     */
    public LinkedList<StringValuePair> getAllLikelihoods() {
        return allLikelihoods;
    }

    /*
     *
     */
    /**
     *
     * @return Returns the section type that the text was classified as being most likely to belong to.
     * The result will correspond to one of the <code>ENUM</code>s in <code>TitleData</code>.
     */
    public String getClassifiedAs() {
        return classifiedAs;
    }

    /**
     *
     * @return Returns the log likelihood of the submitted text being from the section type returned by <code>getClassifiedAs()</code>.
     */
    public double getLogLikelihood() {
        return logLikelihood;
    }

    /**
     *
     * @return Returns the originally submitted text
     */
    public String getTextThatWasClassified() {
        return textThatWasClassified;
    }

    /**
     *
     * @param allLikelihoods
     */
    public void setAllLikelihoods(LinkedList<StringValuePair> allLikelihoods) {
        this.allLikelihoods = allLikelihoods;
    }

    /**
     *
     * @param classifiedAs
     */
    public void setClassifiedAs(String classifiedAs) {
        this.classifiedAs = classifiedAs;
    }

    /**
     *
     * @param logLikelihood
     */
    public void setLogLikelihood(double logLikelihood) {
        this.logLikelihood = logLikelihood;
    }

    /**
     *
     * @param textThatWasClassified
     */
    public void setTextThatWasClassified(String textThatWasClassified) {
        this.textThatWasClassified = textThatWasClassified;
    }

    /** Creates a new instance of ClassificationResult with default parameters*/
    public ClassificationResult() {
        textThatWasClassified = "";
        classifiedAs = "";
        logLikelihood = 0.0d;
        classificationMethod = ClassificationResult.NO_CLASSIFICATION;
        allLikelihoods = new LinkedList<StringValuePair>();
        tokensUsedForClassification = new LinkedList<StringValuePair>();
    }

    /**
     *Creates a new instance of ClassificationResult using user supplied data.
     *This can be used for holding intermediate or partial results.
     * @param classifiedText
     * @param classifiedAs
     * @param classificationMethod
     * @param logLikelihood
     */
    public ClassificationResult(String classifiedText, String classifiedAs, double logLikelihood, String classificationMethod) {
        this.textThatWasClassified = classifiedText;
        this.classifiedAs = classifiedAs;
        this.logLikelihood = logLikelihood;
        this.classificationMethod = classificationMethod;
        allLikelihoods = new LinkedList<StringValuePair>();
        tokensUsedForClassification = new LinkedList<StringValuePair>();
    }

    /**
     *Returns a <code>String</code> representation of the fields stored by this class
     */
    @Override
    public String toString() {
        /*
         *private String textThatWasClassified;
         *private String classifiedAs;
         *private double logLikelihood;
         *private LinkedList<SectionTypeLikelihoodBean> allLikelihoods;
         */

        StringBuilder sb = new StringBuilder();

        sb.append("Classified As:\t" + this.getClassifiedAs() + "\n");
        sb.append("Log Likelihood:\t" + this.getLogLikelihood() + "\n");
        sb.append("Classification Method:\t" + this.getClassificationMethod() + "\n");
        sb.append("Original Text:\t" + this.getTextThatWasClassified() + "\n");
        sb.append("All Log Likelihoods:\n");
        LinkedList<StringValuePair> b = this.getAllLikelihoods();
        for (StringValuePair elem : b) {
            sb.append("\t\t" + elem.toString() + "\n");
        }
        sb.append("All Tokens Used For Classification:\n");
        LinkedList<StringValuePair> t = this.getTokensUsedForClassification();
        for (StringValuePair elem : t) {
            sb.append("\t\t" + elem.toString() + "\n");
        }

        String r = sb.toString();
        return r;
    }
}
