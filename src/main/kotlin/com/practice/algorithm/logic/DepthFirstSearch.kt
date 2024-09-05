package com.practice.algorithm.logic

import com.practice.algorithm.Algorithm
import com.practice.algorithm.dto.AlgorithmParam
import com.practice.algorithm.dto.AlgorithmResponse
import java.util.*

// 하나의 정점으로부터 시작하여 차례대로 모든 정점들을 한 번씩 방문하여 탐색
// https://www.youtube.com/watch?v=_hxFgg7TLZQ
class DepthFirstSearch :Algorithm {
    override fun execute(algorithmParam: AlgorithmParam): AlgorithmResponse {
        val result = AlgorithmResponse()
        algorithmParam.firstSearchGraph?.let {
            result.firstSearchResult = dfs(it.graph, it.start)
        }
        return result
    }

    private fun dfs(graph: Map<Int, List<Int>>, start: Int): List<Int> {
        // 방문한 노드를 기록하는 리스트
        val visited = mutableListOf<Int>()

        // 재귀적인 DFS 탐색 함수
        fun dfsRecursive(node: Int) {
            // 현재 노드를 방문 처리
            visited.add(node)

            // 현재 노드의 인접 노드를 탐색
            val neighbors = graph[node] ?: emptyList()
            for (neighbor in neighbors) {
                // 아직 방문하지 않은 노드만 재귀적으로 탐색
                if (neighbor !in visited) {
                    dfsRecursive(neighbor)
                }
            }
        }

        // DFS 탐색 시작
        dfsRecursive(start)

        // 방문 순서를 반환
        return visited
    }
}