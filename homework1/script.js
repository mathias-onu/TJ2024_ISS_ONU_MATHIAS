async function callEndpoint() {
    const response = await fetch("http://localhost:8080/homework1_war_exploded/matrix", {
        method: "POST",
        body: JSON.stringify({ numVertices: "5", numEdges: "3" })
    })

    console.log(response)
}

callEndpoint()