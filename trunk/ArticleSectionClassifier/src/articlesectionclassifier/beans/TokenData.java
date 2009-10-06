/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package articlesectionclassifier.beans;

import articlesectionclassifier.util.SectionData.SectionType;

/**
 *
 * @author jameseales
 */
public class TokenData implements Comparable<TokenData> {

    private String token;
    private double coefficientOfVariation, introductionProb, methodsProb, resultsProb, discussionProb;

    /**
     *
     */
    public TokenData() {
    }

    /**
     *
     * @param token
     * @param coefficientOfVariation
     * @param introductionProb
     * @param methodsProb
     * @param resultsProb
     * @param discussionProb
     */
    public TokenData(String token, double coefficientOfVariation, double introductionProb, double methodsProb, double resultsProb, double discussionProb) {
        this.token = token;
        this.coefficientOfVariation = coefficientOfVariation;
        this.introductionProb = introductionProb;
        this.methodsProb = methodsProb;
        this.resultsProb = resultsProb;
        this.discussionProb = discussionProb;
    }

    /**
     *
     * @return get a double of the coefficient of variation for this token.
     */
    public double getCoefficientOfVariation() {
        return coefficientOfVariation;
    }

    /**
     *
     * @param coefficientOfVariation
     */
    public void setCoefficientOfVariation(double coefficientOfVariation) {
        this.coefficientOfVariation = coefficientOfVariation;
    }

    /**
     *
     * @return gets a double of the probability of this token occuring in a Discussion section
     */
    public double getDiscussionProb() {
        return discussionProb;
    }

    /**
     *
     * @param discussionProb
     */
    public void setDiscussionProb(double discussionProb) {
        this.discussionProb = discussionProb;
    }

    /**
     *
     * @return gets a double of the probability of this token occuring in a Introduction section
     */
    public double getIntroductionProb() {
        return introductionProb;
    }

    /**
     *
     * @param introductionProb
     */
    public void setIntroductionProb(double introductionProb) {
        this.introductionProb = introductionProb;
    }

    /**
     *
     * @return gets a double of the probability of this token occuring in a Methods section
     */
    public double getMethodsProb() {
        return methodsProb;
    }

    /**
     *
     * @param methodsProb
     */
    public void setMethodsProb(double methodsProb) {
        this.methodsProb = methodsProb;
    }

    /**
     *
     * @return gets a double of the probability of this token occuring in a Results section
     */
    public double getResultsProb() {
        return resultsProb;
    }

    /**
     *
     * @param resultsProb
     */
    public void setResultsProb(double resultsProb) {
        this.resultsProb = resultsProb;
    }

    /**
     *
     * @return Returns a String of the token.
     */
    public String getToken() {
        return token;
    }

    /**
     *
     * @param token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     *
     * @param sectionType
     * @return returns a double of the probability of this token occuring in the given section.
     */
    public double getProbByClass(SectionType sectionType) {
        if (sectionType == SectionType.INTRODUCTION) {
            return getIntroductionProb();
        } else if (sectionType == SectionType.METHODS) {
            return getMethodsProb();
        } else if (sectionType == SectionType.RESULTS) {
            return getResultsProb();
        } else if (sectionType == SectionType.DISCUSSION) {
            return getDiscussionProb();
        } else {
            return 0.0d;
        }
    }

    @Override
    public int compareTo(TokenData o) {
        int r = 0;

        if (o instanceof TokenData) {
            TokenData obj = (TokenData) o;
            double ocv = obj.getCoefficientOfVariation();
            double tcv = this.getCoefficientOfVariation();
            if (ocv > tcv) {
                r = 1;
            } else if (ocv < tcv) {
                r = -1;
            }
        }

        return r;
    }
}
