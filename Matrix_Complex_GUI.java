import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.EventHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Matrix_Complex_GUI {

    private static  JFrame jFrame = new JFrame();
    private static Font font = new Font("Times New Roman",Font.BOLD,20);
    private Map<String , Matrix> mapOfMatrix = new HashMap<>();
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
                                    FillMatrix fillMatrix = new FillMatrix(matrix_name);
                                    fillMatrix.setVisible(true);
                                  }
                              }
                          }
                      }
                  }
              });
              JButton test = new JButton("te");
              jPanel.add(test, BorderLayout.NORTH);
              jPanel.add(createButton, BorderLayout.WEST);
              jFrame.revalidate();
          }
       }
       else
       {System.exit(0);}
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
        public FillMatrix(String matrix_name)
        {
            super(jFrame,"FillingMatrix",false);
            add(new JLabel("Enter like: -2.4+-1.3 and a space after every number"),BorderLayout.NORTH);
            JTextArea values = new JTextArea(5,5);
            add (new JScrollPane(values),BorderLayout.CENTER);
            JButton fillMatr = new JButton("Fill the Matrix");
            fillMatr.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
            add(fillMatr,BorderLayout.SOUTH);
            setBounds(500,500,350,250);
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
