package minq;

public class Bomb {
	
	int x;//������
	int y;//
	
	int liveCircle = 3;
	
	public Bomb(int x,int y){
		this.x = x;
		this.y = y;
	}
	
	public void older(){
		if(this.liveCircle > 0){
			this.liveCircle--;
		}
	}

	
}
