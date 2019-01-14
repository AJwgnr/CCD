package com.ovgu.ccd.gui.chessboardListener;


import java.util.ArrayList;
import java.util.List;


/**
 * @author CCD DeepBlue team
 * @version 1.0
 * @since
 */
public class Node<T> {
    private T data = null;
    private List<Node<T>> children = new ArrayList<>();
    private Node<T> parent = null;
    private String name = "";


    /**
     * constructor
     *
     * @param data data in a certain datatype
     */
    public Node(final T data) {
        this.data = data;
    }


    /**
     * constructor
     *
     * @param   name    name of the node
     * @param   data    data in a certain datatype
     */
    public Node(final String name, final T data) {
        this.data = data;
        this.name = name;
    }


    /**
     * adds a child node to the current node
     *
     * @param child child node
     */
    public void addChild(final Node<T> child) {
        child.setParent(this);
        this.children.add(child);
    }


    /**
     * adds a child node to the current node
     *
     * @param data data with which a new child node will be created
     */
    public void addChild(final T data) {
        Node<T> newChild = new Node<>(data);
        newChild.setParent(this);
        children.add(newChild);
    }


    /**
     * adds a list of childs to the current node
     *
     * @param   children    list of children
     */
    public void addChildren(final List<Node<T>> children) {
        for (Node<T> t : children) {
            t.setParent(this);
        }
        this.children.addAll(children);
    }


    /**
     * boolean function which returns whether the current node contains a certain child or not
     *
     * @param   child   child node to check for
     * @return  1 - true - it contains the child
     *          0 - false - it doesnt contain the child
     */
    public boolean contains(final Node<T> child) {
        return children.contains(child);
    }


    /**
     * adds a list of childs to the current node
     *
     * @param   children    list of children
     */
    public List<Node<T>> getChildren() {
        return children;
    }


    /**
     * returns a certain node (search in tree - in child list)
     *
     * @param   name    searches for a certain name
     * @return  returns the node and null if its not been found at all
     */
    public Node<T> getNode(final String name) {
        if (this.getName().equals(name))
            return this;
        return breadthFirstSearch(this.getChildren(), name);
    }


    /**
     * returns a certain child (only first-level children)
     *
     * @param   name    searches for a certain name
     * @return  returns the node and null if its not been found at all
     */
    public Node<T> getChild(final String name) {
        for (Node<T> child : this.getChildren()) {
            if (child.getName().equals(name))
                return child;
        }
        return null;
    }


    /**
     * returns the name of the current node
     *
     * @return  name
     */
    public String getName() {
        return this.name;
    }


    /**
     * sets the name of the current node
     *
     * @param   name    new name for the node
     */
    public void setName(final String name) {
        this.name = name;
    }


    /**
     * breadth first search through the tree of children of the current parent node
     *
     * @param   allNodesOnLevel list of nodes on the level of the tree to search on
     * @param   targetName name of the node to search for
     * @return  returns the node and null if its not been found at all
     */
    private Node<T> breadthFirstSearch(final List<Node<T>> allNodesOnLevel, final String targetName) {
        List<Node<T>> children = new ArrayList<Node<T>>();
        for (Node<T> node : allNodesOnLevel) {
            if (node.getName().equals(targetName))
                return node;
            children.addAll(node.getChildren());
        }
        return breadthFirstSearch(children, targetName);
    }


    /**
     * returns the data saved in the current node
     *
     * @return  data
     */
    public T getData() {
        return data;
    }


    /**
     * sets the data to be stored in the node
     *
     * @param   data    data to be saved
     */
    public void setData(final T data) {
        this.data = data;
    }


    /**
     * sets the parent node of the node object
     *
     * @param  parent   certain parent node
     */
    public void setParent(final Node<T> parent) {
        this.parent = parent;
    }


    /**
     * return the parent node of that node object
     *
     * @return  parent node
     */
    public Node<T> getParent() {
        return parent;
    }


    /**
     * boolean function which checks whether the node have any children
     *
     * @return  1 - has children
     *          0 - has no children
     */
    public boolean hasChildren() {
        if (this.getChildren().isEmpty())
            return false;
        return true;
    }
}
