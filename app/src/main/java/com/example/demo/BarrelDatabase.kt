import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class FirestoreInitializer {
    fun initialize() {

        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp()
        }


        val db = FirebaseFirestore.getInstance()


        val winegrowerProfileCollection = db.collection("My Winegrowerprofile")


        val vineyardsCollection = db.collection("My Vineyards")


        val barrelsCollection = db.collection("Barrels")


    }
}


data class Barrel(
    val name: String,
    val numberOfBarrels: Int,
    val type: String,
    val totalVolume: Double,
    val dateFirstUse: Date
)

class BarrelDatabase {

    private val barrelsCollection = FirebaseFirestore.getInstance().collection("Barrels")


    fun addBarrel(barrel: Barrel) {
        barrelsCollection.document(barrel.name).set(barrel)
    }


    fun getBarrel(name: String): Barrel? {
        val document = barrelsCollection.document(name).get().get()
        return if (document.exists()) document.toObject(Barrel::class.java) else null
    }


    fun getAllBarrels(): List<Barrel> {
        val barrels = mutableListOf<Barrel>()
        val documents = barrelsCollection.get().get().documents
        for (document in documents) {
            barrels.add(document.toObject(Barrel::class.java))
        }
        return barrels
    }
}


fun main() {

    val firestoreInitializer = FirestoreInitializer()
    firestoreInitializer.initialize()


    val barrel = Barrel("My Barrel", 10, "Red", 100.0, Date())
    val barrelDatabase = BarrelDatabase()
    barrelDatabase.addBarrel(barrel)


    val retrievedBarrel = barrelDatabase.getBarrel("My Barrel")
    if (retrievedBarrel != null) {
        println("Retrieved barrel: $retrievedBarrel")
    } else {
        println("Barrel not found")
    }


    val allBarrels = barrelDatabase.getAllBarrels()
    println("All barrels:")
    for (barrel in allBarrels) {
        println(barrel)
    }
}