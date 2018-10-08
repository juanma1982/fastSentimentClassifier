# Fast Sentiment Classifier

Fast sentiment classifier using SentiWordNet 3.0.


##Download 

1. download the jar file
2. download the folder "data" wichs includes the SentiWordNet Dictionary

##Run

A text in English is expected as a parameter or the parameter -f
and the name of a text file or the parameter -d and a
directory with a bunch of text files with criticism in English, in example:

###Example

java -jar fastSentimentClassifier.jar "The movie was great!" 
or
java -jar fastSentimentClassifier.jar -f critica01.txt 
or
java -jar fastSentimentClassifier.jar -d /path/criticism/ 

