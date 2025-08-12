package com.example.loginservice.filter;

import com.example.loginservice.entity.RefreshToken;
import com.example.loginservice.repository.RefreshTokenRepository;
import com.example.loginservice.service.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Slf4j
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {



        String authHeader = request.getHeader("Authorization");
        String token = null;
        String refreshToken = null;
        Cookie[] cookies = request.getCookies();
        log.info("In jwt filter");
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("refreshToken")) {
                    refreshToken = cookie.getValue();
                    break;
                }
            }
        }

        if(authHeader != null && authHeader.startsWith("Bearer ")){
            token = authHeader.substring(7);

            try{
                int userId = jwtService.extractUserId(token);
                if( SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = userDetailsService.loadUserByUserId(userId);

                    RefreshToken previousRefreshToken = refreshTokenRepository.getLatestRefreshTokensByUserid(userId);

                    if(refreshToken != null && previousRefreshToken != null) {

                        if(refreshToken.equals(previousRefreshToken.getRefreshToken())  ){
                            if(jwtService.validateAccessToken(token,Integer.parseInt(userDetails.getUsername())))
                            {
                                log.info("Sending Token");
                                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                                authentication.setDetails(new WebAuthenticationDetails(request));
                                SecurityContextHolder.getContext().setAuthentication(authentication);
                                request.setAttribute("token", token);
                                filterChain.doFilter(request, response);
                            }
                            else{
                                log.info("Access Token Expired");
                                sendError(response,HttpServletResponse.SC_UNAUTHORIZED,"TOKEN_EXPIRED");
                            }

                        }
                        else{
                            sendError(response, 440, "INVALID_LOGIN_SESSION");
                            log.info("Invalid Login Session");

                        }

                    }


                }
            }
            catch(Exception e){
                sendError(response, HttpServletResponse.SC_UNAUTHORIZED, "INVALID_TOKEN");

                log.info("Invalid Token");
            }

        }
        else{

            if(authHeader!=null){
                sendError(response,HttpServletResponse.SC_UNAUTHORIZED,"NO_ACCESS_TOKEN");
                log.info("No Access Token");
                return;
            }
        }





        filterChain.doFilter(request, response);


    }


    private void sendError(HttpServletResponse response,int status, String errorCode) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");
        response.getWriter().write("{\"message\":\"" + errorCode + "\",\"error\":\"" + errorCode + "\"}");
        response.getWriter().close();
    }
}
