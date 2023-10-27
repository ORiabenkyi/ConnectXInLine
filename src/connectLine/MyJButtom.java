package connectLine;
import javax.swing.*;

public class MyJButtom extends JButton {
    protected int rouws, column;
    protected boolean free;
    protected String whatShow;

    public MyJButtom(String space) {
        super(space);
    }

    public int getRouws() {
        return rouws;
    }

    public int getColumn() {
        return column;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public void setCoordinate(int rouws, int columns) {
        this.rouws = rouws;
        this.column = columns;
    }
}
class ButtonReset extends JButton {
    public ButtonReset(String space) {
        super(space);
    }

    public ButtonReset() {
        super();
    }
}