package com.iotoms.ui.item.search

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.iotoms.data.local.entity.ItemEntity
import com.iotoms.ui.components.OutlinedTextBox
import com.iotoms.ui.components.ProductItem
import com.iotoms.ui.theme.ExtraSmallPadding
import com.iotoms.ui.theme.IconSize
import com.iotoms.ui.theme.SmallPadding
import kotlinx.serialization.Serializable

/**
 * Created by Fasil on 29/12/2025
 */
@Serializable
data class SearchItemScreenNavKey(val isSearch: Boolean = true) : NavKey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchItemScreen(
    uiState: State<SearchItemUiState>,
    pagingItems: LazyPagingItems<ItemEntity>,
    onSearch: (String) -> Unit,
    onItemClick: (ItemEntity) -> Unit,
    onViewItem: (ItemEntity) -> Unit,
    onClickBack: () -> Unit,
    isSearch: Boolean
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    var search by rememberSaveable { mutableStateOf("") }
    LaunchedEffect(uiState.value) {
        if (uiState.value is SearchItemUiState.ItemAdded) {
            Toast.makeText(context, "Item added to cart", Toast.LENGTH_SHORT).show()
            onClickBack()
        }
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
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
                        Text(if (isSearch) "Search Items" else "Items")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            Box(modifier = Modifier.padding(SmallPadding)) {
                OutlinedTextBox(
                    value = search,
                    onValueChange = {
                        search = it
                        onSearch(search)
                    },
                    placeholder = {
                        Text("Type name or itemId")
                    },
                    trailingIcon = {
                        if (search.isNotEmpty()) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = null,
                                modifier = Modifier
                                    .height(IconSize)
                                    .clickable(onClick = {
                                        search = ""
                                        onSearch("")
                                        focusManager.clearFocus()
                                    })
                            )
                        }
                    }
                )
            }
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 200.dp),
                modifier = Modifier.weight(1f)
            ) {
                when (pagingItems.loadState.refresh) {
                    is LoadState.Loading -> {
                        //item { CircularProgressIndicator(modifier = Modifier.size(40.dp)) }
                    }

                    else -> {
                        items(
                            count = pagingItems.itemCount,
                            key = { index -> pagingItems[index]?.id ?: index }
                        ) { index ->
                            pagingItems[index]?.let { item ->
                                Box(
                                    modifier = Modifier
                                        .padding(ExtraSmallPadding)
                                        .clickable(onClick = {
                                            if (isSearch) {
                                                onItemClick(item)
                                            } else {
                                                onViewItem(item)
                                            }
                                        })
                                ) {
                                    ProductItem(item)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}