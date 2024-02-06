package com.example.firsttest

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository: ProfileInfoRepository
    private val readAll : LiveData<List<ProfileInfo>>
    init {
        val profileInfoDB = ProfileInfoDatabase.getDatabase(application).dao()
        repository = ProfileInfoRepository(profileInfoDB)
        readAll = repository.getAll()
    /*
        //repository.insertInitialItem(ProfileInfo(1, "", "Matti"))
        viewModelScope.launch{
            if(repository.isEmpty()){
                repository.insertUser(ProfileInfo(1, "", "Matti"))
            }
        }
*/
        //viewModelScope.launch (Dispatchers.IO) {
            //
        //}


    }
    fun isEmpty(): Boolean{

           return repository.isEmpty()

    }
    fun addInfo(info: ProfileInfo){
        viewModelScope.launch (Dispatchers.IO){
            repository.insertUser(info)
        }
    }
    fun getInfo(id: Long): LiveData<List<ProfileInfo>>{

            return repository.getInfo(id)

    }
    fun getAll(): LiveData<List<ProfileInfo>>{
        return repository.getAll()
    }

    /*
    fun getUsername(): String{
             return repository.getUsername()
    }
    fun getPhoto(): String = repository.getPhoto()


    fun getInfo():LiveData<List<ProfileInfo>> = repository.getAll()
*/


}