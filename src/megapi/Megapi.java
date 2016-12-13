package megapi;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
//Große Zahlen ermöglichen
import java.math.BigDecimal;
import static java.math.BigDecimal.*;

public class Megapi {
	private static final BigDecimal TWO = new BigDecimal(2);
    private static final BigDecimal FOUR = new BigDecimal(4);
	static BigDecimal pistring;
	
	public static void main(String[] args) throws IOException {		
	   File file = new File( System.getProperty("user.home")  + "\\MegaPi_1000.txt");
	   FileWriter fw = new FileWriter(file);
	   pistring = pi(Integer.parseInt("1000"));
	   fw.append( pistring.toString());
	   fw.close();
	   System.out.println(pi(Integer.parseInt("50")));
	   System.out.println("done!");	   		
	}

	// Gauss-Legendre Algorithmus
    public static BigDecimal pi(final int SCALE) {
       int zaehler = 0;
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
          //zum Anzeigen daß sich etwas 'bewegt' (ab 100000 sinnvoll, da Rechnenzeit recht hoch)
          System.out.println(zaehler + " a - b: " + test );
          zaehler++;
       }
       return a.add(b).multiply(a.add(b)).divide(t.multiply(FOUR), SCALE, ROUND_HALF_UP);
    }
    
    // Babylonische-Quadratwurzel nach Newton
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
