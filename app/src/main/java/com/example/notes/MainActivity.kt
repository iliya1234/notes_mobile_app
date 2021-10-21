package com.example.notes


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.notes.databinding.ActivityMainBinding
import com.example.notes.utills.APP_ACTIVITY
import com.example.notes.utills.AppPreference

class MainActivity : AppCompatActivity() {
    private lateinit var mToolbar: Toolbar
    lateinit var mNavController: NavController
    private var _binding: ActivityMainBinding? = null
    private val mBinding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        APP_ACTIVITY = this
        mToolbar = mBinding.toolbar
        mNavController = Navigation.findNavController(this, R.id.nav_host_fragment)
        setSupportActionBar(mToolbar)
        title=getString(R.string.title)
        AppPreference.getPreference(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}