package language; 

/**
 * A {@link BinaryOperator} is an {@link Operator} that performs an operation on two arguments.
 *
 * @param <T> they type of the {@link Operand} being evaluated
 */
public abstract class UnaryOperator<T> implements Operator<T> {
  
  protected Operand<T> op0;
  //protected Operand<T> op1;

  /** {@inheritDoc} */
  @Override
  public final int getNumberOfArguments() {
    return 1; 
  }
  
  /** {@inheritDoc} */
  @Override
  public void setOperand(int i, Operand<T> operand) {
    if (operand == null) throw new NullPointerException("Could not set null operand.");
    if (i > 0)
      throw new IllegalArgumentException(
        "Unary operator only accepts operand 0 but recieved " + i + ".");
    if (i == 0) {
      if (op0 != null)
        throw new IllegalStateException("Position " + i + " has been previously set.");
      op0 = operand;
    } 
  }
}
