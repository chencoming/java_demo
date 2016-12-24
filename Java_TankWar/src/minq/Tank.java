package minq;

import java.util.Vector;

class Tank{

	//坦克坐标
	int x = 0;
	int y = 0;
	
	boolean alive = true;
	
	//坦克方向
	//0 上，1右  ，2下，3左
	int direct = 0;
	
	//坦克速度
	int speed = 1;
	
	//坦克的颜色 
	//0 hero  1 enemy;
	int type = 0;
	
	
	Vector<Bullet> allshots =new Vector<Bullet>();
	
	public void fire(){
		
	}
	/**
	 * @param x 坐标
	 * @param y 坐标
	 * @param dir 方向
	 * @param sp 速度
	 * @param ty 类型
	 */
	public Tank(int x,int y,int dir,int sp,int ty){
		this.x = x;
		this.y = y;
		this.direct = dir;
		this.speed = sp;
		this.type = ty;
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

	public int getDirect() {
		return direct;
	}

	public void setDirect(int direct) {
		this.direct = direct;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getType() {
		return type;
	}
	
	public void moveUp(){
		
		y -= speed;
		if(y <= 0){
			y = 0;
		}
	}
	public void moveDown(){
		y += speed;
		if(y >= 300){
			y = 300;
		}
	}
	public void moveLeft(){
		x -= speed;
		if(x <= 0){
			x = 0;
		}
	}
	public void moveRight(){
		x += speed;
		if(x >= 400){
			x = 400;
		}
	}
	
	public Vector<Bullet> getAllshots(){
		return allshots;
	}
	
}











