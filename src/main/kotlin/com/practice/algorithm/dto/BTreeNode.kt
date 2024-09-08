package com.practice.algorithm.dto

class BTreeNode(
    var leaf: Boolean = true,  // 리프 노드인지 여부
    var keys: MutableList<Int> = mutableListOf(),  // 키들을 저장할 리스트
    var children: MutableList<BTreeNode> = mutableListOf()  // 자식 노드들을 저장할 리스트
)