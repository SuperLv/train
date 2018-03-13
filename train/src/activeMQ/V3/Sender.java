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
		//1 建立connectionfactory工厂对象 需要用户名密码,连接地址 端口为tcp://localhost:61616 
		System.out.println(ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
		ConnectionFactory connectionfactory=new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER,ActiveMQConnectionFactory.DEFAULT_PASSWORD,"tcp://localhost:61616");
		//2 通过connectionfactory工场对象创建一个connection连接,并且调用connection的start方法连接 connection默认是关闭的
		Connection connection=connectionfactory.createConnection();
		connection.start();
		//3 通过connection创建session会话,用于接收消息 配置参数 1是是否启用事务,2配置签收模式 
		Session session=connection.createSession(Boolean.FALSE,Session.AUTO_ACKNOWLEDGE); 
		//4 通过session创建一个Destination对象  指的是一个客户端用来指定生产消息目标和消息的信息来源的对象,在p2p中Destination被称作是Queue,在pub/sub模式中 ,被称为topic
		Destination destination=session.createQueue("queue1");
		//5 需要通过session对象创建消息的发送和接收对象  MessageProducer/MessageConsumer
//		MessageProducer messageProducer=session.createProducer(destination);
		MessageProducer messageProducer=session.createProducer(null);
		//6 使用MessageProducer 的setDeliveryMode方法 设置持久化和非持久化的特性
//		messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		//7 创建数据 并用MessageProducer 发送
		for (int i = 0; i < 5; i++) {
			TextMessage textMessage=session.createTextMessage();
			textMessage.setText("hello world!!!!  "+i);
//			messageProducer.send(textMessage);
			
			/*
			 * 1:目的地
			 * 2:消息
			 * 3:是否持久化
			 * 4:优先级(0-9 默认为4 0-4表示普通,5-9表示加急) 并不保证 按照优先级接受
			 * 5:消息在mq上存放时间 毫秒
			 */
			
			messageProducer.send(destination, textMessage, DeliveryMode.NON_PERSISTENT, i, 1000*60*2);
			System.out.println("生产着:"+textMessage.getText());
		}
		connection.close();
	}
}
