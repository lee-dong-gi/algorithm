package com.practice.algorithm

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class])
class AlgorithmApplication

fun main(args: Array<String>) {
    runApplication<AlgorithmApplication>(*args)
}
