package com.shahad.o.ui.views.widgets

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.EmojiSupportMatch
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shahad.o.ui.theme.OTheme
import com.shahad.o.ui.util.getIndexOfCenter
import com.shahad.o.ui.util.isIndexCenter
import com.shahad.o.ui.util.scrollAndCentralizeItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DaysList(
    modifier: Modifier,
    moods: List<String>,
    initialIndex: Int,
    onScroll: (Int) -> Unit,
) {

    val daysState = rememberLazyListState()
    val daysSnap = rememberSnapFlingBehavior(lazyListState = daysState)

    val centerTextColor = OTheme.colors.background
    val unCenterTextColor = OTheme.colors.onBackground

    val centerBackgroundColor = OTheme.colors.onBackground
    val unCenterBackgroundColor = OTheme.colors.background
    val width = LocalConfiguration.current.screenWidthDp
    val half = (LocalConfiguration.current.screenWidthDp * 0.5) - (width * 0.05)

    LazyRow(
        modifier.padding(top = 8.dp, bottom = 24.dp),
        state = daysState,
        flingBehavior = daysSnap,
        contentPadding = PaddingValues(horizontal = half.dp)
    ) {
        itemsIndexed(moods) { index, mood ->
            val isCenter by remember {
                derivedStateOf {
                    index.isIndexCenter(daysState.layoutInfo, padding = half)
                }
            }
            val backgroundColor1= if(isCenter) centerBackgroundColor else unCenterBackgroundColor
            val textColor1= if(isCenter) centerTextColor else unCenterTextColor
            val scale = if(isCenter) 1f else 0.8f

            Column {
                Box(
                    modifier = Modifier
                        .padding(horizontal = (width * 0.02).dp)
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(backgroundColor1),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "${ index + 1 }",
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = textColor1,
                            fontWeight = FontWeight(500)
                        ),
                    )


                }


                Box(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .padding(horizontal = (width * 0.02).dp)
                        .size(40.dp)
                        .clip(CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        moods[index],
                        style = TextStyle(
                            platformStyle = PlatformTextStyle(
                                emojiSupportMatch = EmojiSupportMatch.None,
                            ),
                            fontSize = 30.sp * scale,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier.clipToBounds()
                    )
                }

            }
        }

    }

    LaunchedEffect({}) {
        daysState.scrollAndCentralizeItem(initialIndex, half)
    }

    LaunchedEffect(daysState.isScrollInProgress) {
        if(!daysState.isScrollInProgress) {
            daysState.getIndexOfCenter(half)?.let(onScroll)
        }
    }

}