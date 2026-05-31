package com.example.angatkinmirea.presentation.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.angatkinmirea.domain.model.Laureate

data class CategoryItem(
    val title: String,
    val apiValue: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LaureatesScreen(
    state: LaureatesState,
    onRetry: () -> Unit,
    onItemClick: (Laureate) -> Unit,
    onFilter: (String?, String?) -> Unit
) {

    var year by remember {
        mutableStateOf("")
    }

    val categories = listOf(
        CategoryItem("Все категории", ""),
        CategoryItem("Physics", "phy"),
        CategoryItem("Chemistry", "che"),
        CategoryItem("Literature", "lit"),
        CategoryItem("Peace", "pea"),
        CategoryItem("Medicine", "med"),
        CategoryItem("Economics", "eco")
    )

    var selectedCategory by remember {
        mutableStateOf(categories.first())
    }

    var expanded by remember {
        mutableStateOf(false)
    }

    Column {

        OutlinedTextField(
            value = year,
            onValueChange = {
                year = it
            },
            label = {
                Text("Год")
            },
            modifier = Modifier.fillMaxWidth()
        )

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {

            OutlinedTextField(
                value = selectedCategory.title,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }
            ) {

                categories.forEach { category ->

                    DropdownMenuItem(
                        text = {
                            Text(category.title)
                        },
                        onClick = {

                            selectedCategory = category

                            expanded = false
                        }
                    )
                }
            }
        }

        Button(
            onClick = {
                onFilter(
                    year.ifBlank { null },
                    selectedCategory.apiValue.ifBlank { null }
                )
            }
        ) {
            Text("Применить фильтр")
        }

        when (state) {

            LaureatesState.Loading -> {

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
            }

            is LaureatesState.Error -> {

                Column(
                    verticalArrangement = Arrangement.Center
                ) {

                    Text(state.message)

                    Button(
                        onClick = onRetry
                    ) {
                        Text("Повторить")
                    }
                }
            }

            is LaureatesState.Success -> {

                LazyColumn {

                    items(state.items) { laureate ->

                        LaureateItem(
                            laureate = laureate,
                            onClick = {
                                onItemClick(laureate)
                            }
                        )
                    }
                }
            }
        }
    }
}