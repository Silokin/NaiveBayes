// …Ÿ¡ÕÕ«” Õ… œÀ«” 3100127

import java.io.FileNotFoundException;


public class Main {

	public static void main(String Args[]) throws FileNotFoundException{
		int k=Integer.parseInt(Args[0]);
		int m=Integer.parseInt(Args[1]);
		int s=Integer.parseInt(Args[2]);
		int c=Integer.parseInt(Args[3]);
		if(k<1||k>10||m<1||s<1||s>3||c<1||c>4)System.out.println("Error: Incorrect Parameters");
		else{
			IG trainer = new IG(k,m,s,c);
			Xvalues[] attributes=new Xvalues[m];
			attributes=trainer.getAtt();
			Bayes tester= new Bayes();
			tester.test(attributes,k,c);
		}
	}
	
}
