import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.websocket.*;

@ClientEndpoint
public class WebSocketClient {

    private Session session;
    private static ExecutorService executorService = Executors.newSingleThreadExecutor();

    public WebSocketClient() {
        try {
            // WebSocket server URI
            URI serverEndpoint = new URI("ws://your-websocket-server");
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, serverEndpoint);
        } catch (URISyntaxException | DeploymentException | IOException e) {
            e.printStackTrace();
        }
    }

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        System.out.println("WebSocket connection opened");
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println("Message received: " + message);
    }

    @OnClose
    public void onClose() {
        System.out.println("WebSocket connection closed");
    }

    @OnError
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    public void sendMessage(String message) throws IOException {
        session.getBasicRemote().sendText(message);
    }

    public static void main(String[] args) {
        executorService.submit(() -> {
            WebSocketClient client = new WebSocketClient();
            // Keep WebSocket running
            while (true) {
                try {
                    Thread.sleep(1000);  // Simulate background work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        // Main application logic
        System.out.println("Application is terminating but WebSocket will continue...");
        // You can optionally terminate the executor if needed
        // executorService.shutdown();
    }
}