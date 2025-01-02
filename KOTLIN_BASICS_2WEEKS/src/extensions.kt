// Extensions.kt
import java.text.SimpleDateFormat
import java.util.*

fun BaseForm.formattedSubmissionDate(): String {
    return SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date())
}
