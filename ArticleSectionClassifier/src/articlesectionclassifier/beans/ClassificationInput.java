/*
 * ClassificationInput.java
 *
 * Created on 28 August 2007, 19:25
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package articlesectionclassifier.beans;

/**
 * A bean class that can be populated with data for submission to the {@link articlesectionclassifier.classifying.ArticleSectionClassifier} class.
 *
 * @author James
 */
public class ClassificationInput {

    private String textToClassify = "";
    private String textTitle = "";

    /**
     *
     * @return Returns the text String field
     */
    public String getTextToClassify() {
        return textToClassify;
    }

    /**
     *
     * @return Returns the title String field
     */
    public String getTextTitle() {
        return textTitle;
    }

    /**
     *Creates a new {@link ClassificationInput} object with a user supplied text section and the title of the text section.
     *
     *@param textToClassify the text you would like to classify
     *@param textTitle the title of the text section you are submitting for classification
     */
    public ClassificationInput(String textToClassify, String textTitle) {
        setTextToClassify(textToClassify);
        setTextTitle(textTitle);
    }

    /**
     *Creates a new {@link ClassificationInput} object with a user supplied section of text.
     *
     *@param textToClassify the text you would like to classify
     */
    public ClassificationInput(String textToClassify) {
        setTextToClassify(textToClassify);
    }

    /**
     *Sets the title of the text section that you would to classify.
     *If your text section has a title, you can submit this to the classifier
     *through this method and it will attempt to use it to classify the text section.
     *The title is not required but can improve results.
     *
     *@param textTitle the title of the text section you are submitting for classification
     */
    public void setTextTitle(String textTitle) {
        this.textTitle = textTitle;
    }

    /**
     *Sets the text that you would like the classifier to analyse
     *
     *@param textToClassify the text you would like to classify
     */
    public void setTextToClassify(String textToClassify) {
        this.textToClassify = textToClassify;
    }

    /**
     * Returns a {@link String} representation of the fields stored by this class
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Text to classify:\t" + this.getTextToClassify() + "\n");
        sb.append("Title of text to classify:\t" + this.getTextTitle() + "\n");

        String r = sb.toString();
        return r;
    }

    /** Creates a new instance of ClassificationInput.
     * 
     */
    public ClassificationInput() {
        textToClassify = "";
        textTitle = "";
    }
}
