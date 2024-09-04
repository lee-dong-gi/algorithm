package com.practice.algorithm.logic

import com.practice.algorithm.Algorithm
import com.practice.algorithm.dto.AlgorithmParam
import com.practice.algorithm.dto.AlgorithmResponse

class Fibonacci : Algorithm {
    override fun execute(algorithmParam: AlgorithmParam): AlgorithmResponse {
        val result = AlgorithmResponse()
        algorithmParam.fibonacci?.let { result.fibonacciResult = fibonacci(it) }
        return result
    }

    private fun fibonacci(n: Int): Int {
        return if (n <= 1) n else fibonacci(n - 1) + fibonacci(n - 2)
    }
}