package com.example.noteappwsr_preparation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.noteappwsr_preparation.presentation.add_edit_note.AddEditNoteScreen
import com.example.noteappwsr_preparation.presentation.notes.NotesScreen
import com.example.noteappwsr_preparation.ui.theme.NoteAppWSR_PreparationTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteAppWSR_PreparationTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = NotesScreen) {
                        composable<NotesScreen> {
                            NotesScreen(navController = navController)
                        }
                        composable<AddEditNoteScreen> {
                            val args = it.toRoute<AddEditNoteScreen>()
                            AddEditNoteScreen(
                                navController = navController,
                                noteColor = args.noteColor
                            )
                        }
                    }
                }
            }
        }
    }
}

@Serializable
object NotesScreen


/**
 * ключ экрана [AddEditNoteScreen]
 *
 * @property noteId Id заметки
 * @property noteColor Цвет в числовом представлении
 */
@Serializable
data class AddEditNoteScreen(
    val noteId: Int?,
    val noteColor: Int
)