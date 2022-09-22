package structures;

public class ScapegoatTree<T extends Comparable<T>> extends BinarySearchTree<T> {
  private int upperBound = 0; 


  @Override
  public void add(T t) {
    // TODO: Implement the add() method
    if(t == null){
      throw new NullPointerException();
    }
    upperBound++;
  
	 root = addHelper(t, root);
		
		double check = Math.log(upperBound) / Math.log(3.0/2.0);
		
		if (this.height() > check) {
			this.balance();
		}
  } 

  private BSTNode<T> addHelper(T elem, BSTNode<T> curr) {
		if (curr == null) {
			return new BSTNode<T>(elem, null, null);
		}
		if (elem.compareTo(curr.getData()) <= 0) {
			curr.setLeft(addHelper(elem, curr.getLeft()));
		} else {
			curr.setRight(addHelper(elem, curr.getRight()));
		}
		return curr;
  }
  
  @Override
  public boolean remove(T element) {
    // TODO: Implement the remove() method
    if (element == null) {
			throw new NullPointerException();
		}
		boolean result = contains(element);
		if (result) {
			root = removeFromSubtree(root, element);
		}
		if (upperBound > 2*size()) {
			balance();
			upperBound = size();
		}
    return result;
  }
 


  public static void main(String[] args) {
    BSTInterface<Integer> tree = new ScapegoatTree<Integer>();
    
   // You can test your Scapegoat tree here.
    for (int r : new int[] {0, 1, 2, 3, 4}) {
      tree.add(r);
      System.out.println(toDotFormat(tree.getRoot()));
    }
    System.out.println(tree.height()); 
  }
}
