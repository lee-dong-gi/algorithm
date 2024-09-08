package com.practice.algorithm.logic

import com.practice.algorithm.Algorithm
import com.practice.algorithm.dto.AlgorithmParam
import com.practice.algorithm.dto.AlgorithmResponse
import com.practice.algorithm.dto.BTreeNode

class BtreeSearch : Algorithm {  // t는 B-Tree 의 최소 차수
    companion object {
        const val MINIMUM_DEGREE: Int = 3
        var root: BTreeNode = BTreeNode(true)
    }

    init {
        insert(10)
        insert(20)
        insert(5)
        insert(6)
        insert(12)
        insert(30)
        insert(7)
        insert(17)
    }

    override fun execute(algorithmParam: AlgorithmParam): AlgorithmResponse {
        val result = AlgorithmResponse()
        algorithmParam.bTreeSearch?.let {
            result.bTreeNode = search(it)
        }
        val bTree = BtreeSearch();
        bTree.search(6)
        return result
    }

    // BTree 에서 키를 검색하는 함수
    fun search(key: Int): BTreeNode? {
        return search(root, key)
    }

    // 재귀적으로 키를 검색하는 함수
    private fun search(node: BTreeNode?, key: Int): BTreeNode? {
        if (node == null) return null  // 노드가 없으면 null 반환

        // 현재 노드의 키들에서 검색
        var i = 0
        while (i < node.keys.size && key > node.keys[i]) {
            i++
        }

        // 키를 찾은 경우 해당 노드를 반환
        if (i < node.keys.size && key == node.keys[i]) {
            return node
        }

        // 리프 노드라면 더 이상 찾을 곳이 없음
        if (node.leaf) {
            return null
        }

        // 자식 노드에서 계속 검색
        return search(node.children[i], key)
    }

    // BTree 에 키를 삽입하는 함수
    private fun insert(key: Int) {
        val r = root
        if (r.keys.size == 2 * MINIMUM_DEGREE - 1) {  // 루트 노드가 꽉 찬 경우
            val s = BTreeNode(leaf = false)
            s.children.add(r)
            splitChild(s, 0, r)  // 루트 노드를 분할
            root = s  // 새로운 루트로 변경
            insertNonFull(s, key)  // 새로운 노드에 키 삽입
        } else {
            insertNonFull(r, key)  // 루트가 꽉 차지 않았을 때 삽입
        }
    }

    // 노드를 분할하는 함수
    private fun splitChild(parent: BTreeNode, i: Int, fullChild: BTreeNode) {
        val newNode = BTreeNode(leaf = fullChild.leaf)
        parent.children.add(i + 1, newNode)  // 부모 노드에 새로운 자식 추가
        parent.keys.add(i, fullChild.keys[MINIMUM_DEGREE - 1])  // 부모 노드에 중간 키 추가

        newNode.keys.addAll(fullChild.keys.subList(MINIMUM_DEGREE, fullChild.keys.size))  // 오른쪽 키 이동
        fullChild.keys.subList(MINIMUM_DEGREE - 1, fullChild.keys.size).clear()  // 왼쪽 키 유지

        // 자식이 있는 경우 자식들도 분할
        if (!fullChild.leaf) {
            newNode.children.addAll(fullChild.children.subList(MINIMUM_DEGREE, fullChild.children.size))
            fullChild.children.subList(MINIMUM_DEGREE, fullChild.children.size).clear()
        }
    }

    // 키를 노드에 삽입하는 함수 (노드가 가득 차 있지 않은 경우)
    private fun insertNonFull(node: BTreeNode, key: Int) {
        var i = node.keys.size - 1

        if (node.leaf) {
            // 리프 노드에 키를 삽입
            node.keys.add(key)
            node.keys.sort()
        } else {
            // 내부 노드인 경우
            while (i >= 0 && key < node.keys[i]) {
                i--
            }
            i++

            // 자식 노드가 꽉 찼을 경우 분할
            if (node.children[i].keys.size == 2 * MINIMUM_DEGREE - 1) {
                splitChild(node, i, node.children[i])
                if (key > node.keys[i]) {
                    i++
                }
            }
            insertNonFull(node.children[i], key)
        }
    }

}