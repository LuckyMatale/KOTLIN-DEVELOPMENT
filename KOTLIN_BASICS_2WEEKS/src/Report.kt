// Object tracking form statistics
object Report {
    var totalSubmitted = 0
    private var totalApproved = 0
    private var totalRejected = 0

    fun displayReport() {
        println("==== Form Report ====")
        println("Total Forms Submitted: $totalSubmitted")
        println("Total Forms Approved: $totalApproved")
        println("Total Forms Rejected: $totalRejected")
        println("=====================")
    }
}