// …Ÿ¡ÕÕ«” Õ… œÀ«” 3100127

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


public class Bayes {
	
	int tp;
	int fn;
	int tn;
	int fp;
	
	
	void test(Xvalues[] att,int k,int c) throws FileNotFoundException{
		String folder=null;
		double PC1;
		double PC0;
		Set<String> fileWords=new HashSet<String>();
		switch(c){
			case 1 : {
				folder="bare";
				break;
			}
			case 2 : {
				folder="lemm";
				break;
			}
			case 3 : {
				folder="lemm_stop";
				break;
			}
			case 4 : {
				folder="stop";
				break;
			}
		}
		File dir = new File("lingspam_public\\"+folder+"\\test\\");
		File[] directoryListing = dir.listFiles();
		File file;
		for(int i=0;i<291;i++){
			PC1=1;
			PC0=1;
			file=directoryListing[i];
		    Scanner s = new Scanner(file);
		    while (s.hasNext()) {
		        String next = s.next();
		        fileWords.add(next);
			}
		    for(int j=0;j<att.length;j++){
		        if(fileWords.contains(att[j].getWord())){
		        	PC0=PC0*((double)(att[j].getC0()+1)/((217*k)+2));
		        	PC1=PC1*((double)(att[j].getC1()+1)/((43*k)+2));
		        }else{
		        	PC0=PC0*((double)(217*k-att[j].getC0()+1)/((217*k)+2));
		        	PC1=PC1*((double)(43*k-att[j].getC1()+1)/((43*k)+2));
		        }
		    }
		    PC0=PC0*((double)217/260);
		    PC1=PC1*((double)43/260);
		    if(PC0>=PC1&&i<242)tp++;
		    else if(PC0>=PC1&&i>=242)fp++;
		    else if(PC1>=PC0&&i>=242)tn++;
		    else if(PC1>=PC0&&i<242)fn++;
		    fileWords.clear();
		}
		double per=(double)(tp+tn)/(tp+tn+fp+fn);
		System.out.println("True Positive:"+tp);
		System.out.println("False Negative:"+fn);
		System.out.println("True Negative:"+tn);
		System.out.println("False Positive:"+fp);
		System.out.println("Correct % :"+per);
	}

}
