package ihsusta.java;

/**
 * 双向链表
 * 为每个节点保留两个引用 prev 和 next
 * prev 指向当前结点的上一个节点
 * next 指向当前节点的下一个节点
 * 将链表的 header 节点与 tail 节点链在一起构成双向循环链表
 * <p>
 * 通过被搜索的index值来判断它更靠近 header or tail
 * if index < size / 2 then startSearchByHeader else startSearchByTail end if
 *
 * @param <T>
 */
public class DuLinkList<T> {
    private class Node {
        private T data;
        private Node prev;
        private Node next;

        public Node() {

        }

        public Node(T data, Node prev, Node next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node header;
    private Node tail;
    private int size;

    public DuLinkList() {
        header = null;
        tail = null;
    }

    public DuLinkList(T element) {
        header = new Node(element, null, null);
        tail = header;
        size++;
    }

    public int length() {
        return size;
    }

    private Node getNodeByIndex(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("线性表索引越界");
        }
        Node current;
        if (index <= size / 2) {
            // startFromHeader
            current = header;
            for (int i = 0; i <= size / 2 && current != null; i++, current = current.next) {
                if (i == index) {
                    return current;
                }
            }
        } else {
            current = tail;
            for (int i = size - 1; i > size / 2 && current != null; i++, current = current.prev) {
                if (i == index) {
                    return current;
                }
            }
        }
        return null;
    }


    public T get(int index) {
        if (getNodeByIndex(index) != null) {
            return getNodeByIndex(index).data;
        }
        return null;
    }

    // 查找链式线性表中指定元素的索引
    public int locate(T element) {
        // 从头结点开始查找
        Node current = header;
        for (int i = 0; i < size && current != null; i++, current = current.next) {
            if (current.data.equals(element)) {
                return i;
            }
        }
        return -1;
    }

    // 向链式线性表中的指定位置插入一个元素
    public void insert(T element, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("线性表索引越界");
        }
        // 如果是空链表 采用尾插法
        if (header == null) {
            add(element);
        } else {
            // 当 index 为 0  时,在链表头处插入
            if (index == 0) {
                addAtHeader(element);
            } else {
                // 获取插入点的前一个节点
                Node prev = getNodeByIndex(index - 1);
                // 获取插入点的节点
                Node next = prev.next;
                Node node = new Node(element, prev, next);
                prev.next = node;
                next.prev = node;
                size++;
            }
        }
    }

    // 尾插法添加节点
    private void add(T element) {
        if (header == null) {
            header = new Node(element, null, null);
            tail = header;
        } else {
            Node node = new Node(element, tail, null);
            tail.next = node;
            tail = node;
        }
        size++;
    }

    // 头插法
    private void addAtHeader(T element) {
        // 创建新节点,让新节点的next指向原来的header
        // 并以新节点作为新的header
        header = new Node(element, null, header);
        if (tail == null) {
            tail = header;
        }
        size++;
    }

    // 删除链式线性表中指定索引处的元素
    public T delete(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("线性表索引越界");
        }
        Node del;
        if (index == 0) {
            del = header;
            header = header.next;
            header.prev = null;
        } else {
            Node prev = getNodeByIndex(index - 1);
            del = prev.next;
            prev.next = del.next;
            if (del.next != null) {
                del.next.prev = prev;
            }
            // 将被删除的prev、next引用置为null
            del.prev = null;
            del.next = null;
        }
        size--;
        return del.data;
    }

    // 删除表中最后一个元素
    public T remove() {
        return delete(size - 1);
    }

    // 判断是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 清空线性表
    public void clear() {
        // 将底层数组所有元素置为null
        header = null;
        tail = null;
        size = 0;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        for (Node node = header; node != null; node = node.next) {
            sb.append(node.data.toString()).append(",");
        }
        return sb.deleteCharAt(sb.lastIndexOf(",")).append("]").toString();
    }

    // 反转 从tail开始
    public String reverseToString(){
        if(isEmpty()){
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        for (Node node = tail; node != null; node = tail.prev) {
            sb.append(node.data.toString()).append(",");
        }
        return sb.deleteCharAt(sb.lastIndexOf(",")).append("]").toString();
    }
}
