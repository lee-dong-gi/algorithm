package com.practice.algorithm

import com.practice.algorithm.dto.UserInfo
import org.springframework.stereotype.Service

@Service
class AlgorithmService {
    fun fibonacci(n: Int): Int {
        return if (n <= 1) n else fibonacci(n - 1) + fibonacci(n - 2)
    }

    fun binarySearch(targetOrder: Int): UserInfo {
        val userArr = UserInfo.createDummyList(100)
        return binarySearchRecursive(userArr, targetOrder, 0, userArr.size - 1)
    }

    fun binarySearchRecursive(userArr: Array<UserInfo>, target: Int, left: Int, right: Int): UserInfo {
        if (left > right) {
            return UserInfo(-1, "", -1) // Base case: Target 이 배열에 없는 경우
        }

        val mid = left + (right - left) / 2
        val order: Int? = userArr[mid].order

        order?.let {
            return when {
                order == target -> userArr[mid]
                order < target -> binarySearchRecursive(userArr, target, mid + 1, right)
                else -> binarySearchRecursive(userArr, target, left, mid - 1)
            }
        } ?: run {
            throw RuntimeException("order 가 없는 객체 발생")
        }
    }
}