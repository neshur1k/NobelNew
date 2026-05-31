package com.example.angatkinmirea.presentation.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.angatkinmirea.domain.model.Laureate

@Composable
fun DetailsScreen(
    laureate: Laureate,
    onBack: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 60.dp)
            .padding(horizontal = 16.dp)
    ) {

        Button(
            onClick = onBack
        ) {
            Text("Назад")
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        AsyncImage(
            model = laureate.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Text(
            text = "Имя: ${laureate.fullName}"
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "Год: ${laureate.year}"
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "Категория: ${laureate.category}"
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "Страна: ${laureate.country}"
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "Описание:"
        )

        Spacer(
            modifier = Modifier.height(4.dp)
        )

        Text(
            text = laureate.motivation
        )
    }
}