session的使用

1:session具有事务 
Session session = connection.createSession(Boolean.FALSE, Session.CLIENT_ACKNOWLEDGE);
createSession的时候如果指定事务为true 那么在发送消息以后 需要session.commit()

2:session的签收方式
    AUTO_ACKNOWLEDGE    自动签收
    CLIENT_ACKNOWLEDGE  客户端签收
    DUPS_DK_ACKNOWLEDGE 指session不必确保消息签收 可能会引起消息接受重复 但是降低了session的开销 如果客户端可以容忍 则可以开启
