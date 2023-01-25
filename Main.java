import java.util.concurrent.CompletionException;

public class Main {
    public static void main(String[] args) {
        Complex a = new Complex(),

                b = new Complex(3.7),

                c = new Complex(2.8, -1.2),

                d = new Complex(c);
        System.out.println(((a.add(b).div(c)).mul((b.plus(a)))).sub(d.division(new Complex(5.14799,2.8385))));
        //Complex number = 4,8295 + 3,2473i
        System.out.println(a.inTrigonometric());
    }
}
