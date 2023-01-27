import java.util.ArrayList;
import java.util.List;
/**
 * Класс для работы с матрицами, поддерживающиЙ элементы типа "комплексное число"(и как следствие тип double) класса
 * {@link Complex} и имеющий свойства <b>row</b> и <b>col</b>
 * @author Андрей помошников
 * @version 0.2
 */
public class Matrix {
    /** Поле матрицы */
    public Complex[][] matrix;
    /** Поле количества строк матрицы */
    private int row;
    /** Поле количества столбцов матрицы */
    private int col;
    /**
     * Конструктор - создание нового объекта
     * @see Matrix#Matrix(int, int)
     * @see Matrix#Matrix(int)
     * @see Matrix#Matrix(Matrix)
     */
    Matrix()
    {
        matrix = new Complex[0][0];
    }

    /**
     * Конструктор - создание нового объекта квадратной матрицы
     * @param n - количество строк и столбцов матрицы
     * @see Matrix#Matrix()
     * @see Matrix#Matrix(int, int)
     * @see Matrix#Matrix(Matrix)
     */
    Matrix(int n)
    {
        this(n,n);

    }
    /**
     * Конструктор - создание нового объекта произвольной матрицы
     * @param n - количество строк
     * @param m - количество столбцов
     * @see Matrix#Matrix()
     * @see Matrix#Matrix(int)
     * @see Matrix#Matrix(Matrix)
     */
    Matrix(int n, int m)
    {
        this.row = n;
        this.col = m;

        matrix = new Complex[row][col];
    }
    /**
     * Конструктор копирования
     * @param other - другой объект класса {@link Matrix}
     * @see Matrix#Matrix()
     * @see Matrix#Matrix(int)
     * @see Matrix#Matrix(int, int)
     */
    Matrix(Matrix other)
    {
        this(other.row, other.col);
        for (int i = 0; i < other.row; ++i)
        {
            for (int j = 0; j < other.col; ++j)
            {
                this.matrix[i][j] = new Complex(other.matrix[i][j]);
            }
        }

    }
    /**
     * Функция-геттер для количества строк матрицы
     * @return возвращает значение количества строк текущего объекта класса
     */
    public int getRow()
    {
        return row;
    }

    /**
     * Функция-геттер для количества столбцов матрицы
     * @return возвращает значение количества столбцов текущего объекта класса
     */
    public int getCol()
    {
        return row;
    }
    /**
     * Цепочечная функция добавление матрицы к текущему объекту класса(имитирует оператор +=)
     * @param other - добавляемая матрица
     * @return возвращает текущий объект класса
     */
    public Matrix add(Matrix other)
    {
        if ((this.col == other.col) && (this.row == other.row))
        {
            for (int i = 0; i < this.row; ++i)
            {
                for (int j = 0; j < this.col; ++j)
                {
                    this.matrix[i][j].add(other.matrix[i][j]);
                }
            }
            return this;
        }
        return null;
    }

    }

