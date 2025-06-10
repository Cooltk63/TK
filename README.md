package com.auth10.federation;

import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;
//import org.slf4j.LoggerFactory;

@Slf4j
public class WSFederationFilter implements Filter {

	private static final String PRINCIPAL_SESSION_VARIABLE = "FederatedPrincipal";

	private String loginPage;
	private String excludedUrlsRegex;
	private String excludedUrlsRegex_JS;
	private String excludedUrlsRegex_CSS;


	public WSFederationFilter() {
		System.out.println("constructing");
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		System.out.println("initializing");
		System.out.println("initializing");
		this.loginPage = config.getInitParameter("login-page-url");
		this.excludedUrlsRegex = config.getInitParameter("exclude-urls-regex");
		this.excludedUrlsRegex_JS = config.getInitParameter("exclude-urls-regex-js");
		this.excludedUrlsRegex_CSS = config.getInitParameter("exclude-urls-regex-css");
		// System.out.println("excludedUrlsRegex <><><> " + excludedUrlsRegex);

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		System.out.println("filtering");
		log.info("filtering :");
		FederatedPrincipal principal = null;
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String path = httpRequest.getRequestURL().toString();

		boolean checkSession = sessionTokenExists(httpRequest);
		
		//SSO only for Prod Env
		String env = FederatedConfiguration.getInstance().getPreIpString();
		httpRequest.setAttribute("Prod", true);
		/*if (!isProdEnv(env)) {
			httpRequest.setAttribute("Prod", false);
			chain.doFilter(httpRequest, response);
			return;
		}*/

		// is the request is a token?
		if (checkSession == false) { // Condition Added By Deepak
			if (this.isSignInResponse(httpRequest)) {
				System.out.println("authenticating with token");
				log.info("authenticating with token");
				principal = this.authenticateWithToken(httpRequest, httpResponse);
				this.writeSessionToken(httpRequest, principal);
				this.redirectToOriginalUrl(httpRequest, httpResponse);
			}
		}

		// is principal in session?
		if (principal == null && this.sessionTokenExists(httpRequest)) {
			System.out.println("authenticating with session token");
			principal = this.authenticateWithSessionToken(httpRequest, httpResponse);
		}

		// if not authenticated at this point, redirect to login page
		// System.out.println("httpRequest.getRequestURL().toString() <><> " + httpRequest.getRequestURL().toString());
		// System.out.println("Matcher <><><>  + Pattern.compile(this.excludedUrlsRegex).matcher(httpRequest.getRequestURL().toString()).find());

		boolean excludedUrl = httpRequest.getRequestURL().toString().contains(this.loginPage)
				|| (this.excludedUrlsRegex != null && !this.excludedUrlsRegex.isEmpty() && Pattern
						.compile(this.excludedUrlsRegex).matcher(httpRequest.getRequestURL().toString()).find());
		// || (this.excludedUrlsRegex != null
		// && !this.excludedUrlsRegex.isEmpty()
		// &&
		// Pattern.compile(this.excludedUrlsRegex).matcher(httpRequest.getRequestURL().toString()).find());

		// System.out.println("excludedUrl <><><> " + excludedUrl);
		if (excludedUrl) {
			// System.out.println("excludedUrl <><> " + excludedUrl);
		}

		if (!excludedUrl && principal == null) {
			// System.out.println("INSIDE  Condition 1");
			if (!FederatedConfiguration.getInstance().getEnableManualRedirect()) {
				// System.out.println("INSIDE  Condition 2");
				System.out.println("redirecting to identity provider");
				this.redirectToIdentityProvider(httpRequest, httpResponse);
			} else {
				// System.out.println("INSIDE  Condition 3");
				System.out.println("redirecting to login page");
				this.redirectToLoginPage(httpRequest, httpResponse);
			}

			return;
		}
		System.out.println("principal=" + principal);
		// System.out.println("excludedUrlsRegex <><> " + excludedUrlsRegex);
		// System.out.println("path.toLowerCase() <><> " + path.toLowerCase());
		boolean pathVal = path.toLowerCase().contains(excludedUrlsRegex);
		// System.out.println("pathVal <><> " + pathVal);
		chain.doFilter(new FederatedHttpServletRequest(httpRequest, principal), response);
		// }

	}

	private boolean isProdEnv(String env) {
		/*if (env.equalsIgnoreCase("P")) {
			return true;
		}
		return false;*/

		return true;
	}

	protected void redirectToLoginPage(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		String encodedReturnUrl = URLUTF8Encoder.encode(getRequestPathAndQuery(httpRequest));
		String redirect = this.loginPage + "?returnUrl=" + encodedReturnUrl;
		httpResponse.setHeader("Location", redirect);
		httpResponse.setStatus(302);
	}

	protected void redirectToIdentityProvider(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		String wctx = getRequestPathAndQuery(httpRequest);
		String redirect = FederatedLoginManager.getFederatedLoginUrl(wctx);
		httpResponse.setHeader("Location", redirect);
		httpResponse.setStatus(302);
	}

	protected void redirectToOriginalUrl(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		String wctx = httpRequest.getParameter("wctx");
		if (wctx != null) {
			httpResponse.setHeader("Location", wctx);
			httpResponse.setStatus(302);
		}
	}

	protected Boolean isSignInResponse(HttpServletRequest request) {
		// System.out.println("request.getMethod().equals(POST) <><><> " + request.getMethod().equals("POST"));
		// System.out.println("request.getParameter(wa) <><><> " + request.getParameter("wa"));
		// System.out.println("request.getParameter(wresult) <><><> " + request.getParameter("wresult"));

		// if(!request.getParameter("wa").equalsIgnoreCase("null")){
		// // System.out.println("HIISJHGFHJGFHJGFHJFSGJHGF");
		// }
		if (request.getMethod().equals("POST") && request.getParameter("wa").equals("wsignin1.0")
				&& request.getParameter("wresult") != null) {
			return true;
		}

		return false;
	}

	protected Boolean sessionTokenExists(HttpServletRequest request) {
		// this could use signed cookies instead of sessions
		return request.getSession().getAttribute(PRINCIPAL_SESSION_VARIABLE) != null;
	}

	protected FederatedPrincipal authenticateWithSessionToken(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		return (FederatedPrincipal) request.getSession().getAttribute(PRINCIPAL_SESSION_VARIABLE);
	}

	protected void writeSessionToken(HttpServletRequest request, FederatedPrincipal principal) throws IOException {
		request.getSession().setAttribute(PRINCIPAL_SESSION_VARIABLE, principal);
	}

	protected FederatedPrincipal authenticateWithToken(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String token = request.getParameter("wresult").toString();

		if (token == null) {
			sendError(request, response, 400, "You were supposed to send a wresult parameter with a token");
		}

		FederatedLoginManager loginManager = FederatedLoginManager.fromRequest(request, null);

		try {

			// System.out.println("token Value <><><><> " + token);
			FederatedPrincipal principal = loginManager.authenticate(token, response);
			// System.out.println("principal <<><><> " + principal);
			// System.out.println("principal <<><><> " + principal);
			return principal;
		} catch (FederationException e) {
			System.out.println("Exception Occurred " +e.getMessage());
			System.out.println(e.getMessage()+ e);
			sendError(request, response, 500, "Oops an error occurred validating the token.");
		}

		return null;
	}

	private void sendError(HttpServletRequest request, HttpServletResponse response, int errorNum, String message)
			throws IOException {
		// create the session to avoid IllegalStateException: Cannot create
		// a session after the response has been committed.
		request.getSession();
		System.out.println("response " + errorNum + ": " + message);
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
