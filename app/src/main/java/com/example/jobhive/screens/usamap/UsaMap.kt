import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import com.example.jobhive.datastore.USAcities
import com.example.jobhive.datastore.StoreDataUser
import com.example.jobhive.screens.profile.Preferences
import com.example.jobhive.screens.usamap.UsaMapViewModel

@OptIn(ExperimentalTextApi::class)
@Composable
fun MapScreen(viewModel: UsaMapViewModel) {
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
        //AK
        drawRect(color = colorSet(viewModel.getScoreBy(city = USAcities.JUNEAU)), topLeft = Offset(900f, 200f), size = size)
        drawRect(color = Color.Black, topLeft = Offset(900f, 200f), size = size, style = Stroke(6f))
        val AKoffset = Offset(990f, 235f)
        rotate(90f, AKoffset) {
            drawText(textMeasurer, "AK", AKoffset)
        }
        //WA
        val WAoffset = Offset(660f, 200f)
        drawRect(color = colorSet(viewModel.getScoreBy(city = USAcities.OLYMPIA)), topLeft = WAoffset, size = size)
        drawRect(color = Color.Black, topLeft = WAoffset, size = size, style = Stroke(6f))
        val WAtextOffset = Offset(WAoffset.x * 1.13f, WAoffset.y * 1.13f)
        rotate(90f, WAtextOffset) {
            drawText(textMeasurer, "WA", WAtextOffset)
        }

        //OR
        drawState(textMeasurer, this, "OR", viewModel.getScoreBy(city = USAcities.SALEM), 540f, 200f, size)
        //CA
        drawState(textMeasurer, this, "CA",  viewModel.getScoreBy(city = USAcities.SACRAMENTO), 420f, 200f, size)
        //HI
        drawState(textMeasurer, this, "HI",  viewModel.getScoreBy(city = USAcities.HONOLULU), 60f, 200f, size)
        //ID
        drawState(textMeasurer, this, "ID",  viewModel.getScoreBy(city = USAcities.BOISE), 660f, 320f, size)
        //NV
        drawState(textMeasurer, this, "NV",  viewModel.getScoreBy(city = USAcities.CARSONCITY), 540f, 320f, size)
        //UT
        drawState(textMeasurer, this, "UT",  viewModel.getScoreBy(city = USAcities.SALTWAKECITY), 420f, 320f, size)
        //AZ
        drawState(textMeasurer, this, "AZ",  viewModel.getScoreBy(city = USAcities.PHOENIX), 300f, 320f, size)
        //MTScore
        drawState(textMeasurer, this, "MT", viewModel.getScoreBy(city = USAcities.HELENA), 660f, 440f, size)
        //WY
        drawState(textMeasurer, this, "WY",  viewModel.getScoreBy(city = USAcities.CHEYENNE), 540f, 440f, size)
        //CO
        drawState(textMeasurer, this, "CO",  viewModel.getScoreBy(city = USAcities.DENVER), 420f, 440f, size)
        //NM
        drawState(textMeasurer, this, "NM",  viewModel.getScoreBy(city = USAcities.SANTAFE), 300f, 440f, size)
        //ND
        drawState(textMeasurer, this, "ND", viewModel.getScoreBy(city = USAcities.BISMARCK), 660f, 560f, size)
        //SD
        drawState(textMeasurer, this, "SD", viewModel.getScoreBy(city = USAcities.PIERRE), 540f, 560f, size)
        //NE
        drawState(textMeasurer, this, "NE", viewModel.getScoreBy(city = USAcities.LINCOLN), 420f, 560f, size)
        //KS
        drawState(textMeasurer, this, "KS", viewModel.getScoreBy(city = USAcities.TOPEKA), 300f, 560f, size)
        //OK
        drawState(textMeasurer, this, "OK", viewModel.getScoreBy(city = USAcities.OKLAHOMACITY), 180f, 560f, size)
        //TX
        drawState(textMeasurer, this, "TX",viewModel.getScoreBy(city = USAcities.AUSTIN) , 60f, 560f, size)
        //MN
        drawState(textMeasurer, this, "MN", viewModel.getScoreBy(city = USAcities.STPAUL), 660f, 680f, size)
        //IA
        drawState(textMeasurer, this, "IA", viewModel.getScoreBy(city = USAcities.DESMOINES), 540f, 680f, size)
        //MO
        drawState(textMeasurer, this, "MO", viewModel.getScoreBy(city = USAcities.JEFFERSONCITY), 420f, 680f, size)
        //AR
        drawState(textMeasurer, this, "AR", viewModel.getScoreBy(city = USAcities.LITTLEROCK), 300f, 680f, size)
        //LA
        drawState(textMeasurer, this, "LA", viewModel.getScoreBy(city = USAcities.WASHINGTON), 180f, 680f, size)
        //WI
        drawState(textMeasurer, this, "WI", viewModel.getScoreBy(city = USAcities.MADISON), 780f, 800f, size)
        //IL
        drawState(textMeasurer, this, "IL", viewModel.getScoreBy(city = USAcities.SPRINGFIELD), 660f, 800f, size)
        //IN
        drawState(textMeasurer, this, "IN", viewModel.getScoreBy(city = USAcities.INDIANAPOLIS), 540f, 800f, size)
        //KY
        drawState(textMeasurer, this, "KY", viewModel.getScoreBy(city = USAcities.FRANKFORT), 420f, 800f, size)
        //TN
        drawState(textMeasurer, this, "TN", viewModel.getScoreBy(city = USAcities.NASHVILLE), 300f, 800f, size)
        //MS
        drawState(textMeasurer, this, "MS", viewModel.getScoreBy(city = USAcities.JACKSON), 180f, 800f, size)
        //MI
        drawState(textMeasurer, this, "MI", viewModel.getScoreBy(city = USAcities.LANSING), 660f, 920f, size)
        //OH
        drawState(textMeasurer, this, "OH", viewModel.getScoreBy(city = USAcities.COLUMBUS), 540f, 920f, size)
        //WV
        drawState(textMeasurer, this, "WV", viewModel.getScoreBy(city = USAcities.CHARLESTON), 420f, 920f, size)
        //NC
        drawState(textMeasurer, this, "NC", viewModel.getScoreBy(city = USAcities.RALEIGH), 300f, 920f, size)
        //AL
        drawState(textMeasurer, this, "AL", viewModel.getScoreBy(city = USAcities.MONTGOMERY), 180f, 920f, size)
        //PA
        drawState(textMeasurer, this, "PA", viewModel.getScoreBy(city = USAcities.HARRISBURG), 540f, 1040f, size)
        //VA
        drawState(textMeasurer, this, "VA", viewModel.getScoreBy(city = USAcities.RICHMOND), 420f, 1040f, size)
        //SC
        drawState(textMeasurer, this, "SC", viewModel.getScoreBy(city = USAcities.COLUMBIA), 300f, 1040f, size)
        //GA
        drawState(textMeasurer, this, "GA", viewModel.getScoreBy(city = USAcities.ATLANTA), 180f, 1040f, size)
        //NY
        drawState(textMeasurer, this, "NY", viewModel.getScoreBy(city = USAcities.ALBANY), 660f, 1160f, size)
        //NJ
        drawState(textMeasurer, this, "NJ", viewModel.getScoreBy(city = USAcities.TRENTON), 540f, 1160f, size)
        //MD
        drawState(textMeasurer, this, "MD", viewModel.getScoreBy(city = USAcities.ANNAPOLIS), 420f, 1160f, size)
        //DC
        drawState(textMeasurer, this, "DC", viewModel.getScoreBy(city = USAcities.RALEIGH), 300f, 1160f, size)
        //FL
        drawState(textMeasurer, this, "FL", viewModel.getScoreBy(city = USAcities.TALLAHASSEE), 60f, 1160f, size)
        //VT
        drawState(textMeasurer, this, "VT", viewModel.getScoreBy(city = USAcities.MONTPELIER), 780f, 1280f, size)
        //MA
        drawState(textMeasurer, this, "MA", viewModel.getScoreBy(city = USAcities.BOSTON), 660f, 1280f, size)
        //CT
        drawState(textMeasurer, this, "CT", viewModel.getScoreBy(city = USAcities.HARTFORD), 540f, 1280f, size)
        //DE
        drawState(textMeasurer, this, "DE", viewModel.getScoreBy(city = USAcities.DOVER), 420f, 1280f, size)
        //ME
        drawState(textMeasurer, this, "ME", viewModel.getScoreBy(city = USAcities.AUGUSTA), 900f, 1400f, size)
        //NH
        drawState(textMeasurer, this, "NH", viewModel.getScoreBy(city = USAcities.CONCORD), 780f, 1400f, size)
        //RI
        drawState(textMeasurer, this, "RI", viewModel.getScoreBy(city = USAcities.PROVIDENCE), 540f, 1400f, size)

        //legenda:
        //bianco = not good at all
        this.drawRect(Color.Black, Offset(175f, 1700f), size, style = Stroke(6f));
        //rosso = not very good
        this.drawRect(Color.Red, Offset(375f, 1700f), size);
        //giallo = average
        this.drawRect(Color.Yellow, Offset(575f, 1700f), size);
        //verde = very good
        this.drawRect(Color.Green, Offset(775f, 1700f), size);


        this.drawText(textMeasurer, "Legend:", Offset(160f, 1625f))
        this.drawText(textMeasurer, "Very Low", Offset(160f, 1825f));
        this.drawText(textMeasurer, "Low", Offset(400f, 1825f));
        this.drawText(textMeasurer, "Average", Offset(560f, 1825f));
        this.drawText(textMeasurer, "High", Offset(800f, 1825f));

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
    val textOffset = Offset(x + 90f, y + 35f)
    drawScope.rotate(90f, textOffset) {
        drawScope.drawText(textMeasurer, stateName, textOffset)
    }
}

fun colorSet(score: Int): Color = when (score) {
    1 -> Color.Red
    2 -> Color.Yellow
    3 -> Color.Green
    else -> Color.White
}