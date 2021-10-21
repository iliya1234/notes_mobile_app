package com.example.notes.screens.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.notes.R
import com.example.notes.databinding.FragmentStartBinding
import com.example.notes.utills.*
import kotlinx.android.synthetic.main.fragment_start.*

class StartFragment : Fragment() {
    private var _binding: FragmentStartBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: StartFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartBinding.inflate(layoutInflater,container,false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        mViewModel = ViewModelProvider(this).get(StartFragmentViewModel::class.java)
        if(AppPreference.getInitUser()){
            mViewModel.initDatabase(AppPreference.getTypeDB()){
                APP_ACTIVITY.mNavController.navigate(R.id.action_startFragment_to_mainFragment)
            }
        } else {
            initialization()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

    private fun initialization() {
        mBinding.btnRoom.setOnClickListener {
            mViewModel.initDatabase(TYPE_ROOM){
                AppPreference.setInitUser(true)
                AppPreference.setTypeDatabase(TYPE_ROOM)
                APP_ACTIVITY.mNavController.navigate(R.id.action_startFragment_to_mainFragment)
            }
        }
        mBinding.btnFirebase.setOnClickListener {
            mBinding.inputEmail.visibility = View.VISIBLE
            mBinding.inputPassword.visibility = View.VISIBLE
            mBinding.btnLogin.visibility = View.VISIBLE
            mBinding.btnLogin.setOnClickListener {
                val inputEmail = mBinding.inputEmail.text.toString()
                val inputPassword = mBinding.inputPassword.text.toString()
                if(inputEmail.isEmpty()&&inputPassword.isEmpty()) showToast(getString(R.string.toast_wrong_email_and_password))
                else if(inputEmail.isEmpty()) showToast(getString(R.string.toast_wrong_email))
                else if(inputPassword.isEmpty()) showToast(getString(R.string.toast_wrong_password))
                else{
                    EMAIL=inputEmail
                    PASSWORD=inputPassword
                    mViewModel.initDatabase(TYPE_FIREBASE){
                        AppPreference.setInitUser(true)
                        AppPreference.setTypeDatabase(TYPE_FIREBASE)
                       APP_ACTIVITY.mNavController.navigate(R.id.action_startFragment_to_mainFragment)
                    }
                }
            }
        }
    }
}