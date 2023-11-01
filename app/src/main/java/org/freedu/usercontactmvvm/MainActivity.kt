package org.freedu.usercontactmvvm

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import org.freedu.usercontactmvvm.viewmodel.UserViewModel
import org.freedu.usercontactmvvm.viewmodel.userViewModelFactory
import org.freedu.usercontactmvvm.databinding.ActivityMainBinding
import org.freedu.usercontactmvvm.room.User
import org.freedu.usercontactmvvm.room.UserDatabase
import org.freedu.usercontactmvvm.room.UserRepository
import org.freedu.usercontactmvvm.viewUi.MyRecyclerViewAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var userViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //Room
        val dao = UserDatabase.getInstance(application).userDAO
        val repository = UserRepository(dao)
        val factory = userViewModelFactory(repository)

        userViewModel = ViewModelProvider(this, factory).get(UserViewModel::class.java)
        binding.userViewModel = userViewModel

        binding.lifecycleOwner = this

        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        DisplayUserList()
    }

    private fun DisplayUserList() {
       userViewModel.users.observe(this, Observer {
           binding.recyclerView.adapter = MyRecyclerViewAdapter(
               it, {selectedItem:User->listItemClicked(selectedItem)}
           )
       })
    }

    private fun listItemClicked(selectedItem: User) {
        Toast.makeText(this, "Selected name is ${selectedItem.name}", Toast.LENGTH_LONG).show()

        userViewModel.initUpdateAndDelete(selectedItem)
    }
}


