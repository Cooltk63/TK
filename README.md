
package com.tcs.csrf;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.io.IOException;
import java.net.*;
import java.security.SecureRandom;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.String;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tcs.utils.CommonConstant;
import org.springframework.web.util.WebUtils;
import org.apache.log4j.Logger;
import com.google.common.net.InternetDomainName;
import java.net.URL;
import org.apache.commons.lang.RandomStringUtils;


import static org.bouncycastle.crypto.tls.ContentType.alert;


public class LoadSalt implements Filter {


    static Logger log = Logger.getLogger(LoadSalt.class.getName());
    private Pattern AllowedURL=Pattern.compile("/assets|/resources/|/index.jsp|/login.jsp|/Security");



    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {


        //log.info("for token validation....");
        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse  httpRes = (HttpServletResponse) response;


        String requestSalt=null;

        requestSalt=(String)httpReq.getHeader("X-CSRF-TOKEN");

        String sessionSalt = null;
        if(null!=httpReq.getSession().getAttribute("csrfPreventionSalt")) {

            sessionSalt = httpReq.getSession().getAttribute("csrfPreventionSalt").toString();

        }

        String hostname=null;

        hostname=(String)httpReq.getServerName();
        //log.info("Hostname"+hostname);

        String referer=null;

        referer=(String)httpReq.getHeader("Referer");
        //log.info("referer"+referer);


        String Forwaded=null ;
        Forwaded=(String)httpReq.getHeader("X-Forwarded-Host");
        if (Forwaded == null) {
            Forwaded = request.getRemoteAddr();
        }
        //log.info("X-Forwarded-For"+Forwaded);
        if(!(Forwaded.equalsIgnoreCase("bsuat.info.sbi")||Forwaded.equalsIgnoreCase("10.0.26.158")||Forwaded.equalsIgnoreCase("bs.info.sbi") || CommonConstant.isIpAddress(Forwaded))){
            //log.info("user not matched");
            throw new ServletException("Duplicate host detected!! Inform a scary sysadmin ASAP.");
        }

        String UrlPath=((HttpServletRequest) request).getServletPath();
        //log.info(UrlPath);

        // Check the user session for the salt cache, if none is present we create one

        Cache<String, Boolean> csrfPreventionSaltCache  = (Cache<String, Boolean>)
                httpReq.getSession().getAttribute("csrfPreventionSaltCache");



        if(null != requestSalt && null!= sessionSalt &&  requestSalt.equals(sessionSalt)){
            //log.info("CSRF token matched ");
        }
        else if(null != requestSalt && null!= sessionSalt &&  !requestSalt.equals(sessionSalt)){
            //log.info("CSRF does not match get out user");
            throw new ServletException("Potential CSRF detected!! Inform a scary sysadmin ASAP.");

        }

        if (csrfPreventionSaltCache == null){
            //log.info("csrfPreventionSaltCache++++");
            csrfPreventionSaltCache = CacheBuilder.newBuilder()
                    .maximumSize(5000)
                    .expireAfterWrite(5, TimeUnit.MINUTES)
                    .build();

            httpReq.getSession().setAttribute("csrfPreventionSaltCache", csrfPreventionSaltCache);
        }

        // Generate the salt and store it in the users cache
        String salt = RandomStringUtils.random(20, 0, 0, true, true, null, new SecureRandom());
        csrfPreventionSaltCache.put(salt, Boolean.TRUE);

        // Add the salt to the current request so it can be used
        // by the page rendered in this request
        httpRes.setHeader("csrfPreventionSalt",salt);

        httpReq.getSession().setAttribute("csrfPreventionSalt", salt);


        Matcher m = AllowedURL.matcher(UrlPath);

        if(m.find())
        {
            //log.info("URL_matchfind");
            chain.doFilter(request, response);
        } else if(null != requestSalt && null!= sessionSalt &&  requestSalt.equals(sessionSalt)){
            //log.info("CSRF token mathced ");
            chain.doFilter(request, response);
        } else if(null == sessionSalt || null != requestSalt && null!= sessionSalt &&  !requestSalt.equals(sessionSalt)){
            //log.info("CSRF does not match get out user");
            throw new ServletException("Potential CSRF detected!! Inform a scary sysadmin ASAP.");

        }else if(sessionSalt!=null&&requestSalt==null){
            //log.info("CSRF does not match get out user");
            throw new ServletException("Potential CSRF detected!! Inform a scary sysadmin ASAP.");

        }

        //  chain.doFilter(request, response);

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}


