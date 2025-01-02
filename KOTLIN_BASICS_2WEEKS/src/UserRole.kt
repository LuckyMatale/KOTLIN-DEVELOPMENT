// Class representing the role of the user submitting the form
class UserRole(var position: String = "REPRESENTATIVE") {
    fun getUserRole() {
        while (true) { // Loop until a valid input is entered
            print("Who is submitting the form? (Representative/Manager): ")
            val input = readln().trim().uppercase()
            if (input == "REPRESENTATIVE" || input == "MANAGER") {
                position = input
                break
            } else {
                println("Invalid input. Please enter 'Representative' or 'Manager'.")
            }
        }
    }
}