// FormRecord.kt
data class FormRecord(
    val formType: FormType,
    override var status: String,  // Use override here
    override var content: String,
    override var approver: String,
    override var submitter: String,
    override var submissionDate: String
) : BaseForm(status, content, approver, submitter, submissionDate)
