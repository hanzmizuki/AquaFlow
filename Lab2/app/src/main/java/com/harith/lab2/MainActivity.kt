package com.harith.lab2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harith.lab2.ui.theme.Lab2Theme
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

//COLOUR SECTION (tema biru lembut)
val BrandDark   = Color(0xFF1565C0)   // biru gelap → tajuk, nama
val BrandMain   = Color(0xFF0072DD)   // biru terang → butang utama, nav aktif
val BrandSoft   = Color(0xFFD6E9FC)   // biru cair → pill coin
val BrandLight  = Color(0xFFBFE0FC)   // biru cair → belakang logo akaun
val Accent      = Color(0xFF4FB3FF)   // biru langit → aksen kecil
val BgBlue      = Color(0xFFE8F2FD)   // latar skrin
val CardWhite   = Color(0xFFFFFFFF)   // latar kad
val CardBorder  = Color(0xFFCFE2F6)   // border kad & divider
val FieldBg     = Color(0xFFF3F8FE)   // latar TextField
val TextDark    = Color(0xFF0F1B3D)   // teks utama (navy)
val TextGrey    = Color(0xFF5B6B82)   // teks kecil (kelabu kebiruan)

// LAB 2: warna untuk kad water usage
val UsageBox    = Color(0xFFEDF5FE)   // latar kotak monitor
val UsageTrack  = Color(0xFFD3E6FA)   // track progress bar
val UsageGood   = Color(0xFF0072DD)   // on track
val UsageWarn   = Color(0xFFE09A1A)   // guna lebih laju dari plan
val UsageOver   = Color(0xFFD9480F)   // lebih goal bulanan
val ErrorRed    = Color(0xFFB3261E)   // mesej error


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab2Theme {
                Surface(modifier = Modifier.fillMaxSize(), color = BgBlue) {
                    AquaFlowHome()
                }
            }
        }
    }
}

@Composable
fun AquaFlowHome() {
    //box bertindih dekat bottom-nav kt atas content
    Box(modifier = Modifier.fillMaxSize()) {
        //content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 110.dp) //space for floating bottom nav
        ) {
            HeaderSection()
            Spacer(modifier = Modifier.height(16.dp))
            AccountCard()
            Spacer(modifier = Modifier.height(16.dp))
            WaterUsageCard()   // LAB 2: ganti PromoCard()
            Spacer(modifier = Modifier.height(16.dp))
            RewardsCard()
            Spacer(modifier = Modifier.height(24.dp))
        }

        //bottom nav (floating pill)
        BottomNav(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp)
        )
    }
}

//HEADER APP
@Composable
fun HeaderSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomStart = 28.dp, bottomEnd = 28.dp))
            .background(
                Brush.linearGradient(listOf(BrandMain, BrandDark))   // gradient biru
            )
            .padding(horizontal = 20.dp, vertical = 24.dp)
    ) {
        Column {
            // Nama aqua
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.aqua),
                    contentDescription = "AquaFlow logo",
                    modifier = Modifier
                        .size(28.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "AquaFlow",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "Harith",
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(Color.White.copy(alpha = 0.2f))
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            //profile,unverified,coin and mail part
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Chip(text = "Profile", filled = false, chipColor = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Chip(
                    text = "Unverified",
                    filled = true,
                    chipColor = Color(0xFFFFE1D2),     // oren cair
                    textColor = Color(0xFFB4410E)      // oren gelap
                )
                Spacer(modifier = Modifier.weight(1f)) //push ke kanan

                // coin
                Row(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.White)
                        .padding(horizontal = 10.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.coin),
                        contentDescription = "Point",
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "260", color = BrandDark, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.width(12.dp))
                Image(
                    painter = painterResource(id = R.drawable.noti),
                    contentDescription = "Notifications",
                    colorFilter = ColorFilter.tint(Color.White),   // loceng jadi putih
                    modifier = Modifier.size(26.dp)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Welcome Back,",
                color = Color.White.copy(alpha = 0.85f),
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic
            )
            Text(
                text = "Jony Father",
                color = Color.White,
                fontSize = 22.sp,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun Chip(
    text: String,
    filled: Boolean,
    chipColor: Color = Accent,
    textColor: Color = if (filled) Color.White else chipColor
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .then(
                if (filled) Modifier.background(chipColor)
                else Modifier.border(1.dp, chipColor, RoundedCornerShape(50))
            )
            .padding(horizontal = 14.dp, vertical = 6.dp)
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

//Account Card
@Composable
fun AccountCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(CardWhite)
            .border(1.dp, CardBorder, RoundedCornerShape(20.dp))
            .padding(20.dp)
    ) {
        Column {
            //button inside card named "Manage"
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "My Account (1)",
                    color = BrandDark,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(BrandMain)
                        .padding(horizontal = 18.dp, vertical = 8.dp)
                ) {
                    Text(text = "Manage", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Row: logo (Image) + nama/akaun + jumlah RM
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.jony),
                    contentDescription = "Logo AquaFlow",
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(BrandLight)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(text = "Jony Father", color = TextDark, fontWeight = FontWeight.Bold)
                    Text(text = "ASI12313", color = TextGrey, fontSize = 13.sp)
                }
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "RM 110.00", color = TextDark, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))
            Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(CardBorder))
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "+  Add Account",
                color = BrandMain,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

/* -------------------- LAB 2: WATER USAGE CARD (TextField + Button + State) -------------------- */
// 4500f jadi "4,500", 45.5f kekal "45.5"
fun formatLitres(value: Float): String =
    if (value % 1f == 0f) "%,d".format(value.toInt()) else "%,.1f".format(value)

// Untuk isi TextField goal: 4500f jadi "4500" (tanpa koma)
fun plainNumber(value: Float): String =
    if (value % 1f == 0f) value.toInt().toString() else value.toString()

// Warna TextField ikut tema biru (bukan ungu default)
@Composable
fun aquaFieldColors(): TextFieldColors = OutlinedTextFieldDefaults.colors(
    focusedBorderColor = BrandMain,
    unfocusedBorderColor = CardBorder,
    focusedLabelColor = BrandMain,
    unfocusedLabelColor = TextGrey,
    cursorColor = BrandMain,
    focusedContainerColor = FieldBg,
    unfocusedContainerColor = FieldBg
)

@Composable
fun WaterUsageCard() {
    // STATE
    // Input dari TextField
    var litresInput by remember { mutableStateOf("") }   // usage yang user taip
    var goalInput by remember { mutableStateOf("") }     // goal yang user taip

    // Data usage (rememberSaveable: tak hilang bila skrin diputar)
    var todayLitres by rememberSaveable { mutableStateOf(0f) }
    var monthLitres by rememberSaveable { mutableStateOf(0f) }
    var monthlyGoal by rememberSaveable { mutableStateOf(4500f) }

    // State untuk UI
    var isEditingGoal by remember { mutableStateOf(false) }
    var usageError by remember { mutableStateOf(false) }
    var goalError by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current


    val calendar = remember { Calendar.getInstance() }
    val monthLabel = remember { SimpleDateFormat("MMMM yyyy", Locale.ENGLISH).format(calendar.time) }
    val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
    val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    val daysLeft = daysInMonth - dayOfMonth + 1

    // NILAI DIKIRA
    val percentUsed = (monthLitres / monthlyGoal * 100).toInt()
    val progress = (monthLitres / monthlyGoal).coerceIn(0f, 1f)
    val remaining = monthlyGoal - monthLitres
    val isOverGoal = monthLitres > monthlyGoal
    val dailyBudget = if (remaining > 0f) remaining / daysLeft else 0f
    val plannedByToday = monthlyGoal * dayOfMonth / daysInMonth
    val isAheadOfPlan = !isOverGoal && monthLitres > plannedByToday

    val barColor = when {
        isOverGoal -> UsageOver
        isAheadOfPlan -> UsageWarn
        else -> UsageGood
    }

    val message = when {
        monthLitres == 0f ->
            "No usage logged yet. Add today's usage to start tracking."
        isOverGoal ->
            "You're ${formatLitres(monthLitres - monthlyGoal)} L over your monthly goal. Try to cut down for the rest of the month."
        isAheadOfPlan ->
            "Heads up: you're using water faster than planned. Try to keep under ${"%,d".format(dailyBudget.toInt())} L per day."
        else ->
            "On track! ${formatLitres(remaining)} L left for $monthLabel."
    }

    // Button
    fun addUsage() {
        val value = litresInput.toFloatOrNull()
        if (value == null || value <= 0f) {
            usageError = true
        } else {
            todayLitres += value
            monthLitres += value
            litresInput = ""
            usageError = false
            focusManager.clearFocus()
        }
    }

    fun saveGoal() {
        val value = goalInput.toFloatOrNull()
        if (value == null || value <= 0f) {
            goalError = true
        } else {
            monthlyGoal = value
            goalInput = ""
            goalError = false
            isEditingGoal = false
            focusManager.clearFocus()
        }
    }

    // UI
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(CardWhite)
            .border(1.dp, CardBorder, RoundedCornerShape(20.dp))
            .padding(20.dp)
    ) {
        // Tajuk
        Text(
            text = "Water Usage",
            color = BrandDark,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Text(text = monthLabel, color = TextGrey, fontSize = 13.sp)

        Spacer(modifier = Modifier.height(12.dp))

        //Montly goals set
        if (!isEditingGoal) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = "Monthly goal", color = TextGrey, fontSize = 13.sp)
                    Text(
                        text = "${formatLitres(monthlyGoal)} L",
                        color = TextDark,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                TextButton(onClick = {
                    goalInput = plainNumber(monthlyGoal)
                    isEditingGoal = true
                }) {
                    Text(text = "Edit goal", color = BrandMain, fontWeight = FontWeight.Bold)
                }
            }
        } else {
            Row(verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(
                    value = goalInput,
                    onValueChange = {
                        goalInput = it
                        goalError = false
                    },
                    label = { Text("Monthly goal (L)") },
                    placeholder = { Text("e.g. 4500") },
                    singleLine = true,
                    isError = goalError,
                    shape = RoundedCornerShape(14.dp),
                    colors = aquaFieldColors(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = { saveGoal() }),
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Button(
                    onClick = { saveGoal() },
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = BrandMain),
                    modifier = Modifier.height(56.dp)
                ) {
                    Text(text = "Save", fontWeight = FontWeight.Bold)
                }
            }
            if (goalError) {
                Spacer(modifier = Modifier.height(6.dp))
                Text(text = "Please enter a goal above 0.", color = ErrorRed, fontSize = 13.sp)
            }
            TextButton(onClick = {
                isEditingGoal = false
                goalError = false
                focusManager.clearFocus()
            }) {
                Text(text = "Cancel", color = TextGrey)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(CardBorder))
        Spacer(modifier = Modifier.height(12.dp))

        // Input usage for everyday
        Text(
            text = "Log today's usage",
            color = TextDark,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            //textfield for input liters number
            OutlinedTextField(
                value = litresInput,
                onValueChange = {
                    litresInput = it
                    usageError = false
                },
                label = { Text("Litres used") },
                placeholder = { Text("e.g. 45") },
                singleLine = true,
                isError = usageError,
                shape = RoundedCornerShape(14.dp),
                colors = aquaFieldColors(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = { addUsage() }),
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(10.dp))


            Button(
                onClick = { addUsage() },
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = BrandMain),
                modifier = Modifier.height(56.dp)
            ) {
                Text(text = "Add", fontWeight = FontWeight.Bold)
            }
        }

        if (usageError) {
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = "Please enter a number above 0.", color = ErrorRed, fontSize = 13.sp)
        }

        Spacer(modifier = Modifier.height(12.dp))

        //Monitor bar dynamic ui for every usage
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(UsageBox)
                .border(1.dp, CardBorder, RoundedCornerShape(14.dp))
                .padding(12.dp)
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                UsageStat(
                    label = "Today",
                    value = "${formatLitres(todayLitres)} L",
                    modifier = Modifier.weight(1f)
                )
                UsageStat(
                    label = "This month",
                    value = "${formatLitres(monthLitres)} L",
                    modifier = Modifier.weight(1f)
                )
                UsageStat(
                    label = "Daily budget",
                    value = "${"%,d".format(dailyBudget.toInt())} L",
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Progress bar custom (track + fill)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
                    .clip(RoundedCornerShape(50))
                    .background(UsageTrack)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(progress)
                        .height(10.dp)
                        .clip(RoundedCornerShape(50))
                        .background(barColor)
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Row {
                Text(text = "$percentUsed% of goal used", color = TextDark, fontSize = 12.sp)
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "$daysLeft days left", color = TextDark, fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = message, color = TextDark, fontSize = 13.sp)
        }
    }
}

// Satu kotak statistik kecil (label + nilai)
@Composable
fun UsageStat(label: String, value: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = label, color = TextGrey, fontSize = 12.sp)
        Text(
            text = value,
            color = TextDark,
            fontSize = 18.sp,
            fontWeight = FontWeight.ExtraBold
        )
    }
}


/* REWARDS CARD  */
@Composable
fun RewardsCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(BrandSoft)
            .border(1.dp, CardBorder, RoundedCornerShape(20.dp))
            .padding(20.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "AquaRewardS", color = BrandDark, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Earn reward points from simple activities.", color = TextGrey, fontSize = 13.sp)
                Spacer(modifier = Modifier.height(12.dp))
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(BrandMain)
                        .padding(horizontal = 18.dp, vertical = 8.dp)
                ) {
                    Text(text = "Join now", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
            Spacer(modifier = Modifier.width(12.dp))
            Image(
                painter = painterResource(id = R.drawable.giftpromo),
                contentDescription = "Reward gift",
                modifier = Modifier.size(56.dp)
            )
        }
    }
}


// BOTTOM NAV (floating pill)
@Composable
fun BottomNav(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .shadow(
                elevation = 12.dp,
                shape = RoundedCornerShape(50),
                ambientColor = BrandMain,
                spotColor = BrandMain
            )
            .clip(RoundedCornerShape(50))
            .background(CardWhite)
            .padding(horizontal = 12.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        NavItem(iconRes = R.drawable.home,    label = "Home",    active = true)
        NavItem(iconRes = R.drawable.info,    label = "Info",    active = false)
        NavItem(iconRes = R.drawable.bill,    label = "Bills",   active = false)
        NavItem(iconRes = R.drawable.gift,    label = "Reward",  active = false)
        NavItem(iconRes = R.drawable.profile, label = "Profile", active = false)
    }
}

@Composable
fun NavItem(iconRes: Int, label: String, active: Boolean) {
    // bulatan biru untuk tab aktif and ikon sahaja (label jadi contentDescription)
    Box(
        modifier = Modifier
            .size(48.dp)
            .clip(CircleShape)
            .then(
                if (active) Modifier.background(BrandMain)
                else Modifier
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = label,
            colorFilter = ColorFilter.tint(if (active) Color.White else TextGrey),
            modifier = Modifier.size(22.dp)
        )
    }
}