package rishabh.server.filter;

import org.apache.catalina.connector.RequestFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rishabh.server.service.VaultService;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.List;


@Component
public class BasicAuthFilter implements Filter {
    
    static List<String> authEndpoints = List.of("/users/[0-9]+");
    
    Logger logger = LoggerFactory.getLogger(BasicAuthFilter.class);
    
    @Autowired
    private VaultService vaultService;
    
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String endpoint = ((RequestFacade)servletRequest).getRequestURI();
        for(String ep:authEndpoints){
            if(endpoint.matches(ep)){
                if(!vaultService.isAuthenticated(((RequestFacade) servletRequest).getHeader("Authorization"))) {
                    // throw error;
                    HttpServletRequest req = new HttpServletRequestWrapper((HttpServletRequest) servletRequest) {
                        @Override
                        public String getRequestURI() {
                            return "/error";
                        }
                    };
                    filterChain.doFilter(req,servletResponse);
                    return;
                }
                filterChain.doFilter(servletRequest,servletResponse);
                return;
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

}
