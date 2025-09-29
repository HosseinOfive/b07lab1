public class Driver {
    public static void main(String[] args) {
        double[] coef1 = {2.0, -3.0, 4.5};
        int[] pwr1 = {2, 1, 0}; // represents 2x^2 - 3x + 4.5
        Polynomial poly1 = new Polynomial(coef1, pwr1);


        
        poly1.saveToFile("poly1.txt");
        System.out.println("Saved poly1 to poly1.txt\n");

        // Read polynomial back from file
        Polynomial poly2 = new Polynomial("poly1.txt");
        System.out.println("Polynomial read from file:");
  

        // Evaluate at a point
        double val = poly2.eval(2.0);
        System.out.println("\npoly2 evaluated at x=2.0 = " + val);

        // Save again after any operations
        poly2.saveToFile("poly2.txt");
        System.out.println("Saved poly2 to poly2.txt");
    }
}

