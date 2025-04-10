package com.alfa0059.kalkulatorakumulasicat.ui.screen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.alfa0059.kalkulatorakumulasicat.R
import com.alfa0059.kalkulatorakumulasicat.navigation.Screen
import com.alfa0059.kalkulatorakumulasicat.ui.theme.KalkulatorAkumulasiCatTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.app_name))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate(Screen.About.route)
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = stringResource(R.string.tentang_aplikasi),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        ScreenContent(Modifier.padding(innerPadding))
    }
}

@SuppressLint("AutoboxingStateCreation", "AutoboxingStateValueProperty")
@Composable
fun ScreenContent(modifier: Modifier = Modifier) {
    var lebar by rememberSaveable { mutableStateOf("") }
    var lebarError by rememberSaveable { mutableStateOf(false) }
    var tinggi by rememberSaveable { mutableStateOf("") }
    var tinggiError by rememberSaveable { mutableStateOf(false) }
    var lapisan by rememberSaveable { mutableStateOf("") }
    var lapisanError by rememberSaveable { mutableStateOf(false) }


    var panjangRuangan by rememberSaveable { mutableStateOf("") }
    var lebarRuangan by rememberSaveable { mutableStateOf("") }
    var tinggiRuangan by rememberSaveable { mutableStateOf("") }
    var lapisanRuangan by rememberSaveable { mutableStateOf("") }

    var panjangRuanganError by rememberSaveable { mutableStateOf(false) }
    var lebarRuanganError by rememberSaveable { mutableStateOf(false) }
    var tinggiRuanganError by rememberSaveable { mutableStateOf(false) }
    var lapisanRuanganError by rememberSaveable { mutableStateOf(false) }

    val radioOptions = listOf(
        stringResource(R.string.tembok),
        stringResource(R.string.kayu),
        stringResource(R.string.gypsum)
    )

    val isDropDownExpanded = rememberSaveable {
        mutableStateOf(false)
    }

    val mode = listOf(
        stringResource(R.string.mode_1),
        stringResource(R.string.mode_2)
    )

    val itemPosition = rememberSaveable {
        mutableIntStateOf(0)
    }

    var texture by rememberSaveable { mutableStateOf(radioOptions[0]) }

    var result by rememberSaveable { mutableFloatStateOf(0f) }

    val context = LocalContext.current

    Column (
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.kalkulator_intro),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth()
        )

        Box {
            Row (
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,

                modifier = Modifier.clickable {
                    isDropDownExpanded.value = true
                }
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = MaterialTheme.shapes.medium
                    )
                    .fillMaxWidth()
            ){
                Text(
                    modifier = Modifier
                        .padding(16.dp)
                        .weight(1f),
                    text = mode[itemPosition.intValue])
                Image(
                    painter = painterResource(id = R.drawable.baseline_arrow_drop_down_24),
                    contentDescription = "Drop Down Icon",
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            DropdownMenu(
                expanded = isDropDownExpanded.value,
                onDismissRequest = { isDropDownExpanded.value = false },
            ) {
                mode.forEachIndexed { index, mode ->
                    DropdownMenuItem(text = {
                        Text(text = mode)
                    },
                        onClick = {
                            isDropDownExpanded.value = false
                            itemPosition.intValue = index
                        })
                }
            }
        }


        if (itemPosition.intValue == 0) {
            OutlinedTextField(
                value = panjangRuangan,
                onValueChange = { panjangRuangan = it },
                label = { Text(text = stringResource(R.string.panjang_ruangan)) },
                trailingIcon = { IconPicker(panjangRuanganError, "m") },
                supportingText = { ErrorHint(panjangRuanganError) },
                isError = panjangRuanganError,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = lebarRuangan,
                onValueChange = { lebarRuangan = it },
                label = { Text(text = stringResource(R.string.lebar_ruangan)) },
                trailingIcon = { IconPicker(lebarRuanganError, "m") },
                supportingText = { ErrorHint(lebarRuanganError) },
                isError = lebarRuanganError,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = tinggiRuangan,
                onValueChange = { tinggiRuangan = it },
                label = { Text(text = stringResource(R.string.tinggi_ruangan)) },
                trailingIcon = { IconPicker(tinggiRuanganError, "m") },
                supportingText = { ErrorHint(tinggiRuanganError) },
                isError = tinggiRuanganError,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = lapisanRuangan,
                onValueChange = { lapisanRuangan = it },
                label = { Text(text = stringResource(R.string.jumlah_lapisan)) },
                trailingIcon = { IconPicker(lapisanRuanganError, stringResource(R.string.lapis)) },
                supportingText = { ErrorHint(lapisanRuanganError) },
                isError = lapisanRuanganError,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }

        if (itemPosition.intValue == 1) {
            OutlinedTextField(
                value = lebar,
                onValueChange = { lebar = it },
                label = { Text(text = stringResource(R.string.lebar_permukaan)) },
                trailingIcon = { IconPicker(lebarError, "m") },
                supportingText = { ErrorHint(lebarError) },
                isError = lebarError,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = tinggi,
                onValueChange = { tinggi = it },
                label = { Text(text = stringResource(R.string.tinggi_permukaan)) },
                trailingIcon = { IconPicker(tinggiError, "m") },
                supportingText = { ErrorHint(tinggiError) },
                isError = tinggiError,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = lapisan,
                onValueChange = { lapisan = it },
                label = { Text(text = stringResource(R.string.jumlah_lapisan)) },
                trailingIcon = { IconPicker(lapisanError, stringResource(R.string.lapis) ) },
                supportingText = { ErrorHint(lapisanError) },
                isError = lapisanError,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }




        Text(
            text = stringResource(R.string.jenis_permukaan),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(top = 8.dp)
        )

        Row (
            modifier = Modifier
                .fillMaxWidth()
        ){
            radioOptions.forEach { text ->
                TextureOption(
                    label = text,
                    isSelected = texture == text,
                    modifier = Modifier
                        .selectable(
                            selected = texture == text,
                            onClick = { texture = text },
                            role = Role.RadioButton
                        )
                        .weight(1f)
                )
            }
        }

        if (itemPosition.intValue == 1) {
            Button(
                onClick = {
                    lebarError = (lebar == "" || lebar == "0")
                    tinggiError = (tinggi == "" || tinggi == "0")
                    lapisanError = (lapisan == "" || lapisan == "0")
                    if (lebarError || tinggiError || lapisanError) return@Button

                    result = hitungJumlahCat(lebar.toFloat(), tinggi.toFloat(), lapisan.toFloat(), texture)
                },
                modifier = Modifier.padding(top = 8.dp),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
            ) {
                Text(text = stringResource(R.string.hitung))
            }
        }

        if (itemPosition.intValue == 0) {
            Button(
                onClick = {
                    panjangRuanganError = (panjangRuangan == "" || panjangRuangan == "0")
                    lebarRuanganError = (lebarRuangan == "" || lebarRuangan == "0")
                    tinggiRuanganError = (tinggiRuangan == "" || tinggiRuangan == "0")
                    lapisanRuanganError = (lapisanRuangan == "" || lapisanRuangan == "0")
                    if (panjangRuanganError || lebarRuanganError || tinggiRuanganError || lapisanRuanganError) return@Button
                    result = hitungJumlahCat(panjangRuangan.toFloat(), lebarRuangan.toFloat(),
                        tinggiRuangan.toFloat(), lapisanRuangan.toFloat(), texture)
                },
                modifier = Modifier.padding(top = 8.dp),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
            ) {
                Text(text = stringResource(R.string.hitung))
            }
        }



        if(result != 0f) {
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp),
                thickness = 1.dp
            )

            Text(
                text = stringResource(R.string.result, result ),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = {
                    val message = if (itemPosition.value == 0) {
                        context.getString(
                            R.string.bagikan_template_ruangan,
                            lebarRuangan, panjangRuangan, tinggiRuangan, lapisan, texture, result
                        )
                    } else {
                        context.getString(
                            R.string.bagikan_template_permukaan,
                            lebar, tinggi, lapisan, texture, result
                        )
                    }

                    shareData(context = context, message = message)
                },
                modifier = Modifier.padding(top = 8.dp),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
            ) {
                Text(text = stringResource(R.string.bagikan))
            }

        }

    }
}

@Composable
fun TextureOption(label : String, isSelected : Boolean, modifier: Modifier = Modifier) {
    Column  (
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = when(label) {
                stringResource(R.string.tembok) -> R.drawable.wall
                stringResource(R.string.kayu) -> R.drawable.wood
                else -> R.drawable.gypsum
            }),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .padding(bottom = 4.dp)
        )
        RadioButton(selected = isSelected, onClick = null)
    }
}


@Composable
fun IconPicker(isError: Boolean, unit: String){
    if(isError){
        Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
    } else {
        Text(text = unit)
    }
}

@Composable
fun ErrorHint(isError: Boolean){
    if(isError){
        Text(text = stringResource(R.string.input_invalid))
    }
}

private fun hitungJumlahCat(lebar: Float, tinggi: Float, lapisan: Float, texture: String): Float {
    return when (texture) {
        "Tembok", "Wall" -> {
            (((lebar * tinggi) * lapisan) / 10f) * 1.15f
        }
        "Kayu", "Wood" -> {
            (((lebar * tinggi) * lapisan) / 12f) * 1.15f
        }
        else -> {
            (((lebar * tinggi) * lapisan) / 9f) * 1.15f
        }
    }

}

private fun hitungJumlahCat(panjang : Float, lebar: Float, tinggi: Float, lapisan: Float, texture: String): Float {
    return when (texture) {
        "Tembok", "Wall" -> {
            ((panjang + lebar) * 2) * tinggi * lapisan / 10f * 1.15f
        }
        "Kayu", "Wood" -> {
            ((panjang + lebar) * 2) * tinggi * lapisan / 12f * 1.15f
        }
        else -> {
            ((panjang + lebar) * 2) * tinggi * lapisan / 9f * 1.15f
        }
    }
}

@SuppressLint("QueryPermissionsNeeded")
private fun shareData(context: Context, message: String) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }
    if (shareIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(shareIntent)
    }
}


@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun MainScreenPreview() {
    KalkulatorAkumulasiCatTheme {
        MainScreen(rememberNavController())
    }
}
