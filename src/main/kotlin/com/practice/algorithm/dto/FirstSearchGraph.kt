package com.practice.algorithm.dto

class FirstSearchGraph(
    val graph: Map<Int, List<Int>>, val start: Int
) {
    companion object {
        fun createSampleGraph(
            graph: Map<Int, List<Int>> = mapOf(  // 기본값으로 샘플 그래프 제공
                1 to listOf(2, 3),
                2 to listOf(4, 5),
                3 to listOf(6),
                4 to emptyList(),
                5 to emptyList(),
                6 to emptyList()
            ),
            start: Int = 0  // 기본값
        ): FirstSearchGraph {
            return FirstSearchGraph(graph, start)
        }
    }
}