import java.util.Scanner;
import java.util.concurrent.CompletionException;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Complex a = new Complex(),

                b = new Complex(144,0.0),

                c = new Complex(10, 0.0),

                d = new Complex(c);
        //System.out.println(b.mul(c));
        //System.out.println(((a.add(b).div(c)).mul((b.plus(a)))).sub(d.division(new Complex(5.14799,2.8385))));
        //Complex number = 1440,0000 + 0,0000i
        //System.out.println(a.inTrigonometric());
        Matrix matr = new Matrix(3,3);
        FillingMatrix tofill = new FillingMatrix();
   /*     tofill.fillTheMatrix(matr);
        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 3; ++j)
            {

                System.out.println(matr.matrix[i][j]);

            }
        }
       // Matrix nmar = new Matrix(matr);
        //matr.add(nmar);
        Matrix matrmul = new Matrix(3,2);
        tofill.fillTheMatrix(matrmul);
        Matrix after_mul = matr.mul(matrmul);
*/
       /* for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 2; ++j)
            {

                System.out.print(after_mul.matrix[i][j]);
                System.out.print(" ");

            }
            System.out.println();
        }
        after_mul = after_mul.transponse();
*/
      /*  for (int i = 0; i < 2; ++i)
        {
            for (int j = 0; j < 3; ++j)
            {

                System.out.print(after_mul.matrix[i][j]);
                System.out.print(" ");

            }
            System.out.println();
        }
*/
        Matrix for_determinant = new Matrix(4);
        tofill.fillTheMatrix(for_determinant);
        System.out.println(for_determinant.determinant());
    }
}
