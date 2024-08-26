import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.node.CanFocusChecker.start
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.wear.compose.material.Chip
import com.example.bienvenidoalcurso.R
import kotlinx.coroutines.NonDisposableHandle.parent


@Composable
fun LazyColumnExample() {
    LazyColumn {
        items(20) { index ->
            Text(
                text = "Item $index",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
fun LazyRowExample() {
    LazyRow {
        items(10) { index ->
            Chip(
                onClick = { /* Handle click */ },
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Chip $index")
            }
        }
    }
}

@Composable
fun GridExample() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize()
    ) {
        items(10) { index ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Grid Item $index",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun ConstraintLayoutExample() {
    androidx.constraintlayout.compose.ConstraintLayout {
        val (text1, text2) = createRefs()

        Text(
            text = "Hello, ConstraintLayout!",
            modifier = Modifier.constrainAs(text1) {
                top.linkTo(parent.top, margin = 16.dp)
                start.linkTo(parent.start, margin = 16.dp)
            }
        )

        Text(
            text = "This is an example.",
            modifier = Modifier.constrainAs(text2) {
                top.linkTo(text1.bottom, margin = 16.dp)
                start.linkTo(parent.start, margin = 16.dp)
            }
        )
    }
}

@Composable
fun ScaffoldExample() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Scaffold Example") })
        },
        bottomBar = {
            BottomAppBar {
                Text("Bottom Navigation")
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Text("Content goes here", modifier = Modifier.padding(16.dp))
        }
    }
}

@Composable
fun SurfaceExample() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Text("Surface Example", modifier = Modifier.padding(16.dp))
    }
}

@Composable
fun ChipExample() {
    Chip(
        onClick = { /* Handle click */ },
        modifier = Modifier.padding(8.dp)
    ) {
        Text("Chip Example")
    }
}

@Composable
fun BackdropScaffoldExample() {
    val scaffoldState = rememberBackdropScaffoldState(BackdropValue.Revealed)
    BackdropScaffold(
        scaffoldState = scaffoldState,
        backLayerContent = {
            Text("Back Layer", modifier = Modifier.padding(16.dp))
        },
        frontLayerContent = {
            Text("Front Layer", modifier = Modifier.padding(16.dp))
        },
        appBar = {
            TopAppBar(title = { Text("BackdropScaffold Example") })
        }
    )
}

@Composable
fun FlowRowExample() {
    FlowRow(
        modifier = Modifier.padding(8.dp),
        mainAxisSpacing = 8.dp,
        crossAxisSpacing = 8.dp
    ) {
        for (index in 0 until 10) {
            Chip(
                onClick = { /* Handle click */ },
                modifier = Modifier.padding(4.dp)
            ) {
                Text("Item $index")
            }
        }
    }
}

@Composable
fun FlowColumnExample() {
    FlowColumn(
        modifier = Modifier.padding(8.dp),
        mainAxisSpacing = 8.dp,
        crossAxisSpacing = 8.dp
    ) {
        for (index in 0 until 10) {
            Card(
                modifier = Modifier.padding(4.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Text("Item $index", modifier = Modifier.padding(16.dp))
            }
        }
    }
}



@Composable
fun AlertDialogExample(showDialog: Boolean, onDismiss: () -> Unit) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = { Text("Alert Dialog") },
            text = { Text("This is an example of an AlertDialog.") },
            confirmButton = {
                TextButton(onClick = { onDismiss() }) {
                    Text("OK")
                }
            }
        )
    }
}

@Composable
fun CardExample() {
    Card(
        modifier = Modifier.padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Text(
            text = "This is a Card",
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun CheckboxExample() {
    var checked by remember { mutableStateOf(false) }

    Checkbox(
        checked = checked,
        onCheckedChange = { checked = it }
    )
}

@Composable
fun FloatingActionButtonExample() {
    FloatingActionButton(onClick = { /* Handle click */ }) {
        Icon(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = "FAB Icon")
    }
}

@Composable
fun ImageExample() {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_foreground),
        contentDescription = "Example Image",
        modifier = Modifier.size(100.dp)
    )
}

@Composable
fun ProgressBarExample() {
    var isLoading by remember { mutableStateOf(true) }

    if (isLoading) {
        CircularProgressIndicator(modifier = Modifier.padding(16.dp))
    }
}

@Composable
fun RadioButtonExample() {
    var selectedOption by remember { mutableStateOf("Option 1") }

    Column {
        Row(modifier = Modifier.padding(8.dp)) {
            RadioButton(
                selected = selectedOption == "Option 1",
                onClick = { selectedOption = "Option 1" }
            )
            Text("Option 1", modifier = Modifier.padding(start = 8.dp))
        }
        Row(modifier = Modifier.padding(8.dp)) {
            RadioButton(
                selected = selectedOption == "Option 2",
                onClick = { selectedOption = "Option 2" }
            )
            Text("Option 2", modifier = Modifier.padding(start = 8.dp))
        }
    }
}

@Composable
fun SliderExample() {
    var sliderPosition by remember { mutableStateOf(0.5f) }

    Slider(
        value = sliderPosition,
        onValueChange = { sliderPosition = it },
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
fun SpacerExample() {
    Column {
        Text("Above Spacer")
        Spacer(modifier = Modifier.height(16.dp))
        Text("Below Spacer")
    }
}

@Composable
fun SwitchExample() {
    var isChecked by remember { mutableStateOf(false) }

    Switch(
        checked = isChecked,
        onCheckedChange = { isChecked = it }
    )
}

@Composable
fun TopAppBarExample() {
    TopAppBar(
        title = { Text("Top App Bar") },
        actions = {
            IconButton(onClick = { /* Handle click */ }) {
                Icon(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = "App Icon")
            }
        }
    )
}

@Composable
fun BottomNavigationExample() {
    BottomAppBar {
        IconButton(onClick = { /* Handle click */ }) {
            Icon(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = "Home")
        }
        IconButton(onClick = { /* Handle click */ }) {
            Icon(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = "Search")
        }
    }
}

@Composable
fun DialogExample(showDialog: Boolean, onDismiss: () -> Unit) {
    if (showDialog) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Surface(
                modifier = Modifier.padding(16.dp),
                shape = MaterialTheme.sh
