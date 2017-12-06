public class DoublyLinkedList<T> {
    private Node<T> initialNode;
    private int count;

    public DoublyLinkedList(T... initData) {
        this.count = 0;
        for(T data : initData) { push(data); }
    }

    public void push(T data) {
        Node<T> newNode = new Node<>(data);
        if (this.count == 0) {
            this.initialNode = newNode;
        } else {
            Node<T> lastNode = getLastNode(this.initialNode);
            makeConnection(lastNode, newNode);
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
        this.count--;
        return data;
    }

    public T shift() {
        T data = this.initialNode.getData();
        if (this.count > 1) {
            this.initialNode = this.initialNode.getNext();
            this.initialNode.assignPrev(null);
        } else {
            this.initialNode = null;
        }
        this.count--;
        return data;
    }

    public void unshift(T data) {
        Node<T> newNode  = new Node<>(data),
                initNode = this.initialNode;
        if (this.count > 0) makeConnection(newNode, initNode);
        this.initialNode = newNode;
        this.count++;
    }

    public void insert(T data, int index) {
      if (index < 0) throw new RuntimeException("Index must be above zero");
      Node<T> requestedNode = getNodeAt(this.initialNode, index, 0),
              prevNode      = (requestedNode == null) ? null : requestedNode.getPrev(),
              newNode       = new Node<>(data);
      if (this.count > 0) makeConnection(newNode, requestedNode);
      if (requestedNode == null || requestedNode.getPrev() == null) {
        this.initialNode = newNode;
      } else {
        makeConnection(prevNode, newNode);
      }
      this.count++;
    }

    public T getValueAtIndex(int index) {
      final String errMsg = "Index outside of range of LinkedList";
      if (index >= this.count) throw new RuntimeException(errMsg);
      Node<T> requestedNode = getNodeAt(this.initialNode, index, 0);
      return requestedNode.getData();
    }

    public int count() {
        return this.count;
    }

    private void makeConnection(Node<T> firstNode, Node<T> secondNode) {
        firstNode.assignNext(secondNode);
        secondNode.assignPrev(firstNode);
    }

    private Node<T> getNodeAt(Node<T> currentNode, int index, int counter) {
      if (currentNode.hasNext() && index != counter) {
        counter++;
        return getNodeAt(currentNode.getNext(), index, counter);
      } else if (index == counter) {
        return currentNode;
      } else {
        return null;
      }
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
