package com.example.angatkinmirea
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.angatkinmirea.data.remote.NobelApi
import com.example.angatkinmirea.data.repository.NobelRepositoryImpl
import com.example.angatkinmirea.domain.usecase.GetLaureatesUseCase
import com.example.angatkinmirea.presentation.list.LaureatesViewModel
import com.example.angatkinmirea.presentation.list.LaureatesViewModelFactory
import com.example.angatkinmirea.presentation.navigation.NavGraph
import com.example.angatkinmirea.presentation.ui.theme.AngatkinMIREATheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AngatkinMIREATheme {
                val api = NobelApi()

                val repository = NobelRepositoryImpl(api)

                val useCase = GetLaureatesUseCase(repository)

                val viewModel: LaureatesViewModel = viewModel(
                    factory = LaureatesViewModelFactory(useCase)
                )

                val navController = rememberNavController()

                NavGraph(
                    navController = navController,
                    viewModel = viewModel
                )
            }
        }
    }
}