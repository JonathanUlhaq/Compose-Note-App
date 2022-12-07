package com.belajar.notesapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.belajar.notesapp.model.Note
import com.belajar.notesapp.screen.NoteScreen
import com.belajar.notesapp.screen.NoteScreenViewModel
import com.belajar.notesapp.ui.theme.NotesAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           NotesApp {

               val viewModel:NoteScreenViewModel by viewModels()
               val listNote = viewModel.noteList.collectAsState().value

                NoteScreen(
                    note = listNote,
                    addNote = {
                        viewModel.insertNote(it)

                    },
                    removeNote = {
                        viewModel.deleteNote(it)

                    }
                )
           }
        }
    }
}

@Composable
fun NotesApp(content:@Composable()() -> Unit) {
    NotesAppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            content.invoke()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NotesAppTheme {

    }
}