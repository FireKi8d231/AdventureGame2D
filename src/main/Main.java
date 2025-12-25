package src.main;

import javax.swing.JFrame;

public class Main
{
    public static void main(String[] args)
    {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // pretty obvious
        window.setResizable(false); // la fel de obvious
        window.setTitle("2D Adventure"); // same

        GamePanel gamePanel = new GamePanel(); // acesta este o subclasa a lui JPanel, care contine mai multe metode
        window.add(gamePanel);

        window.pack(); // tot nu stiu ce este mai exact

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}