public class Complex {
    private double real_part, imag_part;

    Complex(double real, double imag)
    {
        this.real_part = real;
        this.imag_part = imag;
    }
    Complex()
    {
        this(0.0,0.0);
    }

    Complex(double real)
    {
        this(real,0.0);
    }

    Complex(Complex other)
    {
        this(other.real_part, other.imag_part);
    }



}
