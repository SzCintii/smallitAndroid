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

/*class FirestoreInitializer {
    fun initialize() {

        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp()
        }

        val db = FirebaseFirestore.getInstance()

        val winegrowerProfileCollection = db.collection("My Winegrowerprofile")

        val vineyardsCollection = db.collection("My Vineyards")

        val barrelsCollection = db.collection("Barrels")

        val employeesCollection = db.collection("Employees")

        val landparcelsCollection = db.collection("Landparcels")

        val harvestYearCollection = db.collection("Harvestyear")

    }
}
*/
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
        //val document = harvestYearCollection.document(name).get()
        //return if (document.exists()) document.toObject(Harvestyear::class.java) else null

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

        /*fun getAllHarvestyears(): List<Harvestyear> {
            val harvestyears = mutableListOf<Harvestyear>()
            val documents = harvestYearCollection.get().get().documents
            for (document in documents) {
                harvestyears.add(document.toObject(Harvestyear::class.java))
            }
            return harvestyears
        }
    }
         */

/*
fun main() {
    val firestoreInitializer = FirestoreInitializer()
    firestoreInitializer.initialize()

    val harvestyear = Harvestyear("2022", "2022-01-01", "2022-12-31")
    val harvestyearDatabase = HarvestyearDatabase()
    harvestyearDatabase.addHarvestyear(harvestyear)

    val retrievedHarvestyear = harvestyearDatabase.getHarvestyear("2022")
    if (retrievedHarvestyear != null) {
        println("Retrieved harvest year: $retrievedHarvestyear")
    } else {
        println("Harvest year not found")
    }

    val allHarvestyears = harvestyearDatabase.getAllHarvestyears()
    println("All harvest years:")
    for (harvestyear in allHarvestyears) {
        println(harvestyear)
    }
}*/