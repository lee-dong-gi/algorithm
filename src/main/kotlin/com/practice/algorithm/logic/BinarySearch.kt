package com.practice.algorithm.logic

import com.practice.algorithm.Algorithm
import com.practice.algorithm.dto.AlgorithmParam
import com.practice.algorithm.dto.AlgorithmResponse
import com.practice.algorithm.dto.VirtualUserInfo

class BinarySearch : Algorithm {
    override fun execute(algorithmParam: AlgorithmParam): AlgorithmResponse {
        val result = AlgorithmResponse()
        algorithmParam.binarySearch?.let { result.binarySearchResult = binarySearch(it) }
        return result
    }

    // 미리 정렬되어 있는 경우에 가장 적합
    // 대량의 데이터에는 유용하나 적은 데이터에서는 비효율적
    // 요약 : 찾고자하는 값이 배열의 중앙값이면 탐색 완료된다. 배열의 중앙값보다 작으면 중앙값 이전, 크면 중앙값 다음 배열에서 또 다시 중앙값을 찾아 탐색함
    private fun binarySearch(targetOrder: Int): VirtualUserInfo {
        val userArr = VirtualUserInfo.createDummyList(100)
        return binarySearchRecursive(userArr, targetOrder, 0, userArr.size - 1)
    }

    private fun binarySearchRecursive(
        userArr: Array<VirtualUserInfo>,
        target: Int,
        left: Int,
        right: Int
    ): VirtualUserInfo {
        if (left > right) {
            return VirtualUserInfo(-1, "", -1) // Base case: Target 이 배열에 없는 경우
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
}