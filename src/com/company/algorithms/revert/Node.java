package com.company.algorithms.revert;

/**
 * Created by Yevhen on 20.07.2016.
 */
public interface Node<T> {
    Node<T> getNext();

    void setNext(Node<T> node);

    T getValue();

    void setValue(T value);

    static <E> Node<E> revert(Node<E> node) {
        Node<E> previous = node;
        Node<E> current = node.getNext();
        node.setNext(null);

        while (current != null) {
            Node<E> next = current.getNext();
            current.setNext(previous);

            previous = current;
            current = next;
        }

        return previous;
    }
}
