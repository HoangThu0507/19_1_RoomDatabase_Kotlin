package com.example.roomdatabase

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
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.lang.NullPointerException

class MainActivity : AppCompatActivity(), UserAdapter.infor {
    var etName: EditText? = null
    var etAddress: EditText? = null
    lateinit var btnAddUser: Button
    lateinit var rvUser: RecyclerView
    var userAdapter: UserAdapter? = null
    var mlistUser: MutableList<User?>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etName = findViewById(R.id.etName) as EditText
        etAddress = findViewById(R.id.etAddress)
        rvUser = findViewById(R.id.rvUser)
        btnAddUser = findViewById(R.id.btnAddUser)

        userAdapter = UserAdapter(this)
        val linearLayoutManager = LinearLayoutManager(this)
        rvUser.setLayoutManager(linearLayoutManager)
        rvUser.setAdapter(userAdapter)
        loadUser()
        btnAddUser.setOnClickListener(View.OnClickListener { addUser() })

    }

    private fun addUser() {
        val userName = etName!!.text.toString().trim()
        val userAddress = etAddress!!.text.toString().trim()
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(userAddress)) {
            return

        }
        if(btnAddUser.text.equals("Save")){
        val user = User(userName, userAddress)
        UserDatabase.getInstance(this)!!.userDao()!!.insertUser(user)
        Toast.makeText(this, "Successfully", Toast.LENGTH_SHORT).show()
        etAddress!!.setText("")
        etName!!.setText("")
        hideKeyboard()
        //mlistUser = UserDatabase.getInstance(this)!!.userDao()!!.listUser() as MutableList<User>?
        //userAdapter!!.setData(mlistUser, this)
        }else{
            val user = User(userName, userAddress)
            UserDatabase.getInstance(this)!!.userDao()!!.updateUser(user)
        }
        loadUser()


    }
    fun loadUser(){
        val userDatabase = UserDatabase.getInstance(this)
        userDatabase!!.userDao()!!.listUser()
            ?.subscribeOn(Schedulers.computation())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : SingleObserver<MutableList<User?>?> {
                override fun onSubscribe(d: Disposable) {}

                override fun onSuccess(posts: MutableList<User?>) {
                    userAdapter!!.setData(posts, this@MainActivity)
                    userAdapter!!.notifyDataSetChanged()
                }

                override fun onError(e: Throwable) {}

            })
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
