package com.fracta7.crafter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fracta7.crafter.model.ItemRegistry
import com.fracta7.crafter.model.RecipeRegistry
import com.fracta7.crafter.model.itemInit
import com.fracta7.crafter.model.recipeInit
import com.fracta7.crafter.ui.theme.CrafterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CrafterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val itemRegistry = ItemRegistry()
                    val recipeRegistry = RecipeRegistry()
                    itemInit().forEach { item ->
                        itemRegistry.addItem(item)
                    }
                    recipeInit(itemRegistry).forEach { recipe ->
                        recipeRegistry.addRecipe(recipe)
                    }
                }
            }
        }
    }
}