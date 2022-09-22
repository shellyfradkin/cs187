package structures;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<T extends Comparable<T>> implements BSTInterface<T> {
  protected BSTNode<T> root;

  public boolean isEmpty() {
    return root == null;
  }

  public int size() {
    return subtreeSize(root);
  }

  protected int subtreeSize(BSTNode<T> node) {
    if (node == null) {
      return 0;
    } else {
      return 1 + subtreeSize(node.getLeft()) + subtreeSize(node.getRight());
    }
  }
  
  public boolean contains(T t){
    // TODO: Implement the contains() method
     if(t == null){
      throw new NullPointerException();
    }
    boolean containsT; 
    if(get(t) == null){
      containsT = false; 
    }
    else{
      containsT = true; 
    }
    return containsT; 
  }

  public boolean remove(T t) {
    if (t == null) {
      throw new NullPointerException();
    }
    boolean result = contains(t);
    if (result) {
      root = removeFromSubtree(root, t);
    }
    return result;
  }

  protected BSTNode<T> removeFromSubtree(BSTNode<T> node, T t) {
    // node must not be null
    int result = t.compareTo(node.getData());
    if (result < 0) {
      node.setLeft(removeFromSubtree(node.getLeft(), t));
      return node;
    } else if (result > 0) {
      node.setRight(removeFromSubtree(node.getRight(), t));
      return node;
    } else { // result == 0
      if (node.getLeft() == null) {
        return node.getRight();
      } else if (node.getRight() == null) {
        return node.getLeft();
      } else { // neither child is null
        T predecessorValue = getHighestValue(node.getLeft());
        node.setLeft(removeRightmost(node.getLeft()));
        node.setData(predecessorValue);
        return node;
      }
    }
  }

  private T getHighestValue(BSTNode<T> node) {
    // node must not be null
    if (node.getRight() == null) {
      return node.getData();
    } else {
      return getHighestValue(node.getRight());
    }
  }
  
  private BSTNode<T> removeRightmost(BSTNode<T> node) {
    // node must not be null
    if (node.getRight() == null) {
      return node.getLeft();
    } else {
      node.setRight(removeRightmost(node.getRight()));
      return node;
    }
  }
  
  public T get(T t) {
    // TODO: Implement the get() method
    if(t == null){
      throw new NullPointerException();
    }
    return getHelper(t, root); 
  }
  //Helper method for get 
  private T getHelper(T elem, BSTNode<T> curr){
    if(curr == null){
      return null; 
    }
    int res = elem.compareTo(curr.getData());
    if(res == 0){
      return curr.getData(); 
    }
    else if(res < 0){
      return getHelper(elem, curr.getLeft());
    }
    else{
      return getHelper(elem, curr.getRight()); 
    } 
  }
  

  public void add(T t) {
    if (t == null) {
      throw new NullPointerException();
    }
    root = addToSubtree(root, new BSTNode<T>(t, null, null));
  }

  protected BSTNode<T> addToSubtree(BSTNode<T> node, BSTNode<T> toAdd) {
    if (node == null) {
      return toAdd;
    }
    int result = toAdd.getData().compareTo(node.getData());
    if (result <= 0) {
      node.setLeft(addToSubtree(node.getLeft(), toAdd));
    } else {
      node.setRight(addToSubtree(node.getRight(), toAdd));
    }
    return node;
  }

  @Override
  public T getMinimum() {
    // TODO: Implement the getMinimum() method
    if(isEmpty()){
      return null; 
    }
    if(root.getLeft() == null){
      return root.getData(); 
    }
    else{
      return getMinimumHelper(root.getLeft());
    }
  }
  //helper method for getMinimum
  private T getMinimumHelper(BSTNode<T> curr){
    if(curr.getLeft() != null){
      return getMinimumHelper(curr.getLeft());
    }
    else{
      return curr.getData(); 
    }
      
  }


  @Override
  public T getMaximum() {
    // TODO: Implement the getMaximum() method

    if(isEmpty()){
      return null; 
    }
    if(root.getRight() == null){
      return root.getData(); 
    }
    else{
      return getMaximumHelper(root.getRight());
    }
  }
  
  //helper method for getMaximum
  private T getMaximumHelper(BSTNode<T> curr){
    if(curr.getRight() != null){
      return getMaximumHelper(curr.getRight());
    }
    else{
      return curr.getData(); 
    }
      
  }

  @Override
  public int height() {
    // TODO: Implement the height() method
    return heightHelper(root);
    
  }
  private int heightHelper(BSTNode<T> curr){
    if(curr == null){
      return -1; 
    }
    else{
      return 1 + Math.max(heightHelper(curr.getRight()), heightHelper(curr.getLeft())); 
    }
  }
  

  public Iterator<T> preorderIterator() {
    // TODO: Implement the preorderIterator() method
    Queue<T> queue = new LinkedList<T>();
    preorderTraverse(queue, root);
    return queue.iterator();
  }
  //helper method for preoderIterator
  private void preorderTraverse(Queue<T> queue, BSTNode<T> node){
    if(node != null){
      queue.add(node.getData());
      preorderTraverse(queue, node.getLeft());
      preorderTraverse(queue, node.getRight());
    
      
    }
  }


  public Iterator<T> inorderIterator() {
    Queue<T> queue = new LinkedList<T>();
    inorderTraverse(queue, root);
    return queue.iterator();
  }


  private void inorderTraverse(Queue<T> queue, BSTNode<T> node) {
    if (node != null) {
      inorderTraverse(queue, node.getLeft());
      queue.add(node.getData());
      inorderTraverse(queue, node.getRight());
    }
  }

  public Iterator<T> postorderIterator() {
    // TODO: Implement the postorderIterator() method
    Queue<T> queue = new LinkedList<T>();
    postOrderTraverse(queue, root);
    return queue.iterator();
  }
  //helper method for postorderIterator
  private void postOrderTraverse(Queue<T> queue, BSTNode<T> node){
    if (node != null) {
      postOrderTraverse(queue, node.getLeft());
      postOrderTraverse(queue, node.getRight());
      queue.add(node.getData());
    }
  }


  @Override
  public boolean equals(BSTInterface<T> other) {
    // TODO: Implement the equals() method
    return equalsHelper(root, other.getRoot());

  }

  private boolean equalsHelper(BSTNode<T>curr1, BSTNode<T> curr2){
    if(curr1 == null && curr2 == null){
      return true;
    }
    else if(curr1 == null || curr2 == null){
      return false; 
    }
    else if(!curr1.getData().equals(curr2.getData())){
      return false; 
    }
    else{
      return equalsHelper(curr1.getRight(), curr2.getRight()) && equalsHelper(curr1.getLeft(), curr2.getLeft());
    }
    }



  @Override
  public boolean sameValues(BSTInterface<T> other) {
    // TODO: Implement the sameValues() method
    Iterator<T> iter1 = inorderIterator();
		Iterator<T> iter2 = other.inorderIterator();
		while (iter1.hasNext() && iter2.hasNext()) {
      if (!iter1.next().equals(iter2.next())){
        return false;
      }
    }
			
		return !iter1.hasNext() && !iter2.hasNext();
	}



  @Override
  public boolean isBalanced() {
    // TODO: Implement the isBalanced() method
    if(isEmpty()){
      return true;
    }
    return Math.pow(2, height()) <= size() && size() < Math.pow(2, height() + 1);
  }
  
  @Override
  @SuppressWarnings("unchecked")
  public void balance() {
    // TODO: Implement the balanceHelper() method
    Iterator<T> iter = inorderIterator(); 
    T[] values = (T[]) new Comparable[size()];
    int i = 0; 
    while(iter.hasNext()){
      values[i] = iter.next();
      i++;
    }
    root = balanceHelper(values, 0, values.length-1);
    
  }
  //helper method for balance 
  private BSTNode<T> balanceHelper(T values[], int low, int high){
 
    if(low > high){
      return null;
    }
    int mid = (low+high)/2; 
    BSTNode<T> newNode = new BSTNode<T>(values[mid], balanceHelper(values, low, (mid-1)), balanceHelper(values, (mid+1), high));
    return newNode;
    
  }


  @Override
  public BSTNode<T> getRoot() {
    // DO NOT MODIFY
    return root;
  }

  public static <T extends Comparable<T>> String toDotFormat(BSTNode<T> root) {
    // header
    int count = 0;
    String dot = "digraph G { \n";
    dot += "graph [ordering=\"out\"]; \n";
    // iterative traversal
    Queue<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();
    queue.add(root);
    BSTNode<T> cursor;
    while (!queue.isEmpty()) {
      cursor = queue.remove();
      if (cursor.getLeft() != null) {
        // add edge from cursor to left child
        dot += cursor.getData().toString() + " -> " + cursor.getLeft().getData().toString() + ";\n";
        queue.add(cursor.getLeft());
      } else {
        // add dummy node
        dot += "node" + count + " [shape=point];\n";
        dot += cursor.getData().toString() + " -> " + "node" + count + ";\n";
        count++;
      }
      if (cursor.getRight() != null) {
        // add edge from cursor to right child
        dot +=
            cursor.getData().toString() + " -> " + cursor.getRight().getData().toString() + ";\n";
        queue.add(cursor.getRight());
      } else {
        // add dummy node
        dot += "node" + count + " [shape=point];\n";
        dot += cursor.getData().toString() + " -> " + "node" + count + ";\n";
        count++;
      }
    }
    dot += "};";
    return dot;
  }

  public static void main(String[] args) {
    for (String r : new String[] {"a", "b", "c", "d", "e", "f", "g"}) {
      BSTInterface<String> tree = new BinarySearchTree<String>();
      for (String s : new String[] {"d", "b", "a", "c", "f", "e", "g"}) {
        tree.add(s);
      }
      Iterator<String> iterator = tree.inorderIterator();
      while (iterator.hasNext()) {
        System.out.print(iterator.next());
      }
      System.out.println();
      iterator = tree.preorderIterator();
      while (iterator.hasNext()) {
        System.out.print(iterator.next());
      }
      System.out.println();
      iterator = tree.postorderIterator();
      while (iterator.hasNext()) {
        System.out.print(iterator.next());
      }
      System.out.println();

      System.out.println(tree.remove(r));

      iterator = tree.inorderIterator();
      while (iterator.hasNext()) {
        System.out.print(iterator.next());
      }
      System.out.println();
    }

    BSTInterface<String> tree = new BinarySearchTree<String>();
    for (String r : new String[] {"a", "b", "c", "d", "e", "f", "g"}) {
      tree.add(r);
    }
    System.out.println(toDotFormat(tree.getRoot()));
    System.out.println(tree.size());
    System.out.println(tree.height());
    System.out.println(tree.isBalanced());
    tree.balance();
    System.out.println(tree.size());
    System.out.println(tree.height());
    System.out.println(tree.isBalanced());
  }
}
