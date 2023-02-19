import java.awt.*;
import java.util.Random;

public class Ball extends Rectangle {
    int xVelocity;
    int yVelocity;
    int initialSpeed=5;
    Random random;
    Ball(int x,int y,int width,int hight)
    {
        super(x,y,width,hight);
        random=new Random();
        int randomXDirection=random.nextInt(2);
        if(randomXDirection==0)
        {
            randomXDirection--;
        }
        setXDirection(randomXDirection);
        int randomYDirection=random.nextInt(2);
        if(randomYDirection==0)
        {
            randomYDirection--;
        }
        setYDirection(randomYDirection);
    }

    public void setYDirection(int randomYDirection) {
        yVelocity=randomYDirection;
    }

    public void setXDirection(int randomXDirection) {
        xVelocity=randomXDirection;
    }
    public void move()
    {
        x+=xVelocity;
        y+=yVelocity;
    }
    public void draw(Graphics g)
    {
        g.setColor(Color.white);
        g.fillOval(x,y,width,height);

        g.drawLine(1000/2,0,1000/2,555);
    }
}