package com.d121211083.bacharacters.ui.activities.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.d121211083.bacharacters.R
import com.d121211083.bacharacters.data.models.Char
import com.d121211083.bacharacters.ui.activities.detail.DetailActivity
import com.d121211083.bacharacters.ui.theme.D121211083BACharactersTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            D121211083BACharactersTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val mainViewModel: MainViewModel = viewModel(factory = MainViewModel.Factory)
                    ListCharScreen(mainViewModel.mainUiState)
                }
            }
        }
    }

    @Composable
    private fun ListCharScreen(mainUiState: MainUiState, modifier: Modifier = Modifier) {
        when (mainUiState) {
            is MainUiState.Loading -> Text(text = "Loading", fontSize = 16.sp)
            is MainUiState.Error -> Text(text = "Terjadi Kesalahan", fontSize = 16.sp)
            is MainUiState.Success -> CharList(mainUiState.char)
        }
    }

    @Composable
    fun CharList(chars: List<Char>, modifier: Modifier = Modifier) {
        LazyColumn(modifier = modifier) {
            items(chars) { char ->
                CharItem(char = char)
            }
        }
    }

    @Composable
    fun CharItem(char: Char) {

        Box(
            modifier = Modifier
                .padding(16.dp)
                .background(color = Color(android.graphics.Color.parseColor("#ff8906")))
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp),)
                .clickable {
                    val intent = Intent(this, DetailActivity::class.java)
                    intent.putExtra("CHAR", char)
                    startActivity(intent)
                }

        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                // Character Image
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(char.photoUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = char.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(MaterialTheme.shapes.extraSmall),
                    contentScale = ContentScale.Crop
                )

                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(35.dp)
                        .background(
                            color = Color(android.graphics.Color.parseColor("#ff8906")),
                            shape = CircleShape
                        )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.bintang),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = 3.dp)
                            .fillMaxSize()
                            .background(color = Color(android.graphics.Color.parseColor("#ff8906")))
                    )
                }


                // Character Details
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = char.name ?: "Unknown",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "School: ${char.school ?: "Unknown"}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Age: ${char.age ?: "Unknown"}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Role: ${char.role.joinToString(", ")}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

    }
}