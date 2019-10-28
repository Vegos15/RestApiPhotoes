package com.example.restapiphotoes

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)

        viewModel.loadUsers()
//
//        btnClick.setOnClickListener {
//            executeLiveData()
//        }
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        viewModel.userList.observe(this, Observer {
            tvName.text = it.name
        })
        return super.onCreateView(name, context, attrs)
    }



    private fun executeLiveData() {

    }
}
