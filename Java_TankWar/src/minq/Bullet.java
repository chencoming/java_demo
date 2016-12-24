package minq;

public class Bullet implements Runnable{

	int x;
	int y;
	
	int speed = 1;
	int direct;
	boolean alive = true;

	
	public Bullet(int x, int y, int dir){
		
		this.direct = dir;
		
		switch(dir){
		case 0:
			this.x = x + 10;
			this.y = y;
			break;
		case 1:
			this.x = x + 30;
			this.y = y + 10;
			break;
		case 2:
			this.x = x + 10;
			this.y = y + 30;
			break;
		case 3:
			this.x = x;
			this.y = y + 10;
			break;
		}
		
	}
	public int getDirect() {
		return direct;
	}
	public void setDirect(int direct) {
		this.direct = direct;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public boolean getAlive() {
		return alive;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				Thread.sleep(50);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			switch(direct){
			case 0:
				this.y -= this.speed;
				break;  
			case 1:
				this.x += this.speed;
				break;  
			case 2:
				this.y += this.speed;
				break;  
			case 3:
				this.x -= this.speed;
				break;  
			}
			
			//System.out.println("x=" +x + "  y=" + y);
			if(x < 0 || y < 0 || x > 400 || y > 300){
				this.alive = false;
			}
		}
		
	}
	
	
}
