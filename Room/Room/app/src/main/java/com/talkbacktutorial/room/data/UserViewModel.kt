package com.talkbacktutorial.room.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
The ViewModel's role is to provide data to the UI and survive configuration changes.
A ViewModel acts as a communication center between the repository and the UI.
 */
class UserViewModel(application: Application) : AndroidViewModel(application){

    private val readAllData: LiveData<List<User>>
    private val repository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addUser(user: User){
        // Dispatchers.IO means we want to run code in the background thread
        // This is better practice as we only want to launch from background threads not the main
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }
}