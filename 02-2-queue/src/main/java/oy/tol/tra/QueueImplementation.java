package oy.tol.tra;


public class QueueImplementation<E> implements QueueInterface<E> {
    private int capacity;
    private int size ;
    private Object[] ARRAY;

    public QueueImplementation(int capacity) throws QueueAllocationException{
        if(capacity < 2){
            throw new QueueAllocationException("Queue capacity must be at least 2");
        }
        try{//Allocate internal array space
            ARRAY = new Object[capacity];
        }catch(OutOfMemoryError e){
            throw new QueueAllocationException("Failed room for the internal array");
        }
        this.capacity = capacity;//Set queue capacity
    }
    @Override
    public int capacity() {
        return capacity;
    }


    @Override
    public int size() {
        return size;
    }
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    @Override
    public void clear() {
        for(int i = 0; i <size; i++){
            ARRAY[i] = null;
        }
        size = 0;
    }


    public E dequeue() throws QueueIsEmptyException {
        if (isEmpty()) {//If the queue is empty
            throw new QueueIsEmptyException("Queue is empty");
        }
        E element = (E) ARRAY[0];//Get the first element
        for (int i = 0; i < size - 1; i++) {//Move the element forward by one position
            ARRAY[i] = ARRAY[i + 1];
        }
        ARRAY[--size] = null;//Leave the last element blank
        return element;

    }


    public E element() throws QueueIsEmptyException {
        if (isEmpty()) { //If the queue is empty
            throw new QueueIsEmptyException("There's no data in the queue");
        }
        return (E)ARRAY[0]; //Return the first element of the queue
    }
    public void enqueue(E element) throws QueueAllocationException, NullPointerException {
        if (element == null) {
            throw new NullPointerException("element can not be null");
        }
        if (size >= capacity) {
            int newCapacity = capacity * 2;
            Object[] newArray;
            newArray = new Object[newCapacity];//Create a new array
            for (int i = 0; i < size; i++) {
                newArray[i] = ARRAY[i];//Copy the original array elements to the new array
            }
            ARRAY = newArray;
            capacity = newCapacity;//Update queue capacity
        }
        ARRAY[size++] = element;//Add elements to the end of the queue
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (size > 0) {
            sb.append(ARRAY[0]);//Add the first element to the string
            for (int i = 1; i < size; i++) {
                sb.append(", ");
                sb.append(ARRAY[i]);//Add current element
            }
        }
        sb.append("]");//End concatenating string
        return sb.toString();
    }
}