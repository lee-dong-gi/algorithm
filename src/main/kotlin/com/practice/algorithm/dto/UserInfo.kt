package com.practice.algorithm.dto

data class UserInfo(
    val order: Int?,
    val name: String = "",
    val age: Short = 0
) {
    companion object {
        fun createDummyList(size: Int): Array<UserInfo> {
            return Array(size) { i ->
                UserInfo(order = i, name = "User $i", age = (20 + i).toShort())
            }
        }
    }
}
