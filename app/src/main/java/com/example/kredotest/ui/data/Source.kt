package com.example.kredotest.ui.data

sealed class Source(
    open val id: Int,
    open val name: String,
    open val numbers: String,
    open val amount: Int,
    open val isSelected: Boolean
) {
    data class Card(
        override val id: Int = 0,
        override val name: String = "",
        override val numbers: String = "",
        override val amount: Int = 0,
        override val isSelected: Boolean = false
    ) : Source(id, name, numbers, amount, isSelected)

    data class Count(
        override val id: Int = 0,
        override val name: String = "",
        override val numbers: String = "",
        override val amount: Int = 0,
        override val isSelected: Boolean = false
    ) : Source(id, name, numbers, amount, isSelected)

}