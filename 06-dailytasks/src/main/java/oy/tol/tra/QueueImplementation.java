package oy.tol.tra;


public class QueueImplementation<E> implements QueueInterface<E> {
    private int capacity;
    private int head = 0;
    private int tail = 0;
    private int size = 0;
    private Object[] ARRAY;
    private static final int DEFAULT_QUEUE_SIZE = 10;
    public QueueImplementation() throws QueueAllocationException{
        this(DEFAULT_QUEUE_SIZE);
    }
    public QueueImplementation(int capacity) throws QueueAllocationException{
        if(capacity < 2){
            throw new QueueAllocationException("Queue capacity must be at least 2");
        }
        try{//Allocate internal array space
            ARRAY = new Object[capacity];
            this.capacity = capacity;//Set queue capacity
        }catch(OutOfMemoryError e){
            throw new QueueAllocationException("Failed room for the internal array");
        }

    }
    @Override
    public int capacity() {
        return capacity;
    }
    @Override
    public void enqueue(E element) throws QueueAllocationException, NullPointerException {
        if (element == null) {
            throw new NullPointerException("element can not be null");
        }

        // Check if the queue is full and needs to expand
        if (size >= capacity) {
            try {
                int newCapacity = 2 * capacity; // Double the capacity
                Object[] newArray = new Object[newCapacity];
                for (int i = 0; i < size; i++) {
                    // Re-arrange elements considering wrap-around
                    int index = head + i;
                    if (index >= capacity) {
                        index -= capacity; // Wrap around to the beginning of the array
                    }
                    newArray[i] = ARRAY[index];
                }
                ARRAY = newArray;
                head = 0; // Reset head to the start of the new array
                tail = size; // Set tail correctly in the new array
                capacity = newCapacity; // Update capacity to the new size
            } catch (OutOfMemoryError e) {
                throw new QueueAllocationException("Failed to allocate more room for the queue.");
            }
        }

        ARRAY[tail] = element; // Insert the element at the tail
        if (tail == capacity - 1) {
            tail = 0; // Wrap around to the beginning of the array
        } else {
            tail++; // Move tail pointer forward
        }
        size++; // Increment size
    }
    @Override
    public E dequeue() throws QueueIsEmptyException {
        if (size == 0) {//Check if the queue is empty
            throw new QueueIsEmptyException("Queue is empty");
        }

        Object dequeueElement = ARRAY[head];//Get the element at the front
        ARRAY[head] = null;//Remove the element by setting its position to null

        head = head + 1;//Update the head pointer to the next element position
        if (head == capacity) {
            head = 0;
        }//If the capacity is reached, loop it to the beginning of the queue
        size--; //Decrease the size of the queue

        return (E) dequeueElement;
    }
    @Override
    public E element() throws QueueIsEmptyException {
        if (head == tail && size != capacity){
            throw new QueueIsEmptyException("There's no data in the queue");
        }
        Object el = ARRAY[head];
        return (E) el;
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
        for(int i = 0; i <capacity; i++){
            ARRAY[i] = null;
        }
        size = 0;
        tail = 0;
        head = 0;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            //Determine whether the element is at the head of the array
            if (head + i < capacity) {
                builder.append(ARRAY[head + i].toString());
            } else {
                //If not at the head, calculate the position of the element in the loop array
                builder.append(ARRAY[i - (capacity - head)].toString());
            }
            //If it is not the last element, add commas and spaces
            if (i < size - 1) {
                builder.append(", ");
            }
        }
        //Add a right parenthesis at the end to return the concatenated string
        builder.append("]");
        return builder.toString();
    }
}
