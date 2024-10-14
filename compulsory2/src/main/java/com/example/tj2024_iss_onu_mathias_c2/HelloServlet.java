package com.example.tj2024_iss_onu_mathias_c2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/upload")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class HelloServlet extends HttpServlet {
    public void init() { }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Retrieve the file part
        Part filePart = request.getPart("file");

        // Initialize a list to store file lines
        List<String> lines = new ArrayList<>();

        // Read the file line by line and add to the list
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(filePart.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }

        // Store the list of lines in session
        HttpSession session = request.getSession();
        session.setAttribute("fileLines", lines);

        // Redirect to the result.jsp page
        response.sendRedirect("results.jsp");
    }


    public void destroy() {
    }
}