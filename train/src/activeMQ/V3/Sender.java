package activeMQ.V3;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Sender {
	
	public static void main(String[] args) throws JMSException {
		//1 ����connectionfactory�������� ��Ҫ�û�������,���ӵ�ַ �˿�Ϊtcp://localhost:61616 
		System.out.println(ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
		ConnectionFactory connectionfactory=new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER,ActiveMQConnectionFactory.DEFAULT_PASSWORD,"tcp://localhost:61616");
		//2 ͨ��connectionfactory�������󴴽�һ��connection����,���ҵ���connection��start�������� connectionĬ���ǹرյ�
		Connection connection=connectionfactory.createConnection();
		connection.start();
		//3 ͨ��connection����session�Ự,���ڽ�����Ϣ ���ò��� 1���Ƿ���������,2����ǩ��ģʽ 
		Session session=connection.createSession(Boolean.FALSE,Session.AUTO_ACKNOWLEDGE); 
		//4 ͨ��session����һ��Destination����  ָ����һ���ͻ�������ָ��������ϢĿ�����Ϣ����Ϣ��Դ�Ķ���,��p2p��Destination��������Queue,��pub/subģʽ�� ,����Ϊtopic
		Destination destination=session.createQueue("queue1");
		//5 ��Ҫͨ��session���󴴽���Ϣ�ķ��ͺͽ��ն���  MessageProducer/MessageConsumer
//		MessageProducer messageProducer=session.createProducer(destination);
		MessageProducer messageProducer=session.createProducer(null);
		//6 ʹ��MessageProducer ��setDeliveryMode���� ���ó־û��ͷǳ־û�������
//		messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		//7 �������� ����MessageProducer ����
		for (int i = 0; i < 5; i++) {
			TextMessage textMessage=session.createTextMessage();
			textMessage.setText("hello world!!!!  "+i);
//			messageProducer.send(textMessage);
			
			/*
			 * 1:Ŀ�ĵ�
			 * 2:��Ϣ
			 * 3:�Ƿ�־û�
			 * 4:���ȼ�(0-9 Ĭ��Ϊ4 0-4��ʾ��ͨ,5-9��ʾ�Ӽ�) ������֤ �������ȼ�����
			 * 5:��Ϣ��mq�ϴ��ʱ�� ����
			 */
			
			messageProducer.send(destination, textMessage, DeliveryMode.NON_PERSISTENT, i, 1000*60*2);
			System.out.println("������:"+textMessage.getText());
		}
		connection.close();
	}
}
