package com.example.noteappwsr_preparation.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.noteappwsr_preparation.presentation.NotesViewModel


@Composable
fun GridNotes(
    viewModel: NotesViewModel,
    isDelete: MutableState<Boolean>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(5.dp)
    ) {
        items(viewModel.state.value.notes) {
            Surface(
                modifier = modifier,
                color = Color(it.color),
                border = BorderStroke(1.dp, Color.LightGray),
                shape = RoundedCornerShape(10.dp)
            ) {
                Column {
                    Text(
                        text = it.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        modifier = Modifier.padding(6.dp)
                    )

                    Text(
                        text = it.content,
                        modifier = Modifier.padding(6.dp),
                        maxLines = 10,
                        overflow = TextOverflow.Ellipsis
                    )

                    ControlNote(viewModel, it, isDelete)
                }
            }
        }
    }
}
