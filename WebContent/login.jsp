<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%

String username = request.getParameter("username");
String password = request.getParameter("password");
String auto = request.getParameter("auto");
if("on".equals(auto)){
	out.println("点击率自动登录");
	Cookie cookie = new Cookie("autoLogin",username+"&"+password);
	cookie.setMaxAge(360000);
	cookie.setPath(" /login/index.jsp");
	response.addCookie(cookie);
}
out.println(request.getContextPath());

%>