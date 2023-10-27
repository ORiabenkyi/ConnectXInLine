package connectLine;

import java.awt.*;

class Board {
    private MyJButtom[][] arrayButtoms;
    public static int rouwsBoard = 10;
    public static int columnsBoard = 10;
    public static int lengthToWin = 2;

    public Board() {
        arrayButtoms = new MyJButtom[rouwsBoard][columnsBoard];
        for (int counterInRouwn = 0; counterInRouwn < rouwsBoard; counterInRouwn++) {
            for (int counterColumn = 0; counterColumn < columnsBoard; counterColumn++) {
                char lebel = (char) ('A' + counterColumn);
                MyJButtom newButtom = new MyJButtom("");
                newButtom.setText(" ");//"+counterInRouwn + " "+lebel  + counterColumn
                newButtom.whatShow = " ";
                newButtom.setCoordinate(counterInRouwn, counterColumn);
                newButtom.setFree(true);
                newButtom.setName("Button" + lebel + (rouwsBoard - counterInRouwn));
                arrayButtoms[counterInRouwn][counterColumn] = newButtom;
            }
        }
    }

    public MyJButtom getCellBoard(int rows, int colums) {
        return this.arrayButtoms[rows][colums];
    }

    public MyJButtom getFreeCellInRouw(int column) {
        for (int counterInRouwn = 0; counterInRouwn < rouwsBoard; counterInRouwn++) {
            if (getCellBoard(rouwsBoard - counterInRouwn - 1, column).isFree())
                return getCellBoard(rouwsBoard - counterInRouwn - 1, column);
        }
        return null;
    }

    public boolean checkLine(int rows, int columns) {
        if (checkDirestion(rows, columns, new int[]{0, 1})) {
            chengeColorDirestion(rows, columns, new int[]{0, 1});
            return true;
        }
        //count down
        if (checkDirestion(rows, columns, new int[]{1, 0})) {
            chengeColorDirestion(rows, columns, new int[]{1, 0});
            return true;
        }
        if (checkDirestion(rows, columns, new int[]{1, 1})) {
            chengeColorDirestion(rows, columns, new int[]{1, 1});
            return true;
        }
        if (checkDirestion(rows, columns, new int[]{1, -1})) {
            chengeColorDirestion(rows, columns, new int[]{1, -1});
            return true;
        }
        //System.out.println(""+colums);
        return false;
    }

    private void chengeColorDirestion(int rows, int columns, int[] direction) {
        int positionX = rows, positionY = columns;
        int stepX = direction[0], stepY = direction[1];
        Color yellowColorCell = new Color(51, 222, 237);
        String whatShowNow = this.getCellBoard(rows, columns).whatShow;
        boolean allowedStep = ((positionX + stepX) < Board.rouwsBoard) && ((positionY + stepY) < Board.columnsBoard);
        getCellBoard(rows, columns).setBackground(yellowColorCell);
        while (allowedStep) {
            positionX += stepX;
            positionY += stepY;
            MyJButtom buttomThatCheked = this.getCellBoard(positionX, positionY);
            String whatInCell = buttomThatCheked.whatShow;
            if (whatInCell.equals(whatShowNow) == true)
                getCellBoard(positionX, positionY).setBackground(yellowColorCell);
            else break;
            allowedStep = ((positionX + stepX) < Board.rouwsBoard) && ((positionY + stepY) < Board.columnsBoard);
        }
        positionX = rows;
        positionY = columns;
        allowedStep = ((positionX - stepX) >= 0) && ((positionY - stepY) >= 0);
        while (allowedStep) {
            positionX -= stepX;
            positionY -= stepY;
            MyJButtom buttomThatCheked = this.getCellBoard(positionX, positionY);
            String whatInCell = buttomThatCheked.whatShow;
            if (whatInCell.equals(whatShowNow) == true)
                getCellBoard(positionX, positionY).setBackground(yellowColorCell);
            else break;
            allowedStep = ((positionX - stepX) >= 0) && ((positionY - stepY) >= 0);
        }
    }

    private boolean checkDirestion(int rows, int columns, int[] direction) {
        boolean result = false;
        int length = 1;
        int positionX = rows, positionY = columns;
        int stepX = direction[0], stepY = direction[1];
        String whatShowNow = this.getCellBoard(rows, columns).whatShow;
        boolean allowedStep;
        allowedStep = stepY < Math.abs(stepY) ? allowedStep = ((positionX + stepX) < Board.rouwsBoard)
                && ((positionY + stepY) >= 0) : ((positionX + stepX) < Board.rouwsBoard) && ((positionY + stepY) < Board.columnsBoard);
        while (allowedStep) {
            positionX += stepX;
            positionY += stepY;
            MyJButtom buttomThatCheked = this.getCellBoard(positionX, positionY);
            String whatInCell = buttomThatCheked.whatShow;
            if (whatInCell.equals(whatShowNow) == true)
                length++;
            else break;
            allowedStep = stepY < Math.abs(stepY) ? allowedStep = ((positionX + stepX) < Board.rouwsBoard)
                    && ((positionY + stepY) >= 0) : ((positionX + stepX) < Board.rouwsBoard) && ((positionY + stepY) < Board.columnsBoard);
        }
        positionX = rows;
        positionY = columns;
//        allowedStep = ((positionX-stepX)>=0) && ((positionY+stepY)<Board.columnsBoard);
        allowedStep = stepY < Math.abs(stepY) ? ((positionX - stepX) >= 0)
                && ((positionY - stepY) < Board.columnsBoard) : ((positionX - stepX) >= 0) && ((positionY - stepY) >= 0);
        while (allowedStep) {
            positionX -= stepX;
            positionY -= stepY;
            MyJButtom buttomThatCheked = this.getCellBoard(positionX, positionY);
            String whatInCell = buttomThatCheked.whatShow;
            if (whatInCell.equals(whatShowNow) == true)
                length++;
            else break;
            allowedStep = stepY < Math.abs(stepY) ? ((positionX - stepX) >= 0)
                    && ((positionY - stepY) < Board.columnsBoard) : ((positionX - stepX) >= 0) && ((positionY - stepY) >= 0);
        }
        if (length >= lengthToWin) result = true;
        return result;
    }


    public void resetBoard() {
        Color blueColorCell = new Color(205, 235, 252);  //CDEBFC
        Color yellowColorCell = new Color(205, 235, 252);  //CDEBFC
        //Color yellowColorCell = new Color(243,233,192);   //F3E9C0
        Color colorNow = blueColorCell;

        for (int i = 0; i < Board.rouwsBoard; i++)
            for (int j = 0; j < Board.columnsBoard; j++) {
                var button = this.getCellBoard(i, j);
                button.setBackground(colorNow);
                colorNow = colorNow == blueColorCell ? yellowColorCell : blueColorCell;
                button.setText("");
                button.setFree(true);
                button.whatShow = " ";
                //button.setBackground(new Color(205, 235, 252));
            }
    }

    public void chengeColorButtom(int rouws, int column) {
    }
}
