package com.mhdncb.eurosportappmehdi.presentation.component.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.mhdncb.eurosportappmehdi.ui.theme.GreyColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleItem(
    cardBox: @Composable BoxScope.() -> Unit,
    tag: String,
    title: String,
    subTitle: String,
    onCardClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(8.dp),
        onClick = {
            onCardClick()
        }
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxWidth()
        ) {
            val (ArticleBox, tagBox, titleBox, subtitleBox) = createRefs()

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .constrainAs(ArticleBox) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ) {
                cardBox()
            }
            TagChip(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .constrainAs(tagBox) {
                        top.linkTo(ArticleBox.bottom)
                        start.linkTo(parent.start)
                        bottom.linkTo(ArticleBox.bottom)
                    },
                text = tag
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 12.dp, end = 12.dp, bottom = 8.dp)
                    .constrainAs(titleBox) {
                        top.linkTo(tagBox.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ) {
                Text(modifier = Modifier.fillMaxWidth(), text = title, fontSize = 20.sp, fontWeight = FontWeight.Black, color = Color.Black)
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, end = 12.dp, bottom = 16.dp)
                    .constrainAs(subtitleBox) {
                        top.linkTo(titleBox.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ) {
                Text(modifier = Modifier.fillMaxWidth(), text = subTitle, fontSize = 12.sp, fontWeight = FontWeight.Medium, color = GreyColor)
            }
        }
    }
}