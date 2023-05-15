import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.*

data class AgriculturalParcels(){
    var parcelNumber : Int
    var areaPlantedAre : String
    var areaPlantedCentiare : String
    var clone : String
    var grapeVariety : String
    var mainCropCode : Int
    var mainCropGroup : String
    var name : String
    var numberOfPlants : Int
    var parcelId : String
    var rootStock : String
    var vineyardId : String
    var yearPlanted : Int
    init {
        parcelNumber = 0
        areaPlantedAre = ""
        areaPlantedCentiare = ""
        clone = ""
        grapeVariety = ""
        mainCropCode = 0
        mainCropGroup = ""
        name = ""
        numberOfPlants = 0
        parcelId = ""
        rootStock = ""
        vineyardId = ""
        yearPlanted = 0
    }
}

class AgriculturalParcelsDatabase {
    private val agriculturalparcelsCollection = FirebaseFirestore.getInstance().collection("AgricuturalParcels")

    fun addAgriculturalParcels(agriculturalParcels: AgriculturalParcels) {
        agriculturalparcelsCollection.document(agriculturalParcels.name).set(agriculturalParcels)
    }

    fun getAgriculturalParcels(document: DocumentSnapshot?): AgriculturalParcels {

        var agriculturalParcels = AgriculturalParcels()

        agriculturalParcels.parcelNumber = document!!.data!!.get("parcelNumber") as Int

        agriculturalParcels.areaPlantedAre = document!!.data!!.get("areaPlantedAre").toString()

        agriculturalParcels.areaPlantedCentiare = document!!.data!!.get("areaPlantedCentiare").toString()

        agriculturalParcels.clone = document!!.data!!.get("clone").toString()

        agriculturalParcels.grapeVariety = document!!.data!!.get("grapeVariety").toString()

        agriculturalParcels.mainCropCode = document!!.data!!.get("mainCropCode") as Int

        agriculturalParcels.mainCropGroup = document!!.data!!.get("mainCropGroup").toString()

        agriculturalParcels.name = document!!.data!!.get("name").toString()

        agriculturalParcels.numberOfPlants = document!!.data!!.get("numberOfPlants") as Int

        agriculturalParcels.parcelId = document!!.data!!.get("parcelId").toString()

        agriculturalParcels.rootStock = document!!.data!!.get("rootStock").toString()

        agriculturalParcels.vineyardId = document!!.data!!.get("vineyardId").toString()

        agriculturalParcels.yearPlanted = document!!.data!!.get("yearPlanted") as Int

        return agriculturalParcels

    }

    fun getAgriculturalParcels(snapshot: QueryDocumentSnapshot): AgriculturalParcels {


        var agriculturalParcels = AgriculturalParcels()

        agriculturalParcels.parcelNumber = snapshot!!.data!!.get("parcelNumber") as Int

        agriculturalParcels.areaPlantedAre = snapshot!!.data!!.get("areaPlantedAre").toString()

        agriculturalParcels.areaPlantedCentiare = snapshot!!.data!!.get("areaPlantedCentiare").toString()

        agriculturalParcels.clone = snapshot!!.data!!.get("clone").toString()

        agriculturalParcels.grapeVariety = snapshot!!.data!!.get("grapeVariety").toString()

        agriculturalParcels.mainCropCode = snapshot!!.data!!.get("mainCropCode") as Int

        agriculturalParcels.mainCropGroup = snapshot!!.data!!.get("mainCropGroup").toString()

        agriculturalParcels.name = snapshot!!.data!!.get("name").toString()

        agriculturalParcels.numberOfPlants = snapshot!!.data!!.get("numberOfPlants") as Int

        agriculturalParcels.parcelId = snapshot!!.data!!.get("parcelId").toString()

        agriculturalParcels.rootStock = snapshot!!.data!!.get("rootStock").toString()

        agriculturalParcels.vineyardId = snapshot!!.data!!.get("vineyardId").toString()

        agriculturalParcels.yearPlanted = snapshot!!.data!!.get("yearPlanted") as Int

        return agriculturalParcels

    }


    suspend fun getAgriculturalParcels(): AgriculturalParcels {

        var agriculturalParcels = MutableLiveData<AgriculturalParcels>()
        withContext(Dispatchers.IO) {
            val db = Firebase.firestore
            var ref = db.collection("winegrowers").document("ZMOK6WP9W3DoLCpDuvcm")
                .collection("9ZAdVWVHoobXRF1Y606e")
                .document("z4e9EBfSp1JhYD4bVmcJ")
                .get()
                .addOnSuccessListener { documentReference ->
                    Log.d("debug", "")
                }
                .addOnFailureListener { e ->
                    Log.w("debug", "Error", e)
                }
                .await()

            var agriculturalParcelsResult = getAgriculturalParcels(ref)
            agriculturalParcels.parcelId = ref.parcelId
            agriculturalParcels.postValue(agriculturalParcels)
        }
        return agriculturalParcels.value!!