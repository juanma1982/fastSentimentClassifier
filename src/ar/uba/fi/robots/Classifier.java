package ar.uba.fi.robots;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import ar.uba.fi.robots.sentiment.AnalyzeBagOfWords;



public class Classifier {

	public static void main(String[] args) {
		String filename = null;
		String text="";
		
		if(args.length>1 && args[0].equals("-f")){
			filename=args[1];
			if(filename!=null){
				text=loadfile(filename);
			}
			doProcessFile(text);
		}else if(args.length>1 && args[0].equals("-d")){
			filename=args[1];
			doProcessDirectory(filename);
		}else if(args.length==1){
			text=args[0];
			doProcessFile(text);
		}else{
			printhelp();		
		}
			
		
	}
	
	private static void doProcessFile(String text){
		AnalyzeBagOfWords abw =null;
		try {
			abw = new AnalyzeBagOfWords();
		} catch (IOException e) {
			System.out.println("Se produjo el siguiente error: "+e.getMessage());
			e.printStackTrace();
			System.exit(-1);
		}
		double polarity =abw.doAnalysis(text);
		if(polarity>0){
			System.out.println("positive, "+polarity);
		}else{
			System.out.println("negative, "+polarity);
		}
	}
	
	private static void doProcessDirectory(String dirPath){
		AnalyzeBagOfWords abw =null;
		try {
			abw = new AnalyzeBagOfWords();
		} catch (IOException e) {
			System.out.println("Se produjo el siguiente error: "+e.getMessage());
			e.printStackTrace();
			System.exit(-1);
		}
		final File folder = new File(dirPath);
		    for (final File fileEntry : folder.listFiles()) {
		        if (!fileEntry.isDirectory()) {
		        	String text=loadfile(dirPath + fileEntry.getName());
		        	double polarity =abw.doAnalysis(text);
		    		if(polarity>0){
		    			System.out.println("polarity: positive; value="+polarity);
		    		}else{
		    			System.out.println("polarity: negative; value="+polarity);
		    		}
		        }
		    }	
	}

	private static void printhelp(){
		System.out.println("A text in English is expected as a parameter or the parameter -f");
		System.out.println("and the name of a text file or the parameter -d and a");
		System.out.println("directory with a bunch of text files with criticism in English, in example:");
		
		System.out.println("java -jar fastSentimentClassifier.jar \"The movie was great!\" ");
		System.out.println("or");
		System.out.println("java -jar fastSentimentClassifier.jar -f critica01.txt ");
		System.out.println("or");
		System.out.println("java -jar fastSentimentClassifier.jar -d /path/criticism/ ");
	}
	private static void printhelpES() {
		System.out.println("Se espera como parametro un texto en inglés, el parametro -f \n"+
				           " y el nombre de un archivo de texto o bien el parametro -d y un\n"+
							" directorio con archivos de texto con criticas en ingles, por ejemplo: \n");
		System.out.println("java -jar fastSentimentClassifier.jar \"The movie was great!\" ");
		System.out.println("o");
		System.out.println("java -jar fastSentimentClassifier.jar -f critica01.txt ");
		System.out.println("o");
		System.out.println("java -jar fastSentimentClassifier.jar -d /path/criticas ");
		
	}

	private static String loadfile(String filenamepath){
		StringBuilder sb=null;
		try (BufferedReader br = new BufferedReader(new FileReader(filenamepath))) {
			sb=new StringBuilder("");
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
				sb.append("\n");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}
