package com.company.algorithms.revert;

/**
 * Created by Yevhen on 20.07.2016.
 */
public class IntNode implements Node<Integer> {
    private Integer value;
    private Node<Integer> next;

    public IntNode(Integer value, IntNode next) {
        this.value = value;
        this.next = next;
    }

    @Override
    public Node<Integer> getNext() {
        return next;
    }

    @Override
    public void setNext(Node<Integer> node) {
        this.next = node;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public void setValue(Integer value) {
        this.value = value;
    }
}
