import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore

class FirestoreInitializer {
    fun initialize() {

        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp()
        }


        val db = FirebaseFirestore.getInstance()

        val winegrowerProfileCollection = db.collection("My Winegrowerprofile")

        val vineyardsCollection = db.collection("My Vineyards")

        val barrelsCollection = db.collection("Barrels")

        val employeesCollection = db.collection("Employees")


    }
}


data class Employee(
    val firstName: String,
    val lastName: String,
    val email: String,
    val trustedForRegistration: Boolean,
    val invitationAccepted: Boolean
)

class EmployeeDatabase {

    private val employeesCollection = FirebaseFirestore.getInstance().collection("Employees")


    fun addEmployee(employee: Employee) {
        employeesCollection.document("${employee.firstName} ${employee.lastName}").set(employee)
    }


    fun getEmployee(firstName: String, lastName: String): Employee? {
        val document = employeesCollection.document("$firstName $lastName").get().get()
        return if (document.exists()) document.toObject(Employee::class.java) else null
    }


    fun getAllEmployees(): List<Employee> {
        val employees = mutableListOf<Employee>()
        val documents = employeesCollection.get().get().documents
        for (document in documents) {
            employees.add(document.toObject(Employee::class.java))
        }
        return employees
    }
}

// Example usage
fun main() {

    val firestoreInitializer = FirestoreInitializer()
    firestoreInitializer.initialize()


    val employee = Employee("John", "Doe", "johndoe@example.com", true, false)
    val employeeDatabase = EmployeeDatabase()
    employeeDatabase.addEmployee(employee)


    val retrievedEmployee = employeeDatabase.getEmployee("John", "Doe")
    if (retrievedEmployee != null) {
        println("Retrieved employee: $retrievedEmployee")
    } else {
        println("Employee not found")
    }


    val allEmployees = employeeDatabase.getAllEmployees()
    println("All employees:")
    for (employee in allEmployees) {
        println(employee)
    }
}