import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.EventHandler;
import java.util.*;

public class Matrix_Complex_GUI {

    private static  JFrame jFrame = new JFrame();
    private static Font font = new Font("Times New Roman",Font.BOLD,20);
    private static Map<String , Matrix> mapOfMatrix = new HashMap<>();



   static JTable table = new JTable();
    static JScrollPane jsp = new JScrollPane(table);
    Matrix_Complex_GUI()
    {
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        jFrame.setBounds(dimension.width/2 - 250, dimension.height/2 - 150, 500, 300);
        jFrame.setVisible(true);
        jFrame.setTitle("Matrix and Complex");
        jFrame.add(new Hello_Comp());
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
              Create(createButton,jPanel);
              jPanel.add(createButton, BorderLayout.WEST);
              jFrame.revalidate();
          }
       }
       else
       {System.exit(0);}
    }

    private void Create(JButton createButton, JPanel jPanel)
    {
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String matrix_name = JOptionPane.showInputDialog(jPanel,"Please, enter the name of your matrix to have the" +
                        " access to it in the future","matrix1");
                if (!matrix_name.isEmpty())
                {
                    String row = JOptionPane.showInputDialog(jPanel,"Please, enter the numbers of rows in " +
                                    "your matrix",
                            "0");
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
                                mapOfMatrix.put(matrix_name,new Matrix(rows,cols));
                                // System.out.println(mapOfMatrix.get(matrix_name).tostring());
                                // StringBuilder text = mapOfMatrix.get(matrix_name).tostring();
                                // JTextArea textarea = new JTextArea();
                                // textarea.setText(text.toString());
                                // textarea.setFont(new Font("Arial",Font.BOLD,18));
                                // jPanel.add(textarea,BorderLayout.CENTER);
                                // jPanel.revalidate();
                                FillMatrix fillMatrix = new FillMatrix(matrix_name, jPanel);
                                fillMatrix.setVisible(true);

                                jPanel.repaint();
                                jPanel.revalidate();
                            }
                        }
                    }
                }
            }
        });
    }
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

    static class FillMatrix extends JDialog
    {
        public FillMatrix(String matrix_name, JPanel superjPanel)
        {
            super(jFrame,"FillingMatrix",false);

            JFrame acceptFrame = new JFrame();
            acceptFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension dimension = toolkit.getScreenSize();
            acceptFrame.setBounds(dimension.width/2 + 300, dimension.height/2 - 100, 270, 150);
            acceptFrame.setVisible(true);
            acceptFrame.setTitle("Acception");

            JTextArea textarea = new JTextArea();
             textarea.setText(" Please, enter complex numbers\n like this example '-2.1+-3.7'\n in each square and press the\n" +
                     " button Fill the Matrix after this");
             textarea.setFont(new Font("Arial",Font.BOLD,15));
            acceptFrame.add(textarea,BorderLayout.CENTER);
            acceptFrame.revalidate();
          // JTextArea values = new JTextArea(5,5);

          //  add (new JScrollPane(values),BorderLayout.CENTER);

            JTextField[][] matrix;
            matrix = new JTextField[mapOfMatrix.get(matrix_name).getRow()][mapOfMatrix.get(matrix_name).getCol()];
            setLayout(new GridLayout(mapOfMatrix.get(matrix_name).getRow(),mapOfMatrix.get(matrix_name).getCol()));
            for (int i = 0; i < mapOfMatrix.get(matrix_name).getRow(); i++){
                for (int j = 0; j < mapOfMatrix.get(matrix_name).getCol(); j++) {
                    matrix[i][j] = new JTextField("");
                    add(matrix[i][j],BorderLayout.CENTER);
                }
            }
            JButton fillMatr = new JButton("Fill the Matrix");
            fillMatr.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ConsoleInput fillM = new ConsoleInput();
                    for (int i = 0; i < mapOfMatrix.get(matrix_name).getRow(); i++){
                        for (int j = 0; j < mapOfMatrix.get(matrix_name).getCol(); j++) {
                            mapOfMatrix.get(matrix_name).matrix[i][j] = fillM.formatingString(matrix[i][j].getText());
                        }
                    }
                    setVisible(false);
                    acceptFrame.setVisible(false);

                    MatrixModel myModel=  new MatrixModel();
                    table.setModel(myModel);
                    superjPanel.add(jsp);
                    superjPanel.revalidate();
                }
            });
            acceptFrame.add(fillMatr,BorderLayout.SOUTH);
            setBounds(500,500,350,250);
        }
    }

    private static class MatrixModel extends AbstractTableModel
    {

        private Vector tableData;
        private Vector tableTitle;
        public MatrixModel()
        {
            tableData = new Vector();
            tableTitle = new Vector();
            for (int i = 0; i<mapOfMatrix.get("matrix1").getCol(); ++i)
            {
                tableTitle.add(String.valueOf(i));

            }
            for (int i = 0; i<mapOfMatrix.get("matrix1").getRow(); ++i)
            {
                    ArrayList<String> line = new ArrayList<>();
                for (int j = 0; j<mapOfMatrix.get("matrix1").getCol(); ++j)
                {
                    line.add(mapOfMatrix.get("matrix1").matrix[i][j].toString());
                }
                tableData.add(line);
            }
        }
        @Override
        public int getRowCount() {
            return mapOfMatrix.get("matrix1").getRow();
        }
        @Override
        public String getColumnName(int column) {
            return String.valueOf(column);
        }
        @Override
        public int getColumnCount() {
            return mapOfMatrix.get("matrix1").getCol();
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
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

            // Используйте эту функцию для сброса данных ячейки при изменении данных ячейки

            // Мы думаем об этом, данные помещаются в TableData, говоря прямо, чтобы изменить данные, чтобы изменить

            // Данные в TableData, поэтому нам нужно только изменить соответствующие данные TableData здесь



            ((ArrayList) this.tableData.get(rowIndex)).set(columnIndex, (ArrayList) aValue);

            super.setValueAt(aValue, rowIndex, columnIndex);

            //

            // Фактически, супер метод здесь должен вызывать fireTableCellUpdated () только в соответствии с обновлением

            // Данные, соответствующие ячейке

            //fireTableCellUpdated(rowIndex, columnIndex);

        }
    }
    private int YorNDia()
    {
       return JOptionPane.showConfirmDialog(jFrame,"Do you wanna start?","Starting",JOptionPane.YES_NO_OPTION);
    }
    private int showYorN()
    {
        JPanel jPanel = new JPanel();
        GridBagLayout gridBagLayout = new GridBagLayout();
        jPanel.setLayout(gridBagLayout);
        jFrame.add(jPanel);
        GridBagConstraints constraints1 = new GridBagConstraints();
        constraints1 = fillConstrains(0,0,0,0,5,5, 10,10, constraints1);
        GridBagConstraints constraints2 = new GridBagConstraints();
        constraints2 = fillConstrains(0,0,5,0,5,5,10,10, constraints2);
        JButton yesButton = new JButton("Yes");
        JButton noButton = new JButton("No");
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jPanel.removeAll();
                jFrame.getContentPane().removeAll();
                jFrame.repaint();
                jFrame.repaint();
                jFrame.revalidate();

            }
        });
        jPanel.add(yesButton,constraints1);
        jPanel.add(noButton,constraints2);
        jPanel.revalidate();
        return 0;
    }

private GridBagConstraints fillConstrains(int weightx, int weighty,int gridx, int gridy,int gridwidth, int gridheight,
                                          int ipadx, int ipady,
                                          GridBagConstraints constr)
{
    constr.weightx = weightx;
    constr.weighty = weighty;
    constr.gridx = gridx;
    constr.gridy = gridy;
    constr.gridwidth = gridwidth;
    constr.gridheight = gridheight;
    constr.ipadx = ipadx;
    constr.ipady = ipady;
    return constr;
}


}
