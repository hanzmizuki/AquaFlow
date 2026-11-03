package com.example.a211140_drnorizal_lab3.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

// LAB 3: bentuk (shape) bucu bulat untuk semua komponen
val AppShapes = Shapes(
    extraSmall = RoundedCornerShape(8.dp),
    small = RoundedCornerShape(14.dp),     // TextField, butang, kotak monitor
    medium = RoundedCornerShape(20.dp),    // Card (Card guna shapes.medium secara default)
    large = RoundedCornerShape(20.dp),     // bucu bawah header
    extraLarge = RoundedCornerShape(32.dp)
)
