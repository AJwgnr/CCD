package com.ovgu.ccd.gui.chessboardListener;


import java.util.ArrayList;
import java.util.List;


public class Node<T>
{
    private T data = null;
    private List<Node<T>> children = new ArrayList<>();
    private Node<T> parent = null;
    private String name = "";
    

    public Node(T data)
    {
        this.data = data;
    }
    
    public Node(String name, T data)
    {
        this.data = data;
        this.name = name;
    }

    public void addChild(Node<T> child)
    {
        child.setParent(this);
        this.children.add(child);
    }

    public void addChild(T data)
    {
        Node<T> newChild = new Node<>(data);
        newChild.setParent(this);
        children.add(newChild);
    }

    public void addChildren(List<Node<T>> children)
    {
        for(Node<T> t : children) {
            t.setParent(this);
        }
        this.children.addAll(children);
    }

    public boolean contains(Node<T> child)
    {
        return children.contains(child);
    }
    
    public List<Node<T>> getChildren()
    {
        return children;
    }
    
    public Node<T> getNode(String name)
    {
    	if (this.getName().equals(name))
    		return this;
    	return breadthFirstSearch(this.getChildren(), name);
    }
    
    public Node<T> getChild(String name)
    {
    	for (Node<T> child : this.getChildren())
    	{
    		if (child.getName().equals(name))
    			return child;
    	}
    	return null;
    }
    
    public String getName()
    {
    	return this.name;
    }
    
    public void setName(String name)
    {
    	this.name = name;
    }
	
	
	private Node<T> breadthFirstSearch(List<Node<T>> allNodesOnLevel, String targetName)
	{
		List<Node<T>> children = new ArrayList<Node<T>>();
    	for (Node<T> node : allNodesOnLevel)
    	{
    		if (node.getName().equals(targetName))
    			return node;
    		children.addAll(node.getChildren());
    	}
    	return breadthFirstSearch(children, targetName);
	}


    public T getData()
    {
        return data;
    }

    public void setData(T data)
    {
        this.data = data;
    }

    public void setParent(Node<T> parent)
    {
        this.parent = parent;
    }

    public Node<T> getParent()
    {
        return parent;
    }
    
    public boolean hasChildren()
    {
    	if (this.getChildren().isEmpty())
    		return false;
    	return true;
    }
}
