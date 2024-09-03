package com.example.medquei.model

data class Medication(
    var name: String,
    var dosage: Double? = null,
    var date: String? = null,
    var hour: String? = null,
    var image: String? = null
)