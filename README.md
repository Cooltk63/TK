@RestController
@RequestMapping("/service")
public class ProductServiceController {

    @Value("${fincore.service.url}")
    private String fincoreServiceUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    @PostMapping("/getProductOne")
    public String getProductOne(@RequestBody String payload) {
        return "Received getProductOne payload: " + payload;
    }

    @PostMapping("/getProductTwo")
    public List<Map<String, Object>> getProductTwo(@RequestBody List<String> data) {
        System.out.println("Received IDs: " + data);
        List<Map<String, Object>> result = new ArrayList<>();

        for (String id : data) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", id);
            map.put("value", "Product-" + id);
            result.add(map);
        }

        return result;
    }

    @PostMapping("/callFincoreSimple")
    public String callFincoreSimple() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>("Hello from Product Service", headers);

        return restTemplate.postForObject(fincoreServiceUrl + "/fincore/getFincoreService", request, String.class);
    }

    @PostMapping("/callFincoreWithList")
    public String callFincoreWithList() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        List<String> data = List.of("1", "2", "3");

        HttpEntity<List<String>> request = new HttpEntity<>(data, headers);

        List<Map<String, Object>> response = restTemplate.postForObject(
                fincoreServiceUrl + "/fincore/getMapData",
                request,
                List.class
        );

        return response.toString();
    }
}

xxxx

fin 



@RestController
@RequestMapping("/fincore")
public class FincoreServiceController {

    @Value("${product.service.url}")
    private String productServiceUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    @PostMapping("/callProductOne")
    public String callProductOne() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>("Hello from Fincore", headers);

        return restTemplate.postForObject(productServiceUrl + "/service/getProductOne", request, String.class);
    }

    @PostMapping("/getMapData")
    public List<Map<String, Object>> callProductTwo() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        List<String> listData = List.of("1", "2", "3");

        HttpEntity<List<String>> request = new HttpEntity<>(listData, headers);

        return restTemplate.postForObject(productServiceUrl + "/service/getProductTwo", request, List.class);
    }

    @PostMapping("/getFincoreService")
    public String getFincoreService(@RequestBody String payload) {
        return "Fincore received: " + payload;
    }
}