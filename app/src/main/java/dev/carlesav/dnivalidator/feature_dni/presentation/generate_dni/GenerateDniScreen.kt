package dev.carlesav.dnivalidator.feature_dni.presentation.generate_dni

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.carlesav.dnivalidator.R
import dev.carlesav.dnivalidator.core.TestTags
import kotlinx.coroutines.launch

@Composable
fun GenerateDniScreen(
    viewModel: GenerateDniViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.dark_color))
            .wrapContentSize(Alignment.Center)
    ) {
        val scope = rememberCoroutineScope()
        val dniText = rememberSaveable { mutableStateOf("") }

        Text(
            text = "Generate DNI",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 10.dp),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )

        TextField(
            modifier = Modifier
                .width(300.dp)
                .testTag(TestTags.TEXT_INPUT_GENERATE),
            value = dniText.value,
            onValueChange = {
                viewModel.resetDniResult()
                dniText.value = it
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.White
            )
        )

        Button(
            modifier = Modifier
                .width(300.dp)
                .padding(top = 10.dp)
                .testTag(TestTags.BTN_GENERATE),
            onClick = {
                scope.launch {
                    viewModel.generateDni(dniText.value)
                }
            },
            colors = ButtonDefaults.textButtonColors(
                contentColor = colorResource(id = R.color.dark_color),
                backgroundColor = Color.White
            )
        ) {
            Text("Click to generate")
        }

        if (viewModel.dniResultState.value.isGenerated) {
            Text(
                text = if (viewModel.dniResultState.value.isValid) {
                    viewModel.dniResultState.value.message
                } else stringResource(
                    id = R.string.invalid_document
                ),
                fontWeight = FontWeight.Bold,
                color = if (viewModel.dniResultState.value.isValid) {
                    Color.Green
                } else {
                    Color.White
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp)
                    .testTag(TestTags.GENERATION_RESULT),
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
        }
    }
}