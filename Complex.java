/**
 * Класс для работы с комлексными числами со свойствами <b>real_part</b> и <b>imag_part</b>.
 * @autor Андрей помошников
 * @version 0.2
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
     * Цепочечная функция добавление комплексного числа к текущему объекту класса(имитирует оператор +=)
     * @param other - добавляемое комплексное число
     * @return возвращает текущий объект класса
     */
    public Complex add(Complex other)
    {
        this.real_part += other.real_part;
        this.imag_part += other.imag_part;
        return this;
    }

    /**
     * Цепочечная функция вычитания комплексного числа из текущего объекта класса(имитирует оператор -=)
     * @param other - вычитаемое комплексное число
     * @return возвращает текущий объект класса
     */
    public Complex sub(Complex other)
    {
        this.real_part -= other.real_part;
        this.imag_part -= other.imag_part;
        return this;
    }

    /**
     * Цепочечная функция умножения текущего объекта класса на комплексное число(имитирует оператор *=)
     * @param other - множитель
     * @return возвращает текущий объект класса
     */
    public Complex mul(Complex other)
    {
        double temp_real = (this.real_part * other.real_part - this.imag_part * other.imag_part);
        double temp_imag = (this.real_part * other.imag_part + this.imag_part * other.real_part);
        this.real_part = temp_real;
        this.imag_part = temp_imag;
        return this;
    }

    /**
     * Цепочечная функция деления текущего объекта класса на комплексное число(имитирует оператор /=)
     * @param other - делитель
     * @return возвращает текущий объект класса
     */
    public Complex div(Complex other)
    {
        double del = Math.pow(other.real_part,2) + Math.pow(other.imag_part, 2);
        double temp_real =
                ((this.real_part * other.real_part) + (this.imag_part * other.imag_part)) / del ;
        double temp_imag =
                ((this.imag_part * other.real_part) - (this.real_part * other.imag_part)) / del;
        this.real_part = temp_real;
        this.imag_part = temp_imag;
        return this;
    }

    /**
     * Функция создания объекта класса {@link Complex} путем суммирования текущего с другим
     * @param other - добавляемое комплексное число
     * @return возвращает новый объект класса с заданными параметрами
     */
   public Complex plus(Complex other)
   {
       return new Complex(this.real_part + other.real_part, this.imag_part + other.imag_part);
   }

    /**
     * Функция создания объекта класса {@link Complex} путем вычитания из текущего объекта другого
     * @param other - вычитаемое комплексное число
     * @return возвращает новый объект класса с заданными параметрами
     */
    public Complex minus(Complex other)
    {
        return new Complex(this.real_part - other.real_part, this.imag_part - other.imag_part);
    }

    /**
     * Функция создания объекта класса {@link Complex} путем умножения текущего объекта на другой
     * @param other - множитель
     * @return возвращает новый объект класса с заданными параметрами
     */
    public Complex multiplication(Complex other)
    {
        return new Complex(this.real_part * other.real_part - this.imag_part * other.imag_part,
                this.real_part * other.imag_part + this.imag_part * other.real_part);
    }

    /**
     * Функция создания объекта класса {@link Complex} путем деления текущего объекта на другой
     * @param other - делитель
     * @return возвращает новый объект класса с заданными параметрами
     */
    public Complex division(Complex other)
    {
        double del = Math.pow(other.real_part,2) + Math.pow(other.imag_part, 2);
        return new Complex(((this.real_part * other.real_part) + (this.imag_part * other.imag_part)) / del ,
                ((this.imag_part * other.real_part) - (this.real_part * other.imag_part)) / del);
    }

    /**
     * Функция представления объекта класса {@link Complex} в строковом виде
     * @return возвращает строковое представление комплексного числа
     */
    public String toString()
    {
        if (imag_part >=0)
        {
            return String.format("Complex number = %.4f + %.4fi",real_part,imag_part);
        }
        else
        {
            return String.format("Complex number = %.4f - %.4fi",real_part,Math.abs(imag_part));
        }
    }


}
