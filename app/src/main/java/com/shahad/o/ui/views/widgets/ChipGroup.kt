package com.shahad.o.ui.views.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shahad.o.ui.theme.OTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> ChipGroup(
    modifier: Modifier = Modifier,
    items: List<T>,
    selected: T? = null,
    onSelected: (T) -> Unit = {}
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items) {
            FilterChip(
                border = FilterChipDefaults.filterChipBorder(
                    borderColor = OTheme.colors.primary,
                ),
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = OTheme.colors.background,
                    selectedContainerColor = OTheme.colors.primary,
                    labelColor = OTheme.colors.onBackground,
                    selectedLabelColor = Color.White
                ),
                selected = it == selected, onClick = { onSelected(it) }, label = {
                    Text(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                        text = it.toString(),
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Start
                        )
                    )
                })
        }
    }
}