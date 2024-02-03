package com.fracta7.crafter.model

/**
 * A class to represent a node for tree data structure
 * @property children a list of children nodes
 * @property addChild a method to add a child to current node.
 * @property printTree prints all children nodes.
 */
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