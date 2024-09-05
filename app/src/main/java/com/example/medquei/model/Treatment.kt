package com.example.medquei.model

data class Treatment(
    var medication: Medication? = null,
    var daysTreatment: List<Days>? = null,
)

data class Days(
    var date : String? = null,
    var hour: String? = null,
)