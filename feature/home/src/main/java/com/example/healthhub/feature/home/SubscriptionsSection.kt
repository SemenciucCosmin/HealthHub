package com.example.healthhub.feature.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.healthhub.data.home.model.Subscription
import com.example.healthhub.data.util.floorMod
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val MAXIMUM_PAGE_COUNT = 5000
private const val AUTO_SCROLL_DURATION = 4000L

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SubscriptionSection(
    subscriptions: List<Subscription>,
    modifier: Modifier = Modifier
) {
    // For multiple apps enable a bi-directional looping.
    val pageCount = if (subscriptions.count() > 1) MAXIMUM_PAGE_COUNT else subscriptions.count()
    val pagerState = rememberPagerState { pageCount }

    if (subscriptions.count() > 1) {
        with(pagerState) {
            val isDragged by interactionSource.collectIsDraggedAsState()
            LaunchedEffect(isDragged) {
                launch {
                    while (!isDragged) {
                        delay(timeMillis = AUTO_SCROLL_DURATION)
                        val nextPage = (currentPage + 1).mod(pageCount)
                        animateScrollToPage(page = nextPage)
                    }
                }
            }
        }
    }

    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(horizontal = 16.dp),
        pageSpacing = 8.dp,
        beyondBoundsPageCount = 1,
        modifier = modifier
    ) { pageIndex ->
        val subscriptionIndex = pageIndex.floorMod(subscriptions.count())
        val subscription = subscriptions[subscriptionIndex]
        SubscriptionCard(subscription = subscription)
    }
}
