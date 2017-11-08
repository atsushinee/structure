package ihsusta.java;

import java.util.Arrays;

/**
 * 顺序线性表java描述
 * @param <T>
 */
public class SequenceList<T> {
    private final int DEFAULT_SIZE = 16;
    private int capacity;  // 数据长度
    private Object[] elementData;  // 元素数据
    private int size = 0;  // 当前长度

    // 以默认数组长度创建空顺序线性表
    public SequenceList() {
        capacity = DEFAULT_SIZE;
        elementData = new Object[capacity];
    }

    // 以一个初始化元素创建顺序线性表
    public SequenceList(T element) {
        this();
        elementData[0] = element;
        size++;
    }

    /**
     * 以指定长度的数据来创建顺序线性表
     *
     * @param element  指定顺序线性表中第一个元素
     * @param initSize 指定顺序线性表底层数组的长度
     */
    public SequenceList(T element, int initSize) {
        capacity = 1;
        while (capacity < initSize) {
            capacity <<= 1;
        }
        elementData = new Object[capacity];
        elementData[0] = element;
        size++;
    }

    // 获取顺序线性表的大小
    public int length() {
        return size;
    }

    // 获取顺序线性表中索引为i处的元素
    public T get(int i) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("线性表索引越界");
        }
        return cast(elementData[i]);
    }

    // 查找顺序线性表中指定元素的索引
    public int locate(T element) {
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    // 向顺序线性表的指定位置插入一个元素
    public void insert(T element, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("线性表索引越界");
        }
        ensureCapacity(size + 1);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = element;
        size++;
    }

    // 线性顺序表添加一个元素
    public void add(T element) {
        insert(element, size);
    }

    // 适配容量
    private void ensureCapacity(int minCapatity) {
        if (capacity < minCapatity) {
            while (capacity < minCapatity) {
                capacity <<= 1;
            }
            elementData = Arrays.copyOf(elementData, capacity);
        }
    }

    // 删除指定索引位置的元素  先判断索引是否有效 将索引位置元素删除 将删除元素之后的所有元素复制到删除元素的位置
    // 并将最后元素置为null 返回删除的元素
    public T delete(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("线性表索引越界");
        }
        T oldValue = cast(elementData[index]);
        int delIndex = size - index - 1;
        if (index > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, delIndex);
        }
        // 将最后一个元素清空
        elementData[--size] = null;
        return oldValue;
    }

    // 删除并返回线性表最后一个元素
    public T pop() {
        return delete(size - 1);
    }

    // 判断线性表是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 清空线性表
    public void clear() {
        // for 循环
        //   ||
        // Arrays.fill(elementData,null);
        for (Object element : elementData) {
            element = null;
        }
        size = 0;
    }

    // 重写toString
    @Override
    public String toString() {
        if(size == 0){
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        for (Object element :elementData){
            sb.append(element.toString()).append(",");
        }
        return sb.deleteCharAt(sb.lastIndexOf(",")).append("]").toString();

    }

    @SuppressWarnings("unchecked")
    private static <T> T cast(Object obj) {
        return (T) obj;
    }
}
