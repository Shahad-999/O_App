package com.shahad.o.ui.views.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shahad.o.domain.models.Result
import com.shahad.o.ui.states.RecordsCalendarState
import com.shahad.o.ui.theme.OTheme

@Composable
fun DayReview(
    modifier: Modifier = Modifier,
    questions: RecordsCalendarState
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxSize(1f),
        contentAlignment = Alignment.Center
    ) {
        if (questions.isEmpty) {
            Text(text = "لا يوجد بيانات في هذا اليوم",style = TextStyle(
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            ))
        } else if (questions.isLoading) {
            Text(text = "تحميل .....",style = TextStyle(
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            ))
        } else if (questions.isError) {
            Text(text = "خطأو حاول مرة اخرى",style = TextStyle(
                    color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            ))
        } else if (questions.records != null) {
            LazyColumn {
                items(questions.records) {
                    Column(
                        Modifier
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .fillMaxWidth(),
                    ) {
                        Box(
                            modifier = Modifier
                                .background(Color.White, shape = RoundedCornerShape(32))
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                        ) {
                            Text(
                                it.question,
                                style = TextStyle(
                                    color = Color.Black,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            )
                        }
                        Box(
                            modifier = Modifier
                                .align(Alignment.End)
                                .padding(vertical = 8.dp)
                                .background(Color.Transparent, shape = RoundedCornerShape(32))
                                .border(
                                    border = BorderStroke(1.dp, color = Color.White),
                                    shape = RoundedCornerShape(32)
                                )
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                        ) {
                            Text(
                                it.answer,
                                style = TextStyle(
                                    color = Color.White,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true, showBackground = true)
//@Preview(
//    showSystemUi = true,
//    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
//    showBackground = true
//)
@Composable
fun DayReviewPreview() {
    OTheme {
        Surface {
            DayReview(
                questions = RecordsCalendarState(
                    records = listOf(
                        Result(
                            isPositive = false,
                            question = "Are you HAPPY?",
                            answer = "YES",
                            weight = 2
                        ),
                        Result(
                            isPositive = false,
                            question = "Are you HAPPY?",
                            answer = "YES",
                            weight = 2
                        ),
                        Result(
                            isPositive = false,
                            question = "Are you HAPPY?",
                            answer = "YES",
                            weight = 2
                        ),
                    )
                ),
                modifier = Modifier
                    .fillMaxSize()
                    .background(OTheme.colors.primary)
            )
        }
    }
}
