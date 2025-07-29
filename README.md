// From order-service to product-service

@RestController
public class OrderController {

    @GetMapping("/order-details")
    public String getOrderDetails() {
        // Call product-service using Kubernetes DNS name
        String response = new RestTemplate().getForObject("http://product-service/products", String.class);
        return "Order includes: " + response;
    }
}