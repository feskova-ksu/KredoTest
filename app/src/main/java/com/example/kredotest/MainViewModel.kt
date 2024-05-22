package com.example.kredotest

import androidx.lifecycle.ViewModel
import com.example.kredotest.ui.data.Source
import com.example.kredotest.ui.data.mockCards
import com.example.kredotest.ui.data.mockCounts
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {

    private val _listOfCounts = MutableStateFlow(mockCounts)
    val listOfCounts = _listOfCounts.asStateFlow()

    private val _listOfCards = MutableStateFlow(mockCards)
    val listOfCards = _listOfCards.asStateFlow()


    private val _source = MutableStateFlow<Source?>(null)
    val source = _source.asStateFlow()


    fun onNewSource(source: Source) {
        _source.value = source
        when (source) {
            is Source.Card -> _listOfCards.update {
                it.map {
                    if (it != source) return@map it
                    it.copy(isSelected = true)
                }
            }

            is Source.Count -> _listOfCounts.update {
                it.map {
                    if (it != source) return@map it
                    it.copy(isSelected = true)
                }
            }
        }
    }

}