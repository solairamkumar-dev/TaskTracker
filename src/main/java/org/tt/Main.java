package org.tt;
import org.tt.service.TaskManager;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // TaskTracker tracker = getTracker();
        TaskManager manager = new TaskManager();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Task Tracker CLI");
        System.out.println("Type 'help' for a list of commands.");

        while(true) {
            System.out.println(">");
            String input = scanner.nextLine();
            String[] inputValues = input.split(" ", 2);

            if (inputValues.length < 1) {
                System.out.println("Usage: <command> [<args>]");
                continue;
            }

            String jobName = inputValues[0];
            switch (jobName) {
                case "add":
                    if (inputValues.length < 2) {
                        System.out.println("Usage : add <description>");
                    } else {
                        manager.addTask(inputValues[1]);
                    }
                    break;
                case "update":
                    if (inputValues.length < 2) {
                        System.out.println("Usage: update <id> <description>");
                    } else {
                        String[] updateValues = inputValues[1].split(" ", 2);
                        if (updateValues.length < 2) {
                            System.out.println("Usage: update <id> <description>");
                        } else {
                            manager.updateTask(Integer.parseInt(updateValues[0]), updateValues[1]);
                        }

                    }
                    break;
                case "delete":
                    if (inputValues.length < 2) {
                        System.out.println("Usage: delete <id>");
                    } else {
                        manager.deleteTask(Integer.parseInt(inputValues[1]));
                    }
                    break;
                case "mark-in-progress":
                    if (inputValues.length < 2) {
                        System.out.println("Usage: mark-in-progress <id>");
                    } else {
                        manager.markTask(Integer.parseInt(inputValues[1]), "in-progress");
                    }
                    break;
                case "mark-done":
                    if (inputValues.length < 2) {
                        System.out.println("Usage: mark-done <id>");
                    } else {
                        manager.markTask(Integer.parseInt(inputValues[1]), "done");
                    }
                    break;
                case "list":
                    if (inputValues.length < 2) {
                        manager.listTasks(null);
                    } else {
                        manager.listTasks(inputValues[1]);
                    }
                    break;
                case "exit":
                    System.out.println("Exiting Task Tracker CLI.");
                    scanner.close();
                    return;
            }
        }


    }
}