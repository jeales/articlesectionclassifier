This is a naive Bayesian text classifier that, given a bit of text can tell you the posterior probability (returned as a log likelihood) that it comes from each of the standard scientific article sections (Introduction, Methods, Results, Discussion).

What do we mean by a bit of text, well anything you want really, from a few words to a whole article, you decide on the boundaries.

## You can use it to: ##
  * Pull out all the bits of text from an article that are from your chosen section.
  * Score each bit of text in 4 dimensions (i.e. how introductory, methodological, results-based or conclusionary it is) which may be useful for finding similar text.
  * Use it to create training data etc.

## This project contains: ##
  * A local version of the classifier, for better performance (see [Downloads](http://code.google.com/p/articlesectionclassifier/downloads/list), or choose a version from the downloads list to the right)
  * Links and info on how to access the web service interface to the classifier (see below)
  * Example client software Ruby and Perl for accessing the web service from your code (see [Downloads](http://code.google.com/p/articlesectionclassifier/downloads/list))
  * Some Taverna workflows that give a demonstration of how to use the classifier web service (coming soon)
  * The training data (useful for error analyses) [datastore.dump](http://articlesectionclassifier.googlecode.com/files/datastore.dump)
  * A user guide for the web service (coming soon)
  * Some javadoc describing the code for the web service interface and its associated bean classes. [ArticleSectionClassifier-1.0-javadoc.zip](http://articlesectionclassifier.googlecode.com/files/ArticleSectionClassifier-1.0-javadoc.zip)

## Test the classifier ##
You can test the classifier through your browser [here](http://gnode1.mib.man.ac.uk:8080/ArticleSectionClassifierWebApp/)

## Download the classifier ##
Downloads are all listed [here](http://code.google.com/p/articlesectionclassifier/downloads/list) or you can see the featured downloads on the right hand side of this page.

## SOAP/WSDL Web Service ##
The wsdl document for the web service is [here](http://gnode1.mib.man.ac.uk:8080/FullTextWebServices/ArticleSectionClassifierService?wsdl) and you can test it directly through your browser [here](http://gnode1.mib.man.ac.uk:8080/FullTextWebServices/ArticleSectionClassifierService?tester).
You can also download local clients in [Java](http://articlesectionclassifier.googlecode.com/files/WebServiceClient-java.zip), [Perl](http://articlesectionclassifier.googlecode.com/files/WebServiceClient-perl.zip) and [Ruby](http://articlesectionclassifier.googlecode.com/files/WebServiceClient-ruby.zip) for accessing the web service.

## Using the classifier in Java ##
First you must include the [ArticleSectionClassifer.jar](http://articlesectionclassifier.googlecode.com/files/ArticleSectionClassifier-1.0-jar.zip) file in your classpath.  Secondly it is best to increase the memory allocated by the Java executable using the -Xmx Java VM argument, I usually suggest -Xmx256m.  Then you can use the classifier in few lines of code.
```
String textToClassify = "classify this text";
ArticleSectionClassifier classifier = new ArticleSectionClassifier();
String classifiedAs = classifiers.classifyText(textToClassify);
System.out.println(classifiedAs);
```

If you want more detailed output try
```
String textToClassify = "classify this text";
ClassificationInput input = new ClassificationInput(textToClassify);
ArticleSectionClassifier classifier = new ArticleSectionClassifier();
ClassificationResult result = classifier.classifyText(input);
System.out.println(result);
```

## Source code ##
Details on getting the source code for this project can be found by clicking on the 'Source' tab above or by clicking [here](http://code.google.com/p/articlesectionclassifier/source/checkout).  You can also download an archive of the source from [here](http://code.google.com/p/articlesectionclassifier/downloads/list).
The source is uploaded as part of a [Netbeans](http://www.netbeans.org/) project, which can be opened directly into Netbeans or imported into [Eclipse](http://www.eclipse.org/) or most other IDEs.

## Problems ##
If something isn't working, then please post an [issue](http://code.google.com/p/articlesectionclassifier/issues/list).  I will be notified by email and i'll sort it out as soon as I can.  It's very likely that something won't work, but its also likely that i've encountered it before and will know how to fix it, therefore if you have a problem, let me know.