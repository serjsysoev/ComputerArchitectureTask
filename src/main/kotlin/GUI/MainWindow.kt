package GUI

import androidx.compose.desktop.Window
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.application
import java.awt.SystemColor.text

fun MainWindow() = application {
    Window(title = "String completer", size = IntSize(500, 900)) {
        val dataHandler = DataHandler()
        val baseLabel = mutableStateOf("Binary")

        MaterialTheme {
            Column(modifier = Modifier.fillMaxSize()) {
                Text(baseLabel.value,
                    modifier = Modifier.padding(8.dp).clickable(
                        onClick = {
                            dataHandler.isBaseBinary.value = !dataHandler.isBaseBinary.value
                            baseLabel.value = if (dataHandler.isBaseBinary.value) "binary" else "hex"
                        },

                    ))
                inputField(dataHandler.decimal_State, "десятичное", {dataHandler.updateDataBy(EncodingType.Decimal)}, dataHandler::clearCells)
                inputField(dataHandler.noSign_State, "без знака", {dataHandler.updateDataBy(EncodingType.NoSign)}, dataHandler::clearCells)
                inputField(dataHandler.bitForSign_State, "знак под бит", {dataHandler.updateDataBy(EncodingType.BitForSign)}, dataHandler::clearCells)
                inputField(dataHandler.skip128_State, "сдвиг на 128", {dataHandler.updateDataBy(EncodingType.Skip128)}, dataHandler::clearCells)
                inputField(dataHandler.skip127_State, "сдвиг на 127", {dataHandler.updateDataBy(EncodingType.Skip127)}, dataHandler::clearCells)
                inputField(dataHandler.supplementTo2_State, "дополнить до 2", {dataHandler.updateDataBy(EncodingType.SupplementTo2)}, dataHandler::clearCells)
                inputField(dataHandler.supplementTo1_State, "дополнить до 1", {dataHandler.updateDataBy(EncodingType.SupplementTo1)}, dataHandler::clearCells)
                inputField(dataHandler.alternation_State, "с чередованием", {dataHandler.updateDataBy(EncodingType.Alternation)}, dataHandler::clearCells)
                inputField(dataHandler.baseMinus2_State, "основание -2", {dataHandler.updateDataBy(EncodingType.BaseMinus2)}, dataHandler::clearCells)
                inputField(dataHandler.symmetric_State, "симметричная", {dataHandler.updateDataBy(EncodingType.Symmetric)}, dataHandler::clearCells)
            }
        }
    }
}