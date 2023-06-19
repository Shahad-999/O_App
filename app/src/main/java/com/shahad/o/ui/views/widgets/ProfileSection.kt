package com.shahad.o.ui.views.widgets


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.shahad.o.ui.theme.OTheme
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.shahad.o.R
import com.shahad.o.util.UserData

@Composable
fun ProfileSection(
    modifier: Modifier = Modifier,
    user: UserData
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(OTheme.colors.primary, RoundedCornerShape(24.dp))
            .padding(24.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = user.profilePictureUrl,
            contentDescription = "Profile",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(64.dp)
                .border(width = 1.dp, color = Color.White, shape = CircleShape)
                .fillMaxSize()
                .clip(CircleShape),
            error = painterResource(id = R.drawable.logo),
        )

        Spacer(modifier = Modifier.width(16.dp))
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = user.userName ?: "UNKNOWN USER",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = user.userId,
                style = TextStyle(
                    color = Color.White.copy(alpha = 0.63F),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium
                )
            )

        }
    }

}


@Preview(showBackground = true)
@Composable
fun ProfileSectionPreview() {
    OTheme {
        Surface {
            ProfileSection(
                user = UserData.defaultUser
            )
        }
    }
}
