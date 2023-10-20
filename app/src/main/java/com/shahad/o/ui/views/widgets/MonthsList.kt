package com.shahad.o.ui.views.widgets

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shahad.o.ui.theme.OTheme
import kotlin.math.roundToInt

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MonthsList(
    modifier: Modifier,
    months: List<String>
) {

    val monthState = rememberLazyListState()
    val monthSnap = rememberSnapFlingBehavior(lazyListState = monthState)

    val shade1 = OTheme.colors.shade1
    val shade2 = OTheme.colors.shade2
    val width = LocalConfiguration.current.screenWidthDp
    val half =  (LocalConfiguration.current.screenWidthDp * 0.5)-(width*0.085)

    LazyRow(
        modifier.padding(top = 8.dp, bottom = 24.dp),
        state = monthState,
        flingBehavior = monthSnap,
        contentPadding = PaddingValues(horizontal = half.dp)
    ){
        itemsIndexed(months){index,month->
            val weight by remember {
                derivedStateOf {
                    val layoutInfo = monthState.layoutInfo
                    val visibleItemsInfo = layoutInfo.visibleItemsInfo
                    val itemInfo = visibleItemsInfo.firstOrNull { it.index == index}

                    itemInfo?.let {
                        //First item
                        if (itemInfo.index == 0 && itemInfo.offset == 0)
                            return@derivedStateOf FontWeight.ExtraBold

                        //Last item
                        val viewportWidth = layoutInfo.viewportEndOffset + layoutInfo.viewportStartOffset
                        if (itemInfo.index + 1 == layoutInfo.totalItemsCount &&
                            itemInfo.offset + itemInfo.size <= viewportWidth)
                            return@derivedStateOf FontWeight.ExtraBold

                        //Other items
                        val delta = 100
                        val center = monthState.layoutInfo.viewportEndOffset / 2 - half.roundToInt()
                        val childCenter = it.offset + (it.size /1.5).toInt()
                        val target = childCenter - center
                        if (target in -delta..delta) return@derivedStateOf FontWeight.ExtraBold
                    }
                    FontWeight.Normal
                }
            }
            val color by remember {
                derivedStateOf {
                    val layoutInfo = monthState.layoutInfo
                    val visibleItemsInfo = layoutInfo.visibleItemsInfo
                    val itemInfo = visibleItemsInfo.firstOrNull { it.index == index}
                    itemInfo?.let {
                        //First item
                        if (itemInfo.index == 0 && itemInfo.offset == 0)
                            return@derivedStateOf shade1

                        //Last item
                        val viewportWidth = layoutInfo.viewportEndOffset + layoutInfo.viewportStartOffset
                        if (itemInfo.index + 1 == layoutInfo.totalItemsCount &&
                            itemInfo.offset + itemInfo.size <= viewportWidth)
                            return@derivedStateOf shade1

                        //Other items
                        val delta = 100
                        val center = monthState.layoutInfo.viewportEndOffset / 2 - half.roundToInt()
                        val childCenter = it.offset + (it.size /1.5).toInt()
                        val target = childCenter - center
                        if (target in -delta..delta) return@derivedStateOf shade1
                    }
                    shade2
                }
            }
            Text(
                month,
                style= TextStyle(
                    fontWeight = weight,
                    fontSize = 18.sp,
                    color = color
                ),
                modifier= Modifier.padding(horizontal = (width * 0.057).dp)
            )
        }
    }

}