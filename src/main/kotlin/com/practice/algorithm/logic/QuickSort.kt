package com.practice.algorithm.logic

import com.practice.algorithm.Algorithm
import com.practice.algorithm.dto.AlgorithmParam
import com.practice.algorithm.dto.AlgorithmResponse

class QuickSort : Algorithm {
    override fun execute(algorithmParam: AlgorithmParam): AlgorithmResponse {
        val result = AlgorithmResponse()
        algorithmParam.sortArray?.let {
            result.sortedArray = quickSort(it, 0, it.size - 1)
        }
        return result
    }

    private fun quickSort(array: IntArray, low: Int, high: Int): IntArray {
        if (low < high) {
            // 배열을 파티션하여 피벗 위치를 찾습니다.
            val pivotIndex = partition(array, low, high)
            // 피벗을 기준으로 두 부분을 재귀적으로 정렬합니다.
            quickSort(array, low, pivotIndex - 1)
            quickSort(array, pivotIndex + 1, high)
        }
        return array
    }

    private fun partition(array: IntArray, low: Int, high: Int): Int {
        // 피벗을 배열의 마지막 원소로 설정합니다.
        val pivot = array[high]
        var i = low - 1

        for (j in low until high) {
            if (array[j] <= pivot) {
                i++
                // 원소를 교환합니다.
                array.swap(i, j)
            }
        }
        // 피벗을 올바른 위치로 이동시킵니다.
        array.swap(i + 1, high)
        return i + 1
    }

    private fun IntArray.swap(i: Int, j: Int) {
        val temp = this[i]
        this[i] = this[j]
        this[j] = temp
    }
}