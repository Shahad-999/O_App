package com.shahad.o.ui.views.widgets


import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionItem(
    modifier: Modifier = Modifier,
    question: Record,
    onQuestionChange: (Int, String) -> Unit,
    onPositiveAnswerChange: (Int, Boolean) -> Unit
) {
    Row(
        modifier = modifier
            .padding(vertical = 8.dp)
            .clip(shape = RoundedCornerShape(16.dp))
            .border(1.dp, OTheme.colors.shade1, shape = RoundedCornerShape(16.dp))
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.Top
    ) {
        Image(
            painter = painterResource(id = R.drawable.white_heart),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
        )

        Column(
            modifier = Modifier
                .height(100.dp)
                .padding(start = 16.dp, top = 8.dp)
                .fillMaxHeight()
        ) {
            BasicTextField(
                value = question.question,
                onValueChange = {
                    onQuestionChange(question.order,it)
                } ,
                textStyle = TextStyle(
                    color = OTheme.colors.shade1,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                ),
                maxLines = 3,
            )
            Spacer(modifier = Modifier.weight(1F, true))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Is Yes positive answer",
                    style = TextStyle(
                        color = OTheme.colors.shade2,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Start
                    ),
                )
                Spacer(modifier = Modifier.weight(1F))
                OSwitch(
                    checked = question.positive_answer,
                    onCheckedChange = { onPositiveAnswerChange(question.order, it) }
                )
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun QuestionItemPreview() {
    OTheme {
        Surface {
            QuestionItem(
                question = Record(1, "Do you pray and thank god for this day ?Do you pray and thank god for this day ? ", "", true, 1),
                onQuestionChange = { _,_ ->  },
                onPositiveAnswerChange = { _,_ ->  }
            )
        }
    }
}
