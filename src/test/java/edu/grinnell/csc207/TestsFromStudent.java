package edu.grinnell.csc207;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import edu.grinnell.csc207.util.AssociativeArray;
import edu.grinnell.csc207.util.KeyNotFoundException;
import edu.grinnell.csc207.util.NullKeyException;
import org.junit.jupiter.api.Test;

/** A class for writing and running tests on AssociativeArray. */
public class TestsFromStudent {

  /**
   * A test method that always passes.
   *
   * @throws Exception when an unexpected error occurs.
   */
  @Test
  public void alwaysPass() throws Exception {}

  /**
   * Test that checks if setting and getting a key-value pair works.
   *
   * @throws NullKeyException when a null key is used.
   * @throws KeyNotFoundException when trying to access a non-existent key.
   */
  @Test
  public void kimsutest() throws NullKeyException, KeyNotFoundException {
    AssociativeArray<String, String> array = new AssociativeArray<>();

    array.set("Jayson Tatum", "Celtics");

    assertEquals("Celtics", array.get("Jayson Tatum"));
  }

  /**
   * Test for multiple changes to the same key with set.
   *
   * @throws NullKeyException when a null key is used.
   * @throws KeyNotFoundException when trying to access a key that does not exist
   */
  @Test
  public void lastFirstTest1() throws NullKeyException, KeyNotFoundException {
    AssociativeArray<String, String> arr = new AssociativeArray<>();

    // Set key "green" to multiple values
    arr.set("green", "plant");
    assertEquals("plant", arr.get("green"), "First value should be 'plant'");

    // Update value
    arr.set("green", "bug");
    assertEquals("bug", arr.get("green"), "New value should be 'bug'");

    // Update value again
    arr.set("green", "eyes");
    assertEquals("eyes", arr.get("green"), "New value should be 'eyes'");

    // Ensure the size stays the same after updates
    assertEquals(1, arr.size(), "Size should remain 1 after multiple updates.");
  }

  /**
   * Test for adding and verifying multiple key-value pairs in AssociativeArray.
   *
   * @throws NullKeyException when a null key is used.
   * @throws KeyNotFoundException when trying to access a key that does not exist
   */
  @Test
  public void lastFirstTest2() throws NullKeyException, KeyNotFoundException {
    AssociativeArray<Integer, String> arr = new AssociativeArray<>();

    // Add multiple key-value pairs
    arr.set(1, "one");
    arr.set(2, "two");
    arr.set(3, "three");

    // Check that all keys exist and the values are correct
    assertTrue(arr.hasKey(1), "Array should have key '1'.");
    assertEquals("one", arr.get(1), "Value for key '1' should be 'one'.");

    assertTrue(arr.hasKey(2), "Array should have key '2'.");
    assertEquals("two", arr.get(2), "Value for key '2' should be 'two'.");

    assertTrue(arr.hasKey(3), "Array should have key '3'.");
    assertEquals("three", arr.get(3), "Value for key '3' should be 'three'.");
  }

  /** Edge case test for attempting to access a key that is not made yet */
  @Test
  public void lastFirstEdge1() {
    AssociativeArray<String, String> arr = new AssociativeArray<>();

    // Add a key-value pair
    try {
      arr.set("Exists", "banana");

      // Attempt to get a non-existent key
      assertThrows(
          KeyNotFoundException.class,
          () -> arr.get("nonExistentKey"),
          "Expected KeyNotFoundException when trying to access a non-existent key.");

    } catch (NullKeyException e) {
      fail("A valid key should not cause NullKeyException.");
    }
  }
}
