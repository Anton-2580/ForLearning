package com.example.noteappwsr_preparation.presentation.notes.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlin.reflect.KClass


@Composable
fun RadioGrid(
    selectedIdState: MutableIntState,
    items: List<KClass<*>>,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier,
    ) {
        for (i in items.indices) {
            RadioButton(
                selected = i == selectedIdState.intValue,
                onClick = { selectedIdState.intValue = i; onClick() }
            )
            Text(
                text = items[i].simpleName.toString(),
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}
