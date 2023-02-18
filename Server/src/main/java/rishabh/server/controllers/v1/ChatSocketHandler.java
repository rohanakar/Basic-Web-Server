package rishabh.server.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rishabh.server.models.Message;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value="/v1/chat/{userName}")
public class ChatSocketHandler {
    
    Session session;
    static Set<ChatSocketHandler> chatEndpoints
            = new CopyOnWriteArraySet<>();
    static HashMap<String, String> users = new HashMap<>();
    
    static ObjectMapper objectMapper = new ObjectMapper();
    
    Logger logger = LoggerFactory.getLogger(ChatSocketHandler.class);
    
    @OnOpen
    public void onOpen(Session session, @PathParam("userName") String username) throws IOException, EncodeException {
        
        logger.info("*********  session Opened sending connected message ***********");
        this.session = session;
        users.put(session.getId(),username);
        chatEndpoints.add(this);
        
        Message message = new Message();
        message.setFrom(username);
        message.setContent("Connected!");
        broadcast(message);
    }

    @OnMessage
    public void onMessage(Session session, String msg) throws IOException, EncodeException {
        // Handle new messages

        logger.info("*********  Sending message {} ***********",msg);
        Message message =  objectMapper.readValue(msg,Message.class);
        message.setFrom(users.get(session.getId()));
        broadcast(message);
    }

    @OnClose
    public void onClose(Session session) throws IOException, EncodeException {
        // WebSocket connection closes

        chatEndpoints.remove(this);
        Message message = new Message();
        message.setFrom(users.get(session.getId()));
        message.setContent("Disconnected!");
        broadcast(message);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
    }

    private static void broadcast(Message message)
            throws IOException, EncodeException {

        chatEndpoints.forEach(endpoint -> {
            synchronized (endpoint) {
                    try {
                        endpoint.session.getBasicRemote().
                                sendText(objectMapper.writeValueAsString(message));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                
            }
        });
    }
}
