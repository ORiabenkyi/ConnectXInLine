package connectLine;

import javax.swing.*;
import java.awt.*;

public class ConnectFour extends JFrame {
    protected final String showX ="X";
    protected final String showO = "O";
    boolean samBodyWin = false;
    public String turnText = "X";
    public ConnectFour() {
        super("Connect Four");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Board.columnsBoard * 60 + 10, Board.rouwsBoard * 60 + 90);
        setLocationRelativeTo(null);
        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(0,0,Board.columnsBoard * 60 + 10,Board.rouwsBoard * 60 +60 );
        mainPanel.setLayout(null);
        add(mainPanel);

        JPanel upPanel = new JPanel();
        upPanel.setBounds(0,0,Board.columnsBoard * 60,Board.rouwsBoard * 60);
        upPanel.setLayout(new GridLayout(Board.rouwsBoard, Board.columnsBoard, 1, 1));
        mainPanel.add(upPanel);

        Board board = new Board();
        Color blueColorCell = new Color(205, 235, 252);  //CDEBFC
        Color yellowColorCell = new Color(205, 235, 252);  //CDEBFC
        //Color yellowColorCell = new Color(243,233,192);   //F3E9C0
        Color colorNow = blueColorCell;
        for (int i = 0;i<Board.rouwsBoard;i++)
            for (int j=0;j<Board.columnsBoard;j++){
                var button = board.getCellBoard(i,j);
                button.setBackground(colorNow);
                colorNow = colorNow==blueColorCell?yellowColorCell:blueColorCell;
                upPanel.add(button);
                board.getCellBoard(i,j).addActionListener(e -> {
                    if (samBodyWin == true) return;
                    MyJButtom freeButtom = board.getFreeCellInRouw(button.getColumn());
                    if (freeButtom == null) return;
                    freeButtom.setText(""+turnText);
                    freeButtom.whatShow = turnText;
                    freeButtom.setFree(false);
                    samBodyWin = board.checkLine(freeButtom.rouws, freeButtom.column);
                    if (samBodyWin) board.chengeColorButtom(freeButtom.rouws, freeButtom.column);
                    turnText = turnText.equals(showX)?showO:showX;
                });
            }
        //mainPanel.setVisible(true);
        JPanel downPanel = new JPanel();
        downPanel.setBounds(0,Board.rouwsBoard * 60,Board.columnsBoard * 60 + 10,40);  //33DEED
        downPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 8) );
        downPanel.setBackground(new Color(51, 222, 237));
        mainPanel.add(downPanel);
        ButtonReset buttonReset = new ButtonReset();
        buttonReset.setName("ButtonReset");
        buttonReset.setText("Reset");
        buttonReset.setEnabled(true);
        buttonReset.setBounds(0,0,100,30);
        buttonReset.addActionListener(e -> {
            board.resetBoard();
            turnText = "X";
            samBodyWin = false;
        });

        downPanel.add(buttonReset);

        //downPanel.setVisible(true);
        setVisible(true);

    }
    public void addButom(MyJButtom button){
        add(button);
    }
    public void addButom(JLabel jLabel){
        add(jLabel);
    }


}

