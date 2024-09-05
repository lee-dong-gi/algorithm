package com.practice.algorithm.logic

import com.practice.algorithm.Algorithm
import com.practice.algorithm.dto.AlgorithmParam
import com.practice.algorithm.dto.AlgorithmResponse
import java.util.*

// 너비 우선 탐색(Breadth-first search, BFS)은 맹목적 탐색방법의 하나로 시작 정점을 방문한 후 시작 정점에 인접한 모든 정점들을 우선 방문하는 방법이다.
// 더 이상 방문하지 않은 정점이 없을 때까지 방문하지 않은 모든 정점들에 대해서도 너비 우선 검색을 적용한다.
// https://www.youtube.com/watch?v=_hxFgg7TLZQ
class BreadthFirstSearch :Algorithm {
    override fun execute(algorithmParam: AlgorithmParam): AlgorithmResponse {
        val result = AlgorithmResponse()
        algorithmParam.firstSearchGraph?.let {
            result.firstSearchResult = bfs(it.graph, it.start)
        }
        return result
    }

    private fun bfs(graph: Map<Int, List<Int>>, start: Int): List<Int> {
        // 방문한 노드를 기록하는 리스트
        val visited = mutableListOf<Int>()
        // BFS 에서 사용할 큐 (LinkedList 로 구현)
        val queue: Queue<Int> = LinkedList()

        // 시작 노드를 큐에 추가하고 방문 처리
        queue.add(start)
        visited.add(start)

        // 큐가 빌 때까지 반복
        while (queue.isNotEmpty()) {
            // 큐에서 노드를 꺼내서 처리
            val node = queue.poll()

            // 현재 노드의 인접 노드를 탐색
            val neighbors = graph[node] ?: emptyList()
            for (neighbor in neighbors) {
                // 아직 방문하지 않은 노드를 큐에 추가하고 방문 처리
                if (neighbor !in visited) {
                    queue.add(neighbor)
                    visited.add(neighbor)
                }
            }
        }
        // 방문 순서를 반환
        return visited
    }
}