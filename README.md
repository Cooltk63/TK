package com.crs.SsoLoginService.auth10.federation;

import jakarta.servlet.annotation.WebFilter;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.regex.Pattern;



@WebFilter(urlPatterns = {"/SsoService/checkSSO/SSO/*"})
public class WSFederationFilter implements Filter {

	private static final String PRINCIPAL_SESSION_VARIABLE = "FederatedPrincipal";

	private String loginPage;
	private String excludedUrlsRegex;
	private String excludedUrlsRegex_JS;
	private String excludedUrlsRegex_CSS;
	
	//final Logger logger = LoggerFactory.getLogger(WSFederationFilter.class.getName());
	//static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(WSFederationFilter.class.getName());
	static java.util.logging.Logger log = Logger.getLogger(WSFederationFilter.class.getName());

	public WSFederationFilter() {
		log.info("constructing");
	}


	@Override
	public void init(FilterConfig config) throws ServletException {

		System.out.println(" WSFederationFilter API call to: " );
		//logger.debug("initializing");
		log.info("Inside WSFederationFilter Filtering");
		this.loginPage = config.getInitParameter("login-page-url");
		this.excludedUrlsRegex = "/resources/|/views/";//config.getInitParameter("exclude-urls-regex");
		this.excludedUrlsRegex_JS = config.getInitParameter("exclude-urls-regex-js");
		this.excludedUrlsRegex_CSS = config.getInitParameter("exclude-urls-regex-css");
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		log.info("WSFederationFilter filtering");
		System.out.println(" WSFederationFilter API call to: " );
		FederatedPrincipal principal = null;
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String path =  httpRequest.getRequestURL().toString();

		boolean checkSession = sessionTokenExists(httpRequest);
		log.info("checkSession <><><> " + checkSession);

			// is the request is a token?
			if(checkSession == false){ // Condition Added By Deepak
				log.info("WSFederationFilter doFilter httpRequest >>>>>>> " + httpRequest.getRequestURI());
				if (this.isSignInResponse(httpRequest)) {
					log.info("authenticating with token");
					log.info("WSFederationFilter doFilter principal >>>>>>> " + principal);
					log.info("WSFederationFilter doFilter httpResponse >>>>>>> " + httpResponse);
					principal = this.authenticateWithToken(httpRequest, httpResponse);
					log.info("WSFederationFilter doFilter principal >>>>>>> " + principal);
					this.writeSessionToken(httpRequest, principal);
					this.redirectToOriginalUrl(httpRequest, httpResponse);
				}
			}
		log.info("authenticating with token");
			// is principal in session?
			if (principal == null && this.sessionTokenExists(httpRequest)) {
				log.info("authenticating with session token");
				principal = this.authenticateWithSessionToken(httpRequest,
						httpResponse);
			}
		log.info("authenticating with token " + principal);
			// if not authenticated at this point, redirect to login page
			//logger.info("httpRequest.getRequestURL().toString() <><> " + httpRequest.getRequestURL().toString());
			//logger.info("Matcher <><><> " + Pattern.compile(this.excludedUrlsRegex).matcher(httpRequest.getRequestURL().toString()).find());
			
			boolean excludedUrl =false;/* httpRequest.getRequestURL().toString().contains(this.loginPage)
								  || (this.excludedUrlsRegex != null
							      && !this.excludedUrlsRegex.isEmpty() 
							      && Pattern.compile(this.excludedUrlsRegex).matcher(httpRequest.getRequestURL().toString()).find()); */
//							      || (this.excludedUrlsRegex != null
//									  && !this.excludedUrlsRegex.isEmpty() 
//									  && Pattern.compile(this.excludedUrlsRegex).matcher(httpRequest.getRequestURL().toString()).find());

			//logger.info("excludedUrl <><><> " + excludedUrl);
			if(excludedUrl){
				log.info("excludedUrl <><> " + excludedUrl);
			}
			
			if (!excludedUrl && principal == null) {
				log.info("INSIDE  Condition 1");
				if (!FederatedConfiguration.getInstance().getEnableManualRedirect()) {
					log.info("INSIDE  Condition 2");
					log.info("redirecting to identity provider");
					this.redirectToIdentityProvider(httpRequest, httpResponse);
					//log.info("senRedirect : "+httpResponse.getHeader("Location"));
					//httpResponse.sendRedirect(httpResponse.getHeader("Location"));
				} else {
					log.info("INSIDE  Condition 3");
					log.info("redirecting to login page");
					this.redirectToLoginPage(httpRequest, httpResponse);
				}

				return ;
			}
		log.info("principal=" + principal);
		log.info("excludedUrlsRegex <><> " + excludedUrlsRegex);
		log.info("path.toLowerCase() <><> " + path.toLowerCase());
			boolean pathVal =  path.toLowerCase().contains(excludedUrlsRegex);
		log.info("pathVal <><> " + pathVal);
				chain.doFilter(new FederatedHttpServletRequest(httpRequest, principal),response);
//			}
			
			
		
	}

	protected void redirectToLoginPage(HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) {
		String encodedReturnUrl = URLUTF8Encoder
				.encode(getRequestPathAndQuery(httpRequest));
		String redirect = this.loginPage + "?returnUrl=" + encodedReturnUrl;
		httpResponse.setHeader("Location", redirect);
		httpResponse.setStatus(302);
	}

	protected void redirectToIdentityProvider(HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) {
		String wctx = getRequestPathAndQuery(httpRequest);
		String redirect = FederatedLoginManager.getFederatedLoginUrl(wctx);
		httpResponse.setHeader("Location", redirect);
		httpResponse.setStatus(302);
	}

	protected void redirectToOriginalUrl(HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) {
		String wctx = httpRequest.getParameter("wctx");
		log.info("wctx >>>> ");
		if (wctx != null) {
			log.info("Content Type: " + httpRequest.getContentType());
			log.info("response Content Type: " + httpResponse.getContentType());
			httpResponse.setHeader("Location", wctx);
			httpResponse.setContentType("application/json");
			log.info("Content Type: " + httpRequest.getContentType());
			log.info("response Content Type: " + httpResponse.getContentType());
			//httpResponse.setStatus(302);
		}
	}

	protected Boolean isSignInResponse(HttpServletRequest request) {
	//logger.info("request.getMethod().equals(POST) <><><> " + request.getMethod().equals("POST"));
	//logger.info("request.getParameter(wa) <><><> " + request.getParameter("wa"));
	//logger.info("request.getParameter(wresult) <><><> " + request.getParameter("wresult"));
	
//		if(!request.getParameter("wa").equalsIgnoreCase("null")){
//			logger.info("HIISJHGFHJGFHJGFHJFSGJHGF");
//		}
		log.info("method  : "+request.getMethod());
		log.info("wa : "+request.getParameter("wa"));
		log.info("wresult : "+request.getParameter("wresult"));
		if (request.getMethod().equals("POST")
				&& request.getParameter("wa").equals("wsignin1.0")
				&& request.getParameter("wresult") != null) {
			return true;
		}

		return false;
	}

	protected Boolean sessionTokenExists(HttpServletRequest request) {
		// this could use signed cookies instead of sessions
		return request.getSession().getAttribute(PRINCIPAL_SESSION_VARIABLE) != null;
	}

	protected FederatedPrincipal authenticateWithSessionToken(
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		return (FederatedPrincipal) request.getSession().getAttribute(
				PRINCIPAL_SESSION_VARIABLE);
	}

	protected void writeSessionToken(HttpServletRequest request,
			FederatedPrincipal principal) throws IOException {
		request.getSession()
				.setAttribute(PRINCIPAL_SESSION_VARIABLE, principal);
	}

	protected FederatedPrincipal authenticateWithToken(
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String token = request.getParameter("wresult").toString();

		if (token == null) {
			log.info("token Value is null <><><><> " + token);
			sendError(request, response, 400,
					"You were supposed to send a wresult parameter with a token");
		}

		FederatedLoginManager loginManager = FederatedLoginManager.fromRequest(
				request, null);

		try {

			log.info("token Value <><><><> " + token);
			log.info("response Value <><><><> " + response);
			FederatedPrincipal principal = loginManager.authenticate(token,response);  //
			log.info("principal <<><><> " + principal);
			log.info("principal <<><><> " + principal);
			return principal;
		} catch (FederationException e) {
			e.printStackTrace();
			//logger.error(e.getMessage(), e);
			log.info("FederationException :" + e.getMessage());
			sendError(request, response, 500,
					"Oops an error occurred validating the token.");
		}

		return null;
	}

	private void sendError(HttpServletRequest request,
			HttpServletResponse response, int errorNum, String message)
			throws IOException {
		// create the session to avoid IllegalStateException: Cannot create
		// a session after the response has been committed.
		request.getSession();
		//logger.warn("response " + errorNum + ": " + message);
		response.sendError(errorNum, message);
	}

	@Override
	public void destroy() {
	}

	private static String getRequestPathAndQuery(HttpServletRequest req) {
		String reqUri = req.getRequestURI().toString();
		String queryString = req.getQueryString();
		if (queryString != null) {
			reqUri += "?" + queryString;
		}

		return reqUri;
	}
}
