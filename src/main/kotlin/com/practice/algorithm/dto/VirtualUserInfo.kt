package com.practice.algorithm.dto

data class VirtualUserInfo(
    val order: Int?,
    val name: String = "",
    val age: Short = 0
) {
    companion object {
        fun createDummyList(size: Int): Array<VirtualUserInfo> {
            return Array(size) { i ->
                VirtualUserInfo(order = i, name = "User $i", age = (20 + i).toShort())
            }
        }
    }
}
