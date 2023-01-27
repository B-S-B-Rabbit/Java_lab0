import java.util.Scanner;
/**
 * Класс для работы с матрицами, поддерживающиЙ её поэлементное заполнение пользователем с консоли
 * @author Андрей помошников
 * @version 0.2
 */
public class FillingMatrix {
    /** Поле-шаблон - регулярное выражение для работы с вводом элементов матрцы */
    private String pattern = "[-]?\\d+[.]?\\d*[+]?[-]?\\d*[.]?\\d*";

    FillingMatrix()
    {}

    /**
     * Функция заполнения матрицы
     * @param needToFill - входной объект класса {@link Matrix}
     */
    public void fillTheMatrix(Matrix needToFill)
    {
        Scanner in = new Scanner(System.in);
       int row = needToFill.getRow();
       int col = needToFill.getCol();
        if (row > 0 && col > 0)
        {
            for (int i = 0; i < row; ++i)
            {
                for (int j = 0; j < col; ++j)
                {
                    String complex_number_str = in.next(pattern);
                    System.out.println(complex_number_str);
                    needToFill.matrix[i][j] = formatingString((complex_number_str));
                }
            }
            in.close();
        }
    }
    /**
     * Функция преобразования строки в комплексное число класса {@link Complex}
     * @param needToFormat - строка, содержащая комплексное выражение
     * @return - возващает объект класса {@link Complex} с нужными параметрами
     */
    public Complex formatingString(String needToFormat)
    {
       String[] words = needToFormat.split("[+]");
       if (words.length == 2)
       {
           return new Complex(Double.parseDouble(words[0]),Double.parseDouble(words[1]));
       }
       else {
                return new Complex(Double.parseDouble(words[0]));
       }

    }
}
