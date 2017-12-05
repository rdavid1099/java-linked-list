package main.java;

import main.java.Node;

public class DoublyLinkedList<T> {
  private Node<T> initialNode;
  private int count;

  public DoublyLinkedList() {
    this.count = 0;
  }

  public void push(T data) {
    Node<T> newNode = new Node<>(data);
    if (this.count == 0) {
      this.initialNode = newNode;
    } else {
      Node<T> lastNode = getLastNode(this.initialNode);
      lastNode.assignNext(newNode);
      newNode.assignPrev(lastNode);
    }
    this.count++;
  }

  public Node<T> getLastNode(Node<T> currentNode) {
    if (currentNode.hasNext()) {
      return getLastNode(currentNode.getNext());
    } else {
      return currentNode;
    }
  }
}
