import java.awt.*;

public class Scores extends Rectangle {
    static int GAME_WIDTH;
    static int GAME_HEIGHT;
    int player1;
    int player2;
    Scores(int GAME_WIDTH,int GAME_HEIGHT)
    {
        Scores.GAME_HEIGHT=GAME_HEIGHT;
        Scores.GAME_WIDTH=GAME_WIDTH;
    }
    public void draw(Graphics g)
    {
        g.setColor(Color.white);
        g.setFont(new Font("Consolas",Font.PLAIN,60));
        g.drawString(String.valueOf(player1/10)+String.valueOf(player1%10),GAME_WIDTH/2-80,100);
        g.drawString(String.valueOf(player2/10)+String.valueOf(player2%10),GAME_WIDTH/2+20,100);
    }
}