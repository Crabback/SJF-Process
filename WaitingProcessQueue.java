//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P10 SJF Process Scheduler
// Files: CustomProcess.java, WaitingProcessQueue.java, ProcessScheduler.java,
//////////////////// ProcessSchedulerTester.java
// Course: CS 300
//
// Author: Zhengjia Mao
// Email: zmao27@wisc.edu
// Lecturer's Name: Gary DAHL
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: NONE
// Partner Email: NONE
// Partner Lecturer's Name: NONE
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// _YES__ Write-up states that pair programming is allowed for this assignment.
// _YES__ We have both read and understand the course Pair Programming Policy.
// _YES__ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: NONE
// Online Sources: NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.NoSuchElementException;

/**
 * This class implements the WaitingQueueADT interface of CustomProcesses.
 * 
 * @author Zhengjia Mao
 *
 */
public class WaitingProcessQueue implements WaitingQueueADT<CustomProcess> {

  private static final int INITIAL_CAPACITY = 20; // the initial capacity of this
  // waiting process queue
  private CustomProcess[] data; // min heap-array storing the CustomProcesses
  // inserted in this WaitingProcessQueue.
  // data is an oversize array
  private int size; // number of CustomProcesses stored in this WaitingProcessQueue

  /**
   * Empty costructor that initializes the size and data
   */
  public WaitingProcessQueue() {
    size = 0;
    data = new CustomProcess[INITIAL_CAPACITY];
  }

  /**
   * Inserts a newObject in this waitingProcessQueue.
   * 
   * @param newObject to insert in this waiting queue
   */
  @Override
  public void insert(CustomProcess newObject) {
    if (size == data.length) { // if full, create a temp array
      CustomProcess[] temp = new CustomProcess[data.length * 2];
      for (int i = 0; i < data.length; i++) {
        temp[i] = data[i]; // move the elements to the temp array
      }
      data = temp;
    }
    // otherwise, add to the array and sort
    data[size] = newObject;
    minHeapPercolateUp(size);
    size++;
  }


  /**
   * Removes and returns the process with the highest priority.
   * 
   * @return the removed process
   * @throws NoSuchElementException if this waiting queue is empty
   */
  @Override
  public CustomProcess removeBest() {
    if (isEmpty()) { // detect the exception
      throw new NoSuchElementException("empty queue");
    }
    CustomProcess returnBest = data[0]; // save the return element
    data[0] = data[size - 1];
    minHeapPercolateDown(0); // sort
    data[size - 1] = null;
    size--;
    return returnBest;
  }

  /**
   * Returns without removing the process with the highest priority.
   * 
   * @return the process with the highest priority
   * @throws NoSuchElementException if this waiting queue is empty
   */
  @Override
  public CustomProcess peekBest() {
    if (isEmpty()) { // detect the exception
      throw new NoSuchElementException("empty queue");
    }
    CustomProcess returnBest = data[0]; // save the return element
    return returnBest;
  }

  /**
   * returns size of priority queue
   * 
   * @return the size of priority queue
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * checks whether this waiting queue is empty or not.
   * 
   * @return true if this waiting queue is empty, false otherwise
   */
  @Override
  public boolean isEmpty() {
    return size == 0;
  }


  /**
   * Returns a String representation of this Queue
   * 
   * @return a string representation of this Queue
   */
  @Override
  public String toString() {
    String str = "";
    if (!isEmpty()) {
      for (int i = 0; i < size; i++) {
        str += data[i] + " ";
      }
    } else {
      str = " ";
    }
    return str.trim();
  }

  /**
   * Helper method that swaps the two given elements in the heap
   * 
   * @param i - the index of the first element
   * @param j - the index of the second element
   */
  private void swap(int i, int j) {
    if (i != j) {
      CustomProcess temp = data[i];
      data[i] = data[j];
      data[j] = temp;
    } else {
      // do nothing, skip
    }
  }

  /**
   * percolate up the element at the given index in the data min-heap array
   * 
   * @param index - the parent index
   */
  private void minHeapPercolateUp(int index) {
    while (index > 0) {
      int parentIndex = (index - 1) / 2;
      if (data[index].compareTo(data[parentIndex]) >= 0) {
        return;
      } else {
        swap(index, parentIndex);
        index = parentIndex;
      }
    }
  }

  /**
   * percolate down the element at the given index in the data min-heap array
   * 
   * @param index - the parent index
   */
  private void minHeapPercolateDown(int index) {
    int parentIndex = index;
    int childIndex = index * 2 + 1;
    CustomProcess value = data[parentIndex];
    CustomProcess min;
    int minIndex;
    // loop through the array
    while (childIndex < size) {
      min = value;
      minIndex = -1;
      // Find the min among the node and all the node's children
      for (int i = 0; i < 2 && i + childIndex < size; i++) {
        if (data[i + childIndex].compareTo(min) < 0) {
          min = data[i + childIndex];
          minIndex = i + childIndex;
        }
      }
      // if the min is the same, break the loop
      if (min.compareTo(value) == 0) {
        return;
      } else { // swap the min element and the parent index
        swap(parentIndex, minIndex);
        parentIndex = minIndex;
        childIndex = 2 * parentIndex + 1;
      }
    }
  }

}
