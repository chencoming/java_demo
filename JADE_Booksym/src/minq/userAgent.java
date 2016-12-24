package minq;

import javax.swing.JOptionPane;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

@SuppressWarnings("serial")
public class userAgent extends Agent{
	
	ACLMessage msg = null;
	
	
	public void sendQueryMsg(String bookname){
		msg.setPerformative(ACLMessage.CONFIRM);
		msg.setContent(bookname);
		send(msg);
	}
	
	public void sendInformMsg(String bookname){
		msg.setPerformative(ACLMessage.INFORM);
		msg.setContent(bookname);
		send(msg);
	}
	
	protected void setup(){
		//System.out.println("ok!");
		
		Myframe frame  = new Myframe();		
		frame.setframe(frame,this);
		
		
		SequentialBehaviour sb = new SequentialBehaviour();
		
		
		sb.addSubBehaviour(new OneShotBehaviour(this){

			@SuppressWarnings("deprecation")
			@Override
			public void action() {
				// TODO Auto-generated method stub
				msg = new ACLMessage();
				
				msg.addReceiver(new AID("bookstore",AID.ISLOCALNAME));
				msg.setLanguage("Chinese");		
			}
		});
		
		
		sb.addSubBehaviour(new CyclicBehaviour(this){

			@Override
			public void action() {
				// TODO Auto-generated method stub
				MessageTemplate mt1 = MessageTemplate.MatchPerformative(ACLMessage.REFUSE);
				MessageTemplate mt2 = MessageTemplate.MatchPerformative(ACLMessage.AGREE);
				
				String reply = null;
				
				
				ACLMessage msg = receive(mt1);		
				if(msg!=null){
					//拒绝
					reply = msg.getContent();
					JOptionPane.showMessageDialog(null, reply);
				}
				
				
				ACLMessage msg2 = receive(mt2);
				if(msg2!=null){	
					msg2 = receive(mt2);
					if(msg2!=null){
						//有书或者购买成功
						reply = msg2.getContent();
						if(reply.equals("having")){
							JOptionPane.showMessageDialog(null, "查有此书，如需购买请点击“购买”");
						}else{
							JOptionPane.showMessageDialog(null, reply);
						}					
					}
				}
				
			}
			
		});
		
	}
	
	
	
}
