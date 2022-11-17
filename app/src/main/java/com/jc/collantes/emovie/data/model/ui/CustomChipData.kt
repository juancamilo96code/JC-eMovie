package com.jc.collantes.emovie.data.model.ui

data class CustomChipData(
    val id: String,
    val text: String,
    val internalValue: String,
    var isActive: Boolean,
    var onCheckedChange: (Boolean) -> Unit
)
