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

        val landparcelsCollection = db.collection("Landparcels")

    }
}


data class Landparcel(
    val name: String,
    val parcelNumber: String,
    val area: Double
)

class LandparcelDatabase {

    private val landparcelsCollection = FirebaseFirestore.getInstance().collection("Landparcels")


    fun addLandparcel(landparcel: Landparcel) {
        landparcelsCollection.document(landparcel.name).set(landparcel)
    }

    fun getLandparcel(name: String): Landparcel? {
        val document = landparcelsCollection.document(name).get().get()
        return if (document.exists()) document.toObject(Landparcel::class.java) else null
    }

    fun getAllLandparcels(): List<Landparcel> {
        val landparcels = mutableListOf<Landparcel>()
        val documents = landparcelsCollection.get().get().documents
        for (document in documents) {
            landparcels.add(document.toObject(Landparcel::class.java))
        }
        return landparcels
    }
}

fun main() {
    val firestoreInitializer = FirestoreInitializer()
    firestoreInitializer.initialize()


    val landparcel = Landparcel("Vineyard 1", "Parcel 1", 2.5)
    val landparcelDatabase = LandparcelDatabase()
    landparcelDatabase.addLandparcel(landparcel)

    val retrievedLandparcel = landparcelDatabase.getLandparcel("Vineyard 1")
    if (retrievedLandparcel != null) {
        println("Retrieved landparcel: $retrievedLandparcel")
    } else {
        println("Landparcel not found")
    }

    val allLandparcels = landparcelDatabase.getAllLandparcels()
    println("All landparcels:")
    for (landparcel in allLandparcels) {
        println(landparcel)
    }
}