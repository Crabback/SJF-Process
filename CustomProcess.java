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

/**
 * This class represents the data type for the processes used in our application.
 * 
 * @author Zhengjia Mao
 *
 */
public class CustomProcess implements Comparable<CustomProcess> {

  private static int nextProcessId = 1; // stores the id to be assigned to the next process
  // to be created
  private final int PROCESS_ID; // unique identifier for this process
  private int burstTime; // time required by this process for CPU execution

  /**
   * Constructor of the CustomProcess class
   * 
   * @param burstTime - the user-defined time required by this process for CPU execution
   * @throws IllegalArgumentException if the given burstTime is zero or negative
   */
  public CustomProcess(int burstTime) {
    if (burstTime <= 0) { //detect the exception
      throw new IllegalArgumentException("should be non-zero, positive");
    }
    this.burstTime = burstTime;
    PROCESS_ID = nextProcessId;
    nextProcessId++;
  }

  /**
   * Accessor to get the process id
   * 
   * @return the unique identifier for this process
   */
  public int getProcessId() {
    return PROCESS_ID;
  }

  /**
   * Accessor to get the burst time
   * 
   * @return the time required by this process for CPU execution
   */
  public int getBurstTime() {
    return burstTime;
  }


  /**
   * Returns a String representation of this CustomProcess
   * 
   * @return a string representation of this CustomProcess
   */
  @Override
  public String toString() {
    return "p" + PROCESS_ID + "(" + burstTime + ")";
  }

  /**
   * Compare the given CustomProcess to the current one
   * 
   * @param other - the other process to compare
   * @return < 0 if this is smaller than other; 0 if equal; > 0 if this is bigger than other
   */
  public int compareTo(CustomProcess other) {
    int difference = this.burstTime - other.getBurstTime();
    //if the burst time is the same, compare process id
    if (difference == 0) {
      return this.PROCESS_ID - other.getProcessId();
    } else {
      return difference;
    }
  }



}
