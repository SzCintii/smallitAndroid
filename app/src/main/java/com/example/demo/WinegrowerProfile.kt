import android.util.Log
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

enum class State {
    ACTIVE, INACTIVE
}
enum class Approval{
    Approved, Not_Approved
}

class WinegrowerProfile() {
    var active: State
    var address: String
    var approved: Approval
    var btw: String
    var city: String
    var country: String
    var exciseNumber: String
    var name: String
    var phone: Int
    var id: String

    init {
        active = State.INACTIVE
        address = ""
        approved = Approval.Not_Approved
        btw = ""
        city = ""
        country = ""
        exciseNumber = ""
        name = ""
        phone = 0
        id = ""
    }
}

class WinegrowerPofile {
    private val winegrowerProfileCollection = FirebaseFirestore.getInstance().collection("WinegrowerProfile")

    fun addWinegrowerProfile(winegrowerProfile: WinegrowerProfile) {
        winegrowerProfile.collection.document(winegrowerProfile.name).set(winegrowerProfile)
    }

    fun getWinegrowerProfile(document: DocumentSnapshot?): WinegrowerProfile {

        var winegrowerProfile = WinegrowerProfile()

        winegrowerProfile.active = (document!!.data!!.get("active"))

        winegrowerProfile.address = document!!.data!!.get("address").toString()

        winegrowerProfile.approved = document!!.data!!.get("approved")

        winegrowerProfile.btw = document!!.data!!.get("btw").toString()

        winegrowerProfile.city = document!!.data!!.get("city").toString()

        winegrowerProfile.country = document!!.data!!.get("country").toString()

        winegrowerProfile.exciseNumber = document!!.data!!.get("exciseNumber").toString()

        winegrowerProfile.name = document!!.data!!.get("name").toString()

        winegrowerProfile.phone = document!!.data!!.get("phone") as Int

        winegrowerProfile.id = document!!.data!!.get("id").toString()

        return winegrowerProfile

    }

    fun getWinegrowerProfile(snapshot: QueryDocumentSnapshot): WinegrowerProfile {


        var winegrowerProfile = WinegrowerProfile()

        winegrowerProfile.active = snapshot!!.data!!.get("active")

        winegrowerProfile.address = snapshot!!.data!!.get("address").toString()

        winegrowerProfile.approved = snapshot!!.data!!.get("approved")

        winegrowerProfile.btw = snapshot!!.data!!.get("btw").toString()

        winegrowerProfile.city = snapshot!!.data!!.get("city").toString()

        winegrowerProfile.country = snapshot!!.data!!.get("country").toString()

        winegrowerProfile.exciseNumber = snapshot!!.data!!.get("exciseNumber").toString()

        winegrowerProfile.name = snapshot!!.data!!.get("name").toString()

        winegrowerProfile.phone = snapshot!!.data!!.get("name").toString()

        winegrowerProfile.id = snapshot.id

        return winegrowerProfile

    }

    suspend fun getWinegrowerProfile(): WinegrowerProfile {

        var winegrowerProfile = MutableLiveData<WinegrowerProfile>()
        withContext(Dispatchers.IO) {
            val db = Firebase.firestore
            var ref = db.collection("winegrowers").document("ZMOK6WP9W3DoLCpDuvcm")
                .collection("winegrowerProfile")
                .document("z4e9EBfSp1JhYD4bVmcJ")
                .get()
                .addOnSuccessListener { documentReference ->
                    Log.d("debug", "")
                }
                .addOnFailureListener { e ->
                    Log.w("debug", "Error", e)
                }
                .await()

            var winegrowerProfileResult = getWinegrowerProfile(ref)
            winegrowerProfileResult = ref.id
            winegrowerProfile.postValue(winegrowerProfile)
        }
        return winegrowerProfile.value!!

