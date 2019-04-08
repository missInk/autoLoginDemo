package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns="/index.jsp")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    /**
     * ���������Ϣ����
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
    	System.out.println("ִ����doFile����");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        Object object = req.getSession().getAttribute("user");
        // �����session�л�ȡuserΪ�գ����ǲ���Ҫ��ȡcookie���е�½�Լ���ŵ�session��,����ֱ�ӷ���
        if (object == null) {
            // ��ȡ����Я����cookies
            Cookie[] cookies = req.getCookies();
            Cookie cookie = null;
            // ��Ҫ�жϻ�ȡ��cookies�Ƿ���ڣ���Ȼ������ʱ��ᱨ��ָ���쳣
            if (cookies != null && cookies.length > 0) {
                // ����cookies
                for (Cookie c : cookies) {
                    String name = c.getName();
                    if ("autoLogin".equals(name)) {
                        // ˵��cookie�д����Զ���¼��Ϣ
                        cookie = c;
                        break;
                    }
                }
            }
            // ˵�������Զ���½��cookie
            if (cookie != null) {
                // ������Ҫ��cookie��ֵ��ȡ������ŵ�session��
                String[] split = cookie.getValue().split("&");
                System.out.println(split[0]);
                System.out.println(split[1]);
            }
        }

        // ���������ֱ�� ����
        chain.doFilter(req, resp);
        // ������Ӧ
    }
    @Override
    public void destroy() {
    }

}

