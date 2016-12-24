package minq;

import jade.core.behaviours.*;
import jade.lang.acl.*;

@SuppressWarnings("serial")
public class bookbehaviour extends CyclicBehaviour{
	
	bookAgent bkagent = null;
	MessageTemplate mt1=MessageTemplate.MatchPerformative(ACLMessage.CONFIRM);//ѯ��
	MessageTemplate mt2=MessageTemplate.MatchPerformative(ACLMessage.INFORM);//����

	
	public bookbehaviour(bookAgent bg){
		this.bkagent = bg ;
	}
	@Override
	public void action() {
		// TODO Auto-generated method stub
		ACLMessage msg = bkagent.receive(mt1);	
		String bookname= msg.getContent();
		if(msg!=null){
			//��ѯ�鱾
			int num = bkagent.querybook(bookname);
			ACLMessage reply = msg.createReply();
			if(num!=0){
				
				reply.setPerformative(ACLMessage.AGREE);
				reply.setContent("having");
			}else if(num==0){
				reply.setPerformative(ACLMessage.REFUSE);
				reply.setContent("û���Ȿ����߿��Ϊ0��");
			}
			bkagent.send(reply);
		}
		
		
		ACLMessage msg2 = bkagent.receive(mt2);
		bookname= msg2.getContent();
			
		if(msg2!=null){
			//����
			boolean b = bkagent.buybook(bookname);
			ACLMessage reply = msg2.createReply();
			reply.setPerformative(ACLMessage.AGREE);
			if(b){
				reply.setContent("����ɹ���");	
			}else{
				reply.setContent("û�д������Ϊ0��");	
			}
			//ACLMessage.AGREE;msg="����ɹ�"
			bkagent.send(reply);
		}
			
		
		
		block();
		
	}
}
