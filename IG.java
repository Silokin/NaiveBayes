// …Ÿ¡ÕÕ«” Õ… œÀ«” 3100127

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class IG {
	
	int k;
	int m;
	int s;
	int c;
	HashMap<String, Xvalues> countByWords = new HashMap<String, Xvalues>();
	Xvalues[] att;
	Xvalues[] attributes;
	
	IG(int k,int m,int s,int c) throws FileNotFoundException{
		this.k=k;
		this.m=m;
		this.s=s;
		this.c=c;
		calculate();
	}
	
	Xvalues[] getAtt(){
		return this.attributes;
	}
	
	void calculate() throws FileNotFoundException{
		calculateValues("ham",217);
		calculateValues("spam",43);	
		this.att=new Xvalues[countByWords.size()];
		Iterator it = countByWords.entrySet().iterator();
		int i =0;
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        att[i]=(Xvalues) pair.getValue();
	        att[i].setIg();
	        i++;
	        it.remove();
	   }
	   this.countByWords.clear();
	   BubbleSort();
	   this.attributes=new Xvalues[m];
	   for(i=0;i<m;i++){
		   this.attributes[i]=this.att[i];
	   } 
	}
	
	void calculateValues(String p,int j) throws FileNotFoundException{
		String folder=null;
		Set<String> fileWords=new HashSet<String>();
		switch(this.c){
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
		File dir = new File("lingspam_public\\"+folder+"\\train\\"+p);
		File[] directoryListing = dir.listFiles();
		File file;
		for(int i=0;i<j*this.k;i++){
			file=directoryListing[i];
		    Scanner s = new Scanner(file);
		    while (s.hasNext()) {
		        String next = s.next();
		        if(fileWords.contains(next)==false){
		        	fileWords.add(next);
		        	if(this.countByWords.containsKey(next)){
		        		Xvalues values=this.countByWords.get(next);
		        		values.setC(j);
		        	}else{
		        		this.countByWords.put(next,new Xvalues(j,k,next));	
		        	}
		        }
		    }
		    fileWords.clear();
		}
	}
	
	 void BubbleSort(){
		 double v1=0;
		 double v2=0;
	     int j;
	     boolean flag = true;
	     Xvalues temp;
	     while ( flag ){
	            flag= false;
	            for( j=0;  j < this.att.length - 1 ;  j++ ){
	            	switch(this.s){
		            	case 1 : {
		            		v1= this.att[ j ].getIg();
		            		v2=this.att[j+1].getIg();
		            		break;
		            	}
		            	case 2 : {
		            		v1= this.att[ j ].getC0()-this.att[ j ].getC1();
		            		v2= this.att[j+1].getC0()-this.att[j+1].getC1();
		            		break;
		            	}
		            	case 3 : {
		            		v1= this.att[ j ].getC0()+this.att[ j ].getC1();
		            		v2= this.att[j+1].getC0()+this.att[j+1].getC1();
		            		break;
		            	}
	            	}
	            	if(v1<v2){
	            		temp = this.att[ j ];
	                    this.att[ j ] = this.att[ j+1 ];
	                    this.att[ j+1 ] = temp;
	                    flag = true; 
	                } 
	            } 
	      }
	}

}
