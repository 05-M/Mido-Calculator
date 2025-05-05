fun main(){
    println("-------Grade Calculator------")
    println("How many subjects do you have?")

    val numberOfSubjectsInput = readLine()
    val numberOfSubjects = numberOfSubjectsInput?.toIntOrNull() ?: -1

    if(numberOfSubjects <= 0){
        println("Invalid number of subjects, you should enter positive number.")
        return
    }
    println("Okay,you have $numberOfSubjects subjects,let's get their grades. ")

    var totalGrades:Double = 0.0
    var validSubjectsCount = 0

    for (i in 1..numberOfSubjects){
        println("\nSubject#$i:")

        println("Enter subject name: ")
        var subjectNameInput = readLine()


        println("Enter grade for subject #$i: ")
        var subjectGradeInput = readLine()
        var subjectGrade = subjectGradeInput?.toDoubleOrNull()

        if(subjectGrade == null || subjectGrade <0.0 || subjectGrade >100.0){
            println("Invalid grade entered for subject #$i. Please enter a number between 0 and 100. " +
                    "Skipping this subject.")
        } else {
            totalGrades = totalGrades + subjectGrade
            validSubjectsCount++
            println("  Grade $subjectGrade added.")
        }
    }

    println("\n--- Results ---")
    if(validSubjectsCount>0){
        val averageGrade = totalGrades / validSubjectsCount
        println("Number of valid subjects entered: $validSubjectsCount")
        println("Total Grades: $totalGrades")
        println("Average Grade: ${"%.2f".format(averageGrade)}")


    val finalLetterGrade = when {
        averageGrade >= 84 -> "A"
        averageGrade >= 76 -> "B"
        averageGrade >= 68 -> "C"
        averageGrade >= 50 -> "D"
        else -> "F"
    }
    println("Final Letter Grade: $finalLetterGrade")
    } else {
        println("No valid grades were entered to calculate the average.")
    }
}