data class Book(
    val title:String,
    val author:String,
    val publicationYear:Int,
    var isBorrowed: Boolean = false
) {
    fun displayDetails(){
        println("Title: $title")
        println("Author: $author")
        println("Year: $publicationYear")
        println("Status:${if (isBorrowed) "Borrowed" else "Available"}")
        println("-----")
    }
}


data class Member(
    val name:String,
    val memberId:String,
    val borrowedBooks: MutableList<Book> = mutableListOf()
){

    fun displayMemberInfo() {
        println("\n--- Member Information ---")
        println("Name: $name")
        println("Member ID: $memberId")
        if(borrowedBooks.isEmpty()){
            println("Borrowed Books: None")
        } else {
            println("Borrowed Books:")
            borrowedBooks.forEachIndexed { index,book ->
                println("  ${index + 1}. ${book.title} by ${book.author}")
            }
        }
        println("------------------------")
    }

    fun borrowBook(book: Book){
        if(book.isBorrowed){
            println("Sorry, '${book.title}' is already borrowed by someone else.")
        } else{
            book.isBorrowed = true
            borrowedBooks.add(book)
            println("'${book.title}' borrowed successfully by $name.")
        }
    }

    fun returnBook(book:Book){
        if(borrowedBooks.contains(book)){
            book.isBorrowed = false
            borrowedBooks.remove(book)
            println("'${book.title}' returned successfully by $name.")
        } else {
            println("Error: '$name' did not borrow '${book.title}'. Cannot return.")
        }
    }
}

class Library(
    val availableBooks: MutableList<Book> = mutableListOf(),
    val registeredMembers: MutableList<Member> = mutableListOf(),
    val name: String
){
    fun addBookToLibrary(book:Book){
            availableBooks.add(book)
        println("Book '${book.title}' added to the library: $name.")
    }

    fun registerNewMember(member: Member){
        registeredMembers.add(member)
        println("Member '${member.name}' (ID: ${member.memberId}) registered successfully at $name library.")
    }

    fun lendBook(memberIdToSearch: String, bookTitleToSearch: String){
        println("\n--- Attempting to lend a book ---")

        val member = registeredMembers.find { it.memberId == memberIdToSearch }
        if(member == null){
            println("Error: Member with ID '$memberIdToSearch' not found.")
            return
        }

        val book = availableBooks.find { it.title.equals( bookTitleToSearch, ignoreCase = true) }
        if(book == null){
            println("Error: Book with title '$bookTitleToSearch' not found in the library.")
            return
        }

        member.borrowBook(book)
    }

    fun acceptBookReturn(memberIdToSearch: String, bookTitleToSearch: String){
        println("\n--- Attempting to return a book ---")

        val member = registeredMembers.find { it.memberId == memberIdToSearch  }
        if(member == null){
            println("Error: Member with ID '$memberIdToSearch' not found.")
            return
        }

        val book = member.borrowedBooks.find { it.title.equals(bookTitleToSearch, ignoreCase = true) }
        if(book == null){
            println("Error: Book with title '$bookTitleToSearch' was not found with member '${member.name}'.")
            return
        }

        member.returnBook(book)
    }

    fun displayAvailableBooks(){
        println("\n--- Available Books in $name ---")
        val currentlyAvailableBooks = availableBooks.filter { !it.isBorrowed }
        if(availableBooks.isEmpty()){
            println("No books currently available for borrowing.")
        } else {
            currentlyAvailableBooks.forEachIndexed{ index,book ->
                println("${index + 1}. ${book.title} by ${book.author} (Year: ${book.publicationYear})")
            }
        }
        println("---------------------------------")
    }

    fun displayRegisteredMembers(){
        println("\n--- Registered Members in $name ---")
        if(registeredMembers.isEmpty()){
            println("No members registered yet.")
        } else {
            registeredMembers.forEachIndexed { index, member ->
                member.displayMemberInfo()
            }
        }
    }

}

fun main(){
    val midoLibrary = Library(name ="Mido's Grand Library")
    println("--- Welcome to ${midoLibrary.name}! ---")

    midoLibrary.addBookToLibrary(Book("The Kotlin Guide", "Kotlin Team", 2023))
    midoLibrary.addBookToLibrary(Book("Data Structures in CS", "Learner C.", 2022))
    midoLibrary.addBookToLibrary(Book("Algorithms Made Easy", "Algo Expert", 2021))

    midoLibrary.registerNewMember(Member("Ali", "A001"))
    midoLibrary.registerNewMember(Member("Sara", "S002"))

    while (true) {
        println("\n--- Library Menu ---")
        println("1. Add New Book to Library")
        println("2. Register New Member")
        println("3. Display Available Books")
        println("4. Display Registered Members")
        println("5. Lend a Book to a Member")
        println("6. Accept Book Return from a Member")
        println("7. Exit")
        print("Enter your choice (1-7): ")

        val choiceInput = readLine()
        val choice = choiceInput?.toIntOrNull()

        when(choice){
            1 -> {
                print("Enter the book title: ")
                val title = readLine().orEmpty()
                print("Enter the book author: ")
                val author = readLine().orEmpty()
                print("Enter publication year: ")
                val year = readLine()?.toIntOrNull()?:0

                if(title.isNotBlank() && author.isNotBlank() && year > 0){
                    midoLibrary.addBookToLibrary(Book(title,author,year))
                }else {
                    println("Invalid book details. Please try again.")
                }
            }
            2 -> {
                print("Enter member name: ")
                val name = readLine().orEmpty()
                print("Enter member ID: ")
                val memberId = readLine().orEmpty()

                if (name.isNotBlank() && memberId.isNotBlank()) {
                    midoLibrary.registerNewMember(Member(name, memberId))
                } else {
                    println("Invalid member details. Please try again.")
                }
            }
            3 ->{ midoLibrary.displayAvailableBooks()}
            4 -> { midoLibrary.displayRegisteredMembers()}
            5 -> { print("Enter Member ID: ")
                val memberId = readLine().orEmpty()
                print("Enter Title of the book to lend: ")
                val bookTitle = readLine().orEmpty()
                if (memberId.isNotBlank() && bookTitle.isNotBlank()) {
                    midoLibrary.lendBook(memberId, bookTitle)
                } else {
                    println("Member ID and Book Title cannot be empty.")
                }
            }
            6 -> {
                print("Enter Member ID: ")
                val memberId = readLine().orEmpty()
                print("Enter Title of the book to return: ")
                val bookTitle = readLine().orEmpty()
                if (memberId.isNotBlank() && bookTitle.isNotBlank()) {
                    midoLibrary.acceptBookReturn(memberId, bookTitle)
                } else {
                    println("Member ID and Book Title cannot be empty.")
                }
            }
            7 -> {
                println("Exiting Library System. Goodbye!")
                return
            }
            else -> println("Invalid choice. Please enter a number between 1 and 7.")
        }
    }
}