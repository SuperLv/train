session��ʹ��

1:session�������� 
Session session = connection.createSession(Boolean.FALSE, Session.CLIENT_ACKNOWLEDGE);
createSession��ʱ�����ָ������Ϊtrue ��ô�ڷ�����Ϣ�Ժ� ��Ҫsession.commit()

2:session��ǩ�շ�ʽ
    AUTO_ACKNOWLEDGE    �Զ�ǩ��
    CLIENT_ACKNOWLEDGE  �ͻ���ǩ��
    DUPS_DK_ACKNOWLEDGE ָsession����ȷ����Ϣǩ�� ���ܻ�������Ϣ�����ظ� ���ǽ�����session�Ŀ��� ����ͻ��˿������� ����Կ���
