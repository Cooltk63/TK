this is my federation.property file

Pfederation.trustedissuers.issuer=https://sso.sbi.co.in/adfs/ls/
Pfederation.trustedissuers.thumbprint=88d747ea9bc03f8baa88baefc498c9903deba37f
Pfederation.trustedissuers.friendlyname=SBI.SSO
Pfederation.audienceuris=https://sso.sbi.co.in/adfs/Services/Trust|https://bs.info.sbi/BS/
Pfederation.realm=https://bs.info.sbi/BS/
Pfederation.enableManualRedirect=false
Pfederation.reply=https://bs.info.sbi/BS/index.jsp

This is cod where I am calling from this controller api where FE calling here before login
@RequestMapping("/SSO")
public class SsoController {

    @PostMapping("/sso-user")
    public Map<String, Object> getSsoUser(HttpServletRequest request) {

        Map<String, Object> response = new HashMap<>();

        boolean flag = (boolean) request.getAttribute("Prod");
        if(flag)
        {
            log.info("SSO User Env is Prod");
            String PFID = "";
            request.getAttribute("PFID");
            List<Claim> claimsSSO = ((FederatedPrincipal) request.getUserPrincipal()).getClaims();

            System.out.println(claimsSSO);
            PFID = claimsSSO.get(Constant.PFId).getClaimValue();
            PFID = PFID.substring(0, PFID.indexOf('@'));
            response.put("Status",true);
            response.put("PFID", PFID);
            log.info("Response :{}", response);
        }
        else
        {
            response.put("Status",false);
        }
        return response;

    }


    I wanted my sso claim reply on this controller not on index.jsp or any Frontemd page or react Just need the response here using Java-8 
