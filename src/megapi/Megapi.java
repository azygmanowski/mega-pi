package megapi;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
//enable big decimal numbers
import java.math.BigDecimal;
import static java.math.BigDecimal.*;

public class Megapi {
	private static final BigDecimal TWO = new BigDecimal(2);
    private static final BigDecimal FOUR = new BigDecimal(4);
	static BigDecimal pistring;
	
	public static void main(String[] args) throws IOException {
	long startzeit = System.currentTimeMillis();	
  	   //number of digits to calculate
	   String digits = "10000";	
  	   
	   File file = new File( System.getProperty("user.home")  + "\\MegaPi_" + digits + ".txt");
	   FileWriter fw = new FileWriter(file);
	   
	   pistring = pi(Integer.parseInt(digits));
	   fw.append( pistring.toString());
	   fw.close();
	   System.out.println(pi(Integer.parseInt("50")));
	   System.out.println("Done!");	   	
	   System.out.println("Calculating time: " + (System.currentTimeMillis() - startzeit)/1000 + " seconds");	   	
	   
	   
	}

	// Gauss-Legendre algorithm
    public static BigDecimal pi(final int SCALE) {
       int counter = 0;
       BigDecimal a = ONE;
       BigDecimal b = ONE.divide(sqrt(TWO, SCALE), SCALE, ROUND_HALF_UP);
       BigDecimal test = ONE;
       BigDecimal t = new BigDecimal(0.25);
       BigDecimal x = ONE;
       BigDecimal y;
       while (!a.equals(b)) {
          y = a;
          a = a.add(b).divide(TWO, SCALE, ROUND_HALF_UP);
          b = sqrt(b.multiply(y), SCALE);
          t = t.subtract(x.multiply(y.subtract(a).multiply(y.subtract(a))));
          x = x.multiply(TWO);
          test = a.subtract(b);
          //show that process is still alive, cause it's very slow from 100.000 digits and above 
          System.out.println(counter + " a - b: " + test );
          counter++;
       }
       return a.add(b).multiply(a.add(b)).divide(t.multiply(FOUR), SCALE, ROUND_HALF_UP);
    }
    
    // babylonian square root, Newton
    public static BigDecimal sqrt(BigDecimal A, final int SCALE) {
       BigDecimal x0 = new BigDecimal("0");
       BigDecimal x1 = new BigDecimal(Math.sqrt(A.doubleValue()));
       while (!x0.equals(x1)) {
          x0 = x1;
          x1 = A.divide(x0, SCALE, ROUND_HALF_UP);
          x1 = x1.add(x0);
          x1 = x1.divide(TWO, SCALE, ROUND_HALF_UP);
       }
       return x1;
    }	

}
