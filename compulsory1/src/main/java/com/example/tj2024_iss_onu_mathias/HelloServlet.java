package com.example.tj2024_iss_onu_mathias;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "charactersServlet", value = "/characters")
public class HelloServlet extends HttpServlet {
    public void init() { }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Get query parameter from request URL
        String inputString = request.getParameter("inputString");

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><head><title>String to List</title></head><body>");
        out.println("<h2>Characters in the string as an ordered list:</h2>");

        // Check if the input string is not null
        if (inputString != null && !inputString.isEmpty()) {
            out.println("<ol>");
            // Loop through each character of the string and add it to the ordered list
            for (char c : inputString.toCharArray()) {
                out.println("<li>" + c + "</li>");
            }
            out.println("</ol>");
        } else {
            out.println("<p>No input string provided!</p>");
        }

        out.println("</body></html>");
    }

    public void destroy() {
    }
}