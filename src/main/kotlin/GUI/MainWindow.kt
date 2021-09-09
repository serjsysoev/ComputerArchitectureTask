package GUI

import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.window.application

fun MainWindow() = application {
    Window(title = "String completer", size = IntSize(500, 900)) {
        val dataHandler = DataHandler()


        MaterialTheme {
            Column(modifier = Modifier.fillMaxSize()) {
                inputField(dataHandler.decimal_State, "десятичное")  {dataHandler.updateDataBy(EncodingType.Decimal)}
                inputField(dataHandler.noSign_State, "без знака") {dataHandler.updateDataBy(EncodingType.NoSign)}
                inputField(dataHandler.bitForSign_State, "знак под бит") {dataHandler.updateDataBy(EncodingType.BitForSign)}
                inputField(dataHandler.skip128_State, "сдвиг на 128") {dataHandler.updateDataBy(EncodingType.Skip128)}
                inputField(dataHandler.skip127_State, "сдвиг на 127") {dataHandler.updateDataBy(EncodingType.Skip127)}
                inputField(dataHandler.supplementTo2_State, "дополнить до 2") {dataHandler.updateDataBy(EncodingType.SupplementTo2)}
                inputField(dataHandler.supplementTo1_State, "дополнить до 1") {dataHandler.updateDataBy(EncodingType.SupplementTo1)}
                inputField(dataHandler.alternation_State, "с чередованием") {dataHandler.updateDataBy(EncodingType.Alternation)}
                inputField(dataHandler.baseMinus2_State, "основание -2") {dataHandler.updateDataBy(EncodingType.BaseMinus2)}
                inputField(dataHandler.symmetric_State, "симметричная") {dataHandler.updateDataBy(EncodingType.Symmetric)}
            }
        }
    }
}