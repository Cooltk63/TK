 @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;

        response.setHeader("Access-Control-Allow-Origin", "*"); // or specific domain
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(req, res);
        }
    }

    For above code 

    Vulnerability Description in Detail : On line 20 of CORSFilter.java the program defines an overly permissive Cross-Origin Resource Sharing (CORS) policy..

    Likely Impact :On line 20 of CORSFilter.java the program defines an overly permissive Cross-Origin Resource Sharing (CORS) policy..
    Recommendation :Do not use the * as the value of the Access-Control-Allow-Origin header. Instead, provide an explicit list of trusted domains.

    I had the 3 domains Mainly Dev UAT & PROD Also local

    Dev: csdev.info.sni
    UAT:csuat.info.sni
    Prod:.cs.info.sbi

    what abot the local then 
    
