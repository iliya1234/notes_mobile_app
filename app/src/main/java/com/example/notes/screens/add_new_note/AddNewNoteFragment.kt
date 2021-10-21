package com.example.notes.screens.add_new_note

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import com.example.notes.R
import com.example.notes.databinding.FragmentAddNewNoteBinding
import com.example.notes.models.AppNote
import com.example.notes.utills.APP_ACTIVITY
import com.example.notes.utills.showToast
import kotlin.properties.Delegates


class AddNewNoteFragment : Fragment() {
    private var _binding:FragmentAddNewNoteBinding?=null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: AddNewNoteFragmentViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddNewNoteBinding.inflate(layoutInflater,container,false)
        return mBinding.root
    }
    override fun onStart() {
        super.onStart()
        initialization()
    }



    private fun initialization() {
        mViewModel = ViewModelProvider(this).get(AddNewNoteFragmentViewModel::class.java)

        mViewModel.checkInserting.observe(this,{
            if(it){
                mBinding.btnAddNewNote.isEnabled = false
                mBinding.inputNameNote.isEnabled = false
                mBinding.inputTextNote.isEnabled = false
            }
            else {
                APP_ACTIVITY.mNavController.navigate(R.id.action_addNewNoteFragment_to_mainFragment)
            }
        })
        mBinding.btnAddNewNote.setOnClickListener {
            val name = mBinding.inputNameNote.text.toString()
            val text = mBinding.inputTextNote.text.toString()
            if (name.isEmpty()){
                showToast(getString(R.string.toast_enter_name))
            } else {
                mViewModel.insert(AppNote(name = name,text = text))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}