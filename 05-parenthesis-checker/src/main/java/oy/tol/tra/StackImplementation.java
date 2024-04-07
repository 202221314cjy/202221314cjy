package oy.tol.tra;
/**
 * An implementation of the StackInterface.
 * <p>
 * TODO: Students, implement this so that the tests pass.
 *
 * Note that you need to implement construtor(s) for your concrete StackImplementation, which
 * allocates the internal Object array for the Stack:
 * - a default constructor, calling the StackImplementation(int size) with value of 10.
 * - StackImplementation(int size), which allocates an array of Object's with size.
 *  -- remember to maintain the capacity and/or currentIndex when the stack is manipulated.
 */
public class StackImplementation<E> implements StackInterface<E> {

    private Object [] itemArray;
    private int capacity;
    private int currentIndex = -1;
    private static final int DEFAULT_STACK_SIZE = 10;
    private static final int EXTRA = 2;
    /**
     * Allocates a stack with a default capacity.
     * @throws StackAllocationException
     */
    public StackImplementation() throws StackAllocationException {
        // TODO: call the constructor with size parameter with default size of 10.
        this(DEFAULT_STACK_SIZE);
    }

    /** TODO: Implement so that
     * - if the size is less than 2, throw StackAllocationException
     * - if the allocation of the array throws with Java exception,
     *   throw StackAllocationException.
     * @param capacity The capacity of the stack.
     * @throws StackAllocationException If cannot allocate room for the internal array.
     */
    public StackImplementation(int capacity) throws StackAllocationException {
        //Check if the stack capacity is less than 2, and if so, throw an exception
        if (capacity < 2) {
            throw new StackAllocationException("Capacity must be at least 2.");
        }
        try {
            //Attempt to allocate an array of specified capacity size to store stack elements
            itemArray = new Object[capacity];
            //Set the capacity of the stack
            this.capacity = capacity;
        } catch (Exception e) {
            //If allocation fails, throw an exception
            throw new StackAllocationException("allocate failure");
        }
    }

    @Override
    public int capacity() {
        // TODO: Implement this
        return capacity;
    }

    @Override
    public void push(E element) throws StackAllocationException, NullPointerException {
        //Check if the stack is full, and if it is, expand it
        // TODO: Implement this
        if (currentIndex == capacity - 1) {
            int newCapacity = capacity * EXTRA;
            Object[] tempArray = new Object[newCapacity];//Create a temporary array to store expanded elements
            //Copy elements from the original array to a temporary array
            for (int i = 0; i < currentIndex + 1; i++) {
                tempArray[i] = itemArray[i];
            }
            //Point the reference to a new array and update the capacity
            itemArray = tempArray;
            capacity = newCapacity;
        }
        if (element == null) {
            //Check if the element to be pushed onto the stack is null, and if so, throw an exception
            throw new NullPointerException("The element pushed onto the stack cannot be null");
        }
        //Increase the current index and push elements onto the stack
        currentIndex++;
        itemArray[currentIndex] = element;
    }
    @SuppressWarnings("unchecked")
    @Override
    public E pop() throws StackIsEmptyException {
        if (currentIndex == -1) {
            throw new StackIsEmptyException("Stack is empty");
        }
        E poppedElement = (E) itemArray[currentIndex];
        itemArray[currentIndex] = null;
        currentIndex--;
        return poppedElement;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E peek() throws StackIsEmptyException {
        if (currentIndex == -1) {
            throw new StackIsEmptyException("Stack is empty");
        }
        return (E) itemArray[currentIndex];
    }

    @Override
    public int size() {
        // TODO: Implement this
        return currentIndex + 1;
    }

    @Override
    public void clear() {
        // TODO: Implement this
        for (int i = 0; i <= currentIndex; i++) {
            itemArray[i] = null;
        }
        currentIndex = -1;
    }

    @Override
    public boolean isEmpty() {
        // TODO: Implement this
        return currentIndex == -1;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        for (var index = 0; index <= currentIndex; index++) {
            builder.append(itemArray[index].toString());//Convert elements to strings
            if (index < currentIndex) {
                builder.append(", ");//Add a comma separator, unless it is the last element
            }
        }
        builder.append("]");//Adding right parentheses to indicate the end of the stack
        return builder.toString();//return string
    }
}
