package com.practice.algorithm

import com.practice.algorithm.dto.AlgorithmParam
import com.practice.algorithm.dto.Graph
import com.practice.algorithm.enum.AlgorithmType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class AlgorithmServiceTest {

    @Test
    fun fibonacciTest() {
        val fibonacci = AlgorithmFactory.getAlgorithm(AlgorithmType.FIBONACCI)
        val param = AlgorithmParam()

        param.apply { this.fibonacci = 0 }
        assertEquals(0, fibonacci.execute(param).fibonacciResult)

        param.apply { this.fibonacci = 1 }
        assertEquals(1, fibonacci.execute(param).fibonacciResult)

        param.apply { this.fibonacci = 3 }
        assertEquals(2, fibonacci.execute(param).fibonacciResult)
    }

    @Test
    fun binarySearchTest() {
        val binarySearch = AlgorithmFactory.getAlgorithm(AlgorithmType.BINARY_SEARCH)
        val param = AlgorithmParam()

        param.apply { this.binarySearch = 30 }
        assertEquals(30, binarySearch.execute(param).binarySearchResult?.order)

        param.apply { this.binarySearch = 101 }
        assertEquals(-1, binarySearch.execute(param).binarySearchResult?.order)
    }

    @Test
    fun calculateMinimumCostToAllPointsWithDijkstraTest() {
        val dijkstra = AlgorithmFactory.getAlgorithm(AlgorithmType.DIJKSTRA)
        val param = AlgorithmParam()
        param.apply { this.startNode = 0 }

        val distances = dijkstra.execute(param).dijkstraMinimumCostToAllPointsResult
        println("Vertex\tDistance from Source")
        for (i in distances?.indices!!) {
            println("$i\t\t${distances[i]}")
        }
    }

    @Test
    fun calculatePathWithDijkstraTest() {
        val dijkstra = AlgorithmFactory.getAlgorithm(AlgorithmType.DIJKSTRA)
        val param = AlgorithmParam()
        param.apply {
            this.startNode = 0
            this.endNode = 3
        }
        val path = dijkstra.execute(param).dijkstraPathResult
        println("Shortest path from $param.startNode to $param.endNode is: $path")
    }

    @Test
    fun quickSortTest() {
        val array = intArrayOf(3, 6, 8, 10, 1, 2, 1)
        val param = AlgorithmParam()
        param.sortArray = array
        println("정렬 전: ${array.joinToString(", ")}")

        // 퀵 정렬 사용
        val quickSort = AlgorithmFactory.getAlgorithm(AlgorithmType.QUICK_SORT)
        val result = quickSort.execute(param)
        println("퀵 정렬 후: ${result.sortedArray?.joinToString(", ")}")
    }
}