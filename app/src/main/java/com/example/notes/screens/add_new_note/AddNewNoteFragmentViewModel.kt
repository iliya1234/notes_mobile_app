package com.example.notes.screens.add_new_note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.notes.models.AppNote
import com.example.notes.utills.REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNewNoteFragmentViewModel(application: Application):AndroidViewModel(application) {

    private var _checkInserting: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val checkInserting get() = _checkInserting as LiveData<Boolean>
    fun insert(note:AppNote) {
        _checkInserting.value = true
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.insert(note) {
                _checkInserting.postValue(false)
            }
        }
    }
}