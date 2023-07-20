package presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import presentation.screen.common.BackButtonScaffold

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun ComponentScreen() {
    var isChecked by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf(false) }
    var isEnabled by remember { mutableStateOf(false) }
    var progress by remember { mutableStateOf(50f) }
    var text by remember { mutableStateOf("") }

    BackButtonScaffold("UI components") {
        Column(
            modifier = Modifier.padding(it).padding(all = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {
                // Do something
            }) {
                Text(text = "Button")
            }

            Text(text = "This is a text component.")

            Card(
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text(text = "This is a card component.")
            }

            Checkbox(checked = isChecked, onCheckedChange = { isChecked = it })
            RadioButton(
                selected = selectedItem,
                onClick = { selectedItem = !selectedItem },
                colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colorScheme.primary)
            )
            Switch(checked = isEnabled, onCheckedChange = { isEnabled = it })
            Slider(
                value = progress,
                onValueChange = { progress = it },
                valueRange = 0f..100f,
                steps = 10,
                modifier = Modifier.padding(all = 8.dp)
            ) {
                Text(text = "Slider")
            }
            TextField(
                value = text,
                onValueChange = { text = it },
                label = { Text(text = "TextField") },
                modifier = Modifier.padding(all = 8.dp)
            )
            Button(
                onClick = { },
                modifier = Modifier.padding(all = 8.dp)
            ) {
                Text(text = "Button")
            }
            CircularProgressIndicator()
            Chip(
                {},
                colors = ChipDefaults.chipColors(backgroundColor = MaterialTheme.colorScheme.primary)
            ) {
                Text(text = "Chip")
            }
            FloatingActionButton(
                onClick = { }
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
            }
        }
    }
}