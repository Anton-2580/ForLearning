package com.example.noteappwsr_preparation.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.noteappwsr_preparation.domain.util.NoteOrder
import com.example.noteappwsr_preparation.domain.util.OrderType
import kotlin.reflect.full.primaryConstructor


@Composable
fun SortingNotes(
    selectedOrderIdState: MutableIntState,
    selectedOrderTypeIdState: MutableIntState,
    changeOrder: (order: NoteOrder) -> Unit
) {
    val orders = NoteOrder::class.sealedSubclasses
    val orderTypes = listOf(OrderType.Ascending, OrderType.Descending)

    fun orderChange() {
        changeOrder(
            orders[selectedOrderIdState.intValue].primaryConstructor!!.call(
                orderTypes[selectedOrderTypeIdState.intValue]
            )
        )
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp, 0.dp),
        contentAlignment = Alignment.TopEnd
    ) {
        Column {
            RadioGrid(selectedOrderIdState, orders) {
                orderChange()
            }
            RadioGrid(selectedOrderTypeIdState, orderTypes.map { it::class }) {
                orderChange()
            }
        }
    }
}
