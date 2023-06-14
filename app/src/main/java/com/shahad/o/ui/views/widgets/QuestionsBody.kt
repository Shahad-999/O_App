package com.shahad.o.ui.views.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shahad.o.util.Record
import com.shahad.o.ui.theme.OTheme

@Composable
fun QuestionsBody(
    modifier: Modifier = Modifier,
    questions: List<Record>,
    currentIndex: Int,
    onClickYes: () -> Unit,
    onClickNo: () -> Unit,
) {
    Column(
        modifier = modifier
    ) {

        LinearIndicator(
            modifier = Modifier
                .padding(top = 16.dp)
                .padding(horizontal = 8.dp),
            currentIndex = currentIndex,
            total = questions.size

        )

        if(questions.isNotEmpty()){
            QuestionPage(
                modifier = Modifier
                    .padding(top = 32.dp, bottom = 24.dp)
                    .weight(1F),
                question = questions[currentIndex].question,
                imageUrl = questions[currentIndex].imageUrl,
                onClickYes = onClickYes,
                onClickNo = onClickNo
            )
        }

    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun QuestionsBodyPreview() {
    OTheme {
        Surface {
            QuestionsBody(
                questions = listOf(
                    Record(
                        0,
                        "Are you happy ? ",
                        "https://avatars.githubusercontent.com/u/118618262?v=4",
                         true,
                        1
                    ),
                    Record(
                        1,
                        "Are you happy ? ",
                        "https://avatars.githubusercontent.com/u/118618262?v=4",
                        true,
                        1),
                    Record(
                        3,
                        "Are you happy ? ",
                        "https://avatars.githubusercontent.com/u/118618262?v=4",
                        true,
                        1),
                ), currentIndex = 2,  onClickNo = {}, onClickYes = {}
            )
        }
    }
}
