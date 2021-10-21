package com.example.notes.screens.note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.notes.models.AppNote
import com.example.notes.utills.REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteFragmentViewModel(application: Application):AndroidViewModel(application) {
    private var _checkInserting: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val checkInserting get() = _checkInserting as LiveData<Boolean>
    fun delete(note:AppNote){
        _checkInserting.value = true
        viewModelScope.launch(Dispatchers.IO){
            REPOSITORY.delete(note){
                _checkInserting.postValue(false)
            }
        }
    }
}