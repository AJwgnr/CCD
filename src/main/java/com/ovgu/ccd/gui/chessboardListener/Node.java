package com.ovgu.ccd.gui.chessboardListener;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * @param <T>
 */
public class Node<T> {
    private T data = null;
    private List<Node<T>> children = new ArrayList<>();
    private Node<T> parent = null;
    private String name = "";

    /**
     *
     * @param data
     */
    public Node(final T data) {
        this.data = data;
    }

    public Node(final String name, final T data) {
        this.data = data;
        this.name = name;
    }

    public void addChild(final Node<T> child) {
        child.setParent(this);
        this.children.add(child);
    }

    public void addChild(final T data) {
        Node<T> newChild = new Node<>(data);
        newChild.setParent(this);
        children.add(newChild);
    }

    public void addChildren(final List<Node<T>> children) {
        for (Node<T> t : children) {
            t.setParent(this);
        }
        this.children.addAll(children);
    }

    public boolean contains(final Node<T> child) {
        return children.contains(child);
    }

    public List<Node<T>> getChildren() {
        return children;
    }

    public Node<T> getNode(final String name) {
        if (this.getName().equals(name))
            return this;
        return breadthFirstSearch(this.getChildren(), name);
    }

    public Node<T> getChild(final String name) {
        for (Node<T> child : this.getChildren()) {
            if (child.getName().equals(name))
                return child;
        }
        return null;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }


    private Node<T> breadthFirstSearch(final List<Node<T>> allNodesOnLevel, final String targetName) {
        List<Node<T>> children = new ArrayList<Node<T>>();
        for (Node<T> node : allNodesOnLevel) {
            if (node.getName().equals(targetName))
                return node;
            children.addAll(node.getChildren());
        }
        return breadthFirstSearch(children, targetName);
    }


    public T getData() {
        return data;
    }

    public void setData(final T data) {
        this.data = data;
    }

    public void setParent(final Node<T> parent) {
        this.parent = parent;
    }

    public Node<T> getParent() {
        return parent;
    }

    public boolean hasChildren() {
        if (this.getChildren().isEmpty())
            return false;
        return true;
    }
}
