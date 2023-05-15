import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.*

data class Batches(){
    var agriculturalParcel : agriculturalParcel
    var createDate: Date
    var email : String
    var harvestYearId: Harvestyear.id
    var historic: String
    var name : String
    var wineStorages : wineStorages
    init {
        agriculturalParcel = ""
        createDate = Date()
        email = ""
        harvestYearId = Harvestyear.id
        historic = ""
        name = ""
        wineStorages = ""
    }
}

class BatchesDatabase {
    private val batchesCollection = FirebaseFirestore.getInstance().collection("Batches")

    fun addBatches(batches: Batches) {
        batchesCollection.document(batches.name).set(batches)
    }

    fun getBatches(document: DocumentSnapshot?): Batches {

        var batches = Batches()

        agriculturalParcel.id = document!!.id

        batches.createDate = (document!!.data!!.get("startDate") as Timestamp).toDate()

        batches.email = document.data!!.get("email").toString()

        batches.HarvestyearId = document.data!!.get("harvestYearId") as Harvestyear

        batches.historic = document!!.get("historic").toString()

        batches.name = document!!.get("name").toString()

        batches.wineStorages = document!!.get("wineStorages") as WineStorages

        return Batches()

    }

    fun getBatches(snapshot: QueryDocumentSnapshot): Batches {


        var batches = Batches()

        agriculturalParcel.id = snapshot!!.id

        batches.createDate = (snapshot!!.data!!.get("startDate") as Timestamp).toDate()

        batches.email = snapshot.data!!.get("email").toString()

        batches.HarvestyearId = snapshot.data!!.get("harvestYearId") as Harvestyear

        batches.historic = snapshot!!.get("historic").toString()

        batches.name = snapshot!!.get("name").toString()

        batches.wineStorages = snapshot!!.get("wineStorages") as WineStorages

        return batches

    }


    suspend fun getBatches(): Batches {

        var batches = MutableLiveData<Batches>()
        withContext(Dispatchers.IO) {
            val db = Firebase.firestore
            var ref = db.collection("batches").document("ZMOK6WP9W3DoLCpDuvcm")
                .collection("batches")
                .document("unMTFsER9CNcS8llAfbl")
                .get()
                .addOnSuccessListener { documentReference ->
                    Log.d("debug", "")
                }
                .addOnFailureListener { e ->
                    Log.w("debug", "Error", e)
                }
                .await()

            var batchesResult = getbatches(ref)
           batchesResult.id = ref.id
            batches.postValue(batchesResult)
        }
        return batches.value!!
