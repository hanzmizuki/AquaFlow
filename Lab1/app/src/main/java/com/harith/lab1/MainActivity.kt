package com.harith.lab1

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harith.lab1.ui.theme.Lab1Theme
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle

//COLOUR SECTION
//COLOUR SECTION
val BrandDark   = Color(0xFF3457B2)   // biru gelap → teks tajuk, logo
val BrandMain   = Color(0xFF5B86E5)   // biru utama → header, butang
val BrandLight  = Color(0xFF36D1DC)   // cyan terang → belakang logo
val Accent      = Color(0xFF36D1DC)   // coral → promo card (contrast)
val BgGrey      = Color(0xFFF2F4F5)
val CardWhite   = Color(0xFFFFFFFF)
val TextDark    = Color(0xFF1C2B2A)
val TextGrey    = Color(0xFF7A8785)


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab1Theme {
                Surface(modifier = Modifier.fillMaxSize(), color = BgGrey) {
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
                .padding(bottom = 80.dp) //space for bottom nav
        ) {
            HeaderSection()
            Spacer(modifier = Modifier.height(16.dp))
            AccountCard()
            Spacer(modifier = Modifier.height(16.dp))
            PromoCard()
            Spacer(modifier = Modifier.height(16.dp))
            RewardsCard()
            Spacer(modifier = Modifier.height(24.dp))
        }

        //bottom nav
        BottomNav(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        )
    }
}

//HEADER APP
@Composable
fun HeaderSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(BrandMain)
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
            }
            Spacer(modifier = Modifier.height(16.dp))

            //profile,unverified,coin and mail part
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Chip(text = "Profile", filled = false, chipColor = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Chip(text = "Unverified", filled = true, chipColor = Color(0xFFFF8A65))
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
                    painter = painterResource(id = R.drawable.envelope),
                    contentDescription = "Mesej",
                    modifier = Modifier.size(28.dp)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Welcome Back,", color = Color.White, fontSize = 16.sp, fontStyle = FontStyle.Italic)
            Text(
                text = "John Smith",
                color = Color.White,
                fontSize = 20.sp,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun Chip(text: String, filled: Boolean, chipColor: Color = Accent) {
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
            color = if (filled) Color.White else chipColor,
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
            .clip(RoundedCornerShape(16.dp))
            .background(CardWhite)
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
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo AquaFlow",
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(BrandLight)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(text = "John Smith", color = TextDark, fontWeight = FontWeight.Bold)
                    Text(text = "ASI12313", color = TextGrey, fontSize = 13.sp)
                }
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "RM 110.00", color = TextDark, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))
            Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(BgGrey))
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

/* -------------------- Announcement Card  -------------------- */
@Composable
fun PromoCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Accent)
            .padding(20.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Track your usage and save more water",
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(12.dp))
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(Color.White)
                        .padding(horizontal = 18.dp, vertical = 8.dp)
                ) {
                    Text(text = "Learn more", color = Accent, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

/* -------------------- REWARDS CARD -------------------- */
@Composable
fun RewardsCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFE0F2F1))
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


/* -------------------- BOTTOM NAV -------------------- */
@Composable
fun BottomNav(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .background(Color(0xFFB2DFDB))
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        NavItem(iconRes = R.drawable.home,    label = "Home",    active = true)
        NavItem(iconRes = R.drawable.info,    label = "Info",    active = false)
        NavItem(iconRes = R.drawable.bill,   label = "Bills",   active = false)
        NavItem(iconRes = R.drawable.gift,  label = "Reward",  active = false)
        NavItem(iconRes = R.drawable.profile, label = "Profile", active = false)
    }
}

@Composable
fun NavItem(iconRes: Int, label: String, active: Boolean) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        // bulatan belakang icon
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .then(
                    if (active) Modifier.background(CardWhite)
                    else Modifier
                )
                .padding(10.dp)
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = label,
                modifier = Modifier.size(22.dp)
            )
        }
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            fontSize = 14.sp,
            color = if (active) BrandMain else TextGrey,
            fontWeight = if (active) FontWeight.Bold else FontWeight.Normal
        )
    }
}