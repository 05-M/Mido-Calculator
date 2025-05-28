data class Task(val description: String, var isCompleted: Boolean = false)
val tasks = mutableListOf<Task>()

fun addTask(){
    print("Enter the name of the new task: ")
    val taskDescriptionInput = readLine()

    if(taskDescriptionInput.isNullOrBlank()){
        println("Task description cannot be empty. Task not added")
    } else {
        val newTask = Task(description = taskDescriptionInput)
        tasks.add(newTask)
        println("Task ${newTask.description} added successfully!")
    }
}

fun viewTasks() {
    val isEmpty = tasks.isEmpty()
    if (isEmpty) {
        println("\n--- Your To-Do List ---")
        println("Hooray! No tasks to do. Time to relax! 🥳")
        println("-----------------------")
    } else {
        println("\n--- Your To-Do List ---")
        for ((index, taskObject) in tasks.withIndex()) {
            val status = if (taskObject.isCompleted) "[DONE]" else "[PENDING]"
            println("${index + 1}. ${taskObject.description} - $status")
            println("-----------------------")
        }
    }
}
    fun markTaskAsComplete() {
        if (tasks.isEmpty()) {
            println("\nNo tasks to mark as complete. Your list is empty. Add some tasks first! 👍")
            return
        }

        println("\n--- Mark a Task as Complete ---")
        viewTasks()

        print("Enter the number of the task you want to mark as complete: ")
        val taskNumberInput = readLine()
        val taskNumber = taskNumberInput?.toIntOrNull()

        if (taskNumber == null) {
            println("Invalid input. Please enter a number.")
            return
        }

        val taskIndex = taskNumber - 1

        if (taskIndex >= 0 && taskIndex < tasks.size) {
            val taskToMark = tasks[taskIndex]
            if (taskToMark.isCompleted) {
                println("Task \"${taskToMark.description}\" (number $taskNumber) is already marked as complete.")
            } else {
                taskToMark.isCompleted = true
                println("Task \"${taskToMark.description}\" (number $taskNumber) marked as complete. Well done! ✅")
            }
        } else {
            println("Invalid task number. No task with number $taskNumber exists.")
        }
    }

    fun removeTask() {
        if (tasks.isEmpty()) {
            println("\nNo tasks to remove. Your list is already empty! 👍")
            return
        }
        println("\n--- Remove a Task ---")
        viewTasks()

        print("Enter the number of the task you want to remove: ")
        val taskNumberInput = readLine()
        val taskNumber = taskNumberInput?.toIntOrNull()

        if (taskNumber == null) {
            println("Invalid input. Please enter a number.")
            return
        }

        val taskIndex = taskNumber - 1
        if (taskIndex >= 0 && taskIndex < tasks.size) {
            val removedTaskObject = tasks.removeAt(taskIndex)
            println("Task \"${removedTaskObject.description}\" (number $taskNumber) removed successfully.")
        } else {
            println("Invalid task number. No task with number $taskNumber exists.")
        }
    }

fun main() {
        println("--- Welcome to Mido's Simple To-Do List App! ---")

        while (true) {
            println("\nWhat would you like to do?")
            println("1. Add a new task")
            println("2. View all tasks")
            println("3. Mark a task as complete")
            println("4. Remove a task")
            println("5. Exit")
            print("Enter your choice (1-5): ")

            val choiceInput = readLine()
            val choice = choiceInput?.toIntOrNull()

            when (choice) {
                1 -> {
                    addTask()
                }

                2 -> {
                    viewTasks()
                }

                3 -> {
                    markTaskAsComplete()
                }

                4 -> {
                    removeTask()
                }

                5 -> {
                    println("Goodbye!")
                    return
                }

                else -> {
                    println("Invalid choice. Please enter a number between 1 and 5.")
                }
            }
        }
    }