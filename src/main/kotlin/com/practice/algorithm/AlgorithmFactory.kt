package com.practice.algorithm

import com.practice.algorithm.enum.AlgorithmType
import com.practice.algorithm.logic.*

class AlgorithmFactory {
    companion object {
        fun getAlgorithm(type: AlgorithmType): Algorithm {
            return when (type) {
                AlgorithmType.BINARY_SEARCH -> BinarySearch()
                AlgorithmType.DIJKSTRA -> Dijkstra()
                AlgorithmType.FIBONACCI -> Fibonacci()
                AlgorithmType.QUICK_SORT -> QuickSort()
                AlgorithmType.BREADTH_FIRST_SEARCH -> BreadthFirstSearch()
                AlgorithmType.DEPTH_FIRST_SEARCH -> DepthFirstSearch()
            }
        }
    }
}