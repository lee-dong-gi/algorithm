package com.practice.algorithm

import com.practice.algorithm.dto.AlgorithmParam
import com.practice.algorithm.dto.FirstSearchGraph
import com.practice.algorithm.enum.AlgorithmType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class AlgorithmServiceTest {

    @Test
    fun fibonacciTest() {
        val fibonacci = AlgorithmFactory.getAlgorithm(AlgorithmType.FIBONACCI)
        val param = AlgorithmParam()

        param.apply { this.fibonacci = 0 } // -
        assertEquals(0, fibonacci.execute(param).fibonacciResult)

        param.apply { this.fibonacci = 1 } // 1
        assertEquals(1, fibonacci.execute(param).fibonacciResult)

        param.apply { this.fibonacci = 3 } // 1 1 2
        assertEquals(2, fibonacci.execute(param).fibonacciResult)

        param.apply { this.fibonacci = 10 } // 1 1 2 3 5 8 13 21 34 55
        assertEquals(55, fibonacci.execute(param).fibonacciResult)
    }

    @Test
    fun binarySearchTest() {
        val binarySearch = AlgorithmFactory.getAlgorithm(AlgorithmType.BINARY_SEARCH)
        val param = AlgorithmParam()

        param.apply { this.binarySearch = 30 } // exist value
        assertEquals(30, binarySearch.execute(param).binarySearchResult?.order)

        param.apply { this.binarySearch = 101 } // not exist value
        assertEquals(-1, binarySearch.execute(param).binarySearchResult?.order)
    }

    @Test
    fun calculateMinimumCostToAllPointsWithDijkstraTest() {
        val dijkstra = AlgorithmFactory.getAlgorithm(AlgorithmType.DIJKSTRA)
        val param = AlgorithmParam()
        param.apply { this.startNode = 0 }

        val distances = dijkstra.execute(param).dijkstraMinimumCostToAllPointsResult
        println("Vertex\tDistance from Source")
        for (i in distances?.indices!!) { // 0부터 distances.size - 1까지의 범위를 나타내는 IntRange 객체를 받아 루프
            println("$i\t\t${distances[i]}")
        }

        val expected = intArrayOf(0, 7, 3, 9, 5)
        assertTrue(expected.contentEquals(distances))
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
        println("Shortest path from ${param.startNode} to ${param.endNode} is: $path")

        val expected = listOf(0, 2, 1, 3)
        assertEquals(expected, path)
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

        val expected = intArrayOf(1, 1, 2, 3, 6, 8, 10)
        assertTrue(expected.contentEquals(result.sortedArray))
    }

    @Test
    fun breadthFirstSearchTest() {
        val bfs = AlgorithmFactory.getAlgorithm(AlgorithmType.BREADTH_FIRST_SEARCH)
        val param = AlgorithmParam()
        param.firstSearchGraph = FirstSearchGraph.createSampleGraph(start = 1)

        val result = bfs.execute(param) // BFS 실행
        println(result.firstSearchResult)

        val expected = listOf(1, 2, 3, 4, 5, 6)
        assertEquals(expected, result.firstSearchResult)
    }

    @Test
    fun depthFirstSearchTest() {
        val dfs = AlgorithmFactory.getAlgorithm(AlgorithmType.DEPTH_FIRST_SEARCH)
        val param = AlgorithmParam()
        param.firstSearchGraph = FirstSearchGraph.createSampleGraph(start = 1)

        val result = dfs.execute(param) // DFS 실행
        println(result.firstSearchResult)

        val expected = listOf(1, 2, 4, 5, 3, 6)
        assertEquals(expected, result.firstSearchResult)
    }

    @Test
    fun bTreeTest () {
        val bTree = AlgorithmFactory.getAlgorithm(AlgorithmType.B_TREE_SEARCH)
        val param = AlgorithmParam()
        param.bTreeSearch = 17

        // 키 검색
        val resultNode = bTree.execute(param)
        if (resultNode.bTreeNode != null) {
            println("키 ${param.bTreeSearch}을 찾았습니다: ${resultNode.bTreeNode!!.keys}")
        } else {
            println("키 ${param.bTreeSearch}을 찾을 수 없습니다.")
        }
    }
}