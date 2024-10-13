<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Random Adjacency Matrix</title>
</head>
<body>
<h1><%= "Generate a random adjacency matrix" %>
</h1>
<br/>
<form action="matrix" method="post">
  <p>
    <label for="numVertices">Number of vertices:</label>
    <input type="number" min="1" id="numVertices" name="numVertices" />
  </p>
  <p>
    <label for="numEdges">Number of edges:</label>
    <input type="number" min="0" id="numEdges" name="numEdges" />
  </p>

  <button type="submit">Send the numbers</button>
</form>
</body>
</html>