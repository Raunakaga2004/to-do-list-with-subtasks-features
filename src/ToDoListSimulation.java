import java.security.spec.RSAOtherPrimeInfo;
import java.util.Scanner;

public class ToDoListSimulation extends ToDoList{
    ToDoListSimulation() throws Exception {

        simulate(new ToDoList());
    }
    private void simulate(ToDoList list) throws Exception {
        System.out.println("Program Started ... ");

//        ToDoList list = new ToDoList();
        Scanner sc = new Scanner(System.in);

        int taskNum = -1;

        while(true){
            System.out.println("\n\nDo you want to :\n" +
                    "\tadd task - type 'add'\n" +  //done
                    "\tdelete task - type 'delete'\n" +  //done
                    "\tadd category to category list - type 'addcat'\n" +  //done
                    "\tdelete category from category list- type 'deletecat'\n" + //done
                    "\tdisplay tasks and subtasks - type 'display'\n" + //done
                    "\tdisplay categories - type 'displaycat'\n" + //done
                    "\tdisplay tasks in detail - type 'detaileddisplay'\n" + //done
                    "\tcheck progress - type 'progress'\n" + //done
                    "\tcategorize task on basis of category - type 'category'\n" +
                    "\tlist of completed or uncompleted tasks - type 'check'\n" +
                    "\tto access sublist of task - type 'tasklist'\n" + //done
                    "\tto access the task and its operations - type 'task'\n" + //done
                    "Close the program or Task List - type 'exit'"); //done

            System.out.println("\nType : ");
            String str = sc.next();

            //add task or subtask
            if(str.equals("add")){
                list.add();
                taskNum ++;
                addSubTasks(list, taskNum);
            }

            //add category
            if(str.equals("addcat")){
                System.out.println("Enter the name of Category : ");
                String category = sc.next();
                addNewCategory(category);
            }

            //delete category
            if(str.equals("deletecat")){
                displayCategories();
                System.out.println("Enter the position of Category : ");
                int num = sc.nextInt();
                deleteCategory(num-1);
            }

            //display categories
            if(str.equals("displaycat")){
                displayCategories();
            }

            //display task in detail
            if(str.equals("detaileddisplay")){
                list.detailedDisplayTasks();
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

            //to access the sublist of task
            if(str.equals("tasklist")){
                list.displayTasksAndSubTasks();
                System.out.println("Enter the number of task you want to access : ");
                int num = sc.nextInt();
                Task node = list.headTask;
                while(num != 1){
                    node = node.Next;
                    num--;
                }
                simulate(node.subtasks);
            }

            //to access the task and its operation
            if(str.equals("task")){
                list.detailedDisplayTasks();
                System.out.println("Enter the number of task : ");
                int num = sc.nextInt();

                accessTask(list, num-1);
            }

            //to check progress
            if(str.equals("progress")){
                float pro = list.displayProgress();
                System.out.println("Progress : " + pro);
            }

            //categorize task on the basis of category
            if(str.equals("category")){
                list.displayCategories();
                System.out.println("Enter the category name : ");
                String cat = sc.next();
                list.categorizeTasks(cat);
            }

            //categorize task on the basis of check or uncheck
            if(str.equals("check")){
                System.out.println("Enter 'true' for checked tasks and 'false' for unchecked tasks : ");
                Boolean check = sc.nextBoolean();
                list.categorizeTasksCompleted(check);
                System.out.println("Task list categorized!");
            }

            //to exit the program or Task List
            if(str.equals("exit"))break;
        }

        System.out.println("\n\nProgram Ended ... ");
    }

    private void accessTask(ToDoList list, int index) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("\t\tTo mark the task checked : Enter 'check'\n" + //done
                "\t\tTo mark the task unchecked : Enter 'uncheck'\n" + //done
                "\t\tTo add category to task : Enter 'add cat'\n" + //done
                "\t\tTo remove the category from task : Enter 'remove cat'\n" + //done
                "\t\tTo edit the name : Enter 'name'\n" + //done
                "\t\tTo edit the description : Enter 'desc'\n" + //done
                "\t\tTo exit : Enter 'exit'"); //done
        System.out.println("Enter : ");
        String op = sc.next();

        Task node = list.headTask;
        int i = index;
        while(i != 0){
            node = node.Next;
            i--;
        }

        //mark checked
        if(op.equals("check")){
            list.markComplete(index);
        }

        //mark unchecked
        if(op.equals("uncheck")){
            list.unmarkComplete(index);
        }

        //add category to task
        if(op.equals("addcat")){
            node.Category = chooseCat();
        }

        //delete category from task
        if(op.equals("deletecat")){
            System.out.println(node.Category);
            System.out.println("Enter the position of Category to delete : ");
            int num = sc.nextInt();
            node = deleteCategory(index, num);
        }

        //To edit the name of task
        if(op.equals("name")){
            System.out.println("Enter the new name : ");
            String name = sc.nextLine();
            node.taskName = name;
        }

        //To edit the description of task
        if(op.equals("desc")){
            System.out.println("Enter the new description : ");
            String desc = sc.nextLine();
            node.description = desc;
        }

        //to exit
        if(op.equals("exit")){
            return;
        }
    }

    //adding subtasks
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
