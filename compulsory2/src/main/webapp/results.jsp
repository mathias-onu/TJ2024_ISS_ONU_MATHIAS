<%@ page import="java.util.List" %>
<%@ page import="java.util.Collections" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="upload">Hello Servlet</a>

  <h2>Shuffled Lines of the Uploaded File</h2>

  <%
    // Retrieve the lines from session
    List<String> fileLines = (List<String>) session.getAttribute("fileLines");
    if (fileLines != null && !fileLines.isEmpty()) {
      // Shuffle the lines
      Collections.shuffle(fileLines);
    }
  %>

  <% for (String line : fileLines) { %>
    <p><%= line %></p>
  <% } %>
</body>
</html>
