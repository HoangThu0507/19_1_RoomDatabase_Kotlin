package com.example.roomdatabase

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.NullPointerException
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    var etName: EditText? = null
    var etAddress: EditText? = null
    lateinit var btnAddUser: Button
    lateinit var rvUser: RecyclerView
    var userAdapter: UserAdapter? = null
    var mlistUser: List<User>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etName = findViewById(R.id.etName)
        etAddress = findViewById(R.id.etAddress)
        rvUser = findViewById(R.id.rvUser)
        btnAddUser = findViewById(R.id.btnAddUser)
        userAdapter = UserAdapter()
        mlistUser = ArrayList()
        userAdapter!!.setData(mlistUser)
        val linearLayoutManager = LinearLayoutManager(this)
        rvUser.setLayoutManager(linearLayoutManager)
        rvUser.setAdapter(userAdapter)
        btnAddUser.setOnClickListener(View.OnClickListener { addUser() })
    }

    private fun addUser() {
        val userName = etName!!.text.toString().trim { it <= ' ' }
        val userAddress = etAddress!!.text.toString().trim { it <= ' ' }
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(userAddress)) {
            return
        }
        val user = User(userName, userAddress)
        UserDatabase.getInstance(this)!!.userDao()!!.insertUser(user)
        Toast.makeText(this, "Successfully", Toast.LENGTH_SHORT).show()
        etAddress!!.setText("")
        etName!!.setText("")
        hideKeyboard()
        mlistUser = UserDatabase.getInstance(this)!!.userDao()!!.getListUser() as List<User>?
        userAdapter!!.setData(mlistUser)
    }

    fun hideKeyboard() {
        try {
            val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        } catch (ex: NullPointerException) {
            ex.printStackTrace()
        }
    }
}
