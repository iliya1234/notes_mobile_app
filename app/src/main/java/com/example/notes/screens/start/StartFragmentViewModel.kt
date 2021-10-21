package com.example.notes.screens.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.notes.database.firebase.AppFirebaseRepository
import com.example.notes.database.room.AppRoomDatabase
import com.example.notes.database.room.AppRoomRepository
import com.example.notes.utills.REPOSITORY
import com.example.notes.utills.TYPE_FIREBASE
import com.example.notes.utills.TYPE_ROOM
import com.example.notes.utills.showToast

class StartFragmentViewModel(application: Application):AndroidViewModel(application) {
    private val mContext = application

    fun initDatabase(type:String,onSuccess:()->Unit){
        when(type){
            TYPE_ROOM -> {
                val dao = AppRoomDatabase.getInstance(mContext).getAppRoomDao()
                REPOSITORY = AppRoomRepository(dao)
                onSuccess()
            }
            TYPE_FIREBASE -> {
                REPOSITORY = AppFirebaseRepository()
                REPOSITORY.connectToDatabase({onSuccess()},{ showToast(it)})
            }
        }
    }
}