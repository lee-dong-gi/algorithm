package com.practice.algorithm.dto

class Graph(val vertices: Int) {
    val adjacencyList: Array<MutableList<Edge>> = Array(vertices) { mutableListOf() }

    fun addEdge(source: Int, destination: Int, weight: Int) {
        adjacencyList[source].add(Edge(destination, weight))
    }

    companion object {
        // 그래프를 초기화하고, 기본 엣지를 추가합니다.
        fun createSampleGraph(vertices: Int): Graph {
            val graph = Graph(vertices)

            // 예시 엣지 추가 (그래프의 예시)
            // 예를 들어, 무방향 그래프의 엣지 추가
            graph.addEdge(0, 1, 10)
            graph.addEdge(0, 2, 3)
            graph.addEdge(1, 2, 1)
            graph.addEdge(1, 3, 2)
            graph.addEdge(2, 1, 4)
            graph.addEdge(2, 3, 8)
            graph.addEdge(2, 4, 2)
            graph.addEdge(3, 4, 7)
            graph.addEdge(4, 3, 9)

            return graph
        }
    }
}