import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;



public class Polynomial {
  public double[] coef;
  public int[] pwr;
  
  
  
  
  public Polynomial(){
    coef = new double[1];
    pwr = new int[1];
    coef[0] = 0;
    pwr[0] = 0;
    //System.out.println();
  }
  public Polynomial( double[] coefficient, int[] exponent){
    coef = coefficient;
    pwr = exponent; 
    
    /*for(int i = 0; i < a.length; i++) {

    System.out.println(coef[i]);
    }
    System.out.println(); */ 
  }
  public Polynomial( String path) throws IOException {

	  Scanner txt = new Scanner(new File(path));
	  String line = txt.nextLine();
	  txt.close();
	  String [] term = line.split("(?=[+-])");
	  coef = new double[term.length];
	  pwr = new int[term.length];
	  
	  for( int i =0; i < term.length; i++) {
		  if( term[i].contains("x")) {
		  String [] x = term[i].split("x");
		  coef[i] = Double.parseDouble(x[0]);
		  pwr[i] = Integer.parseInt(x[1]); }
		  else {
			  coef[i] = Double.parseDouble(term[i]);
			  pwr[i] = 0;
		  }
		  
	  }

	  
  }
  
  
  
  private boolean in_list(int [] list, int x) {
	  for(int i = 0; i < list.length ; i++) {
		  if(list[i] == x) return true;
	  }
	  return false;
  }
  
  
  
  
  private void rm_0_coef() {
	    int count = 0;
	    for (double x : coef) {
	        if (x != 0) count++;
	    }
	    double[] newCoef = new double[count];
	    int[] newPwr = new int[count];
	    int j = 0;
	    for (int i = 0; i < coef.length; i++) {
	        if (coef[i] != 0) {
	            newCoef[j] = coef[i];
	            newPwr[j] = pwr[i];
	            j++;
	        }
	    }
	    coef = newCoef;
	    pwr = newPwr;
	}
  
  
  
  public Polynomial add( Polynomial given){ 
	  
	  
	  int similar = 0; //this tells me how big my list is
	  
	  for( int i = 0; i < given.coef.length;i++ ) {
		  for( int x: this.pwr ) {
			  if( in_list(given.pwr, x) ) {
				  similar ++; //there is something similar
			  }
		  }
	  }
	  ///we know how big are array should be
	  int[] new_pwr = new int[this.pwr.length + given.pwr.length - similar]; 
	  double[] new_coef = new double[this.pwr.length + given.pwr.length - similar];
	  
	  for(int i = 0; i < given.coef.length; i++) {   //bigger loop goes through given

		  new_pwr[i] = given.pwr[i];
		  new_coef[i]= given.coef[i];
			  for( int j = 0; j < this.coef.length ; j++) {
				  if(this.pwr[j] == given.pwr[j]) {   ///we found a match
					  new_coef[i] = this.coef[j] + given.coef[i];
					  break;
				  }
			  
		  }
		  
		  
	  }//now evrything in coef is writen down added to or just by itslef 
	  //////this turn
	  int i = given.coef.length;  ///in the power 
	  int j = 0; 
	  while( i < new_pwr.length && j < this.pwr.length) {
		  if( ! in_list( new_pwr, this.pwr[j])) {
			  new_pwr[i] = this.pwr[j];
			  new_coef[i] = this.coef[j];
			  i++;
		  }
		  j++;
	  }
	  rm_0_coef();
	  Polynomial new_poly = new Polynomial(new_coef, new_pwr);
	  return new_poly; 
	  
  }
  public double evaluate(double point) {
	  if(point == 0) return coef[0];  //cuase a fault in the loop
	  double result = 0;
	  for(int i = 0; i < this.coef.length; i++){
		  result = result +this.coef[i]*Math.pow(point, this.pwr[i]);
		  ///System.out.println(result + "+" + coef[i]);
	  }
	  return result;
  }
  public boolean hasRoot( double point) {
	  return evaluate( point) == 0;
	  
  }
  public Polynomial multiply( Polynomial given) {
	 int [] pwrs = new int[given.coef.length + this.coef.length];
	 double [] coefs = new double[given.coef.length + this.coef.length];
	 int idx = 0;
	 for(int i = 0; i < given.coef.length; i++) {
		 for(int j = 0; j < this.coef.length; j++) {
			 pwrs[idx] = this.pwr[j] + given.pwr[i];
			 coefs[idx] = this.coef[j] + given.coef[i];
			 idx ++;
		 } //I have them all
		 
	 } /// I hvae to check to see how many are repeating in power
	 int similar = 0;
	 for(int i = 0; i < pwrs.length; i++) {
		 for(int j = 1+i; j < pwrs.length; j++) {
			 if( pwrs[i] == pwrs[j]) similar++;
		 }
	 }
	 int [] new_pow = new int[pwrs.length - similar];
	 double [] new_coef = new double[pwrs.length - similar];
	 int indx = 0;
	 for(int i = 0; i < pwrs.length; i++) {
		 if( pwrs[i] != -1) {
		 new_pow[indx] = pwrs[i];
		 new_coef[indx] = coefs[i];
		 for(int j = 1+i; j < pwrs.length; j++) {
			 if( pwrs[i] == pwrs[j]) {
				 //new_pow[indx] = pwrs[i];
				 new_coef[indx] = coefs[i] + coefs[j];
				 pwrs[j] = -1;
				 break;
			 }
		 }
		 
		 indx++;}
	 }
	 rm_0_coef();
	 Polynomial newer = new Polynomial(new_coef, new_pow);
	 return newer;
  }
  void saveToFile(String file_path) {
	  try {
		FileWriter output = new FileWriter( file_path, false);
		  for(int i = 0; i < this.pwr.length; i++) {
			  if(i != 0 && coef[i] >= 0) output.write("+");
			  
			  output.write(String.valueOf(coef[i]));
			  if(pwr[i] != 0) {
			  output.write("x");
			  output.write(String.valueOf(pwr[i]));
			  }
			  
			  
			  
		  }
		  output.close();
	  } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  }

	  
	  
  }
  
  
}
