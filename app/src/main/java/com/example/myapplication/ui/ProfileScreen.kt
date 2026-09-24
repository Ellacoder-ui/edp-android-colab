package com.example.myapplication.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.domain.model.User

@Composable
fun ProfileScreen(user: User, onLogout: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ExamGradient)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card(
                colors = CardDefaults.cardColors(containerColor = Color(0x2569F0AE)),
                border = BorderStroke(1.dp, Color(0xFF69F0AE)),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text(
                        "You successfully logged in!",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF69F0AE),
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        "Welcome back, ${user.fullName}.",
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }
            }

            Text(
                "My Profile",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            ProfileRow("Full name", user.fullName)
            ProfileRow("Email", user.email)
            ProfileRow("Birthdate", user.birthdate)
            ageFrom(user.birthdate)?.let { ProfileRow("Age", "$it years old") }
            ProfileRow("User ID", user.id)

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = onLogout,
                shape = RoundedCornerShape(50),
                border = BorderStroke(1.5.dp, Color.White),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0x301D0538),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(
                    "Log out",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun ProfileRow(label: String, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0x30655B96), RoundedCornerShape(12.dp))
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Text(
            label,
            style = MaterialTheme.typography.labelMedium,
            color = Color.White.copy(alpha = 0.7f),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            value,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White,
            fontWeight = FontWeight.SemiBold
        )
    }
}

fun ageFrom(birthdate: String): Int? {
    val parts = birthdate.split("-")
    if (parts.size != 3) return null
    val y = parts[0].toIntOrNull() ?: return null
    val m = parts[1].toIntOrNull() ?: return null
    val d = parts[2].toIntOrNull() ?: return null

    val now = java.util.Calendar.getInstance()
    val ty = now.get(java.util.Calendar.YEAR)
    val tm = now.get(java.util.Calendar.MONTH) + 1
    val td = now.get(java.util.Calendar.DAY_OF_MONTH)

    var age = ty - y
    if (tm < m || (tm == m && td < d)) {
        age -= 1
    }
    return age
}