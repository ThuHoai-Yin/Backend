package com.example.demo.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.annotations.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import com.example.demo.service.UserService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	//** Provide JWT token */
    @Autowired
    private JwtTokenProvider tokenProvider;

    //** User Detail Service */
    @Autowired
    private UserService userService;
    
    /**
	 * Check JWT valid from request and find user from JWT
	 * 
	 * @param request, response,filterChain
	 */
    @Override
    protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException 
    {   
    	// catch JWT not valid
        try {
            String jwt = getJwtFromRequest(request);
            // Check JWT not empty and JWT valid this server, true: get username from JWT and have role of user
            if (!jwt.isEmpty() && tokenProvider.validateToken(jwt)) {
                String username = tokenProvider.getUsernameFromJWT(jwt);
                UserDetails userDetails =userService.loadUserByUsername(username);
                if(userDetails != null) {
                    UsernamePasswordAuthenticationToken
                            authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
                                                                                     userDetails
                                                                                             .getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
                
            }
        } catch (Exception ex) {
              
        }

        filterChain.doFilter(request, response);
        
    }

    /**
   	 * Get JWT from client-request
   	 * 
   	 * @param request, response,filterChain
   	 * @return String
   	 */
    private String getJwtFromRequest(HttpServletRequest request) {
    	
        String bearerToken = request.getHeader("Authorization");
        // check header Authorization have jwt token, true: return jwt token
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        
        return null;
        
    }
}
