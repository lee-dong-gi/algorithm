package com.practice.algorithm.logic

import com.practice.algorithm.Algorithm
import com.practice.algorithm.dto.AlgorithmParam
import com.practice.algorithm.dto.AlgorithmResponse
import com.practice.algorithm.dto.Graph
import java.util.*

class Dijkstra : Algorithm {
    override fun execute(algorithmParam: AlgorithmParam): AlgorithmResponse {
        val result = AlgorithmResponse()
        val graph = Graph.createSampleGraph(5)

        if (algorithmParam.startNode != null) {
            algorithmParam.endNode?.let {
                result.dijkstraPathResult = calculatePath(graph, algorithmParam.startNode!!, it)
            } ?: run {
                result.dijkstraMinimumCostToAllPointsResult =
                    calculateMinimumCostToAllPoints(graph, algorithmParam.startNode!!)
            }
        }

        return result
    }

    private    // https://www.youtube.com/watch?v=JfwzA467D04&t=41s
    fun calculateMinimumCostToAllPoints(graph: Graph, startNode: Int): IntArray {
        // 모든 정점에 대해 초기 거리를 무한대로 설정. 시작 정점의 거리는 0으로 설정
        val distances = IntArray(graph.vertices) { Int.MAX_VALUE } // 배열의 각 요소를 Int 최대값으로 설정
        distances[startNode] = 0 // 시작점은 0으로

        // 우선순위 큐를 사용하여 방문할 정점을 선택. 정점과 그 정점까지의 거리 쌍을 저장
        // Pair 객체에서 두번째(second) 값을 기준으로 비교하여 오름차순으로 우선순위 큐에 저장
        val priorityQueue = PriorityQueue(compareBy<Pair<Int, Int>> { it.second })
        priorityQueue.add(Pair(startNode, 0))

        // 우선순위 큐가 비어있을 때까지 반복
        while (priorityQueue.isNotEmpty()) {
            // 큐에서 현재 가장 가까운 정점을 선택
            // 우선순위가 가장 높은 요소(이 경우 compareBy 기준으로 가장 작은 값)을 가져오고 큐에서 제거
            val (currentVertex, currentDistance) = priorityQueue.poll()

            // 현재 정점의 거리가 이미 저장된 거리보다 크면 건너뛴다 (최적화)
            if (currentDistance > distances[currentVertex]) continue

            // 현재 정점과 연결된 모든 인접 정점을 확인
            for (edge in graph.adjacencyList[currentVertex]) {
                // 현재 정점을 거쳐서 인접 정점으로 가는 거리를 계산
                val newDistance = currentDistance + edge.weight

                // 새로운 경로가 더 짧으면 거리를 갱신하고, 큐에 인접 정점을 추가
                if (newDistance < distances[edge.destination]) {
                    distances[edge.destination] = newDistance
                    priorityQueue.add(Pair(edge.destination, newDistance))
                }
            }
        }

        // 시작 정점에서 각 정점까지의 최단 거리를 반환
        return distances
    }

    private fun calculatePath(graph: Graph, startNode: Int, endNode: Int): List<Int> {
        // 모든 정점에 대해 초기 거리를 무한대로 설정. 시작 정점의 거리는 0으로 설정
        val distances = IntArray(graph.vertices) { Int.MAX_VALUE } // 배열의 각 요소를 Int 최대값으로 설정
        distances[startNode] = 0 // 시작점은 0으로

        // 경로 추적을 위한 배열. -1인 경우 더이상 정점이 없음을 의미
        val previousNodes = IntArray(graph.vertices) { -1 }

        // 우선순위 큐를 사용하여 방문할 정점을 선택. 정점과 그 정점까지의 거리 쌍을 저장
        val priorityQueue = PriorityQueue(compareBy<Pair<Int, Int>> { it.second })
        priorityQueue.add(Pair(startNode, 0))

        // 우선순위 큐가 비어있을 때까지 반복
        while (priorityQueue.isNotEmpty()) {
            // 큐에서 현재 가장 가까운 정점을 선택
            val (currentVertex, currentDistance) = priorityQueue.poll()

            // 현재 정점의 거리가 이미 저장된 거리보다 크면 건너뛴다 (최적화)
            if (currentDistance > distances[currentVertex]) continue

            // 현재 정점과 연결된 모든 인접 정점을 확인
            for (edge in graph.adjacencyList[currentVertex]) {
                // 현재 정점을 거쳐서 인접 정점으로 가는 거리를 계산
                val newDistance = currentDistance + edge.weight

                // 새로운 경로가 더 짧으면 거리를 갱신하고, 큐에 인접 정점을 추가
                if (newDistance < distances[edge.destination]) {
                    distances[edge.destination] = newDistance
                    previousNodes[edge.destination] = currentVertex // 경로 추적을 위해 이전 정점 기록
                    priorityQueue.add(Pair(edge.destination, newDistance))
                }
            }
        }

        // 목적지까지의 경로를 추적
        // previousNodes 에는 정점에 대한 모든 경로가 저장되어있어서 따로 추적하는 작업이 필요함
        val path = mutableListOf<Int>()
        var currentNode = endNode
        while (currentNode != -1) {
            path.add(currentNode)
            currentNode = previousNodes[currentNode]
        }

        // 경로가 거꾸로 되어 있으므로, 순서를 뒤집어서 반환
        return path.reversed()
    }

}