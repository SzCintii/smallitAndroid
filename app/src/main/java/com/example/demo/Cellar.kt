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

data class Cellars(){
    var id: String
    var name: String
    var adress: String
    var city: String
    var wineGrower: String
    init {
        id = ""
        name = ""
        adress = ""
        city = ""
        wineGrower = ""
    }
}

class CellarsDatabase {
    private val cellarsCollection = FirebaseFirestore.getInstance().collection("Cellars")

    fun addCellars(cellars: Cellars) {
        cellarsCollection.document(cellars.name).set(cellars)
    }

    fun getCellars(document: DocumentSnapshot?): Cellars {

        var cellars = Cellars()

        cellars.id = document!!.id

        cellars.name = document!!.data!!.get("name").toString()

        cellars.adress = document.data!!.get("adress").toString()

        cellars.city = document.data!!.get("city").toString()

        cellars.wineGrower = document.data!!.get("wineGrower").toString()
        return cellars

    }

    fun getCellars(snapshot: QueryDocumentSnapshot): Cellars {


        var cellars = Cellars()

        cellars.id = snapshot!!.id

        cellars.name = snapshot!!.data!!.get("name").toString()

        cellars.adress = snapshot.data!!.get("adress").toString()

        cellars.city = snapshot.data!!.get("city").toString()

        cellars.wineGrower = snapshot.data!!.get("wineGrower").toString()
        return cellars

    }


    suspend fun getCellars(): Cellars {

        var cellars = MutableLiveData<Cellars>()
        withContext(Dispatchers.IO) {
            val db = Firebase.firestore
            var ref = db.collection("winegrowers").document("ZMOK6WP9W3DoLCpDuvcm")
                .collection("harvestYear")
                .document("3KmcNfV8IRCQGw4jJ1hW")
                .get()
                .addOnSuccessListener { documentReference ->
                    Log.d("debug", "")
                }
                .addOnFailureListener { e ->
                    Log.w("debug", "Error", e)
                }
                .await()

            var cellarsResult = getCellars(ref)
            cellarsResult.id = ref.id
            .postValue(cellarsResult)
        }
        return cellars.value!!