# The-Calculator
# follow this when intializing a new branch
<p>1) git init <br>
2) git add .  <br>
3) git commit -m 'write what you committed'  <br>
4) git branch -M main  <br>
5) git remote add origin <copy the repo URL here> example. https://github.com/trevorm10/The-Calculator.git  <br>
6) git push -u origin main </p> 

# this is switching to branch and committing
<p>1) git checkout -b secondleg <br>
2) git add .  <br>
3) git commit -m 'write what you committed'  <br>
4) git push -u origin secondleg </p> 

# this is switching to branch 
<p>1) git checkout main </p> 


# The begining of the code
package com.example.packinglist
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx. navigation.NavHostController
import androidx.navigation.compose.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PackingListApp()
        }
    }
}

@Composable
fun PackingListApp() {
    val navController = rememberNavController()

    // Parallel arrays
    val itemNames = remember { mutableStateListOf<String>() }
    val categories = remember { mutableStateListOf<String>() }
    val quantities = remember { mutableStateListOf<Int>() }
    val comments = remember { mutableStateListOf<String>() }

    NavHost(navController, startDestination = "main") {
        composable("main") {
            MainScreen(navController, itemNames, categories, quantities, comments)
        }
        composable("list") {
            ListScreen(navController, itemNames, categories, quantities, comments)
        }
    }
}

@Composable
fun MainScreen(
    navController: NavHostController,
    itemNames: MutableList<String>,
    categories: MutableList<String>,
    quantities: MutableList<Int>,
    comments: MutableList<String>
) {
    var name by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var comment by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Add Packing Item", style = MaterialTheme.typography.h6)

        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Item Name") })
        OutlinedTextField(value = category, onValueChange = { category = it }, label = { Text("Category") })
        OutlinedTextField(value = quantity, onValueChange = { quantity = it }, label = { Text("Quantity") })
        OutlinedTextField(value = comment, onValueChange = { comment = it }, label = { Text("Comments") })

        if (error.isNotEmpty()) {
            Text(error, color = MaterialTheme.colors.error)
        }

        Button(onClick = {
            if (name.isBlank() || category.isBlank() || quantity.toIntOrNull() == null) {
                error = "Please enter valid item name, category, and numeric quantity."
            } else {
                itemNames.add(name)
                categories.add(category)
                quantities.add(quantity.toInt())
                comments.add(comment)
                name = ""
                category = ""
                quantity = ""
                comment = ""
                error = ""
            }
        }) {
            Text("Add to Packing List")
        }

        Button(onClick = { navController.navigate("list") }) {
            Text("Go to List")
        }

        Button(onClick = { System.exit(0) }) {
            Text("Exit App")
        }
    }
}

@Composable
fun ListScreen(
    navController: NavHostController,
    itemNames: List<String>,
    categories: List<String>,
    quantities: List<Int>,
    comments: List<String>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Packing List", style = MaterialTheme.typography.h6)

        Button(onClick = {
            println("Full Packing List:")
            for (i in itemNames.indices) {
                println("${itemNames[i]} (${categories[i]}): ${quantities[i]} - ${comments[i]}")
            }
        }) {
            Text("Display Full List (Check Logcat)")
        }

        Button(onClick = {
            println("Items with Quantity ≥ 2:")
            for (i in itemNames.indices) {
                if (quantities[i] >= 2) {
                    println("${itemNames[i]} (${quantities[i]})")
                }
            }
        }) {
            Text("Display Items with Quantity ≥ 2 (Check Logcat)")
        }

        Button(onClick = { navController.navigate("main") }) {
            Text("Back to Main")
        }
    }
}
