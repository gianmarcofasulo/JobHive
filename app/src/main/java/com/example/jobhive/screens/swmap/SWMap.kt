package com.example.jobhive.screens.swmap

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import com.example.jobhive.datastore.SWISSCities
import com.example.jobhive.datastore.StoreDataUser
import com.example.jobhive.screens.profile.Preferences

@OptIn(ExperimentalTextApi::class)
@Composable
fun SWMapScreen(viewModel: SwMapViewModel) {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        // handle the result of the activity here, if needed
    }


    val dataStore = StoreDataUser(context)
    val salary = dataStore.getSalary.collectAsState(initial = 0)
    val nightLife = dataStore.getNightlife.collectAsState(initial = "")
    val occupation = dataStore.getOccupation.collectAsState(initial = "")
    val location = dataStore.getLocation.collectAsState(initial = "")
    val temperature = dataStore.getTemperature.collectAsState(initial = "")
    val hobby = dataStore.getHobby.collectAsState(initial = "")

    LaunchedEffect(key1 = true) {
        viewModel.scoreCalculator(
            preferences = Preferences(
                salary = salary.value ?: 0,
                city = location.value ?: "",
                hobby = hobby.value ?: "",
                occupation = occupation.value ?: "",
                hangoutTime = nightLife.value ?: "",
                temperature = temperature.value ?: ""
            )
        )

    }


    //impostazioni quadrato:
    val size = Size(120f, 120f)
    val textSize = Size(10f, 10f)

    val textMeasurer = rememberTextMeasurer()

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        //change in drawState for all the states JU to viewmodel.getScoreBy(city = SWISSCities.JURA) and so on for all the states
        drawState(textMeasurer, this, "ZH", viewModel.getScoreBy(city = SWISSCities.ZURICH), 30f + 50f, 240f, size)
        drawState(textMeasurer, this, "VD", viewModel.getScoreBy(city = SWISSCities.SION), 90f + 50f, 600f, size)
        drawState(textMeasurer, this, "GE", viewModel.getScoreBy(city = SWISSCities.GENEVA), 30f + 50f, 720f, size)
        drawState(textMeasurer, this, "FR", 1, 210f + 50f, 600f, size)
        drawState(textMeasurer, this, "NE", viewModel.getScoreBy(city = SWISSCities.NEUCHATEL), 210f + 50f, 480f, size)
        drawState(textMeasurer, this, "JU", viewModel.getScoreBy(city = SWISSCities.JURA), 210f + 50f, 360f, size)
        drawState(textMeasurer, this, "VS", viewModel.getScoreBy(city = SWISSCities.VS), 270f + 50f, 720f, size)
        drawState(textMeasurer, this, "BE", viewModel.getScoreBy(city = SWISSCities.BE), 330f + 50f, 600f, size)
        drawState(textMeasurer, this, "SO", viewModel.getScoreBy(city = SWISSCities.SO), 330f + 50f, 480f, size)
        drawStateDouble(textMeasurer, this, "BS/BL", viewModel.getScoreBy(city = SWISSCities.BSBL), 330f + 50f, 360f, size)
        drawState(textMeasurer, this, "AG", viewModel.getScoreBy(city = SWISSCities.AG), 450f + 50f, 360f, size)
        drawState(textMeasurer, this, "LU", 2, 450f + 50f, 480f, size)
        drawStateDouble(textMeasurer, this, "ow/nw", viewModel.getScoreBy(city = SWISSCities.OWNW), 450f + 50f, 600f, size)
        drawState(textMeasurer, this, "SH", 1, 570f + 50f, 240f, size)
        drawState(textMeasurer, this, "ZH", viewModel.getScoreBy(city = SWISSCities.ZH), 570f + 50f, 360f, size)
        drawState(textMeasurer, this, "ZG", 2, 570f + 50f, 480f, size)
        drawState(textMeasurer, this, "SZ", viewModel.getScoreBy(city = SWISSCities.SZ), 570f + 50f, 600f, size)
        drawState(textMeasurer, this, "UR", 1, 570f + 50f, 720f, size)
        drawState(textMeasurer, this, "TI", viewModel.getScoreBy(city = SWISSCities.TI), 570f + 50f, 840f, size)
        drawState(textMeasurer, this, "TG", viewModel.getScoreBy(city = SWISSCities.TG), 690f + 50f, 360f, size)
        drawState(textMeasurer, this, "SG", 2, 690f + 50f, 480f, size)
        drawState(textMeasurer, this, "GL", viewModel.getScoreBy(city = SWISSCities.GL), 690f + 50f, 600f, size)
        drawStateDouble(textMeasurer, this, "AR/AI", viewModel.getScoreBy(city = SWISSCities.ARAI), 810f + 50f, 420f, size)
        drawState(textMeasurer, this, "GR", viewModel.getScoreBy(city = SWISSCities.GR), 810f + 50f, 600f, size)

        //legenda:
        //bianco = not good at all
        this.drawRect(Color.Black, Offset(175f, 1100f), size, style = Stroke(6f));
        //rosso = not very good
        this.drawRect(Color.Red, Offset(375f, 1100f), size);
        //giallo = average
        this.drawRect(Color.Yellow, Offset(575f, 1100f), size);
        //verde = very good
        this.drawRect(Color.Green, Offset(775f, 1100f), size);

        this.drawText(textMeasurer, "Legend:", Offset(160f, 1025f))
        this.drawText(textMeasurer, "Very Low", Offset(160f, 1225f));
        this.drawText(textMeasurer, "Low", Offset(400f, 1225f));
        this.drawText(textMeasurer, "Average", Offset(560f, 1225f));
        this.drawText(textMeasurer, "High", Offset(800f, 1225f));

    }
}

@OptIn(ExperimentalTextApi::class)
fun drawState(
    textMeasurer: TextMeasurer,
    drawScope: DrawScope,
    stateName: String,
    stateScore: Int,
    x: Float,
    y: Float,
    size: Size
) {

    drawScope.drawRect(colorSet(stateScore), Offset(x, y), size)
    drawScope.drawRect(Color.Black, topLeft = Offset(x, y), size = size, style = Stroke(6f))
    val textOffset = Offset(x + 35f, y + 35f)

    drawScope.drawText(textMeasurer, stateName, textOffset)
}

@OptIn(ExperimentalTextApi::class)
fun drawStateDouble(
    textMeasurer: TextMeasurer,
    drawScope: DrawScope,
    stateName: String,
    stateScore: Int,
    x: Float,
    y: Float,
    size: Size
) {

    drawScope.drawRect(colorSet(stateScore), Offset(x, y), size)
    drawScope.drawRect(Color.Black, topLeft = Offset(x, y), size = size, style = Stroke(6f))
    val textOffset = Offset(x + 6f, y + 35f)

    drawScope.drawText(textMeasurer, stateName, textOffset)
}

fun colorSet(score: Int): Color = when (score) {
    1 -> Color.Red
    2 -> Color.Yellow
    3 -> Color.Green
    else -> Color.White
}

@Preview
@Composable
fun preview(){
    SWMapScreen(viewModel = SwMapViewModel())
}
