package com.shahad.o.ui.views.widgets


import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shahad.o.R
import com.shahad.o.ui.theme.OTheme
import com.shahad.o.util.Record
import com.shahad.o.util.toPositiveAnswer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionItem(
    modifier: Modifier = Modifier,
    question: Record,
    onQuestionChange: (Int, String) -> Unit,
    onPositiveAnswerChange: (Int, Boolean) -> Unit
) {
    Column(
        modifier = modifier
            .padding(vertical = 8.dp)
            .clip(shape = RoundedCornerShape(16.dp))
            .border(1.dp, OTheme.colors.primary, shape = RoundedCornerShape(16.dp))
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 16.dp),
        ) {
        Row(
            modifier = modifier
                .height(60.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.q),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            BasicTextField(
                value = question.question,
                onValueChange = {
                    onQuestionChange(question.order, it)
                },
                textStyle = TextStyle(
                    color = OTheme.colors.shade1,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                ),
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Positive Answer",
                style = TextStyle(
                    color = OTheme.colors.shade2,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Start
                ),
            )
            Spacer(modifier = Modifier.weight(1F))
            ChipGroup(items = listOf("Yes","No"), onSelected = { onPositiveAnswerChange(question.order,it=="Yes") }, selected = question.positiveAnswer.toPositiveAnswer())

        }
    }

}


@Preview(showBackground = true)
@Composable
fun QuestionItemPreview() {
    OTheme {
        Surface {
            QuestionItem(
                question = Record(1, "Do you pray and thank god for this day ?Do you pray and thank god for this day ?Do you pray and thank god for this day ?Do you pray and thank god for this day ?Do you pray and thank god for this day ?Do you pray and thank god for this day ?Do you pray and thank god for this day ?Do you pray and thank god for this day ? ", "", true, 1),
                onQuestionChange = { _,_ ->  },
                onPositiveAnswerChange = { _,_ ->  }
            )
        }
    }
}
