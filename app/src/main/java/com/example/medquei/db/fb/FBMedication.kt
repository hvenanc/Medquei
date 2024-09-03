package com.example.medquei.db.fb

import com.example.medquei.model.Medication

class FBMedication {

    var name : String? = null
    var dosage : Double? = null
    var date : String? = null
    var hour : String? = null
    var image : String? = null

    fun toMedication() : Medication {
        return Medication(name = name!!, dosage = dosage, date = date, hour = hour, image = image)
    }
}

fun Medication.toFBMedication() : FBMedication {
    val fbMedication = FBMedication()

    fbMedication.name = this.name
    fbMedication.dosage = this.dosage
    fbMedication.date = this.date
    fbMedication.hour = this.hour
    fbMedication.image = this.image

    return fbMedication
}