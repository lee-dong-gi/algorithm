package com.practice.algorithm

import com.practice.algorithm.dto.AlgorithmParam
import com.practice.algorithm.dto.AlgorithmResponse

interface Algorithm {
    fun execute(algorithmParam: AlgorithmParam): AlgorithmResponse
}