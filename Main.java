import java.util.concurrent.CompletionException;

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
        Matrix matr = new Matrix(4);
        Matrix nmar = new Matrix(matr);

    }
}
