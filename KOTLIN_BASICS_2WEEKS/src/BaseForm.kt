// BaseForm.kt
open class BaseForm(
    open var status: String,  // Marked as open for overriding
    open var content: String,
    open var approver: String,
    open var submitter: String,
    open var submissionDate: String
)
