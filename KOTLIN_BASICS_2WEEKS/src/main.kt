// FormManager.kt


class FormManager {
    private val formRecords = mutableListOf<FormRecord>()

    fun displayMenu() {
        val menu = mapOf(
            1 to "Submit a form",
            2 to "View Form report",
            3 to "Search Form",
            4 to "Exit"
        )

        do {
            println("Choose an option:")
            menu.forEach { (key, value) -> println("$key. $value") }

            when (readlnOrNull()?.toIntOrNull()) {
                1 -> submitForm()
                2 -> Report.displayReport()
                3 -> searchForm()
                4 -> {
                    println("Thank you for using the system!")
                    break
                }
                else -> println("Invalid choice. Please try again.")
            }
        } while (true)
    }

    private fun submitForm() {
        val form = Form("", "").apply { submit() }
        // Create a new FormRecord and add it to formRecords here if needed.
    }

    private fun searchForm() {
        // Implement search functionality as described previously.
    }

    // Other methods like filterFormsByStatus, filterFormsByApprover, etc.
}

fun main() {
    val formManager = FormManager()
    formManager.displayMenu()
}
