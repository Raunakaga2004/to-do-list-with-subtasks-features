import java.util.Scanner;

public class ToDoListSimulation extends ToDoList{
    ToDoListSimulation() throws Exception {
        simulate();
    }
    public void simulate() throws Exception {
        System.out.println("Program Started ... ");

        ToDoList list = new ToDoList();
        Scanner sc = new Scanner(System.in);

        int taskNum = -1;

        while(true){
            System.out.println("\n\nDo you want to :\n" +
                    "\tadd task - type 'add'\n" +
                    "\tdelete task - type 'delete'\n" +
                    "\tadd category - type 'add cat'\n" +
                    "\tdelete category - type 'delete cat'\n" +
                    "\tdisplay tasks and subtasks - type 'display'\n" +
                    "\tdisplay categories - type 'display cat'\n" +
                    "\tdisplay tasks in detail - type 'display task in detail'\n" +
                    "\tcheck progress - type 'progress'\n" +
                    "\tcategorize task on basis of category - type 'category'\n" +
                    "\tlist of completed or uncompleted tasks - type 'check'\n" +
                    "Close the program - type 'exit'");

            System.out.println("\nType : ");
            String str = sc.nextLine();

            //add task or subtask
            if(str.equals("add")){
                list.add();
                taskNum ++;
                addSubTasks(list, list.headTask.taskName , sc, taskNum);
            }

            //delete task or subtask

            //display task and subtasks
            if(str.equals("display")){
                list.displayTasksAndSubTasks();
            }

            if(str.equals("exit"))break;
        }

        System.out.println("\n\nProgram Ended ... ");
    }

    private void addSubTasks(ToDoList curList, String taskName, Scanner sc, int num) throws Exception {
        sc = new Scanner(System.in);
        String addsub = new String("yes");
        while(addsub.equals("yes")) {
            System.out.println("Do you want to add Subtasks to '" +taskName + "' (type yes or no) : ");
            addsub = sc.next();
            if(addsub.equals("yes")) {
                curList.addSubTasks(num);
            }
        }
    }
}
