package com.shahad.o.ui.views.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shahad.o.util.Record
import com.shahad.o.ui.theme.OTheme

@Composable
fun RecordBody(
    modifier: Modifier = Modifier,
    records: List<Record>,
    currentIndex: Int,
    onClickBack: () -> Unit,
    onClickYes: () -> Unit,
    onClickNo: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(OTheme.colors.primaryVariant)
            .padding(24.dp)
    ) {

        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            modifier = Modifier
                .size(32.dp)
                .clickable { onClickBack() },
            tint = Color(0xFFDBE6EC)
        )
        LinearIndicator(
            modifier = Modifier
                .padding(top = 16.dp)
                .padding(horizontal = 8.dp),
            currentIndex = currentIndex,
            total = records.size

        )

        if(records.isNotEmpty()){

            QuestionPage(
                modifier = Modifier
                    .padding(top = 32.dp, bottom = 24.dp)
                    .weight(1F),
                question = records[currentIndex].question,
                imageUrl = records[currentIndex].imageUrl,
                onClickYes = onClickYes,
                onClickNo = onClickNo
            )
        }

    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RecordBodyPreview() {
    OTheme {
        Surface {
            RecordBody(
                records = listOf(
                    Record(
                        0,
                        "Are you happy ? ",
                        "https://avatars.githubusercontent.com/u/118618262?v=4"
                    ),
                    Record(
                        1,
                        "Are you happy ? ",
                        "https://avatars.githubusercontent.com/u/118618262?v=4"
                    ),
                    Record(
                        3,
                        "Are you happy ? ",
                        "https://avatars.githubusercontent.com/u/118618262?v=4"
                    ),
                ), currentIndex = 2,  onClickBack = {}, onClickNo = {}, onClickYes = {}
            )
        }
    }
}
