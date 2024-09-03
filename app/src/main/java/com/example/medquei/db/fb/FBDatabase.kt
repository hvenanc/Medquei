package com.example.medquei.db.fb

import com.example.medquei.model.Medication
import com.example.medquei.model.User
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.firestore

class FBDatabase(private val listener: Listener? = null) {
    private val auth = Firebase.auth
    private val db = Firebase.firestore
    private var medicationsListReg: ListenerRegistration? = null
    interface Listener {
        fun onUserLoaded(user: User)
        fun onMedicationAdded(medication: Medication)
        fun onMedicationRemoved(medication: Medication)
    }
    init {
        auth.addAuthStateListener { auth ->
            if (auth.currentUser == null) {
                medicationsListReg?.remove()
                return@addAuthStateListener
            }

            val refCurrUser = db.collection("users")
                .document(auth.currentUser!!.uid)
            refCurrUser.get().addOnSuccessListener {
                it.toObject(FBUser::class.java)?.let { user ->
                    listener?.onUserLoaded(user.toUser())
                }
            }

            medicationsListReg =  refCurrUser.collection("medications")
                .addSnapshotListener { snapshots, ex ->
                    if (ex != null) return@addSnapshotListener
                    snapshots?.documentChanges?.forEach { change ->
                        val fbMedication = change.document.toObject(FBMedication::class.java)
                        if (change.type == DocumentChange.Type.ADDED) {
                            listener?.onMedicationAdded(fbMedication.toMedication())
                        } else if (change.type == DocumentChange.Type.REMOVED) {
                            listener?.onMedicationRemoved(fbMedication.toMedication())
                        }
                    }
                }
        }
    }
    fun register(user: User) {
        if (auth.currentUser == null)
            throw RuntimeException("User not logged in!")
        val uid = auth.currentUser!!.uid
        db.collection("users").document(uid + "").set(user.toFBUser());
    }
    fun add(medication: Medication) {
        if (auth.currentUser == null)
            throw RuntimeException("User not logged in!")
        val uid = auth.currentUser!!.uid
        db.collection("users").document(uid).collection("medications")
            .document(medication.name.toString()).set(medication.toFBMedication())

    }
    fun remove(medication: Medication) {
        if (auth.currentUser == null)
            throw RuntimeException("User not logged in!")
        val uid = auth.currentUser!!.uid
        db.collection("users").document(uid).collection("medications")
            .document(medication.name).delete()
    }
}
