import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable{
    static final int GAME_WIDTH=1000;
    static final int GAME_HEIGHT=(int)(GAME_WIDTH*(0.555));

    static final Dimension SCREEN_SIZE=new Dimension(GAME_WIDTH,GAME_HEIGHT);

    static final int PADDLE_HEIGHT=100;
    static final int PADDLE_WIDTH=25;

    static final int BALL_DIAMETRE=20;

    Thread gameThread;

    Image image;
    Graphics graphics;
    Paddles paddle1;
    Paddles paddle2;
    Ball ball;
    Scores score=new Scores(GAME_WIDTH,GAME_HEIGHT);
    GamePanel()
    {
        newPaddles();
        newBall();
        this.setFocusable(true);
        this.addKeyListener(new AL());

        this.setPreferredSize(SCREEN_SIZE);

        gameThread=new Thread(this);
        gameThread.start();


    }

    private void newBall() {
        Random random=new Random();
        ball=new Ball(GAME_WIDTH/2-BALL_DIAMETRE/2,random.nextInt(GAME_HEIGHT-BALL_DIAMETRE),BALL_DIAMETRE,BALL_DIAMETRE);
    }

    private void newPaddles() {
        paddle1=new Paddles(0,(GAME_HEIGHT/2-PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
        paddle2=new Paddles(GAME_WIDTH-PADDLE_WIDTH,(GAME_HEIGHT/2-PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2);
    }

    @Override
    public void paint(Graphics g) {
        image=createImage(getWidth(),getHeight());
        graphics=image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);
    }

    private void draw(Graphics g) {
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
    }

    @Override
    public void run() {
        long lastTime=System.nanoTime();
        double amountOfTicks=60.0;
        double ns=1000000000/amountOfTicks;
        double delta=0;
        while(true)
        {
            long now=System.nanoTime();
            delta+=(now-lastTime)/ns;
            lastTime=now;
            if(delta>=1)
            {
                move();
                repaint();
                checkCollsion();
                delta--;
            }

        }
    }

    private void checkCollsion() {
        //bouce ball off the top or bottom window edges
        if(ball.y<=0)
        {
            ball.setYDirection(-ball.yVelocity);
        }
        if(ball.y>=GAME_HEIGHT-BALL_DIAMETRE)
        {
            ball.setYDirection(-ball.yVelocity);
        }
        if(ball.intersects(paddle1))
        {
            ball.xVelocity=-ball.xVelocity;
            ball.xVelocity++;
            if(ball.yVelocity>0)
            {
                ball.yVelocity++;
            }else
            {
                ball.yVelocity--;
            }
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }
        if(ball.intersects(paddle2))
        {
            ball.xVelocity=-ball.xVelocity;
            ball.xVelocity--;
            if(ball.yVelocity>0)
            {
                ball.yVelocity++;
            }else
            {
                ball.yVelocity--;
            }
            ball.setYDirection(ball.yVelocity);
            ball.setXDirection(ball.xVelocity);
        }
        if(paddle1.y<=0)
        {
            paddle1.y=0;

        }
        if(paddle1.y>=GAME_HEIGHT-PADDLE_HEIGHT)
        {
            paddle1.y=GAME_HEIGHT-PADDLE_HEIGHT;
        }
        if(paddle2.y<=0)
        {
            paddle2.y=0;
        }
        if(paddle2.y>=GAME_HEIGHT-PADDLE_HEIGHT)
        {
            paddle2.y=GAME_HEIGHT-PADDLE_HEIGHT;
        }
        if(ball.x>=GAME_WIDTH-BALL_DIAMETRE)
        {
            newPaddles();
            newBall();
            score.player1++;

        }
        if(ball.x<=0)
        {
            newPaddles();
            newBall();
            score.player2++;
        }
    }

    private void move() {
        paddle1.move();
        paddle2.move();
        ball.move();
    }
    public class AL extends KeyAdapter {
        public void keyPressed(KeyEvent e)
        {
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }
        public void keyReleased(KeyEvent e)
        {
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }
}