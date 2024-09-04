package com.practice.algorithm

import com.practice.algorithm.dto.Graph
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class AlgorithmServiceTest {

    @Autowired
    lateinit var algorithmService: AlgorithmService

    @Test
    fun fibonacciTest() {
        assertEquals(0, algorithmService.fibonacci(0))
        assertEquals(1, algorithmService.fibonacci(1))
        assertEquals(2, algorithmService.fibonacci(3))
    }

    @Test
    fun binarySearchTest() {
        assertEquals(30, algorithmService.binarySearch(30).order)
        assertEquals(-1, algorithmService.binarySearch(101).order)
    }

    @Test
    fun calculateMinimumCostToAllPointsWithDijkstraTest() {
        val graph = Graph.createSampleGraph(5) // 노드의 개수(0 ~ 4)
        val startNode = 0
        val distances = algorithmService.calculateMinimumCostToAllPointsWithDijkstra(graph, startNode)
        println("Vertex\tDistance from Source")
        for (i in distances.indices) {
            println("$i\t\t${distances[i]}")
        }
    }

    @Test
    fun calculatePathWithDijkstraTest() {
        val graph = Graph.createSampleGraph(5) // 노드의 개수(0 ~ 4)
        val startNode = 0
        val endNode = 3
        val path = algorithmService.calculatePathWithDijkstra(graph, startNode, endNode)
        println("Shortest path from $startNode to $endNode is: $path")
    }
}