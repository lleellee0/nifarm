package com.my.sketchbook;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@ServerEndpoint("/echo")
public class EchoHandler extends TextWebSocketHandler{
	private Logger logger = LoggerFactory.getLogger(EchoHandler.class);
	 
    /**
     * ������ ������ ����ڵ��� �����ϴ� ����Ʈ
     */
    private List<WebSocketSession> connectedUsers;
 
    public EchoHandler() {
        connectedUsers = new ArrayList<WebSocketSession>();
    }
 
    /**
     * ���Ӱ� ���õ� Event Method
     * 
     * @param WebSocketSession
     *            ������ �����
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        connectedUsers.add(session);
 
        logger.info(session.getId() + "���� �����߽��ϴ�.");
        logger.info("���� IP : " + session.getRemoteAddress().getHostName());
    }
 
    /**
     * �� ���� �̺�Ʈ�� ó��
     * 
     * 1. Send : Ŭ���̾�Ʈ�� �������� �޽����� ����
     * 2. Emit : ������ ����Ǿ� �ִ� Ŭ���̾�Ʈ���� �޽����� ����
     * 
     * @param WebSocketSession
     *            �޽����� ���� Ŭ���̾�Ʈ
     * @param TextMessage
     *            �޽����� ����
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
 
        MessageBean messageVO = MessageBean.convertMessage(message.getPayload());
        String hostName = "";
 
        for (WebSocketSession webSocketSession : connectedUsers) {
            if (messageVO.getType().equals("all")) {
                if (!session.getId().equals(webSocketSession.getId())) {
                    webSocketSession.sendMessage(
                            new TextMessage(session.getRemoteAddress().getHostName() + " �� " + messageVO.getMessage()));
                }
            } else {
                hostName = webSocketSession.getRemoteAddress().getHostName();
                if (messageVO.getTo().equals(hostName)) {
                    webSocketSession.sendMessage(
                            new TextMessage(
                                    "<span style='color:red; font-weight: bold;' >"
                                    + session.getRemoteAddress().getHostName() + "�� " + messageVO.getMessage()
                                    + "</span>") );
                    break;
                }
            }
        }
 
        /*
         * Payload : ����ڰ� ���� �޽���
         */
        logger.info(session.getId() + "���� �޽��� : " + message.getPayload());
    }
 
    /**
     * Ŭ���̾�Ʈ�� ������ ������ �������� ����Ǵ� �޼ҵ�
     * 
     * @param WebSocketSession
     *            ������ ���� Ŭ���̾�Ʈ
     * @param CloseStatus
     *            ���� ����(Ȯ�� �ʿ���)
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
 
        connectedUsers.remove(session);
 
        for (WebSocketSession webSocketSession : connectedUsers) {
            /*
             * �ڽ��� ���� �޽����� ���� �ʴ´�.
             */
            if (!session.getId().equals(webSocketSession.getId())) {
                webSocketSession.sendMessage(new TextMessage(session.getRemoteAddress().getHostName() + "�����߽��ϴ�."));
            }
        }
 
        logger.info(session.getId() + "���� �����߽��ϴ�.");
    }

}