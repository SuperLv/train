package activeMQ.V2;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Receiver {
	public static void main(String[] args) throws Exception {
		// 1 ����connectionfactory�������� ��Ҫ�û�������,���ӵ�ַ �˿�Ϊtcp://localhost:61616
		System.out.println(ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
		ConnectionFactory connectionfactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER,
				ActiveMQConnectionFactory.DEFAULT_PASSWORD, "tcp://localhost:61616");
		// 2 ͨ��connectionfactory�������󴴽�һ��connection����,���ҵ���connection��start��������
		// connectionĬ���ǹرյ�
		Connection connection = connectionfactory.createConnection();
		connection.start();
		// 3 ͨ��connection����session�Ự,���ڽ�����Ϣ ���ò��� 1���Ƿ���������,2����ǩ��ģʽ
//		Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
		Session session = connection.createSession(Boolean.FALSE, Session.CLIENT_ACKNOWLEDGE);
		//ʹ������ķ�ʽ ������Ϣ�ķ���
//		Session session = connection.createSession(Boolean.TRUE , Session.AUTO_ACKNOWLEDGE);
		// 4 ͨ��session����һ��Destination����
		// ָ����һ���ͻ�������ָ��������ϢĿ�����Ϣ����Ϣ��Դ�Ķ���,��p2p��Destination��������Queue,��pub/subģʽ�� ,����Ϊtopic
		Destination destination = session.createQueue("queue1");
		// 5 ��Ҫͨ��session���󴴽���Ϣ�ķ��ͺͽ��ն��� MessageProducer/MessageConsumer
		MessageConsumer messageConsumer=session.createConsumer(destination);
		// 6 �������� ����MessageProducer ����
		while(true) {
			TextMessage msg=(TextMessage) messageConsumer.receive();
			//�ֹ�ȥǩ����Ϣ,����һ���߳�ȥ֪ͨmq ���� ȷ��ǩ��
			msg.acknowledge();
			if(msg==null) break;
			System.out.println("���ܵ�������"+msg.getText( ));
		}

		connection.close();
	}
}
