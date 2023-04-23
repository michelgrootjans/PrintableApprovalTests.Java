package org.approvaltests.combinations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CombinationTest
{
  private PrintableCombinationApprovals approvals;

  @BeforeEach
  void setUp() {
    approvals = new PrintableCombinationApprovals();
  }

  @Test
  public void testLockDown()
  {
    approvals.verifyAllCombinations((i, s) -> String.format("[%s, %s]", i, s), new Integer[] {1, 2, 3, 4, 5}, new String[] {"a", "b", "c", "d"});
  }

  @Test
  public void testCombinations()
  {
    Integer[] points = new Integer[]{4, 5, 10};
    String[] words = new String[]{"Bookkeeper", "applesauce"};
    approvals.verifyAllCombinations((i, s) -> s.substring(0, i), points, words);
  }
  @Test
  public void test1Parameter()
  {
    approvals.verifyAllCombinations(i -> i * i, new Integer[]{1, 2, 3, 4, 5});
  }
  @Test
  public void testPassMethod()
  {
    approvals.verifyAllCombinations(this::processCall, new Integer[]{1, 2, 3, 4, 5},
        new String[]{"a", "b", "c", "d"});
  }
  public Object processCall(Integer i, String s)
  {
    if (i == 5)
    { throw new RuntimeException("5 is not alive"); }
    if ("d".equals(s))
    { throw new SkipCombination(); }
    return String.format("[%s, %s]", i, s);
  }
  @Test
  void templateCode()
  {
    // begin-snippet: CombinationsStartingPoint
    String[] inputs1 = {"input1.value1", "input1.value2"};
    String[] inputs2 = {"input2.value1", "input2.value2", "input2.value3"};
    approvals.verifyAllCombinations((a, b) -> "placeholder", inputs1, inputs2);
    // end-snippet
  }
  @Test
  void testCombinationsOfTwo()
  {
    // begin-snippet: YouCanVerifyCombinationsOf2
    String[] strings = {"hello", "world"};
    Integer[] numbers = {1, 2, 3};
    approvals.verifyAllCombinations((s, i) -> String.format("(%s,%s)", s, i), strings, numbers);
    // end-snippet
  }
  @Test
  void testCustomPrinter()
  {
    Order[] orders = {new Order(1, "Spaghetti"), new Order(2, "Beer")};
    approvals.registerPrinter(Order.class, order -> String.format("%d %s", order.getQty(), order.getName()));
    approvals.verifyAllCombinations(o -> o, orders);
  }

  private class Order {
    private final int qty;
    private final String name;

    public Order(int qty, String name) {
      this.qty = qty;
      this.name = name;
    }

    public int getQty() {
      return qty;
    }

    public String getName() {
      return name;
    }

    @Override
    public String toString() {
      return "Order{" +
          "qty=" + qty +
          ", name='" + name + '\'' +
          '}';
    }
  }

}