import java.text.SimpleDateFormat
import java.util.*

fun main() {
    displayMenu()
}

// Enum class representing all types of forms.
enum class FormType {
    EMPLOYEE_FORM, LEAVE_FORM, FEEDBACK_FORM
}

// Class representing the role of the user submitting the form
class UserRole(var position: String = "REPRESENTATIVE") {
    fun getUserRole() {
        print("Who is submitting the form? (Representative/Manager): ")
        position = readln().trim().uppercase() ?: "REPRESENTATIVE"
    }
}

// Class handling form operations and details
class Form(private val formType: FormType, private var formStatus: String, private var content: String) {

    private lateinit var submissionDate: String

    fun getStatus(userRole: UserRole) {
        print("Is the form complete? (complete/incomplete): ")
        formStatus = readln().trim().lowercase()

        when (formStatus) {
            "incomplete" -> handleIncompleteForm()
            "complete" -> handleCompleteForm(userRole)
            else -> println("Invalid form status. Please enter 'complete' or 'incomplete'.")
        }
    }

    private fun handleIncompleteForm() {
        Report.totalRejected++
        if (content.isEmpty()) {
            println("Needs editing: Form content is empty")
        } else {
            println("Needs editing")
        }
    }

    private fun handleCompleteForm(userRole: UserRole) {
        Report.totalApproved++
        val approver = if (userRole.position == "REPRESENTATIVE") "Manager" else "Director"
        println("Approved by $approver on $submissionDate")
    }

    fun submitForm() {
        Report.totalSubmitted++
        val userRole = UserRole()
        userRole.getUserRole()

        println("Select Form Type:")
        FormType.values().forEachIndexed { index, formType -> println("${index + 1}. $formType") }
        val formTypeIndex = readlnOrNull()?.toIntOrNull()?.minus(1) ?: 0
        val selectedFormType = FormType.values().getOrNull(formTypeIndex) ?: FormType.EMPLOYEE_FORM

        print("Enter form content: ")
        content = readlnOrNull() ?: ""

        submissionDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date())

        println("Assigned to ${if (userRole.position == "REPRESENTATIVE") "Manager" else "Director"}")
        getStatus(userRole)

        val approver = if (userRole.position == "REPRESENTATIVE") "Manager" else "Director"
        formRecords.add(FormRecord(selectedFormType, formStatus, content, approver, userRole.position, submissionDate))
    }
}

// Object tracking form statistics
object Report {
    var totalSubmitted = 0
    var totalApproved = 0
    var totalRejected = 0

    fun displayReport() {
        println("==== Form Report ====")
        println("Total Forms Submitted: $totalSubmitted")
        println("Total Forms Approved: $totalApproved")
        println("Total Forms Rejected: $totalRejected")
        println("=====================")
    }
}

// Displays menu options to the user
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
            1 -> Form(FormType.EMPLOYEE_FORM, "", "").submitForm()
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

// Handles searching and filtering of forms
fun searchForm() {
    println("Search and View Options:")
    println("1. Search by Status")
    println("2. Search by Approver")
    println("3. View All Forms")
    val choice = readlnOrNull()?.toIntOrNull() ?: 3

    when (choice) {
        1 -> filterFormsByStatus()
        2 -> filterFormsByApprover()
        3 -> displayFilteredForms(formRecords)
        else -> println("Invalid option!")
    }
}

fun filterFormsByStatus() {
    print("Enter status to filter (complete/incomplete): ")
    val status = readlnOrNull()?.trim()?.lowercase()
    val filteredForms = formRecords.filter { it.status.lowercase() == status }
    displayFilteredForms(filteredForms)
}

fun filterFormsByApprover() {
    print("Enter approver to filter (Manager/Director): ")
    val approver = readlnOrNull()?.trim()?.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    val filteredForms = formRecords.filter { it.approver == approver }
    displayFilteredForms(filteredForms)
}

fun displayFilteredForms(forms: List<FormRecord>) {
    if (forms.isEmpty()) {
        println("No forms found.")
    } else {
        println("=== Filtered Forms ===")
        forms.forEach { form ->
            println("Type: ${form.formType} \nStatus: ${form.status} \nContent: ${form.content} \nApprover: ${form.approver} \nSubmitter: ${form.submitter} \nDate: ${form.submissionDate}")
        }
        println("======================")
    }
}

// Data class representing a record of a form
data class FormRecord(
    val formType: FormType,
    val status: String,
    val content: String,
    val approver: String,
    val submitter: String,
    val submissionDate: String
)

// Stores all submitted form records
val formRecords = mutableListOf<FormRecord>()
