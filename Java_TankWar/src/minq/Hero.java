package minq;


class Hero extends Tank{
	
	Bullet heroshot = null;
	
	public Hero(int x,int y){
		super(x,y,0,2,0);
	}
	
	public void fire(){
		heroshot = new Bullet(this.x, this.y,this.direct);
		allshots.add(heroshot);
		Thread th = new Thread(heroshot);
		th.start();
	}
	
	
}
