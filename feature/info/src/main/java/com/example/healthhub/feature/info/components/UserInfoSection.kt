package com.example.healthhub.feature.info.components

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.healthhub.data.account.model.Child
import com.example.healthhub.data.account.model.Parent
import com.example.healthhub.data.account.model.ParentPreviewParameterProvider
import com.example.healthhub.ui.catalog.R
import com.example.healthhub.ui.catalog.theme.HealthHubTheme
import kotlinx.coroutines.launch

private const val PARENT_PAGE = 0
private const val CHILD_PAGE = 1
private const val PAGE_COUNT = 2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserInfoSection(
    parent: Parent,
    child: Child?,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState {
        when {
            child == null -> PAGE_COUNT - CHILD_PAGE
            else -> PAGE_COUNT
        }
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.lbl_users_information),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        SingleChoiceSegmentedButtonRow {
            SegmentedButton(
                selected = pagerState.currentPage == PARENT_PAGE,
                shape = MaterialTheme.shapes.small,
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(PARENT_PAGE)
                    }
                }
            ) {
                Text(text = stringResource(R.string.lbl_parent_tab))
            }

            SegmentedButton(
                selected = pagerState.currentPage == CHILD_PAGE,
                shape = MaterialTheme.shapes.small,
                enabled = child != null,
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(CHILD_PAGE)
                    }
                }
            ) {
                Text(text = stringResource(R.string.lbl_child_tab))
            }
        }

        HorizontalPager(
            state = pagerState,
            pageSpacing = 16.dp,
            contentPadding = PaddingValues(vertical = 16.dp),
            modifier = Modifier.animateContentSize()
        ) { pageIndex ->
            if (pageIndex == PARENT_PAGE) {
                ParentInfoCard(parent = parent)
            } else {
                child?.let { ChildInfoCard(child = it) }
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewUserInfoSection(
    @PreviewParameter(ParentPreviewParameterProvider::class)
    parent: Parent,
) {
    HealthHubTheme {
        UserInfoSection(
            parent = parent,
            child = null
        )
    }
}