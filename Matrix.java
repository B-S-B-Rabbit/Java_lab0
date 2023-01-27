import java.util.ArrayList;
import java.util.List;
/**
 * Класс для работы с матрицами, поддерживающиЙ элементы типа "комплексное число"(и как следствие тип double) класса
 * {@link Complex} и имеющий свойства <b>row</b> и <b>col</b>
 * @author Андрей помошников
 * @version 0.1
 */
public class Matrix {
    /** Поле матрицы */
    Complex[][] matrix;
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
        for (int i = 0; i<other.row; ++i)
        {
            if (other.col >= 0)
            {
                System.arraycopy(other.matrix[i], 0, this.matrix[i], 0, other.col);
            }
        }

    }

    }

