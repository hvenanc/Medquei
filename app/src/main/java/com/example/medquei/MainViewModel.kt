package com.example.medquei

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.medquei.db.fb.FBDatabase
import com.example.medquei.model.Medication
import com.example.medquei.model.User
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.storage.FirebaseStorage

class MainViewModel : ViewModel(), FBDatabase.Listener {
    private val _medications = getMedications().toMutableStateList()
    private val _user = mutableStateOf (User("", ""))
    val user : User
        get() = _user.value

    val medications : List<Medication>
        get() = _medications

    private var _loggedIn = mutableStateOf(false)
    val loggedIn: Boolean
        get() = _loggedIn.value

    private val listener = FirebaseAuth.AuthStateListener { firebaseAuth ->
        _loggedIn.value = firebaseAuth.currentUser != null
    }

    fun getImageUrl(imageName: String): LiveData<String?> {
        val imageUrl = MutableLiveData<String?>()

        // Referência ao Firebase Storage
        val storageReference = FirebaseStorage.getInstance().reference
        val imageRef = storageReference.child("images/$imageName")

        // Busca o URL de download
        imageRef.downloadUrl.addOnSuccessListener { uri ->
            imageUrl.value = uri.toString() // Armazena a URL na LiveData
        }.addOnFailureListener {
            // Tratar o erro caso necessário
            imageUrl.value = null
        }

        return imageUrl
    }

    init {
        listener.onAuthStateChanged(Firebase.auth)
        Firebase.auth.addAuthStateListener(listener)
    }

    override fun onUserLoaded(user: User) {
        _user.value = user
    }

    override fun onMedicationAdded(medication: Medication) {
        _medications.add(medication)
    }

    override fun onMedicationRemoved(medication: Medication) {
        _medications.remove(medication)
    }
}

fun getMedications() = List(0) {i ->
    Medication(name = "")
}
