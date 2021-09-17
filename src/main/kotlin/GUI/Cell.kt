package GUI

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun inputField(
    typedTextState: MutableState<String>,
    labelName: String,
    onValueChanged: () -> Unit,
    dataClearer: () -> Unit,
    isBaseBinary: MutableState<Boolean>
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
        Text(if (isBaseBinary.value) "0b" else "0x")
        OutlinedTextField(
            value = typedTextState.value,
            modifier = Modifier.weight(weight = 1F),
            onValueChange = {
                typedTextState.value = it
                if (it == "") {
                    dataClearer()
                }
                else {
                    onValueChanged()
                }
            },
            label = { Text(text = labelName) },

            )

        Spacer(modifier = Modifier.width(8.dp))
    }
}

@Composable
fun inputFieldSimple(
    typedTextState: MutableState<String>,
    labelName: String,
    onValueChanged: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
        OutlinedTextField(
            value = typedTextState.value,
            modifier = Modifier.weight(weight = 1F),
            onValueChange = {
                typedTextState.value = it
                onValueChanged()
            },
            label = { Text(text = labelName) },
        )

        Spacer(modifier = Modifier.width(8.dp))
    }
}
