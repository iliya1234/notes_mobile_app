package com.example.notes.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import java.io.Serializable

@Entity(tableName = "notes-tables")
data class AppNote(
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    @ColumnInfo val name:String = "",
    @ColumnInfo val text:String = "",
    val idFirebase:String = ""
) : Serializable