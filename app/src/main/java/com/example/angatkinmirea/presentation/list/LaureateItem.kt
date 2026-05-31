package com.example.angatkinmirea.presentation.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.angatkinmirea.domain.model.Laureate

@Composable
fun LaureateItem(
    laureate: Laureate,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick = onClick
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = laureate.fullName
            )

            Text(
                text = laureate.year
            )

            Text(
                text = laureate.category
            )

            Text(
                text = laureate.motivation
                    .take(100)
            )
        }
    }
}