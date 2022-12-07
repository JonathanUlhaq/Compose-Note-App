package com.belajar.notesapp.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.belajar.notesapp.R
import com.belajar.notesapp.model.Note
import com.belajar.notesapp.util.formatDate
import java.time.format.DateTimeFormatter


@Composable
fun TopBarApps() {
    TopAppBar(
        backgroundColor = Color.LightGray
    ) {
        Text(text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.weight(1F))
        Icon(imageVector = Icons.Rounded.Notifications,
            contentDescription = "Icon" )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TextField(
    text:String,
    onTextChange:(String) -> Unit,
    label:String,
    maxLine:Int = 1,
    imeAction: ImeAction = ImeAction.Done,
    keyboardAction:() -> Unit = {}
) {
   val keyboardController = LocalSoftwareKeyboardController.current
    OutlinedTextField(value = text ,
        onValueChange = onTextChange,
        label = { Text(text = label)},
        maxLines = maxLine,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(onDone = {
            keyboardAction.invoke()
            keyboardController?.hide()
        })
    )
}

@Composable
fun SaveButton(
    text: String,
    onClick:() -> Unit
) {
    Button(onClick = { onClick.invoke() },
            shape = CircleShape) {
        Text(text)
    }
}

@Composable
fun NoteRow(
    note:Note,
    onClickNote:(Note) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        shape = RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp),
        color = Color.LightGray
    ) {
            Column(
                Modifier.padding(16.dp)
                    .clickable { onClickNote.invoke(note) },
                horizontalAlignment = Alignment.Start
            ) {
                    Text(text = note.title,
                        fontSize = 16.sp)
                    Text(text = note.desc,
                        fontWeight = FontWeight.Bold)
                    Text(text = formatDate(note.entryDate.time))
            }
    }
}