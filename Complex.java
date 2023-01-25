/**
 * Класс для работы с комлексными числами со свойствами <b>real_part</b> и <b>imag_part</b>.
 * @autor Андрей помошников
 * @version 0.1
 */
public class Complex {
    /** Поле действительной части числа */
    private double real_part;

    /** Поле комплексной части числа */
    private double imag_part;

    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param real - реальная часть числа
     * @param imag - комплексная часть числа
     * @see Complex#Complex()
     * @see Complex#Complex(double)
     * @see Complex#Complex(Complex)
     */
    Complex(double real, double imag)
    {
        this.real_part = real;
        this.imag_part = imag;
    }

    /**
     * Конструктор - создание нового объекта
     * @see Complex#Complex(double, double)
     * @see Complex#Complex(double)
     * @see Complex#Complex(Complex)
     */
    Complex()
    {
        this(0.0,0.0);
    }

    /**
     * Конструктор - создание нового объекта с одной действительной частью
     * @param real - реальная часть числа
     * @see Complex#Complex()
     * @see Complex#Complex(double,double)
     * @see Complex#Complex(Complex)
     */
    Complex(double real)
    {
        this(real,0.0);
    }

    /**
     * Конструктор копирования
     * @param other - другой объект класса {@link Complex}
     * @see Complex#Complex()
     * @see Complex#Complex(double,double)
     * @see Complex#Complex(double)
     */
    Complex(Complex other)
    {
        this(other.real_part, other.imag_part);
    }
    /**
     * Процедура добавление комплексного числа к текущему объекту класса(имитирует оператор +=)
     * @param other - добавляемое комплексное число
     */
    public void add(Complex other)
    {
        this.real_part += other.real_part;
        this.imag_part += other.imag_part;
    }

    /**
     * Процедура вычитания комплексного числа из текущего объекта класса(имитирует оператор -=)
     * @param other - вычитаемое комплексное число
     */
    public void sub(Complex other)
    {
        this.real_part -= other.real_part;
        this.imag_part -= other.imag_part;
    }

    /**
     * Процедура умножения текущего объекта класса на комплексное число(имитирует оператор *=)
     * @param other - множитель
     */
    public void mul(Complex other)
    {
        double temp_real = (this.real_part * other.real_part - this.imag_part * other.imag_part);
        double temp_imag = (this.real_part * other.imag_part - this.imag_part * other.real_part);
        this.real_part = temp_real;
        this.imag_part = temp_imag;
    }

    /**
     * Процедура деления текущего объекта класса на комплексное число(имитирует оператор /=)
     * @param other - делитель
     */
    public void div(Complex other)
    {
        double del = Math.pow(other.real_part,2) + Math.pow(other.imag_part, 2);
        double temp_real =
                ((this.real_part * other.real_part) + (this.imag_part * other.imag_part)) / del ;
        double temp_imag =
                ((this.imag_part * other.real_part) - (this.real_part * other.imag_part)) / del;
        this.real_part = temp_real;
        this.imag_part = temp_imag;
    }

}
