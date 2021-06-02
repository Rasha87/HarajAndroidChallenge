package  com.example.harajtask.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harajtask.data.HarajData
import com.example.harajtask.utils.Resource
import com.mindorks.framework.mvvm.data.repository.MainRepository
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout

class MainViewModel(private val jsonFileString : String?, private val mainRepository: MainRepository) : ViewModel() {
    private val harajData = MutableLiveData<Resource<List<HarajData>>>()

    init {
        fetchUsers()
    }



    private fun fetchUsers() {
        viewModelScope.launch {
            harajData.postValue(Resource.loading(null))
            try {
                withTimeout(100) {
                    val harajDataFromAsset = mainRepository.getHarajData(jsonFileString);
                    harajData.postValue(Resource.success(harajDataFromAsset))
                }
            } catch (e: TimeoutCancellationException) {
                harajData.postValue(Resource.error("TimeoutCancellationException", null))
            } catch (e: Exception) {
                harajData.postValue(Resource.error("Something Went Wrong", null))
            }
        }
    }

    fun getHarajData(): LiveData<Resource<List<HarajData>>> {
        return harajData
    }
}