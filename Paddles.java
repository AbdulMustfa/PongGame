import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddles extends Rectangle {
    int id;
    int yVelocity;
    int speed=10;
    Paddles(int x,int y,int PADDLE_WIDTH,int PADDLE_HEIGHT,int id)
    {
        super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
        this.id=id;
    }
    public void draw(Graphics g)
    {
        if(id==1)
        {
            g.setColor(Color.blue);
        }else {
            g.setColor(Color.red);
        }
        g.fillRect(x,y,width,height);
    }
    public void keyPressed(KeyEvent e)
    {
        switch(id) {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYDirection(-speed);
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(speed);
                }
                break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setYDirection(-speed);
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDirection((speed));
                }
                break;
        }

    }
    public void keyReleased(KeyEvent e)
    {
        switch(id) {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYDirection(0);
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(0);
                }
                break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setYDirection(0);
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDirection(0);
                }
                break;
        }
    }


    public void setYDirection(int i) {
        yVelocity=i;
    }

    public void move()
    {
        y=y+yVelocity;
    }
}