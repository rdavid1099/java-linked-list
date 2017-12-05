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

    public T pop() {
        Node<T> lastNode = getLastNode(this.initialNode);
        T data = lastNode.getData();
        if (this.count == 1) {
            this.initialNode = null;
        } else {
            lastNode.getPrev().assignNext(null);
        }
        lastNode.assignPrev(null);
        count--;
        return data;
    }

    public int count() {
        return this.count;
    }

    private Node<T> getLastNode(Node<T> currentNode) {
        if (currentNode.hasNext()) {
            return getLastNode(currentNode.getNext());
        } else {
            return currentNode;
        }
    }
}

class Node<T> {
    private T data;
    private Node<T> nextNode, prevNode;

    public Node(T data) {
        this.data = data;
    }

    public void assignNext(Node<T> nextNode) {
        this.nextNode = nextNode;
    }

    public void assignPrev(Node<T> prevNode) {
        this.prevNode = prevNode;
    }

    public T getData() {
        return this.data;
    }

    public Node<T> getNext() {
        return this.nextNode;
    }

    public Node<T> getPrev() {
        return this.prevNode;
    }

    public boolean hasNext() {
        return this.nextNode != null;
    }

    public boolean hasPrev() {
        return this.prevNode != null;
    }
}
