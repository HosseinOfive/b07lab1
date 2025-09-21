public class Polynomial {
  public double[] coef;
  public Polynomial(){
    coef = new double[1];
    coef[0] = 0;
    //System.out.println();
  }
  public Polynomial( double[] a){
    coef = a;
    /*for(int i = 0; i < a.length; i++) {

    System.out.println(coef[i]);
    }
    System.out.println(); */ 
  }
  public Polynomial add( Polynomial given){
	  int  size = Math.max( this.coef.length, given.coef.length);
	  double[] new_poly = new double[size];
	  for(int i = 0; i < size; i++) {
		  double a,b;   //we make sure we don't go out of bounds
		  if( i < this.coef.length) a = this.coef[i];
		  else a = 0;
		  if( i < given.coef.length) b = given.coef[i];
		  else b = 0;
		  
	
		  new_poly[i] = a + b; 
	  
	  }
	  Polynomial result = new Polynomial( new_poly);
	  return result;
  }
  public double evaluate(double point) {
	  if(point == 0) return coef[0];  //cuase a fault in the loop
	  double result = 0;
	  for(int i = 0; i < this.coef.length; i++){
		  result = result +this.coef[i]*Math.pow(point, i);
		  ///System.out.println(result + "+" + coef[i]);
	  }
	  return result;
  }
  public boolean hasRoot( double point) {
	  return evaluate( point) == 0;
	  
  }
  
  
  
  
}
