<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.bookshop.dao.IUserDAO, com.bookshop.dao.impl.IUserDAOImpl"%>
<%@page import="com.bookshop.domain.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		int row;
		int id;
		String msg;

		IUserDAO dao = new IUserDAOImpl();
		id = dao.getId();
		User user = new User(id, username, password, email);
		row = dao.addUser(user);
		if (row == 0) {
			response.getWriter().write("Sorry");
			msg = "对不起，注册失败，请重新注册";
		} else {
			response.getWriter().write("OK");
			msg = "恭喜你，注册成功";
		}
		request.setAttribute("msg", msg);
	%>
	<%-- <jsp:forward page="../../error.jsp"> --%>
</body>
</html>