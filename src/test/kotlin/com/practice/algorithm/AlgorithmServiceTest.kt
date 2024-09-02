package com.practice.algorithm

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class AlgorithmServiceTest {

    @Autowired
    lateinit var algorithmService: AlgorithmService

    @Test
    fun testFibonacci() {
        assertEquals(0, algorithmService.fibonacci(0))
        assertEquals(1, algorithmService.fibonacci(1))
        assertEquals(2, algorithmService.fibonacci(3))
    }
}