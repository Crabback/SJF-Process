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
 * Tester class that checks the functionality of the methods
 * 
 * @author Zhengjia Mao
 *
 */
public class ProcessSchedulerTester {

  /**
   * checks the correctness of the insert operation implemented in the WaitingProcessQueue class
   * 
   * @return true if the method works correctly; false otherwise
   */
  public static boolean testInsertWaitingProcessQueue() {
    WaitingProcessQueue q = new WaitingProcessQueue();

    q.insert(new CustomProcess(10));
    q.insert(new CustomProcess(2));
    System.out.println(q);
    if (!q.peekBest().toString().equals("p2(2)"))
      return false;

    q.insert(new CustomProcess(5));
    q.insert(new CustomProcess(3));
    q.insert(new CustomProcess(1));
    System.out.println(q);
    if (!q.peekBest().toString().equals("p5(1)"))
      return false;

    return true;
  }

  /**
   * checks the correctness of the removeBest operation implemented in the WaitingProcessQueue class
   * 
   * @return true if the method works correctly; false otherwise
   */
  public static boolean testRemoveBestWaitingProcessQueue() {
    WaitingProcessQueue q = new WaitingProcessQueue();

    q.insert(new CustomProcess(10));
    q.insert(new CustomProcess(2));
    q.removeBest();
    System.out.println(q);
    if (!q.peekBest().toString().equals("p6(10)"))
      return false;

    q.insert(new CustomProcess(5));
    q.insert(new CustomProcess(3));
    q.insert(new CustomProcess(1));
    q.removeBest();
    System.out.println(q);
    if (!q.peekBest().toString().equals("p9(3)"))
      return false;

    return true;
  }

  /**
   * checks the correctness of the isEmpty operation implemented in the WaitingProcessQueue class
   * 
   * @return true if the method works correctly; false otherwise
   */
  public static boolean testIsEmptyWaitingProcessQueue() {
    WaitingProcessQueue q = new WaitingProcessQueue();
    q.insert(new CustomProcess(10));
    q.removeBest();
    if (!q.isEmpty())
      return false;
    return true;
  }

  /**
   * Checks the correctness of the size operation implemented in the WaitingProcessQueue class
   * 
   * @return true if the method works correctly; false otherwise
   */
  public static boolean testSizeWaitingProcessQueue() {
    WaitingProcessQueue q = new WaitingProcessQueue();
    q.insert(new CustomProcess(10));
    if (q.size() != 1)
      return false;
    q.insert(new CustomProcess(12));
    if (q.size() != 2)
      return false;
    q.removeBest();
    q.removeBest();
    if (q.size() != 0)
      return false;
    return true;
  }

  /**
   * Checks the correctness of the compareTo operation implemented in the WaitingProcessQueue class
   * 
   * @return true if the method works correctly; false otherwise
   */
  public static boolean testCompareToCustomProcess() {
    CustomProcess q1 = new CustomProcess(10);
    CustomProcess q2 = new CustomProcess(5);
    CustomProcess q3 = new CustomProcess(15);
    CustomProcess q4 = new CustomProcess(10); // same burst time but different id
    if (q1.compareTo(q2) <= 0)
      return false;
    if (q1.compareTo(q3) >= 0)
      return false;
    if (q1.compareTo(q4) >= 0)
      return false;
    return true;
  }

  /**
   * Run the tests
   * 
   * @param args
   */
  public static void main(String[] args) {

    System.out.println(testInsertWaitingProcessQueue());
    System.out.println(testRemoveBestWaitingProcessQueue());
    System.out.println(testIsEmptyWaitingProcessQueue());
    System.out.println(testCompareToCustomProcess());
    System.out.println(testSizeWaitingProcessQueue());

  }

}
