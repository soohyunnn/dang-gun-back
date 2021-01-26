package dang.gun.com.jwt;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class BeerRequestFilter implements Filter {
    private FilterConfig fc;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("init BeerRequestFilter");
        this.fc = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String name = httpServletRequest.getRemoteUser();
        log.info("doFilter BeerRequestFilter, uri : {}", ((HttpServletRequest)request).getRequestURI());
        if(name != null) {
            fc.getServletContext().log("User" + name + "is updating");
        }
        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {
        log.info("destroy BeerRequestFilter");
    }
}
