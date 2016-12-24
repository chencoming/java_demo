package others;
public class Exercise8_4 {
  public static void main(String[] args) {
    final int NUM_OF_PRIMES = 30;
    int count = 0;
    StackOfObjects stack = new StackOfObjects();

    // Repeatedly find prime numbers
    for (int number = 2; count < NUM_OF_PRIMES; number++)
      if (isPrime(number)) {
        stack.push(new Integer(number));
        count++; // Increase the prime number count
      }

    // Print the first 30 prime numbers in decreasing order
    System.out.println("The first 30 prime numbers are \n");
    final int NUMBER_PER_LINE = 10;

    while (!stack.empty()) {
      System.out.print(stack.pop() + " ");

      if (stack.getSize() % NUMBER_PER_LINE == 0)
        System.out.println(); // advance to the new line
    }
  }

  public static boolean isPrime(int number) {
    // Assume the number is prime
    boolean isPrime = true;

    // Test if number is prime
    for (int divisor = 2; divisor <= number / 2; divisor++) {
      //If true, the number is not prime
      if (number % divisor == 0) {
        // Set isPrime to false, if the number is not prime
        isPrime = false;
        break; // Exit the for loop
      }
    }

    return isPrime;
  }
}

class StackOfObjects {
  private Object[] elements;
  private int size;

  /** Default constructor */
  public StackOfObjects() {
    this(16);
  }

  /** Construct a stack with the specified maximum capacity */
  public StackOfObjects(int capacity) {
    elements = new Object[capacity];
  }

  /** Push a new integer into the top of the stack */
  public Object push(Object element) {
    if (size >= elements.length) {
      Object[] temp = new Object[elements.length * 2];
      System.arraycopy(elements, 0, temp, 0, elements.length);
      elements = temp;
    }

    return elements[size++] = element;
  }

  /** Return and remove the top element from the stack */
  public Object pop() {
    return elements[--size];
  }

  /** Return the top element from the stack */
  public Object peek() {
    return elements[size - 1];
  }

  /** Test whether the stack is empty */
  public boolean empty() {
    return size == 0;
  }

  /** Return the number of elements in the stack */
  public int getSize() {
    return size;
  }
}
