@RestController
@RequestMapping("/api")
public class SsoController {

    @GetMapping("/sso-user")
    public ResponseEntity<Map<String, String>> getSsoUser(HttpServletRequest request) {
        Object isProd = request.getAttribute("Prod");
        Object userId = request.getAttribute("SSO_USER"); // Or however you're storing it

        Map<String, String> response = new HashMap<>();
        response.put("env", isProd != null ? isProd.toString() : "false");
        response.put("username", userId != null ? userId.toString() : "");

        return ResponseEntity.ok(response);
    }
}