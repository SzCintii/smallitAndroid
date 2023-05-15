import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class Employee(
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var invitationAccepted: Boolean = false
)