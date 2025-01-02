// Form.kt
import java.text.SimpleDateFormat
import java.util.*

class Form(private var formStatus: String, private var content: String) {
    private lateinit var submissionDate: String

    fun submit() {
        Report.totalSubmitted++

        // Get user role for submission
        val userRole = UserRole()
        userRole.getUserRole()

        // Select Form Type
        println("Select Form Type:")
        FormType.entries.forEachIndexed { index, formType -> println("${index + 1}. $formType") }

        val selectedFormTypeIndex = readlnOrNull()?.toIntOrNull()?.minus(1) ?: 0
        val selectedFormType = FormType.entries.toTypedArray().getOrElse(selectedFormTypeIndex) { FormType.EMPLOYEE_FORM }

        print("Enter form content: ")
        content = readlnOrNull() ?: ""

        submissionDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date())

        // Determine approver based on user role and set status
        val approver = if (userRole.position == "REPRESENTATIVE") "Manager" else "Director"
        getStatus(userRole)

        // Here you could also add logic for adding the new record back to the manager's records if needed.
    }

    private fun getStatus(userRole: UserRole) {
        while (true) {
            print("Is the form complete? (complete/incomplete): ")
            formStatus = readln().trim().lowercase()
            if (formStatus == "complete" || formStatus == "incomplete") {
                break
            } else {
                println("Invalid form status. Please enter 'complete' or 'incomplete'.")
            }
        }
    }
}
