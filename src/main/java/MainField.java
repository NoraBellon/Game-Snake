import javax.swing.*;

public class MainField extends JFrame
{
    public MainField()
    {
        setTitle("Snake");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(320,345);
        setLocationByPlatform(true);
        add(new GameField());
        setVisible(true);

    }
    public static void main(String[] agrs)
    {
        MainField mw = new MainField();
    }
}

