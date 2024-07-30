import java.util.ArrayList;
import java.util.Scanner;

public class ToDoList {
    Task headTask;
    int size = 0;
    static ArrayList<String> Categories = new ArrayList<>();

    class Task {
        String taskName;
        String description;
        ArrayList<String> Category;
        boolean completed;
        Task Next; //linked list of tasks

        //subtasks
        ToDoList subtasks = new ToDoList();

        //constructors
        Task(String taskName, String description, ArrayList<String> Category, boolean completed) {
            this.taskName = taskName;
            this.description = description;
            this.Category = Category;
            this.completed = completed;
        }

        Task(String taskName, String description, ArrayList<String> Category, boolean completed, ToDoList subtasks) {
            this.taskName = taskName;
            this.description = description;
            this.Category = Category;
            this.completed = completed;
            this.subtasks.headTask = subtasks.headTask;
        }

    }

    // Inserting the task with description and subtask and category
    public Task add() throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.println("\nEnter the name of Task : ");
        String taskName = sc.nextLine();

        System.out.println("Enter the description for task : ");
        String desc = sc.nextLine();

        ArrayList<String> cat = chooseCat();

        size++;
        return insertTask(taskName, desc, cat, false);
    }

    public Task addSubTasks(int index) throws Exception {
        Task node = headTask;
        int count = 0;
        while (count < index) {
            node = node.Next;
            count++;
        }
        node.subtasks.headTask = node.subtasks.add();
        return headTask;
    }

    private Task insertTask(String taskName, String desc, ArrayList<String> cat, boolean check) {
        if (headTask == null) {
            headTask = new Task(taskName, desc, cat, check);
        } else {
            Task node = headTask;
            while (node.Next != null) {
                node = node.Next;
            }
            node.Next = new Task(taskName, desc, cat, check);
        }

        return headTask;
    }

    //choosing new category for task
    public ArrayList<String> chooseCat() throws Exception {
        Scanner sc = new Scanner(System.in);

        ArrayList<String> chosenCat = new ArrayList<>();

        System.out.println("Do you want to add Category : ");
        boolean addcat = sc.nextBoolean();

        if (!addcat) {
            return chosenCat;
        } else {
            displayCategories();

            System.out.println("Enter the category Name : ");
            String catName = sc.next();
            chosenCat.add(catName);

            if (Categories.isEmpty() || Categories.indexOf(catName) == -1) {
                addNewCategory(catName);
            }
        }

        chosenCat.addAll(chooseCat());
        return chosenCat;
    }

    // deleting category for task
    public Task deleteCategory(int taskIndex, int CatIndex) {
        Task node = headTask;
        int count = 0;
        while (count < taskIndex) {
            node = node.Next;
            count++;
        }
        node.Category.remove(CatIndex);

        return headTask;
    }

    // adding new category to global category
    public void addNewCategory(String name) throws Exception {
        if (Categories.isEmpty()) {
            Categories.add(name);
        } else if (Categories.indexOf(name) == -1) {
            Categories.add(name);
        } else {
            throw new Exception("Category already exists!");
        }
    }

    // display global category
    public void displayCategories() {
        System.out.println("Categories : " + Categories);
    }

    // deleting global category
    public void deleteCategory(int index) throws Exception {
        if (index >= Categories.size()) throw new Exception("Index out of Bound!");
        Categories.remove(index);
    }

    // Deleting the task
    public Task deleteTask(int index) throws Exception {
        if (size <= index) throw new Exception(("Task not found!"));
        else {
            if (index == 0) {
                headTask = headTask.Next;
                return headTask;
            }

            int count = 0;

            Task node = headTask;
            Task prevNode = null;
            while (count < index) {
                prevNode = node;
                node = node.Next;
                count++;
            }

            prevNode.Next = node.Next;
        }
        size--;
        return headTask;
    }

    // check the task
    public Task markComplete(int index) throws Exception {

        if (size <= index) throw new Exception(("Task not found!"));

        int count = 0;

        Task node = headTask;
        while (count < index) {
            node = node.Next;
            count++;
        }

        node.completed = true;

        return headTask;
    }

    // uncheck task
    public Task unmarkComplete(int index) throws Exception {
        if (size <= index) throw new Exception(("Task not found!"));

        int count = 0;

        Task node = headTask;
        while (count < index) {
            node = node.Next;
            count++;
        }

        node.completed = false;

        return headTask;
    }

    // Display the list of task
    public void displayTasks() {
        System.out.println("\nList of Tasks : \n");
        Task node = headTask;
        int count = 0;
        while (node != null) {
            System.out.print((count + 1) + ". ");
            System.out.print(node.taskName);
            if (node.completed) {
                System.out.print("\t(checked)");
            } else {
                System.out.print("\t(unchecked)");
            }
            System.out.println();

            node = node.Next;
            count++;
        }
    }

    // Detailed display of tasks
    public void detailedDisplayTasks() {
        System.out.println("\nList of Tasks : \n");
        Task node = headTask;
        int count = 0;
        while (node != null) {
            System.out.println((count + 1) + ". " + "Task Name : " + node.taskName);
            String status = node.completed ? "checked" : "unchecked";
            System.out.println("Status : " + status);
            System.out.println("Description : " + node.description);
            System.out.println("Categories : " + node.Category);

            System.out.println();

            node = node.Next;
            count++;
        }
    }

    // display task and subtasks
    public void displayTasksAndSubTasks() {
        System.out.println();
        displayTasksSubTasks(0);
    }

    private void displayTasksSubTasks(int level) {
        for (int i = 0; i < level; i++) {
            System.out.print("\t");
        }
        System.out.print("List of ");
        if (level > 0) System.out.print("Subtasks");
        else System.out.print("Tasks");
        System.out.print(" : \n");
        Task node = headTask;
        int count = 0;
        while (node != null) {
            for (int i = 0; i < level; i++) {
                System.out.print("\t");
            }
            System.out.print((count + 1) + ". ");
            System.out.print(node.taskName);
            if (node.completed) {
                System.out.print("\t(checked)\n");
            } else {
                System.out.print("\t(unchecked)\n");
            }
            if (node.subtasks.headTask != null) {
                ToDoList temp = new ToDoList();
                temp.headTask = node.subtasks.headTask;
                temp.displayTasksSubTasks(level + 1);
            }
            node = node.Next;
            count++;
        }
    }

    // to access the sublist of particular task
    public ToDoList getSubList(int index) {
        Task node = headTask;
        int count = 0;
        while (count < index) {
            node = node.Next;
            count++;
        }
        return node.subtasks;
    }

    // Filter by categories (returns new ToDoList)
    public void categorizeTasks(String catName){
        categorizeTasksHelper(catName).displayTasks();
    }
    private ToDoList categorizeTasksHelper(String catName) {
        ToDoList catList = new ToDoList();

        Task node = this.headTask;
        while (node != null) {
            if (node.Category.indexOf(catName) != -1) {
                catList.insertTask(node.taskName, node.description, node.Category, node.completed);
            }
            node = node.Next;
        }

        return catList;
    }

    // Filter completed and uncompleted tasks (returns new ToDoList)
    public void categorizeTasksCompleted(Boolean check){
        categorizeTasksCompletedHelper(check).displayTasks();
    }
    public ToDoList categorizeTasksCompletedHelper(Boolean check) { //categorize by completed(true) or uncompleted(false)
        ToDoList catList = new ToDoList();

        Task node = this.headTask;
        while (node != null) {
            if (node.completed == check) {
                catList.insertTask(node.taskName, node.description, node.Category, node.completed);
            }
            node = node.Next;
        }

        return catList;
    }

    // Display progress
    public float displayProgress() {
        float count = 0;
        Task node = headTask;
        while (node != null) {
            if (node.completed) {
                count += 1;
            } else if (node.subtasks.headTask != null) {
                count += node.subtasks.displayProgress() / 100;
            }
            node = node.Next;
        }
        return (count / (float) size) * 100;
    }
}