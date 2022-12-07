package com.belajar.notesapp.screen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.belajar.notesapp.components.NoteRow
import com.belajar.notesapp.components.SaveButton
import com.belajar.notesapp.components.TextField
import com.belajar.notesapp.components.TopBarApps
import com.belajar.notesapp.data.dataSource
import com.belajar.notesapp.model.Note

@Composable
fun NoteScreen(
    note:List<Note>,
    addNote:(Note) -> Unit,
    removeNote:(Note) -> Unit
) {

    val title = remember {
        mutableStateOf("")
    }

    val input = remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopBarApps()
        }
    ) {
        Column(
            Modifier
                .padding(it)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
                TextField(text = title.value,
                    onTextChange = {
                                   title.value = it
                    } , label = "Title" ) {

                }
            Spacer(modifier = Modifier.height(12.dp))
            TextField(text = input.value,
                onTextChange = {
                    input.value = it
                } , label = "Add a Note" ){

            }
            Spacer(modifier = Modifier.height(10.dp))
            SaveButton(text = "Save") {
                if (title.value.isNotEmpty() && input.value.isNotEmpty())
                    addNote(Note(title = title.value,
                        desc = input.value))
                    input.value = ""
                    title.value = ""
                Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show()
            }
            Divider(Modifier.padding(10.dp))
            LazyColumn(
                Modifier.padding(horizontal = 12.dp)
                ,content = {
                items(note) { note ->
                    NoteRow(note = note) {
                        removeNote.invoke(note)
                    }
                }
            })
        }
    }
}
