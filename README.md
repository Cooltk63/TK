 public static String getMachineHostAddress() {

        String server = null;
        try {
            InetAddress inetAddr = InetAddress.getLocalHost();
            server = inetAddr.getHostAddress();
        } catch (Exception e) {
            //log.info(e);
        }

        return server;
    }

    on this function line   InetAddress inetAddr = InetAddress.getLocalHost(); I had the security issue 'Often Misused: Authentication'  Likely impact :The information returned by the call to getLocalHost() is not trustworthy. Attackers may spoof DNS entries. Do not rely on DNS for security.
    Recommandation You can increase confidence in a domain name lookup if you check to make sure that the host's forward and backward DNS entries match. Attackers will not be able to spoof both the forward and the reverse DNS entries without controlling the nameservers for the target domain. This is not a foolproof approach however: attackers may be able to convince the domain registrar to turn over the domain to a malicious nameserver. Basing authentication on DNS entries is simply a risky proposition. While no authentication mechanism is foolproof, there are better alternatives than host-based authentication. Password systems offer decent security, but are susceptible to bad password choices, insecure password transmission, and bad password management. A cryptographic scheme like SSL is worth considering, but such schemes are often so complex that they bring with them the risk of significant implementation errors, and key material can always be stolen. In many situations, multi-factor authentication including a physical token offers the most security available at a reasonable price.

Line where this above function get called 
String userServer = CommonFunction.getMachineHostAddress();

    Help me to resolve this security issue using great way and understandable
