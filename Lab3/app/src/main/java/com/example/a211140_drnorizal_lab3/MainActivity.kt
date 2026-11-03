package com.example.a211140_drnorizal_lab3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a211140_drnorizal_lab3.ui.theme.A211140_DrNorizal_Lab3Theme
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

// LAB 3: semua warna hardcode Lab 2 (BrandMain, CardWhite, ...) dah dibuang.
// Sekarang semua warna ambil dari MaterialTheme.colorScheme (ui/theme/Color.kt + Theme.kt)


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            A211140_DrNorizal_Lab3Theme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
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
            WaterUsageCard()
            Spacer(modifier = Modifier.height(16.dp))
            RewardsCard()
            Spacer(modifier = Modifier.height(24.dp))
        }

        // jalur biru belakang status bar supaya content tak nampak bawah jam bila scroll
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .windowInsetsTopHeight(WindowInsets.statusBars)
                .background(MaterialTheme.colorScheme.primaryContainer)
        )

        //bottom nav (floating pill)
        BottomNav(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(horizontal = 24.dp, vertical = 16.dp)
        )
    }
}

//HEADER APP
@Composable
fun HeaderSection() {
    val colors = MaterialTheme.colorScheme
    // LAB 3: guna shapes.large dari tema, tapi bucu atas dibuang (rata)
    val headerShape = MaterialTheme.shapes.large.copy(
        topStart = CornerSize(0.dp),
        topEnd = CornerSize(0.dp)
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(headerShape)
            .background(colors.primaryContainer)   // #0072DD (core colour)
            .statusBarsPadding()
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
                    color = colors.onPrimaryContainer,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "A211140 Harith",
                    color = colors.onPrimaryContainer,
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(colors.onPrimaryContainer.copy(alpha = 0.2f))
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            //profile,unverified,coin and mail part
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Chip(text = "Profile", filled = false, chipColor = colors.onPrimaryContainer)
                Spacer(modifier = Modifier.width(8.dp))
                Chip(
                    text = "Unverified",
                    filled = true,
                    chipColor = colors.tertiaryContainer,      // oren dari tema (tertiary)
                    textColor = colors.onTertiaryContainer
                )
                Spacer(modifier = Modifier.weight(1f)) //push ke kanan

                // coin
                Row(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(colors.surface)
                        .padding(horizontal = 10.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.coin),
                        contentDescription = "Point",
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "260", color = colors.primary, style = MaterialTheme.typography.labelLarge)
                }
                Spacer(modifier = Modifier.width(12.dp))
                Image(
                    painter = painterResource(id = R.drawable.noti),
                    contentDescription = "Notifications",
                    colorFilter = ColorFilter.tint(colors.onPrimaryContainer),
                    modifier = Modifier.size(26.dp)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Welcome Back,",
                color = colors.onPrimaryContainer.copy(alpha = 0.85f),
                style = MaterialTheme.typography.bodyLarge,
                fontStyle = FontStyle.Italic
            )
            Text(
                text = "John Smith",
                color = colors.onPrimaryContainer,
                style = MaterialTheme.typography.headlineSmall,
                fontStyle = FontStyle.Italic
            )
        }
    }
}

@Composable
fun Chip(
    text: String,
    filled: Boolean,
    chipColor: Color,
    textColor: Color = chipColor
) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .then(
                if (filled) Modifier.background(chipColor)
                else Modifier.border(1.dp, chipColor, CircleShape)
            )
            .padding(horizontal = 14.dp, vertical = 6.dp)
    ) {
        Text(text = text, color = textColor, style = MaterialTheme.typography.labelMedium)
    }
}

/* -------------------- LAB 3: ANIMATION HELPER -------------------- */
// Anak panah yang berpusing 180° bila kad dibuka
@Composable
fun ExpandIcon(expanded: Boolean, modifier: Modifier = Modifier) {
    val rotation by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        label = "arrow rotation"
    )
    Icon(
        painter = painterResource(id = R.drawable.ic_expand_more),
        contentDescription = if (expanded) "Show less" else "Show more",
        tint = MaterialTheme.colorScheme.primary,
        modifier = modifier.rotate(rotation)
    )
}

// Spring yang sama macam codelab Woof: smooth, tak melantun
fun Modifier.smoothResize(): Modifier = this.animateContentSize(
    animationSpec = spring(
        dampingRatio = Spring.DampingRatioNoBouncy,
        stiffness = Spring.StiffnessMedium
    )
)

// Satu baris label : nilai (untuk butiran yang tersembunyi)
@Composable
fun DetailRow(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Text(
            text = label,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

//Account Card
//  ElevatedCard + tekan kad untuk expand/collapse butiran akaun
@Composable
fun AccountCard() {
    var expanded by rememberSaveable { mutableStateOf(false) }
    val colors = MaterialTheme.colorScheme

    ElevatedCard(
        onClick = { expanded = !expanded },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .smoothResize()   // animateContentSize: kad membesar/mengecil dengan lancar
                .padding(20.dp)
        ) {
            //button inside card named "Manage"
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "My Account (1)",
                    color = colors.primary,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = { },
                    contentPadding = PaddingValues(horizontal = 18.dp, vertical = 8.dp)
                ) {
                    Text(text = "Manage", style = MaterialTheme.typography.labelLarge)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Row: logo (Image) + nama/akaun + jumlah RM + anak panah
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Profile picture",
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(colors.primaryContainer)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(text = "John Smith", color = colors.onSurface, style = MaterialTheme.typography.titleSmall)
                    Text(text = "ASI12313", color = colors.onSurfaceVariant, style = MaterialTheme.typography.bodySmall)
                }
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "RM 30.00", color = colors.onSurface, style = MaterialTheme.typography.titleSmall)
                Spacer(modifier = Modifier.width(4.dp))
                ExpandIcon(expanded = expanded)
            }

            // expanded
            if (expanded) {
                Spacer(modifier = Modifier.height(12.dp))
                Surface(
                    color = colors.surfaceContainer,
                    shape = MaterialTheme.shapes.small,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp)) {
                        DetailRow(label = "Address", value = "No. 12, Jalan Air Jernih, Bangi")
                        DetailRow(label = "Meter no.", value = "MTR-0098712")
                        DetailRow(label = "Last bill", value = "RM 45.20")
                        DetailRow(label = "Due date", value = "15 Nov 2026")
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(color = colors.outlineVariant)
            Spacer(modifier = Modifier.height(4.dp))

            TextButton(
                onClick = { },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "+  Add Account", style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}

/* WATER USAGE CARD (TextField + Button + State */
// 4500f jadi "4,500", 45.5f kekal "45.5"
fun formatLitres(value: Float): String =
    if (value % 1f == 0f) "%,d".format(value.toInt()) else "%,.1f".format(value)

// Untuk isi TextField goal: 4500f jadi "4500" (tanpa koma)
fun plainNumber(value: Float): String =
    if (value % 1f == 0f) value.toInt().toString() else value.toString()

// LAB 3: aquaFieldColors() dibuang - OutlinedTextField sekarang ikut warna tema secara automatik

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
    val colors = MaterialTheme.colorScheme


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

    // warna status ikut role tema (primary = ok, tertiary = amaran, error = lebih)
    val barColor = when {
        isOverGoal -> colors.error
        isAheadOfPlan -> colors.tertiary
        else -> colors.primary
    }

    //progress bar & warna bergerak perlahan, bukan melompat
    val animatedProgress by animateFloatAsState(targetValue = progress, label = "usage progress")
    val animatedBarColor by animateColorAsState(targetValue = barColor, label = "usage color")

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
    // ElevatedCard
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .smoothResize()   // kad resize dengan lancar
                .padding(20.dp)
        ) {
            // Tajuk
            Text(text = "Water Usage", color = colors.primary, style = MaterialTheme.typography.titleMedium)
            Text(text = monthLabel, color = colors.onSurfaceVariant, style = MaterialTheme.typography.bodySmall)

            Spacer(modifier = Modifier.height(12.dp))

            //Montly goals set
            if (!isEditingGoal) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = "Monthly goal", color = colors.onSurfaceVariant, style = MaterialTheme.typography.bodySmall)
                        Text(
                            text = "${formatLitres(monthlyGoal)} L",
                            color = colors.onSurface,
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                    TextButton(onClick = {
                        goalInput = plainNumber(monthlyGoal)
                        isEditingGoal = true
                    }) {
                        Text(text = "Edit goal", style = MaterialTheme.typography.labelLarge)
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
                        shape = MaterialTheme.shapes.small,
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
                        shape = MaterialTheme.shapes.small,
                        modifier = Modifier.height(56.dp)
                    ) {
                        Text(text = "Save", style = MaterialTheme.typography.labelLarge)
                    }
                }
                if (goalError) {
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(text = "Please enter a goal above 0.", color = colors.error, style = MaterialTheme.typography.bodySmall)
                }
                TextButton(onClick = {
                    isEditingGoal = false
                    goalError = false
                    focusManager.clearFocus()
                }) {
                    Text(text = "Cancel", color = colors.onSurfaceVariant)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
            HorizontalDivider(color = colors.outlineVariant)
            Spacer(modifier = Modifier.height(12.dp))

            // Input usage for everyday
            Text(text = "Log today's usage", color = colors.onSurface, style = MaterialTheme.typography.labelLarge)
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
                    shape = MaterialTheme.shapes.small,
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
                    shape = MaterialTheme.shapes.small,
                    modifier = Modifier.height(56.dp)
                ) {
                    Text(text = "Add", style = MaterialTheme.typography.labelLarge)
                }
            }

            if (usageError) {
                Spacer(modifier = Modifier.height(6.dp))
                Text(text = "Please enter a number above 0.", color = colors.error, style = MaterialTheme.typography.bodySmall)
            }

            Spacer(modifier = Modifier.height(12.dp))

            //Monitor bar dynamic ui for every usage
            // LAB 3: OutlinedCard (kad bergaris, tiada bayang) di dalam ElevatedCard
            OutlinedCard(
                shape = MaterialTheme.shapes.small,
                colors = CardDefaults.outlinedCardColors(containerColor = colors.surfaceContainerLow),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
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
                            .clip(CircleShape)
                            .background(colors.surfaceContainerHighest)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(animatedProgress)
                                .height(10.dp)
                                .clip(CircleShape)
                                .background(animatedBarColor)
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Row {
                        Text(text = "$percentUsed% of goal used", color = colors.onSurface, style = MaterialTheme.typography.labelMedium)
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = "$daysLeft days left", color = colors.onSurface, style = MaterialTheme.typography.labelMedium)
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = message, color = colors.onSurface, style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}

// Satu kotak statistik kecil (label + nilai)
@Composable
fun UsageStat(label: String, value: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = label, color = MaterialTheme.colorScheme.onSurfaceVariant, style = MaterialTheme.typography.labelMedium)
        Text(text = value, color = MaterialTheme.colorScheme.onSurface, style = MaterialTheme.typography.titleMedium)
    }
}


/* REWARDS CARD  */
// LAB 3: Card (filled, warna secondaryContainer) + tekan untuk lihat cara kumpul point
@Composable
fun RewardsCard() {
    var expanded by rememberSaveable { mutableStateOf(false) }
    val colors = MaterialTheme.colorScheme

    Card(
        onClick = { expanded = !expanded },
        colors = CardDefaults.cardColors(
            containerColor = colors.secondaryContainer,
            contentColor = colors.onSecondaryContainer
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .smoothResize()
                .padding(20.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = "AquaRewardS", color = colors.primary, style = MaterialTheme.typography.titleLarge)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "Earn reward points from simple activities.", style = MaterialTheme.typography.bodySmall)
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        onClick = { },
                        contentPadding = PaddingValues(horizontal = 18.dp, vertical = 8.dp)
                    ) {
                        Text(text = "Join now", style = MaterialTheme.typography.labelLarge)
                    }
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = R.drawable.giftpromo),
                        contentDescription = "Reward gift",
                        modifier = Modifier.size(56.dp)
                    )
                    ExpandIcon(expanded = expanded)
                }
            }

            if (expanded) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "How to earn points", color = colors.primary, style = MaterialTheme.typography.labelLarge)
                Spacer(modifier = Modifier.height(4.dp))
                RewardRow(activity = "Log your usage today", points = "+5")
                RewardRow(activity = "Pay your bill on time", points = "+10")
                RewardRow(activity = "Stay under goal for 7 days", points = "+20")
            }
        }
    }
}

@Composable
fun RewardRow(activity: String, points: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = activity, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.weight(1f))
        Text(
            text = points,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
                .padding(horizontal = 10.dp, vertical = 2.dp)
        )
    }
}


// BOTTOM NAV (floating pill)
@Composable
fun BottomNav(modifier: Modifier = Modifier) {
    // LAB 3: Surface dengan shadowElevation ganti Modifier.shadow manual
    Surface(
        modifier = modifier,
        shape = CircleShape,
        color = MaterialTheme.colorScheme.surfaceContainerLowest,
        shadowElevation = 12.dp
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
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
}

@Composable
fun NavItem(iconRes: Int, label: String, active: Boolean) {
    val colors = MaterialTheme.colorScheme
    // bulatan biru untuk tab aktif and ikon sahaja (label jadi contentDescription)
    Box(
        modifier = Modifier
            .size(48.dp)
            .clip(CircleShape)
            .then(
                if (active) Modifier.background(colors.primary)
                else Modifier
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = label,
            colorFilter = ColorFilter.tint(if (active) colors.onPrimary else colors.onSurfaceVariant),
            modifier = Modifier.size(22.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AquaFlowLightPreview() {
    A211140_DrNorizal_Lab3Theme(darkTheme = false) {
        Surface(color = MaterialTheme.colorScheme.background) {
            AquaFlowHome()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AquaFlowDarkPreview() {
    A211140_DrNorizal_Lab3Theme(darkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.background) {
            AquaFlowHome()
        }
    }
}
