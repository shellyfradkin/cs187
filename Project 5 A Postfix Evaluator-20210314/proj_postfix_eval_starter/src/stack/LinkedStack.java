package stack;

/**
 * A {@link LinkedStack} is a stack that is implemented using a Linked List structure to allow for
 * unbounded size.
 *
 * @param <T> the elements stored in the stack
 */
public class LinkedStack<T> implements StackInterface<T> {

  //SF
  public LLNode<T> top;
  public int size; 
  
  /** {@inheritDoc} */
  @Override
  public T pop() throws StackUnderflowException {
    // TODO: Implement the stack operation for `pop`!
    if (isEmpty()) {
      throw new StackUnderflowException(); 
    }
      T data = top.getData();
      top = top.getNext();
      size--; 
    return data; 
  }
  
  /** {@inheritDoc} */
  @Override
  public T top() throws StackUnderflowException {
    // TODO: Implement the stack operation for `top`!
    if (isEmpty()) {
      throw new StackUnderflowException();
    }
    T data = top.getData();
    return data;
  }

  /** {@inheritDoc} */
  @Override
  public boolean isEmpty() {
    // TODO: Implement the stack operation for `isEmpty`!
    boolean empty = false;  
    if(size == 0){
      empty = true;
    }
    return empty; 
  }

  /** {@inheritDoc} */
  @Override
  public int size() {
    // TODO: Implement the stack operation for `size`!
    return size; 
  }

  /** {@inheritDoc} */
  @Override
  public void push(T elem) {
    // TODO: Implement the stack operation for `push`!
    LLNode<T> newTop = new LLNode<T>(elem);
    newTop.setNext(top);
    top = newTop; 
    size++;
    
  }
}
