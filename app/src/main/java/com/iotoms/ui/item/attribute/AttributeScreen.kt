package com.iotoms.ui.item.attribute

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import com.iotoms.ui.components.AttributeItem
import com.iotoms.ui.theme.IconSize
import com.iotoms.ui.theme.SmallPadding
import kotlinx.serialization.Serializable

/**
 * Created by Fasil on 03/01/2026
 */
@Serializable
data class AttributeScreenNavKey(val attrType: String) : NavKey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttributeScreen(
    uiState: State<AttributeScreenUiState>,
    onClickBack: () -> Unit,
    onAttrClick: (Pair<Int, String>) -> Unit,
    attrType: String
) {
    val items = when (val result = uiState.value) {
        is AttributeScreenUiState.Data -> result.list
        else -> emptyList()
    }
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = null,
                            modifier = Modifier
                                .height(IconSize)
                                .clickable(onClick = onClickBack)
                        )
                        Spacer(Modifier.width(SmallPadding))
                        Text(attrType)
                    }
                }
            )
        }
    ) { innerPadding ->
        if (items.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text("No $attrType found")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(SmallPadding)
            ) {
                items(
                    count = items.size,
                    key = { items[it].first }
                ) {
                    AttributeItem(
                        attr = items[it],
                        onClick = {
                            onAttrClick(items[it])
                        }
                    )
                }
            }
        }
    }
}