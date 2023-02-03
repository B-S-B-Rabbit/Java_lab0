/**
 * Класс, представляющий собой графический интерфейс для работы с матрицами
 * @author Андрей Помошников
 * @version 1.0
 */

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Matrix_Complex_GUI {

    /** Поле-флаг */
    private static int flag = 0;
    /** Поле оболочки GUI */

    private static  JFrame jFrame = new JFrame();

    /** Поле шрифта */
    private static Font font = new Font("Times New Roman",Font.BOLD,20);

    /** Поле-словарь для хранения матриц */
    private static Map<String , Matrix> mapOfMatrix = new HashMap<>();

    /** Поле имени матрицы для вывода на экран */
    private static String matrixNameFirst;
    /** Поле таблицы вывода матрицы */
    private static JTable table = new JTable();
    /** Поле scroll для матрицы */
   private static JScrollPane jsp = new JScrollPane(table);
    /** Поле множественного выбора вверху окна*/
   private static JComboBox<String> matrixNameList2 = new JComboBox();
    /** Поле множественного выбора внизу окна */
    private static JComboBox<String> matrixNameList1 = new JComboBox();
    /** Поле модель таблицы */
    private static  MatrixModel myModel;
    /**
     * Конструктор - запуск GUI
     */
    Matrix_Complex_GUI()
    {
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        jFrame.setBounds(dimension.width/2 - 250, dimension.height/2 - 150, 500, 300);
        jFrame.setIconImage(new ImageIcon("C:\\Users\\user\\Desktop\\2c76677998382cfb86a410d273ffcc87.jpg").getImage());
        jFrame.setVisible(true);
        jFrame.setTitle("Matrix and Complex");
        jFrame.add(new Hello_Comp());
        jFrame.revalidate();
        JMenuBar jMenuBar = new JMenuBar();
        jFrame.setJMenuBar(jMenuBar);
        jFrame.revalidate();

        JMenu save = new JMenu("Save");
        JMenu file = new JMenu("File");
        jMenuBar.add(file);
        jMenuBar.add(save);
        save.setEnabled(false);
        JMenuItem exit = file.add(new JMenuItem("Exit"));
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        jMenuBar.revalidate();
        jFrame.revalidate();
       if (YorNDia() == 0)
       {
          int result = JOptionPane.showOptionDialog(jFrame, "Do you wanna continue to work with matrix or complex " +
                           "numbers only?", "Choice", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                   null, new Object[]{"Matrix", "Complex"}, "Matrix");
          if (result == 0)
          {
              jFrame.getContentPane().removeAll();
              jFrame.repaint();
              jFrame.revalidate();
              JPanel jPanel = new JPanel();
                BorderLayout borderLayout = new BorderLayout();
              jPanel.setLayout(borderLayout);
              jFrame.add(jPanel);
              JButton createButton = new JButton("Create");
              JButton deleteButton = new JButton("Operations");
              deleteButton.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      Operations operations = new Operations(jPanel);
                      operations.setVisible(true);
                  }
              });
              jPanel.add(createButton,BorderLayout.WEST);
              jPanel.add(deleteButton,BorderLayout.EAST);
              Create(createButton,jPanel);
              matrixNameList1.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      if( flag == 1) {
                          matrixNameFirst = matrixNameList1.getSelectedItem().toString();
                          myModel = new MatrixModel();
                          table.setModel(myModel);

                          jPanel.add(jsp);
                          jPanel.revalidate();
                      }
                  }
              });
              jFrame.revalidate();
          }
          else
          {
              jFrame.getContentPane().removeAll();
              jFrame.repaint();
              jFrame.revalidate();
              jFrame.add(new Sorry_Comp());
              jFrame.revalidate();
          }
       }
       else
       {System.exit(0);}
    }
    /**
     * Процедура для создания новой матрицы
     * @param createButton - кнопка Create
     * @param  jPanel -  текущая панель
     */
    private void Create(JButton createButton, JPanel jPanel)
    {
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                matrixNameFirst = JOptionPane.showInputDialog(jPanel,"Please, enter the name of your matrix to have the" +
                        " access to it in the future","matrix1");
                if (!matrixNameFirst.isEmpty())
                {
                    String row = JOptionPane.showInputDialog(jPanel,"Please, enter the numbers of rows in " +
                                    "your matrix",
                            "0");
                    try {
                    if (!row.isEmpty()) {
                        String col = JOptionPane.showInputDialog(jPanel, "Please, enter the numbers of cols in " +
                                        "your matrix",
                                "0");
                        if (!col.isEmpty())
                        {
                            Scanner inRow = new Scanner(row);
                            Scanner inCol= new Scanner(col);
                            if (inRow.hasNextInt() && inCol.hasNextInt())
                            {
                                int rows = inRow.nextInt();
                                int cols = inCol.nextInt();
                                mapOfMatrix.put(matrixNameFirst,new Matrix(rows,cols));
                                try {
                                    FillMatrix fillMatrix = new FillMatrix(jPanel);
                                    fillMatrix.setVisible(true);
                                } catch (IllegalArgumentException il)
                                {
                                    JOptionPane.showMessageDialog(jFrame,"You try to enter something illegal, please exit and try again");
                                }


                                jPanel.repaint();
                                jPanel.revalidate();


                                if(((DefaultComboBoxModel)matrixNameList1.getModel()).getIndexOf(matrixNameFirst) == -1)
                                {
                                    matrixNameList1.addItem(matrixNameFirst);
                                    matrixNameList2.addItem(matrixNameFirst);
                                }
                                jPanel.add(matrixNameList1,BorderLayout.NORTH);
                                jPanel.add(matrixNameList2,BorderLayout.SOUTH);
                                jPanel.repaint();
                                jPanel.revalidate();
                            }
                        }
                    }
                }
                catch (NullPointerException nul)
                {
                    JOptionPane.showMessageDialog(jFrame,"Please, fill the matrix initial values correctly");
                }
                }
            }
        });
    }

    /**
     * Класс, отрисовывающий текст приветствия на оболочку
     */
    static class Hello_Comp extends JComponent
    {
        @Override
        protected void paintComponent(Graphics g)
        {
            Graphics2D g2 = (Graphics2D) g;
            g2.setFont(font);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2.drawString("Hello! Do you wanna start?",125 ,50);

        }

    }
    /**
     * Класс, отрисовывающий извинение на оболочку
     */
    static class Sorry_Comp extends JComponent
    {
        @Override
        protected void paintComponent(Graphics g)
        {
            Graphics2D g2 = (Graphics2D) g;
            g2.setFont(font);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2.drawString("Sorry, we can't do this yet",125 ,50);

        }

    }

    /**
     * Класс заполнения матрицы в одельном диалоговом окне
     */
    static class FillMatrix extends JDialog
    {
        /**
         * Конструктор заполнения матрицы
         */
        public FillMatrix(JPanel superjPanel)
        {
            super(jFrame,"FillingMatrix",false);
            flag = 0;
            JFrame acceptFrame = new JFrame();
            acceptFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension dimension = toolkit.getScreenSize();
            acceptFrame.setBounds(dimension.width/2 + 300, dimension.height/2 - 100, 270, 150);
            acceptFrame.setVisible(true);
            acceptFrame.setTitle("Acception");

            JTextArea textarea = new JTextArea();
             textarea.setText(" Please, enter complex numbers\n like this example '-2.1+-3.7'\n in each square and press" +
                     " the\n" +
                     " button Fill the Matrix after this");
             textarea.setFont(new Font("Arial",Font.BOLD,15));
            acceptFrame.add(textarea,BorderLayout.CENTER);
            acceptFrame.revalidate();


            JTextField[][] matrix;
            matrix = new JTextField[mapOfMatrix.get(matrixNameFirst).getRow()][mapOfMatrix.get(matrixNameFirst).getCol()];
            setLayout(new GridLayout(mapOfMatrix.get(matrixNameFirst).getRow(),mapOfMatrix.get(matrixNameFirst).getCol()));
            for (int i = 0; i < mapOfMatrix.get(matrixNameFirst).getRow(); i++){
                for (int j = 0; j < mapOfMatrix.get(matrixNameFirst).getCol(); j++) {
                    matrix[i][j] = new JTextField("");
                    add(matrix[i][j],BorderLayout.CENTER);
                }
            }
            JButton fillMatr = new JButton("Fill the Matrix");
            fillMatr.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                    ConsoleInput fillM = new ConsoleInput();
                    for (int i = 0; i < mapOfMatrix.get(matrixNameFirst).getRow(); i++){
                        for (int j = 0; j < mapOfMatrix.get(matrixNameFirst).getCol(); j++) {
                            mapOfMatrix.get(matrixNameFirst).matrix[i][j] = fillM.formatingString(matrix[i][j].getText());
                        }
                    }
                    setVisible(false);
                    acceptFrame.setVisible(false);


                    myModel = new MatrixModel();
                    table.setModel(myModel);

                    superjPanel.add(jsp);
                    superjPanel.revalidate();
                    flag = 1;
                }
                    catch (NumberFormatException ex)
                    {
                        JOptionPane.showMessageDialog(jFrame,"Fill the matrix, please");
                    }
                }
            });
            acceptFrame.add(fillMatr,BorderLayout.SOUTH);
            setBounds(500,500,350,250);
        }
    }

    /**
     * Класс выбора реализуемых операций в отдельном диалоговом окне
     */
    static class Operations extends JDialog
    {
        /**
         * Конструктор создания окна
         */
        public Operations(JPanel superjPanel)
        {
            super(jFrame,"Operations",false);

            BoxLayout boxLayout =  new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS);
            setLayout(boxLayout);
            revalidate();

            JLabel info = new JLabel("Use operations below:");
            info.setFont(font);
            info.setAlignmentX(CENTER_ALIGNMENT);
            add(info);
            JButton addM = new JButton("ADD");
            addM.setAlignmentX(CENTER_ALIGNMENT);
            JButton mulM = new JButton("MUL");
            mulM.setAlignmentX(CENTER_ALIGNMENT);
            JButton transponseM = new JButton("TRANSPONSE");
            transponseM.setAlignmentX(CENTER_ALIGNMENT);
            JButton determinantM = new JButton("DETERMINANT");
            determinantM.setAlignmentX(CENTER_ALIGNMENT);

            add(addM);
            add(mulM);
            add(transponseM);
            add(determinantM);

            JLabel resTo = new JLabel("return result to:");
            resTo.setFont(font);
            resTo.setAlignmentX(CENTER_ALIGNMENT);

            add(resTo);
            JComboBox<String> matrixNameList = new JComboBox();
            for (Map.Entry<String, Matrix> mName :
                    mapOfMatrix.entrySet()) {

                // Printing all elements of a Map
                matrixNameList.addItem(mName.getKey());
            }
            matrixNameList.setAlignmentX(CENTER_ALIGNMENT);
            add(matrixNameList);
            addM.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                    String operMatrix1Name = matrixNameList1.getSelectedItem().toString();
                    String operMatrix2Name = matrixNameList2.getSelectedItem().toString();
                    String operMatrixResName = matrixNameList.getSelectedItem().toString();
                    if ((mapOfMatrix.get(operMatrix1Name).getCol() == mapOfMatrix.get(operMatrix2Name).getCol())
                            && (mapOfMatrix.get(operMatrix1Name).getRow() == mapOfMatrix.get(operMatrix2Name).getRow()))
                    {
                        Matrix result = mapOfMatrix.get(operMatrix1Name).plus(mapOfMatrix.get(operMatrix2Name));
                        mapOfMatrix.put(operMatrixResName, result);
                        matrixNameFirst = operMatrixResName;
                        MatrixModel myModel = new MatrixModel();
                        table.setModel(myModel);
                        superjPanel.add(jsp);
                        superjPanel.revalidate();
                    }
                    else {
                        JOptionPane.showMessageDialog(jFrame,"You try to add inappropriate matrix");
                    }
                }  catch (NullPointerException ex)
                    {
                        JOptionPane.showMessageDialog(jFrame,"Select matrix, please");
                    }
                }
            });
            mulM.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{

                        String operMatrix1Name = matrixNameList1.getSelectedItem().toString();
                        String operMatrix2Name = matrixNameList2.getSelectedItem().toString();
                        String operMatrixResName = matrixNameList.getSelectedItem().toString();
                    if ( mapOfMatrix.get(operMatrix1Name).getCol() == mapOfMatrix.get(operMatrix2Name).getRow()) {
                        Matrix result = mapOfMatrix.get(operMatrix1Name).mul(mapOfMatrix.get(operMatrix2Name));
                        mapOfMatrix.put(operMatrixResName, result);
                        matrixNameFirst = operMatrixResName;
                        MatrixModel myModel = new MatrixModel();
                        table.setModel(myModel);
                        superjPanel.add(jsp);
                        superjPanel.revalidate();
                    }
                    else {
                        JOptionPane.showMessageDialog(jFrame,"You try to multiply inappropriate matrix");
                    }
                }
                    catch (NullPointerException ex)
                    {
                        JOptionPane.showMessageDialog(jFrame,"Select matrix, please");
                    }
                }
            });

            determinantM.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                    String operMatrix1Name = matrixNameList1.getSelectedItem().toString();
                    if (mapOfMatrix.get(operMatrix1Name).getRow() == mapOfMatrix.get(operMatrix1Name).getCol()
                            && mapOfMatrix.get(operMatrix1Name).getCol()  > 0) {
                        Complex result = mapOfMatrix.get(operMatrix1Name).determinant();
                        JOptionPane.showMessageDialog(jFrame, "The determinant of " + operMatrix1Name + " =" + tostring(result));
                    }
                    else {
                        JOptionPane.showMessageDialog(jFrame,"You try to find determinant with inappropriate matrix");
                    }
                }
                    catch (NullPointerException ex)
                    {
                        JOptionPane.showMessageDialog(jFrame,"Select matrix, please");
                    }
                }
            });
            transponseM.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String operMatrix1Name = matrixNameList1.getSelectedItem().toString();
                        String operMatrixResName = matrixNameList.getSelectedItem().toString();
                        Matrix result = mapOfMatrix.get(operMatrix1Name).transponse();
                        mapOfMatrix.put(operMatrixResName, result);
                        matrixNameFirst = operMatrixResName;
                        MatrixModel myModel = new MatrixModel();
                        table.setModel(myModel);
                        superjPanel.add(jsp);
                        superjPanel.revalidate();
                    }
                    catch (NullPointerException ex)
                    {
                        JOptionPane.showMessageDialog(jFrame,"Select matrix, please");
                    }
                }
            });
            setBounds(500,500,350,250);
        }
    }
    /**
     * Класс табличной модели для вывода матрицы в удобном виде
     */
    private static class MatrixModel extends AbstractTableModel
    {
        /**
         * Поле данных в строке
         */
        private Vector tableData;
        /**
         * Поле заголовков столбцов
         */
        private Vector tableTitle;
        /**
         * Конструктор, инициализирующий поля
         */
        public MatrixModel()
        {
            tableData = new Vector();
            tableTitle = new Vector();

            for (int i = 0; i<mapOfMatrix.get(matrixNameFirst).getCol(); ++i)
            {
                tableTitle.add(String.valueOf(i));

            }
            for (int i = 0; i<mapOfMatrix.get(matrixNameFirst).getRow(); ++i)
            {
                    ArrayList<String> line = new ArrayList<>();
                for (int j = 0; j<mapOfMatrix.get(matrixNameFirst).getCol(); ++j)
                {
                    line.add(tostring(mapOfMatrix.get(matrixNameFirst).matrix[i][j]));
                }
                tableData.add(line);
            }
        }
        @Override
        public int getRowCount()
        {
            return mapOfMatrix.get(matrixNameFirst).getRow();
        }
        @Override
        public String getColumnName(int column)
        {
            return String.valueOf(column);
        }
        @Override
        public int getColumnCount()
        {
            return mapOfMatrix.get(matrixNameFirst).getCol();
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex)
        {
            ArrayList lineTemp = (ArrayList)this.tableData.get(rowIndex);
            return lineTemp.get(columnIndex);
        }
        @Override

        public boolean isCellEditable(int rowIndex, int columnIndex)

        {
            return false;
        }
        @Override

        public void setValueAt(Object aValue, int rowIndex, int columnIndex)
        {
            ((ArrayList) this.tableData.get(rowIndex)).set(columnIndex, (ArrayList) aValue);
            super.setValueAt(aValue, rowIndex, columnIndex);
        }
    }
    /**
     * Функция, предоставляющая диалоговое окно с 2-мя вариантами выбора
     * @return возвращает int-овый результат выбора
     */
    private int YorNDia()
    {
       return JOptionPane.showConfirmDialog(jFrame,"Do you wanna start?","Starting",JOptionPane.YES_NO_OPTION);
    }

    /**
     * Функция преобразования комплексного числа в строку
     * @param obj - объект класса {@link Complex}
     * @return возвращает строковое представление числа
     */
    public static String tostring(Complex obj)
    {
        if (obj.getImag_part() >=0)
        {
            return String.format("(%.2f + %.2fi) ",obj.getReal_part(),obj.getImag_part());
        }
        else
        {
            return String.format("(%.2f - %.2fi) ",obj.getReal_part(),Math.abs(obj.getImag_part()));
        }
    }

}
