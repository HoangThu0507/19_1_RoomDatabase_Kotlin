package com.example.roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.NullPointerException
import java.util.ArrayList

class MainActivity : AppCompatActivity(), UserAdapter.infor {
    var etName: EditText? = null
    var etAddress: EditText? = null
    lateinit var btnAddUser: Button
    lateinit var rvUser: RecyclerView
    var userAdapter: UserAdapter? = null
    var mlistUser: MutableList<User>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etName = findViewById(R.id.etName) as EditText
        etAddress = findViewById(R.id.etAddress)
        rvUser = findViewById(R.id.rvUser)
        btnAddUser = findViewById(R.id.btnAddUser)
//        var userDatabase: UserDatabase = UserDatabase.getInstance(this)!!
//        userDatabase.userDao()!!.insertUser(User("Thu", "Hai Duong1"))
//            ?.subscribeOn(Schedulers.computation())
//            ?.subscribe(CompletableObserver(){
//
//            })

        userAdapter = UserAdapter(this)
        mlistUser = ArrayList()
        userAdapter!!.setData(mlistUser, this)
        val linearLayoutManager = LinearLayoutManager(this)
        rvUser.setLayoutManager(linearLayoutManager)
        rvUser.setAdapter(userAdapter)
        //mlistUser = UserDatabase.getInstance(this)!!.userDao()!!.listUser() as MutableList<User>?
        // userAdapter!!.setData(mlistUser, this)
        btnAddUser.setOnClickListener(View.OnClickListener { addUser() })

    }

    private fun addUser() {
        val userName = etName!!.text.toString().trim()
        val userAddress = etAddress!!.text.toString().trim()
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(userAddress)) {
            return

        }
//        if(btnAddUser.text.equals("SAVE")){
        val user = User(userName, userAddress)
        UserDatabase.getInstance(this)!!.userDao()!!.insertUser(user)
        Toast.makeText(this, "Successfully", Toast.LENGTH_SHORT).show()
        etAddress!!.setText("")
        etName!!.setText("")
        hideKeyboard()
        mlistUser = UserDatabase.getInstance(this)!!.userDao()!!.listUser() as MutableList<User>?
        userAdapter!!.setData(mlistUser, this)
//        }else{
//
//            UserDatabase.getInstance(this)!!.userDao()!!.insertUser(user)
//        }
    }

    fun hideKeyboard() {
        try {
            val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        } catch (ex: NullPointerException) {
            ex.printStackTrace()
        }
    }
    override fun update(user: User) {
        etName!!.setText(user.name)
        etAddress!!.setText(user.address)
        btnAddUser.setText("Update")

    }
}
