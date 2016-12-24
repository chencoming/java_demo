package minq;

import jade.core.behaviours.*;
import jade.lang.acl.*;

@SuppressWarnings("serial")
public class bookbehaviour extends CyclicBehaviour{
	
	bookAgent bkagent = null;
	MessageTemplate mt1=MessageTemplate.MatchPerformative(ACLMessage.CONFIRM);//询问
	MessageTemplate mt2=MessageTemplate.MatchPerformative(ACLMessage.INFORM);//购买

	
	public bookbehaviour(bookAgent bg){
		this.bkagent = bg ;
	}
	@Override
	public void action() {
		// TODO Auto-generated method stub
		ACLMessage msg = bkagent.receive(mt1);	
		String bookname= msg.getContent();
		if(msg!=null){
			//查询书本
			int num = bkagent.querybook(bookname);
			ACLMessage reply = msg.createReply();
			if(num!=0){
				
				reply.setPerformative(ACLMessage.AGREE);
				reply.setContent("having");
			}else if(num==0){
				reply.setPerformative(ACLMessage.REFUSE);
				reply.setContent("没有这本书或者库存为0！");
			}
			bkagent.send(reply);
		}
		
		
		ACLMessage msg2 = bkagent.receive(mt2);
		bookname= msg2.getContent();
			
		if(msg2!=null){
			//买书
			boolean b = bkagent.buybook(bookname);
			ACLMessage reply = msg2.createReply();
			reply.setPerformative(ACLMessage.AGREE);
			if(b){
				reply.setContent("购买成功！");	
			}else{
				reply.setContent("没有此书或库存为0！");	
			}
			//ACLMessage.AGREE;msg="买书成功"
			bkagent.send(reply);
		}
			
		
		
		block();
		
	}
}
