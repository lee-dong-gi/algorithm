package com.practice.algorithm

import org.springframework.stereotype.Service

@Service
class AlgorithmService {
    fun fibonacci(n: Int): Int {
        return if (n <= 1) n else fibonacci(n - 1) + fibonacci(n - 2)
    }
}