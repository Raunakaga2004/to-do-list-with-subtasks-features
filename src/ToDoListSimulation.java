import java.util.Scanner;

public class ToDoListSimulation extends ToDoList{
    ToDoListSimulation() throws Exception {

        simulate(new ToDoList());
    }
    public void simulate(ToDoList list) throws Exception {
        System.out.println("Program Started ... ");

//        ToDoList list = new ToDoList();
        Scanner sc = new Scanner(System.in);

        int taskNum = -1;

        while(true){
            System.out.println("\n\nDo you want to :\n" +
                    "\tadd task - type 'add'\n" +  //done
                    "\tdelete task - type 'delete'\n" +  //done
                    "\tadd category - type 'add cat'\n" +
                    "\tdelete category - type 'delete cat'\n" +
                    "\tdisplay tasks and subtasks - type 'display'\n" + //done
                    "\tdisplay categories - type 'display cat'\n" +
                    "\tdisplay tasks in detail - type 'display task in detail'\n" +
                    "\tcheck progress - type 'progress'\n" +
                    "\tcategorize task on basis of category - type 'category'\n" +
                    "\tlist of completed or uncompleted tasks - type 'check'\n" +
                    "\tto access task - type 'task'\n" + //done
                    "Close the program - type 'exit'"); //done

            System.out.println("\nType : ");
            String str = sc.nextLine();

            //add task or subtask
            if(str.equals("add")){
                list.add();
                taskNum ++;
                addSubTasks(list, taskNum);
            }

            //delete task or subtask
            if(str.equals("delete")){
                System.out.println("Enter the number of Task you want to delete : ");
                int num = sc.nextInt() -1;
                list.deleteTask(num);
            }

            //display task and subtasks
            if(str.equals("display")){
                list.displayTasksAndSubTasks();
            }

            //to access the tasks
            if(str.equals("task")){
                System.out.println("Enter the number of task you want to access : ");
                int num = sc.nextInt();
                Task node = list.headTask;
                while(num != 1){
                    node = node.Next;
                    num--;
                }
                simulate(node.subtasks);
            }

            if(str.equals("exit"))break;
        }

        System.out.println("\n\nProgram Ended ... ");
    }

    private void addSubTasks(ToDoList curList, int num) throws Exception {
        Scanner sc = new Scanner(System.in);
        String addsub = new String("yes");
        int index = 0;

        Task node = curList.headTask;
        while(node.Next!=null){
            node =node.Next;
        }
        while(addsub.equals("yes")) {
            System.out.println("Do you want to add Subtasks to '" +node.taskName + "' (type yes or no) : ");
            addsub = sc.next();
            if(addsub.equals("yes")) {
                curList.addSubTasks(num);
                addSubTasks(curList.getSubList(num), index );
                index++;
            }
        }
    }
}
