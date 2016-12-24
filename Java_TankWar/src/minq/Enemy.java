package minq;

class Enemy extends Tank implements Runnable{
	
	
	Bullet emyshot = null;
	

	public Enemy(int x,int y){
		super(x,y,2,2,1);
		
	}
	
	public void fire(){
		this.emyshot = new Bullet(this.x, this.y,this.direct);
		this.allshots.add(this.emyshot);
		Thread th = new Thread(this.emyshot);
		th.start();
	}	

	@Override
	public void run() {
		while(true){
			switch(this.direct){
			
			case 0:
				for(int i =0;i <30;i++){
					moveUp();
					try{
						Thread.sleep(50);
					}catch(Exception e){
						e.printStackTrace();
					}
				}			
				break;
			
			case 1:
				for(int i =0;i <30;i++){
					moveRight();
					try{
						Thread.sleep(50);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				break;
			
			case 2:
				for(int i =0;i <30;i++){
					moveDown();
					try{
						Thread.sleep(50);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				break;
			
			case 3:
				for(int i =0;i <30;i++){
					moveLeft();
					try{
						Thread.sleep(50);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				break;
			}
			
			
			this.direct = (int)(Math.random()*4);
			
			if(!this.alive){
				break;
			}
			if(this.emyshot!= null && (!this.emyshot.alive)){
				this.allshots.remove(this.emyshot);
				this.fire();
				//System.out.println(this.allshots.size());
			}
		}
		
	}
	
	
	
}

