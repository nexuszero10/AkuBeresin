package com.unsoed.noteapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "notes") // Representasi table database
@Parcelize // agar object Note bisa dikirim anatar Fragment
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val noteTitle: String,
    val noteDesc: String
): Parcelable