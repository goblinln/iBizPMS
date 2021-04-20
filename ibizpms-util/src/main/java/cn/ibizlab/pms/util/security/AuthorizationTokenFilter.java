package cn.ibizlab.pms.util.security;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.util.service.AuthenticationUserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import org.springframework.util.StringUtils;

@Slf4j
@Component
public class AuthorizationTokenFilter extends OncePerRequestFilter {

    private final AuthenticationUserService userDetailsService;
    private final AuthTokenUtil authTokenUtil;
    private final String tokenHeader;
    private Set<String> excludesPattern = new HashSet<String>();
    private PathMatcher pathMatcher = new AntPathMatcher();

    public AuthorizationTokenFilter(AuthenticationUserService userDetailsService, AuthTokenUtil authTokenUtil, @Value("${ibiz.jwt.header:Authorization}") String tokenHeader) {
        this.userDetailsService = userDetailsService;
        this.authTokenUtil = authTokenUtil;
        this.tokenHeader = tokenHeader;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        if (isExclusion(request.getRequestURI())) {
            chain.doFilter(request, response);
            return;
        }
        
        final String requestHeader = request.getHeader(this.tokenHeader);

        String username = null;
        String authToken = null;
        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
            authToken = requestHeader.substring(7);
            try {
                username = authTokenUtil.getUsernameFromToken(authToken);
            } catch (ExpiredJwtException e) {
                log.error(e.getMessage());
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            final String requestOrgId = request.getHeader("srforgid");
            final String requestSystemId = request.getHeader("srfsystemid");
            if(StringUtils.isEmpty(requestOrgId)||StringUtils.isEmpty(requestSystemId)){
                throw new RuntimeException("系统标识或组织标识不能为空");
            }
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(requestSystemId,requestOrgId,username);
            if (authTokenUtil.validateToken(authToken, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                String strInstTag = request.getParameter("srfinsttag") ;
                String strInstTag2 = request.getParameter("srfinsttag2") ;
                AuthenticationUser curUser = (AuthenticationUser)userDetails ;
                curUser.setSrfdynainsttag(strInstTag);
                curUser.setSrfdynainsttag2(strInstTag2);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(request, response);
    }
    
    public void setExcludesPattern(String excludesPattern) {
        this.excludesPattern = new HashSet(Arrays.asList(excludesPattern.split("\\s*,\\s*")));
    }

    public void addExcludePattern(String excludePattern) {
        excludesPattern.add(excludePattern);
    }

    private boolean isExclusion(String requestURI) {
        if (this.excludesPattern == null) {
            return false;
        } else {
            Iterator excludeIterator = this.excludesPattern.iterator();
            String pattern;
            do {
                if (!excludeIterator.hasNext()) {
                    return false;
                }
                pattern = (String) excludeIterator.next();
            } while (!pathMatcher.match(pattern, requestURI));
            return true;
        }
    }
}
