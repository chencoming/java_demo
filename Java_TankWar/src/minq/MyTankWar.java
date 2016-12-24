package minq;

import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

@SuppressWarnings("serial")
public class MyTankWar extends JFrame{
	
	
	public MyPanel mp =null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MyTankWar();  
	}
	
	public MyTankWar(){
		
		mp = new MyPanel();
		Thread th = new Thread(mp);
		th.start();
		this.add(mp);
		this.addKeyListener(mp);
		
		this.setSize(600,600);
		this.setLocation(500,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	

}


@SuppressWarnings("serial")
class MyPanel extends JPanel implements KeyListener,Runnable{
	
	Hero hero = null;
	
	// 敌人
	int enemySize = 3;
	Vector<Enemy> emys = new Vector <Enemy>();

	// 爆炸效果
	Vector<Bomb> bombs = new Vector <Bomb>();
	
	Image bomb1 = null;
	Image bomb2 = null;
	Image bomb3 = null;
	
	public MyPanel(){
		
		hero = new Hero(100,100);
		
		for(int i = 0;i < enemySize;i++){
			Enemy em = new Enemy((i +1)*50, 0);
			Thread t = new Thread(em);
			t.start();
			em.fire();
			emys.add(em);
		}
		
		bomb1 = new ImageIcon("res/bomb1.png").getImage();
		bomb2 = new ImageIcon("res/bomb2.png").getImage();
		bomb3 = new ImageIcon("res/bomb3.png").getImage();
	}
	public void paint(Graphics g){
		super.paint(g);
		g.fillRect(0, 0, 400, 300);
		
		
		
		//画英雄坦克
		if(hero.alive){
			drawTank(hero.getX(),hero.getY(),hero.getDirect(),hero.getType(),g);	
		}
		
		Vector<Bullet> heroAllshots = hero.getAllshots();
		//System.out.println("heroAllshots.size()"+ heroAllshots.size());
		//int heroBulletNum = heroAllshots.size();
		
		//画英雄坦克的子弹
		g.setColor(Color.CYAN);
		for(int i= 0 ; i< heroAllshots.size();i++){		
			Bullet mybullet = heroAllshots.get(i);
			if( mybullet != null && mybullet.getAlive()){
				
				g.draw3DRect(mybullet.getX(), mybullet.getY(),3,3,false);
			}
			if(!mybullet.getAlive()){
				heroAllshots.remove(mybullet);
				//System.out.println( "第 " + i + "bullet dead");
			}
		}
		
		
		
		//画敌人坦克
		for(int i = 0;i < emys.size();i++){
			Enemy emyTank = emys.get(i); 
			if(emyTank.alive){
				drawTank(emyTank.getX(),emyTank.getY(),emyTank.getDirect() ,emyTank.getType(),g);				
			}
			
			//画敌人坦克的子弹
			g.setColor(Color.YELLOW);
			Vector<Bullet> emyAllshots = emyTank.getAllshots();
			
			for(int j= 0 ; j< emyAllshots.size();j++){
				
				Bullet emybullet = emyAllshots.get(j);
				if( emybullet!=null && emybullet.getAlive()){
					g.draw3DRect(emybullet.getX(), emybullet.getY(), 3, 3, false);
				}
				if(!emybullet.getAlive()){
					emyAllshots.remove(emybullet); 
				}
			}

		}
		
		
		//画出子弹爆炸
		for(int i = 0; i < bombs.size(); i++){
			Bomb bomb = bombs.get(i);
			if(bomb != null){
				if(bomb.liveCircle == 3){
					g.drawImage(bomb1, bomb.x, bomb.y, null);
				}else if(bomb.liveCircle == 2){
					g.drawImage(bomb2, bomb.x + 2, bomb.y + 3, null);
				}else if(bomb.liveCircle == 1){
					g.drawImage(bomb3, bomb.x + 8, bomb.y + 8, null);
				}
				bomb.older();
				if(bomb.liveCircle == 0){
					bombs.remove(bomb);
				}
			}
			
		}
	}                                                                       
	
	/**
	 * 画出坦克
	 * @param x
	 * @param y
	 * @param dir
	 * @param type
	 * @param g
	 */
	public void drawTank(int x,int y,int dir,int type,Graphics g){
		
		//判断坦克的类型
		switch(type){
			case 0:                             
				g.setColor(Color.CYAN);break;
			case 1:                                                                    
				g.setColor(Color.YELLOW);break;
		}
		
		//判断坦克的方向
		switch(dir){
			case 0:	//向上			
				g.fill3DRect(x, y, 5, 30, false);
				g.fill3DRect(x + 15, y, 5, 30, false);
				g.fill3DRect(x + 5, y+5, 10, 20, false);
				g.fillOval(x + 5, y+10, 10, 10);			
				g.drawLine(x + 10, y+15, x + 10, y);
				break;
			case 1:	//向右			
				g.fill3DRect(x, y, 30, 5, false);
				g.fill3DRect(x , y+ 15,30, 5,  false);
				g.fill3DRect(x + 5, y+5, 20, 10, false);
				g.fillOval(x + 10, y+5, 10, 10);
				g.drawLine(x + 15, y+10, x + 30, y+10);
				break;
			case 2:	//向下			
				g.fill3DRect(x, y, 5, 30, false);
				g.fill3DRect(x + 15, y, 5, 30, false);
				g.fill3DRect(x + 5, y+5, 10, 20, false);
				g.fillOval(x + 5, y+10, 10, 10);
				g.drawLine(x + 10, y+15, x + 10, y+30);
				break;
			case 3:	//向左		
				g.fill3DRect(x, y, 30, 5, false);
				g.fill3DRect(x , y+ 15,30, 5,  false);
				g.fill3DRect(x + 5, y+5, 20, 10, false);
				g.fillOval(x + 10, y+5, 10, 10);
				g.drawLine(x + 15, y+10, x, y+10);
				break;
		}
	}
	
	
	
	/**
	 * 四个按键（w a s d）控制坦克运动方向
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		
		if(!hero.alive){
			return;
		}
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_W){
			this.hero.setDirect(0);
			this.hero.moveUp();
			
		}else if(e.getKeyCode()==KeyEvent.VK_D){
			this.hero.setDirect(1);
			this.hero.moveRight();
			
		}else if(e.getKeyCode()==KeyEvent.VK_S){
			this.hero.setDirect(2);
			this.hero.moveDown();
			
		}else if(e.getKeyCode()==KeyEvent.VK_A){
			this.hero.setDirect(3);
			this.hero.moveLeft();
		}
		
		if(e.getKeyCode()==KeyEvent.VK_J){	
			if(this.hero.getAllshots().size() < 5){
				this.hero.fire();
			}
			
		}	
			
		this.repaint();
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//调用函数判断英雄子弹和敌人坦克是否相碰
			for(int i = 0; i < hero.getAllshots().size();i++){
				
				Bullet heroshot = hero.getAllshots().get(i);
				
				if(heroshot.getAlive()){
					for(int j = 0;j < this.emys.size();j++){
						Enemy emyTank = emys.get(j);
						if(emyTank.alive){
							checkHitTank(heroshot,emyTank);	
						}
					}
				}			
			}
			
			//调用函数判断敌人子弹和英雄坦克是否相碰
			for(int i = 0; i < emys.size();i++){
				
				Enemy emyTank = emys.get(i);
				if(emyTank!=null){
					for(int j = 0;j < emyTank.getAllshots().size();j++){
						Bullet emyshot = emyTank.getAllshots().get(j);
						if(emyshot.getAlive()&& hero.alive){					
							checkHitTank(emyshot,hero);
						}	
					}
				}			
			}
			
			this.repaint();
		}
	}


	/**
	 * 检查子弹是否击中坦克
	 * @param b 子弹
	 * @param t 坦克
	 */
	public void checkHitTank(Bullet b,Tank t){
		Bomb myBomb = null;
		switch(t.getDirect()){
		case 0:
		case 2:
			if(b.getX()> t.getX() && b.getX()< t.getX()+20 
					&& b.getY()> t.getY() && b.getY()< t.getY()+30){
				b.alive = false;
				t.alive = false;				
				System.out.println(t.getClass().getName());
				myBomb = new Bomb(t.getX(),t.getY());				
			}
			break;
		case 1:
		case 3:
			if(b.getX()> t.getX() && b.getX()< t.getX()+30 
					&& b.getY()> t.getY() && b.getY()< t.getY()+20){
				b.alive = false;
				t.alive = false;
				System.out.println(t.getClass().getName());
				myBomb = new Bomb(t.getX(),t.getY());
			}
			break;
		}
		
		bombs.add(myBomb);
	}



}



