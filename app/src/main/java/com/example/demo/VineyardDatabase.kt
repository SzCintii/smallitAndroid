import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.FirebaseApp
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.type.DateTime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.Date

data class Harvestyear(){
    var id: String
    var name: String
    var startDate: Date
    var endDate: Date
    init {
        id = ""
        name = ""
        startDate = Date()
        endDate = Date()
    }
}

class HarvestyearDatabase {
    private val harvestYearCollection = FirebaseFirestore.getInstance().collection("Harvestyear")

    fun addHarvestyear(harvestyear: Harvestyear) {
        harvestYearCollection.document(harvestyear.name).set(harvestyear)
    }

    fun getHarvestYear(document: DocumentSnapshot?): Harvestyear {

        var harvestYear = Harvestyear()

        harvestYear.id = document!!.id

        harvestYear.startDate = (document!!.data!!.get("startDate") as Timestamp).toDate()

        harvestYear.name = document.data!!.get("name").toString()

        harvestYear.endDate = (document.data!!.get("endDate") as Timestamp).toDate()

        return harvestYear

    }

    fun getHarvestYear(snapshot: QueryDocumentSnapshot): Harvestyear {


        var harvestYear = Harvestyear()

        harvestYear.id = snapshot!!.id

        harvestYear.startDate = (snapshot!!.data!!.get("startDate") as Timestamp).toDate()

        harvestYear.name = snapshot.data!!.get("name").toString()

        harvestYear.endDate = (snapshot.data!!.get("endDate") as Timestamp).toDate()

        return harvestYear

    }


    suspend fun getHarvestyear(): Harvestyear {

        var harvestYear = MutableLiveData<Harvestyear>()
        withContext(Dispatchers.IO) {
            val db = Firebase.firestore
            var ref = db.collection("winegrowers").document("ZMOK6WP9W3DoLCpDuvcm")
                .collection("harvestYear")
                .document("z4e9EBfSp1JhYD4bVmcJ")
                .get()
                .addOnSuccessListener { documentReference ->
                    Log.d("debug", "")
                }
                .addOnFailureListener { e ->
                    Log.w("debug", "Error", e)
                }
                .await()

            var harvestyearResult = getHarvestYear(ref)
            harvestyearResult.id = ref.id
            harvestYear.postValue(harvestyearResult)
        }
        return harvestYear.value!!

