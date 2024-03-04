package com.example.project

    import android.graphics.Bitmap
    import androidx.lifecycle.ViewModel
    import androidx.lifecycle.viewModelScope
    import kotlinx.coroutines.delay
    import kotlinx.coroutines.flow.MutableStateFlow
    import kotlinx.coroutines.flow.asStateFlow
    import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

        private val _bitmaps = MutableStateFlow<List<Bitmap>>(emptyList())
        val bitmaps = _bitmaps.asStateFlow()

        private val _isReady = MutableStateFlow(false)
        val isReady = _isReady.asStateFlow()

        init {
            viewModelScope.launch {
                delay(2000L)
                _isReady.value = true
            }
        }

        fun onTakePhoto(bitmap: Bitmap) {
            _bitmaps.value += bitmap
        }
    }
