package agency.five.tmdb.movieDetails

import agency.five.tmdb.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter


@Composable
fun DetailsScreen(viewModel: DetailsViewModel) {
    DetailsContent(viewModel = viewModel)

}


@Composable
fun DetailsContent(viewModel: DetailsViewModel) {
    LazyColumn(
        modifier = Modifier,
    ) {
        items(count = 1) {
            Box(modifier = Modifier) {
                Image(
                    painter = painterResource(id = R.drawable.ic_twin_peaks),
                    modifier = Modifier
                        .fillMaxSize()
                        .height(304.dp)
                        .width(363.dp),
                    contentDescription = "Movie image",
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = "User Score: " + viewModel.movie.score.toString() + "%",
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                    color = Color.White
                )
                Text(
                    text = viewModel.movie.title,
                    modifier = Modifier.padding(start = 16.dp, top = 40.dp),
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.padding(vertical = 8.dp))
                Text(
                    text = viewModel.movie.releaseDate,
                    modifier = Modifier.padding(start = 16.dp, top = 80.dp),
                    color = Color.White,
                    fontSize = 16.sp
                )
                Text(
                    text = viewModel.movie.genre,
                    modifier = Modifier.padding(start = 16.dp, top = 96.dp),
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Overview",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(start = 16.dp, top = 8.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(text = viewModel.movie.overview, modifier = Modifier.padding(start = 16.dp))

            Spacer(modifier = Modifier.height(16.dp))

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = dimensionResource(id = R.dimen.home_movies_list_content_padding))
            ) {
                items(viewModel.directors) {
                    DirectorPart(
                        item = it,
                        modifier = Modifier.padding(
                            bottom = dimensionResource(id = R.dimen.micro_spacing),
                            end = dimensionResource(id = R.dimen.micro_spacing)
                        )
                    )
                }

            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Top billed cast",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(start = 16.dp)
            )

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = dimensionResource(id = R.dimen.home_movies_list_content_padding))
            ) {
                items(viewModel.actors) {
                    ActorCard(
                        item = it,
                        modifier = Modifier.padding(16.dp)
                    )
                }

            }
            Spacer(Modifier.height(72.dp))
        }

    }


}

@Composable
fun ActorCard(item: CastMember, modifier: Modifier) {

    Card(
        elevation = 12.dp,
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .size(
                width = 126.dp,//dimensionResource(id = R.dimen.movie_card_width),
                height = 209.dp//dimensionResource(id = R.dimen.movie_card_height)
            )
            .clip(RoundedCornerShape(8.dp/*dimensionResource(id = R.dimen.small_spacing)*/))

    ) {
        Column {
            Image(
                painter = rememberImagePainter(item.image),
                contentDescription = null,
                modifier = Modifier
                    .height(132.dp)
                    .width(126.dp),
                contentScale = ContentScale.Crop

            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = item.name,
                modifier = Modifier,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = item.role, modifier = Modifier, fontSize = 12.sp
            )

        }
    }

}


@Composable
fun DirectorPart(item: CastMember, modifier: Modifier) {
    Column() {
        Text(text = item.name, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(4.dp))
        Text(text = item.role)
    }
}
