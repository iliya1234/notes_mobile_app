package com.example.notes.database.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notes.models.AppNote


@Dao
interface AppRoomDao {
    @Query("Select * from `notes-tables`")
    fun getAllNotes(): LiveData<List<AppNote>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: AppNote)

    @Delete
    suspend fun delete(note: AppNote)
}