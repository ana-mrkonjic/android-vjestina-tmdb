package agency.five.tmdb.extensions

import agency.five.tmdb.R
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.sp

fun LazyListScope.titleSection(title: String, modifier: Modifier = Modifier) {
    item {
        Column(modifier = modifier) {
            Text(
                modifier = modifier, text = title, style = MaterialTheme.typography.h6.copy(
                    color = colorResource(id = R.color.text_blue),
                    fontSize = 24.sp
                )
            )
        }
    }

}

@Composable
private fun TitleSection(title: String, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier, text = title, style = MaterialTheme.typography.h6.copy(
            color = colorResource(id = R.color.text_blue),
            fontSize = 24.sp
        )
    )
}