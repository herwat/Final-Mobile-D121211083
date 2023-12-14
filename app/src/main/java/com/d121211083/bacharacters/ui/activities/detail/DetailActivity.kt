package com.d121211083.bacharacters.ui.activities.detail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.d121211083.bacharacters.data.models.Char
import com.d121211083.bacharacters.ui.theme.D121211083BACharactersTheme

class DetailActivity : ComponentActivity() {

    private var selectedchar: Char? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectedchar = intent.getParcelableExtra("CHAR")
        setContent {
            D121211083BACharactersTheme{
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorScheme.background
                ) {
                    CharDetailScreen(selectedchar)
                }
            }
        }
    }
}

@Composable
fun CharDetailScreen(selectedchar: Char?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
            .verticalScroll(rememberScrollState())
    ) {
      
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(selectedchar?.photoUrl)
                .crossfade(true)
                .build(),
            contentDescription = selectedchar?.name,
            modifier = Modifier
                .width(420.dp)
                .height(600.dp)
                .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop
        )


        Spacer(modifier = Modifier.height(16.dp))
        Text(text = selectedchar?.name ?: "Unknown", style = MaterialTheme.typography.displayMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Age: ${selectedchar?.age ?: "Unknown"}", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "School: ${selectedchar?.school ?: "Unknown"}", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(6.dp))
        Text(text = "Role: ${selectedchar?.role?.joinToString(", ") ?: "Unknown"}", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = selectedchar?.background ?: "", style = MaterialTheme.typography.bodyMedium)
    }
}
