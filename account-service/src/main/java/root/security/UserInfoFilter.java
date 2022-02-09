package root.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.GenericFilterBean;
import root.model.dto.UserInfo;

@Slf4j
public class UserInfoFilter extends GenericFilterBean {
    public static UserInfo userInfo;

    private final ObjectMapper objectMapper;

    public UserInfoFilter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String userInfoStr = request.getHeader("user-info");
        try{
            userInfo = objectMapper.readValue(userInfoStr, UserInfo.class);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }


        int k = 1;

        filterChain.doFilter(servletRequest, servletResponse);
    }

}
