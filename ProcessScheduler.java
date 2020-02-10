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

import java.util.Scanner;

/**
 * This class represents the data type for the main scheduler for our processes
 * 
 * @author Zhengjia Mao
 *
 */
public class ProcessScheduler {

  private int currentTime; // stores the current time after the last run
  private int numProcessesRun; // stores the number of processes run so far
  private WaitingProcessQueue queue; // this processing unit’s queue

  // Strings to be printed out
  private static String PROMPT_WELCOME =
      "========== Welcome to the SJF Process Scheduler App ========\n";
  private static String PROMPT_MENU =
      "Enter command: \n[schedule <burstTime>] or [s <burstTime>] \n[run] or [r] \n[quit] or [q]\n";
  private static String WARNING_VALID = "WARNING: Please enter a valid command!\n";
  private static String WARNING_INTEGER = "WARNING: burst time MUST be an integer!\n";
  private static String PROMPT_END = "Thank you for using our scheduler!\nGoodbye!";

  /**
   * Empty constructor that initialize the values
   * 
   */
  public ProcessScheduler() {
    currentTime = 0;
    numProcessesRun = 0;
    queue = new WaitingProcessQueue();
  }


  /**
   * This method inserts the given process in the WaitingProcessQueue queue.
   * 
   * @param process
   */
  public void scheduleProcess(CustomProcess process) {
    queue.insert(process);
    System.out.println("Process ID " + process.getProcessId() + " scheduled. Burst Time = "
        + process.getBurstTime() + "\n");
  }

  /**
   * runs the ready processes already scheduled in this processScheduler’s queue
   * 
   * @return the String to be printed out
   */
  public String run() {
    String log = "";

    if (queue.size() <= 1) {
      log += "Starting " + queue.size() + " process\n\n";
    } else {
      log += "Starting " + queue.size() + " processes\n\n";
    }

    while (!queue.isEmpty()) {
      CustomProcess next = queue.removeBest();
      log += "Time " + currentTime + " : Process ID " + next.getProcessId() + " Starting.\n";
      currentTime += next.getBurstTime();
      numProcessesRun++;
      log += "Time " + currentTime + " : Process ID " + next.getProcessId() + " Completed.\n";
    }
    log += "\nTime " + currentTime + ": All scheduled processes completed.\n";
    
    return log;
  }

  /**
   * The driver method
   * 
   * @param scnr - Scanner with System.in
   * @param ps   - the given ProcessScheduler
   */
  public static void driver(Scanner scnr, ProcessScheduler ps) {
    boolean isTerminated = false;
    System.out.println(PROMPT_WELCOME);
    while (!isTerminated) {
      System.out.println(PROMPT_MENU);
      isTerminated = processCommand(scnr, ps); 
      //"quit" returns true, end the while loop
    }
    System.out.print(ps.numProcessesRun + " processes run in " + ps.currentTime + " units of time!\n" + PROMPT_END);
  }

  /**
   * Take commands and process them
   * 
   * @param scn - Scanner with System.in
   * @param ps  - the given ProcessScheduler
   * @return
   */
  private static boolean processCommand(Scanner scn, ProcessScheduler ps) {
    String[] input = scn.nextLine().trim().toLowerCase().split(" +"); // read user command line
    String cmd = String.valueOf(input[0]);


    switch (cmd) {

      case "s":
        try {
          Integer bust = Integer.parseInt(input[1]);
          ps.scheduleProcess(new CustomProcess(bust));
        } catch (Exception e) { //catch the exception
          System.out.println(WARNING_INTEGER);
        }
        break;
        
      case "schedule":
        try {
          Integer bust2 = Integer.parseInt(input[1]);
          ps.scheduleProcess(new CustomProcess(bust2));
        } catch (Exception e) { //catch the exception
          System.out.println(WARNING_INTEGER);
        }
        break;

      case "r":
        String log = ps.run();
        System.out.println(log);
        break;
        
      case "run":
        String log2 = ps.run();
        System.out.println(log2);
        break;

      case "quit": // break
        return true;
        
      case "q": // break
        return true;

      default:
        System.out.println(WARNING_VALID);
        break;
    }
    return false;
  }

  /**
   * Run the driver
   * 
   * @param args
   */
  public static void main(String[] args) {
    driver(new Scanner(System.in), new ProcessScheduler());
  }

}
