package com.example.homework1;

import java.io.*;
import java.util.Random;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/matrix")
public class HelloServlet extends HttpServlet {
    public void init() {}

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static int[][] generateAdjacencyMatrix(int vertices, int edges) {
        int[][] adjacencyMatrix = new int[vertices][vertices];
        Random random = new Random();

        int edgeCount = 0;
        int vertex1 = 0;
        int vertex2 = 0;

        while (edgeCount < edges) {
            vertex1 = random.nextInt(vertices);
            vertex2 = random.nextInt(vertices);

            while (vertex1 == vertex2){
                vertex2 = random.nextInt(vertices);
            }

            if (vertex1 != vertex2 && adjacencyMatrix[vertex1][vertex2] == 0) {
                adjacencyMatrix[vertex1][vertex2] = 1;
                adjacencyMatrix[vertex2][vertex1] = 1;
                edgeCount++;
            }
        }

        return adjacencyMatrix;
    }

    public static String arrayToHTMLTable(int[][] matrix) {
        String table = "<table>\n";
        for (int[] row : matrix) {
            table += "<tr>\n";
            for (int value : row) {
                table += "<td>" + value + "</td>\n";
            }
            table +="</tr>\n";
        }
        table += "</table>";
        return table;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        System.out.println(request.getMethod() + " " + request.getRemoteAddr() + " " + request.getHeader("User-Agent")
                + " " + request.getHeader("Accept-Language") + " vertices:" + request.getParameter("numVertices")
                + " edges:" + request.getParameter("numEdges"));

        String table = "<h1> vertices / edges are not numbers </h1>";
        String verticesString = request.getParameter("numVertices");
        String edgesString = request.getParameter("numEdges");

        if (isNumeric(verticesString) && isNumeric(edgesString)){
            int vertices = Integer.parseInt(verticesString);
            int edges = Integer.parseInt(edgesString);

            table = "<h1> Invalid number of vertices / edges </h1>";

            if (vertices * (vertices-1) / 2 >= edges){
                int [][] array = generateAdjacencyMatrix(vertices, edges);
                table = arrayToHTMLTable(array);
            }
        }

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        out.println("<html><body>");
        out.println("<h1> Number of vertices: " + request.getParameter("numVertices") + "</h1>");
        out.println("<h1> Number of edges: " + request.getParameter("numEdges") + "</h1>");
        out.println(table);
        out.println("</body></html>");
    }

    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Manually add CORS headers for preflight requests
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        // Allow the request to proceed
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void destroy() {
    }
}