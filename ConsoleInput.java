import java.util.Scanner;
/**
 * Класс для работы с комплексными числами, поддерживающиЙ возврат отформатированного числа из строки
 * @author Андрей Помошников
 * @version 1.0
 */
public class ConsoleInput {

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
