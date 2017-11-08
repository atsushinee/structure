package ihsusta.java;

/**
 * 链式线性表java描述
 * @param <T>
 */
public class LinkList<T> {
    // 定义内部类表示节点
    private class Node {
        // 保存节点数据
        private T data;
        // 指向下个节点的引用
        private Node next;

        public Node() {
        }

        // 初始化属性
        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    // 保存链表的头结点
    private Node header;
    // 保存链表的尾节点
    private Node tail;
    // 保存该链表中已包含的节点数
    private int size;

    // 创建空链表
    public LinkList() {
        header = null;
        tail = null;
    }

    // 以指定数据元素来创建链表，链表只包含一个元素
    public LinkList(T element) {
        header = new Node(element, null);
        tail = header;
        size++;
    }

    // 返回链表的长度
    public int length() {
        return size;
    }

    // 根据索引index获取指定位置的节点
    public Node getNodeByIndex(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("线性表索引越界");
        }
        // 从头结点开始遍历
        Node current = header;
        for (int i = 0; i < size && current != null; i++, current = current.next) {
            if (i == index) {
                return current;
            }
        }
        return null;
    }

    // 获取链式线性表中索引为index处的元素
    public T get(int index) {
        if (getNodeByIndex(index) != null) {
            return getNodeByIndex(index).data;
        }
        return null;
    }

    // 查找链式线性表中指定元素的索引
    public int locate(T element) {
        Node current = header;
        for (int i = 0; i < size && current != null; i++, current = current.next) {
            if (current.data.equals(element)) {
                return i;
            }
        }
        return -1;
    }

    // 向链式线性表的指定位置插入一个元素
    public void insert(T element, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("线性表索引越界");
        }
        // 如果是空链表
        if (header == null) {
            add(element);
        } else {
            // 当index==0,在链表头处插入
            if (index == 0) {
                addAtHeader(element);
            } else {
                // 获取插入点的前一个节点
                Node prev = getNodeByIndex(index - 1);
                prev.next = new Node(element, prev.next);
                size++;
            }
        }
    }

    // 采用头插法为链表添加新节点
    private void addAtHeader(T element) {
        header = new Node(element, header);
        // 如果插入前是空链表
        if (tail == null) {
            tail = header;
        }
        size++;
    }

    // 采用尾插法为链表添加新节点
    private void add(T element) {
        if (header == null) {
            header = new Node(element, null);
            tail = header;
        } else {
            Node node = new Node(element, null);
            tail.next = node;
            tail = node;
        }
        size++;
    }

    // 删除链式线性表中指定索引处的元素
    public T delete(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("线性表索引越界");
        }
        Node del;
        // 如果删除的是头结点
        if (index == 0) {
            del = header;
            header = header.next;
        } else {
            // 获取删除节点的前一个节点
            Node prev = getNodeByIndex(index - 1);
            del = prev.next;
            prev.next = del.next;
            // 将被删除节点的next引用置为null
            del.next = null;
        }
        size--;
        return del.data;
    }

    // 删除链式线性表中最后一个元素
    public T remove() {
        return delete(size - 1);
    }

    // 判断链式线性表是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 清空线性表
    public void clear() {
        // 将header,tail置为null
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
        Node current = header;
        while (current != null) {
            sb.append(current.data.toString()).append(",");
            current = current.next;
        }
        return sb.deleteCharAt(sb.lastIndexOf(",")).append("]").toString();
    }
}
