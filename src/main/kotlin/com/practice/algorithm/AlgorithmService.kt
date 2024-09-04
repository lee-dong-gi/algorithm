package com.practice.algorithm

import com.practice.algorithm.dto.Graph
import com.practice.algorithm.dto.UserInfo
import org.springframework.stereotype.Service
import java.util.*

@Service
class AlgorithmService {
    fun fibonacci(n: Int): Int {
        return if (n <= 1) n else fibonacci(n - 1) + fibonacci(n - 2)
    }

    // 미리 정렬되어 있는 경우에 가장 적합
    // 대량의 데이터에는 유용하나 적은 데이터에서는 비효율적
    // 요약 : 찾고자하는 값이 배열의 중앙값이면 탐색 완료된다. 배열의 중앙값보다 작으면 중앙값 이전, 크면 중앙값 다음 배열에서 또 다시 중앙값을 찾아 탐색함
    fun binarySearch(targetOrder: Int): UserInfo {
        val userArr = UserInfo.createDummyList(100)
        return binarySearchRecursive(userArr, targetOrder, 0, userArr.size - 1)
    }

    fun binarySearchRecursive(userArr: Array<UserInfo>, target: Int, left: Int, right: Int): UserInfo {
        if (left > right) {
            return UserInfo(-1, "", -1) // Base case: Target 이 배열에 없는 경우
        }

        val mid = left + (right - left) / 2  // 배열의 중앙을 찾음
        val order: Int? = userArr[mid].order

        order?.let {
            return when {
                order == target -> userArr[mid]  // 찾고자하는 값이면 반환
                order < target -> binarySearchRecursive(
                    userArr,
                    target,
                    mid + 1,
                    right
                ) // 대상 값이 중앙보다 작으면 현재 중앙 기준 이후 값을 탐색
                else -> binarySearchRecursive(userArr, target, left, mid - 1) // 대상 값이 중앙보다 크다면 현재 중앙 기준 이전 값을 탐색
            }
        } ?: run {
            throw RuntimeException("order 가 없는 객체 발생")
        }
    }

    // https://www.youtube.com/watch?v=JfwzA467D04&t=41s
    fun calculateMinimumCostToAllPointsWithDijkstra(graph: Graph, startNode: Int): IntArray {
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

    fun calculatePathWithDijkstra(graph: Graph, startNode: Int, endNode: Int): List<Int> {
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