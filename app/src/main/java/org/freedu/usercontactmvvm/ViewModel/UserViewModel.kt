package org.freedu.usercontactmvvm.ViewModel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.freedu.usercontactmvvm.room.User
import org.freedu.usercontactmvvm.room.UserRepository

class UserViewModel(private val repository: UserRepository):ViewModel(), Observable {
    val users = repository.users
    private var isUpdateOrDelete = false
    private lateinit var userToUpdateOrDelete : User

    @Bindable
    val inputName = MutableLiveData<String>()

    @Bindable
    val inputEmail = MutableLiveData<String>()
}