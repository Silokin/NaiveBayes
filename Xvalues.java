// …Ÿ¡ÕÕ«” Õ… œÀ«” 3100127

public class Xvalues {
	
	int k;
	String word;
	int C0=0;
	int C1=0;
	double ig=0;
	
	Xvalues(int i,int k,String word){
		if(i==217) C0++;
		else C1++;
		this.word=word;
		this.k=k;
	}
	
	void setC(int i){
		if(i==217) C0++;
		else C1++;
	}
	
	void setIg(){
		double hc=-(((double)430/2600)*(Math.log(430)-Math.log(2600)))-(((double)2170/2600)*(Math.log(2170)-Math.log(2600)));
		double hcx0=-(((double)(217*k-C0)/((217*k-C0)+(43*k-C1)))*(Math.log(217*k-C0)-Math.log(((217*k-C0)+(43*k-C1)))))-(((double)(43*k-C1)/((217*k-C0)+(43*k-C1)))*(Math.log(43*k-C1)-Math.log(((217*k-C0)+(43*k-C1)))));
		double hcx1;
		if(C0==0&&C1!=0)hcx1=-((double)(C1)/((C0+C1))*(Math.log(C1)-Math.log(C0+C1)));
		else if(C0!=0&&C1==0)hcx1=-((double)(C0)/((C0+C1))*(Math.log(C0)-Math.log(C0+C1)));
		else hcx1=-((double)(C0)/((C0+C1))*(Math.log(C0)-Math.log(C0+C1)))-((double)(C1)/((C0+C1))*(Math.log(C1)-Math.log(C0+C1)));
		double Sigmapx=-(((double)((260*k)-(C0+C1))/(260*k))*hcx0)-((double)((C0+C1)/(260*k))*hcx1);
		this.ig=hc+Sigmapx;
	}
	
	double getIg(){
		return this.ig;
	}
	
	String getWord(){
		return word;
	}
	
	int getC0(){
		return this.C0;
	}
	
	int getC1(){
		return this.C1;
	}

}
