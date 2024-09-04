package com.practice.algorithm

import com.practice.algorithm.enum.AlgorithmType
import com.practice.algorithm.logic.BinarySearch
import com.practice.algorithm.logic.Dijkstra
import com.practice.algorithm.logic.Fibonacci
import com.practice.algorithm.logic.QuickSort

class AlgorithmFactory {
    companion object {
        fun getAlgorithm(type: AlgorithmType): Algorithm {
            return when (type) {
                AlgorithmType.BINARY_SEARCH -> BinarySearch()
                AlgorithmType.DIJKSTRA -> Dijkstra()
                AlgorithmType.FIBONACCI -> Fibonacci()
                AlgorithmType.QUICK_SORT -> QuickSort()
            }
        }
    }
}