package com.fracta7.crafter.model

class TreeNode<T>(val data: T) {
    val children: MutableList<TreeNode<T>> = mutableListOf()

    fun addChild(child: TreeNode<T>) {
        children.add(child)
    }

    fun printTree(indent: String = "") {
        println("$indent$data")
        for (child in children) {
            child.printTree("$indent  ")
        }
    }
}