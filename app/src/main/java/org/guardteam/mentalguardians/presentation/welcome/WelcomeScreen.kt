package org.guardteam.mentalguardians.presentation.welcome

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import org.guardteam.mentalguardians.presentation.theme.fontFamily

@ExperimentalFoundationApi
@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    navigateToSignIn: () -> Unit = {},
    welcomeViewModel: WelcomeViewModel = hiltViewModel()
) {
    val pages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third
    )

    val pagerState = rememberPagerState(
        pageCount = {
            pages.size
        }
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        HorizontalPager(
            state = pagerState
        ) { position ->
            PagerScreen(onBoardingPage = pages[position])
        }
        Row(
            modifier = Modifier.padding(
                start = 24.dp,
                top = 6.dp,
                end = 24.dp,
                bottom = 6.dp
            )
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color: Color =
                    if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant

                val width by animateDpAsState(
                    targetValue = if (pagerState.currentPage == iteration) 30.dp else 10.dp,
                    animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
                    label = "Indicator Width"
                )
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .height(10.dp)
                        .width(width)
                        .clip(CircleShape)
                        .background(color)
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 42.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(onClick = {
                welcomeViewModel.saveOnBoardingState(true)
                navigateToSignIn()
            }) {
                Text(
                    text = "Skip",
                    fontFamily = fontFamily,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            AnimatedVisibility(
                visible = pagerState.currentPage == 2,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                TextButton(onClick = {
                    welcomeViewModel.saveOnBoardingState(true)
                    navigateToSignIn()
                }) {
                    Text(
                        text = "Get Started",
                        fontFamily = fontFamily,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}

@Composable
fun PagerScreen(
    onBoardingPage: OnBoardingPage,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = onBoardingPage.image),
            contentDescription = onBoardingPage.title,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.padding(top = 32.dp)
        )
        Text(
            text = onBoardingPage.title,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(
                start = 24.dp,
                top = 6.dp,
                end = 24.dp,
                bottom = 6.dp
            )
        )
        Text(
            text = onBoardingPage.description,
            fontFamily = fontFamily,
            fontSize = 34.sp,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(
                start = 24.dp,
                top = 6.dp,
                end = 24.dp,
                bottom = 6.dp
            ),
            lineHeight = 42.sp
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun FirstPagerScreen() {
    MaterialTheme {
        PagerScreen(onBoardingPage = OnBoardingPage.First)
    }
}