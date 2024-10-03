package edu.grinnell.csc207.util;

import static java.lang.reflect.Array.newInstance;

/**
 * A basic implementation of Associative Arrays with keys of type K and values of type V.
 * Associative Arrays store key/value pairs and permit you to look up values by key.
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public class AssociativeArray<K, V> {
  // +-----------+---------------------------------------------------
  // | Constants |
  // +-----------+

  /** The default capacity of the initial array. */
  static final int DEFAULT_CAPACITY = 16;

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /** The size of the associative array (the number of key/value pairs). */
  int size;

  /** The array of key/value pairs. */
  KVPair<K, V>[] pairs;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /** Create a new, empty associative array. */
  @SuppressWarnings({"unchecked"})
  public AssociativeArray() {
    this.pairs = (KVPair<K, V>[]) newInstance(KVPair.class, DEFAULT_CAPACITY);
    this.size = 0;
  } // end of constructor

  // +------------------+--------------------------------------------
  // | Standard Methods |
  // +------------------+

  /**
   * Create a copy of this AssociativeArray.
   *
   * @return a new copy of the array
   */
  public AssociativeArray<K, V> clone() {
    AssociativeArray<K, V> copy = new AssociativeArray<>();

    // Copy each key-value pair (deep copy)
    copy.pairs = (KVPair<K, V>[]) newInstance(KVPair.class, pairs.length);
    for (int i = 0; i < this.size; i++) {
      copy.pairs[i] =
          new KVPair<>(this.pairs[i].key, this.pairs[i].val); // Deep copy of each KVPair
    } //end of for loop

    // Copy the size
    copy.size = this.size;

    return copy;
  } // end of clone method

  /**
   * Convert the array to a string.
   *
   * @return a string of the form "{Key0:Value0, Key1:Value1, ... KeyN:ValueN}"
   */
  public String toString() {
    StringBuilder result = new StringBuilder("{");
    for (int i = 0; i < this.size; i++) {
      result.append(pairs[i].toString());
      if (i < this.size - 1) {
        result.append(", ");
      } //end of if statement
    } //end of for loop
    result.append("}");
    return result.toString();
  } // end of toString method

  // +----------------+----------------------------------------------
  // | Public Methods |
  // +----------------+

  /**
   * Set the value associated with key to value. Future calls to get(key) will return value.
   *
   * @param key The key whose value we are setting.
   * @param value The value of that key.
   * @throws NullKeyException If the client provides a null key.
   */
  public void set(K key, V value) throws NullKeyException {
    if (key == null) {
      throw new NullKeyException("Key cannot be null");
    } //end of if statement

    // Look for the key and update the value if found
    for (int i = 0; i < size; i++) {
      if (pairs[i].key.equals(key)) {
        pairs[i].val = value;
        return;
      } //end of if statement
    } //end of for loop

    // If the array is full, expand it
    if (size >= pairs.length) {
      expand();
    } //end of if statement

    // Add the new key-value pair
    pairs[size] = new KVPair<>(key, value);
    size++;
  } // end of set method

  /**
   * Get the value associated with key.
   *
   * @param key A key
   * @return The corresponding value
   * @throws KeyNotFoundException when the key does not appear in the associative array.
   */
  public V get(K key) throws KeyNotFoundException {
    for (int i = 0; i < size; i++) {
      if (pairs[i].key.equals(key)) {
        return pairs[i].val;
      } //end of if statement
    } //end of for loop
    throw new KeyNotFoundException("Key not found: " + key);
  } // end of get method

  /**
   * Determine if key appears in the associative array.
   *
   * @param key The key we're looking for.
   * @return true if the key appears and false otherwise.
   */
  public boolean hasKey(K key) {
    if (key == null) {
      return false;
    } //end of if statement
    for (int i = 0; i < size; i++) {
      if (pairs[i].key.equals(key)) {
        return true;
      } // end of if statement
    } //end of for loop
    return false;
  } // end of hasKey method

  /**
   * Remove the key/value pair associated with a key.
   *
   * @param key The key to remove.
   */
  public void remove(K key) {
    for (int i = 0; i < size; i++) {
      if (pairs[i].key.equals(key)) {
        // Shift the remaining elements to fill the gap
        for (int j = i; j < size - 1; j++) {
          pairs[j] = pairs[j + 1];
        } //end of for loop
        pairs[size - 1] = null; // Clear the last entry
        size--; // Reduce the size of the array
        return;
      } //end of is statement
    } //end of for loop
  } // end of remove method

  /**
   * Determine how many key/value pairs are in the associative array.
   *
   * @return The number of key/value pairs in the array.
   */
  public int size() {
    return this.size;
  } // end of size method

  // +-----------------+---------------------------------------------
  // | Private Methods |
  // +-----------------+

  /** Expand the underlying array to accommodate more elements. */
  void expand() {
    // Remove this println statement or replace with proper logging if necessary.
    this.pairs = java.util.Arrays.copyOf(this.pairs, this.pairs.length * 2);
  } // end of expand method
} // end of class AssociativeArray
