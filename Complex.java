/**
 * Класс для работы с комлексными числами со свойствами <b>real_part</b> и <b>imag_part</b>.
 * @author Андрей Помошников
 * @version 1.0
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
     * Функция нахождения модуля комплексного числа
     * @return возвращает модуль комплексного числа
     */
    public double mod()
    {
        return Math.sqrt(real_part*real_part + imag_part*imag_part);
    }

    /**
     * Функция нахождения аргумента комплексного числа
     * @return возвращает аргумент комплексного числа
     */
    public double arg()
    {
        return Math.atan(imag_part/real_part);
    }

    /**
     * Функция представления объекта класса {@link Complex} в строковом виде
     * @return возвращает строковое представление комплексного числа в тригонометрической форме
     */
    public String inTrigonometric()
    {
        return String.format("Complex number in trigonometric = %.4f * (cos(%.4f) + i*sin(%.4f))", mod(),arg(), arg());
    }

    /**
     * Функция-геттер для реальной части комплексного числа
     * @return возвращает реальную часть текущего объекта класса
     */
    public double getReal_part()
    {
        return real_part;
    }

    /**
     * Функция-геттер для мнимой части комплексного числа
     * @return возвращает мнимую часть текущего объекта класса
     */
    public double getImag_part()
    {
        return imag_part;
    }
    /**
     * Процедура-сеттер для реальной части комплексного числа
     * @param num - новое значение для реальной части теущего объекта класса
     */
    public void setReal_part(double num)
    {
        real_part = num;
    }
    /**
     * Процедура-сеттер для мнимой части комплексного числа
     * @param num - новое значение для мнимой части теущего объекта класса
     */
    public void setImag_part(double num)
    {
        imag_part = num;
    }
}
