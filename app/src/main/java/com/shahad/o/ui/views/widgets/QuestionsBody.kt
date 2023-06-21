package com.shahad.o.ui.views.widgets


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shahad.o.ui.theme.OTheme
import com.shahad.o.util.Record

@Composable
fun QuestionsBody(
    modifier: Modifier = Modifier,
    questions: List<Record>
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
    ) {
        items(
            questions,
        ) {
            QuestionItem(modifier = Modifier, question = it)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun QuestionsBodyPreview() {
    OTheme {
        Surface {
            QuestionsBody(
                questions = listOf(
                    Record(1, "Do you pray and thank god for this day ?Do you pray and thank god for this day ?", "", true, 1),
                    Record(1, "Are you happy? ", "", true, 1),
                    Record(1, "Are you happy? ", "", true, 1),
                    Record(1, "Are you happy? ", "", true, 1),
                )
            )
        }
    }
}
