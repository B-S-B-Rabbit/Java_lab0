import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Класс для работы с матрицами, поддерживающиЙ элементы типа "комплексное число"(и как следствие тип double) класса
 * {@link Complex} и имеющий свойства <b>row</b> и <b>col</b>
 * @author Андрей Помошников
 * @version 0.2.1
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
        return col;
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

    public Matrix plus(Matrix other)
    {
        if ((this.col == other.col) && (this.row == other.row))
        {
            Matrix result = new Matrix(this.row,this.col);
            for (int i = 0; i < this.row; ++i)
            {
                for (int j = 0; j < this.col; ++j)
                {
                    result.matrix[i][j] = this.matrix[i][j].plus(other.matrix[i][j]);
                }
            }
            return result;
        }
        return null;
    }

    public Matrix mul(Matrix other)
    {

        if (this.col == other.row)
        {
            Matrix result = new Matrix(this.row, other.col);
            for (int i = 0; i < this.row; ++i)
            {
                for (int j = 0; j < other.col; ++j)
                {
                    result.matrix[i][j] = new Complex(0);
                    for (int k  = 0; k < this.col; ++k)
                    {
                        result.matrix[i][j] = result.matrix[i][j].plus(this.matrix[i][k].multiplication(other.matrix[k][j]));
                    }
                }
            }
            return result;
        }
        return null;
    }

    public Matrix transponse()
    {
        Matrix result = new Matrix(col, row);
        for (int i = 0; i < col; ++i)
        {
            for (int j = 0; j < row; ++j)
            {
                result.matrix[i][j] = matrix[j][i];
            }
        }
        return result;
    }

    public Complex determinant()
    {
        Complex result = new Complex();
        if (this.row == this.col && this.col > 0)
        {
            if (this.col == 1)
            {
                return this.matrix[0][0];
            }
            else if (this.col == 2)
            {
                return (this.matrix[0][0].multiplication(this.matrix[1][1])).
                        minus(this.matrix[0][1].multiplication(this.matrix[1][0]));
            }
            else {
                Matrix tempObj = new Matrix(this.row - 1);
                for(int i = 0; i < this.col; ++i)
                {
                    int r = 0;
                    int c = 0;

                    for(int j = 1;j < this.row; ++j)
                    {
                        for( int k=0; k< this.col; ++k)
                        {
                            if(k == i)
                            {
                                continue;
                            }
                            tempObj.matrix[r][c] = this.matrix[j][k];
                            ++c;

                            if(c == this.col - 1)
                            {
                                ++r;
                                c = 0;
                            }
                        }
                    }
                    result.add(this.matrix[0][i].multiplication(new Complex(Math.pow(-1,i))).multiplication(tempObj.determinant()));
                }
            }
        }
        return result;
    }



}

