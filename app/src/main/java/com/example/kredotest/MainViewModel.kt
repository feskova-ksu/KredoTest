package com.example.kredotest

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.kredotest.tools.dateFormat
import com.example.kredotest.ui.data.FormData
import com.example.kredotest.ui.data.Source
import com.example.kredotest.ui.data.mockCards
import com.example.kredotest.ui.data.mockCounts
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.*

class MainViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(MainUIState())
    val uiState = _uiState.asStateFlow()

    fun onNewSource(source: Source) {
        _uiState.update {
            it.copy(source = source)
        }
        checkButtonEnabled()
    }

    fun onAmountChanged(newAmount: String) {
        try {
            _uiState.update {
                it.copy(
                    form = it.form.copy(
                        amount = newAmount.toInt()
                    )
                )
            }
            checkButtonEnabled()
        } catch (e: Exception) {
            Log.e("onAmountChanged", "Error = ${e.message}")
        }
    }

    fun onSwitchChanged(isChecked:Boolean){
        _uiState.update {
            it.copy(switchState = isChecked)
        }
    }
    fun onPurposeChanged(newPurpose: String) {
        try {
            _uiState.update {
                it.copy(
                    form = it.form.copy(
                        purpose = newPurpose
                    )
                )
            }
            checkButtonEnabled()
        } catch (e: Exception) {
            Log.e("onPurposeChanged", "Error = ${e.message}")
        }
    }

    fun getAllData(): String {
        return "source = ${_uiState.value.source?.name.toString()}\nform = amount:${_uiState.value.form.amount}, goal:${_uiState.value.form.purpose}"
    }

    fun saveDate(dateInMillis: Long?) {
        dateInMillis?.let {
            val dateString = dateFormat.format(Date(dateInMillis)).replace("/", ".", true)
            _uiState.update {
                it.copy(
                    selectedDate = dateString
                )
            }
        }
    }

    private fun checkButtonEnabled() {
        _uiState.value.let { state ->
            val amountValid = state.form.amount != null && state.form.amount.toString().isNotEmpty()
            val sourceValid =
                state.source != null && state.source.amount >= (state.form.amount ?: 0)
            _uiState.update {
                it.copy(isActionButtonEnabled = amountValid && sourceValid)
            }
        }
    }
}

data class MainUIState(
    val cards: List<Source.Card> = mockCards,
    val counts: List<Source.Count> = mockCounts,
    val source: Source? = null,
    val form: FormData = FormData(),
    val isActionButtonEnabled: Boolean = false,
    val switchState: Boolean = false,
    val selectedDate: String = dateFormat.format(Date(System.currentTimeMillis()))
        .replace("/", ".", true)
)

