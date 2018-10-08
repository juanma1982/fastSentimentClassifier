package ar.uba.fi.robots.sentiment;

import java.io.IOException;

import ar.uba.fi.robots.sentiment.SentiWordNetDic.PartsOfSpeech;


public class AnalyzeBagOfWords {
	
	
	private SentiWordNetDic swn = null;	
	
	public AnalyzeBagOfWords() throws IOException{
		
		swn = new SentiWordNetDic();
	}
	
	public double getWordScore(String word){
		double total = 0.0;
		
			total += swn.extract(word,PartsOfSpeech.Adjective);
			total += swn.extract(word,PartsOfSpeech.Adverb);
			total += swn.extract(word,PartsOfSpeech.Verb);
			total += swn.extract(word,PartsOfSpeech.Noun);			 
		
		return total;		
	}
	
	public double doAnalysis(String textAsBagOfWords){
		String[] words = textAsBagOfWords.split("\\s+"); 
		double totalScore = 0;
		for(String word : words) {
		    word = word.replaceAll("([^a-zA-Z\\s])", "");		    
		    totalScore += getWordScore(word);
		}
		return ((double) totalScore/words.length);
	}

}
