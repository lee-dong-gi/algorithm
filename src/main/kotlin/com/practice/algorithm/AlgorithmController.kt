package com.practice.algorithm

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class AlgorithmController (private val algorithmService: AlgorithmService){
    @GetMapping("/fibonacci/{n}")
    fun getFibonacci(@PathVariable n: Int): Int {
        return algorithmService.fibonacci(n)
    }
}