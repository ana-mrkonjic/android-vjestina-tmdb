package agency.five.tmdb.ui

import agency.five.tmdb.R
import agency.five.tmdb.database.CastMember
import agency.five.tmdb.database.DetailsItem
import agency.five.tmdb.viewmodel.DetailsViewModel
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import org.koin.androidx.compose.viewModel
import org.koin.core.parameter.parametersOf


@Composable
fun DetailsScreen(movieId: Int, navigateUp: () -> Unit) {

    val viewModel: DetailsViewModel by viewModel { parametersOf(movieId) }

    val movie = viewModel.getMovie().collectAsState(initial = null).value


    Scaffold(
        topBar = {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                elevation = 16.dp
            ) {
                TopAppBarDetails(navigateUp)
            }
        },
        content = {
            if (movie != null) {
                DetailsContent(movie = movie)
            }
        },
    )

}

@Composable
fun TopAppBarDetails(navigateUp: () -> Unit) {
    Row(
        Modifier.background(
            colorResource(id = R.color.primary_blue)
        )
    ) {

        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_back),
            contentDescription = "Back button",
            modifier = Modifier
                .clickable {
                    navigateUp()
                }
                .align(Alignment.CenterVertically)
                .padding(12.dp)
                .background(colorResource(id = R.color.primary_blue)),
            tint = Color.White
        )

        Image(
            painter = painterResource(id = R.drawable.ic_tmdb_title),
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .background(colorResource(id = R.color.primary_blue))
                .fillMaxWidth()
                .height(56.dp)
                .padding(vertical = 8.dp)
                .fillMaxHeight(),
            contentDescription = "App title image",
        )
    }


}


@Composable
fun DetailsContent(movie: DetailsItem) {

    Column(Modifier.verticalScroll(rememberScrollState())) {

        Header(movie)

        Spacer(modifier = Modifier.height(8.dp))

        Overview(movie)

        Spacer(modifier = Modifier.height(16.dp))

        CastList(movie)

        Spacer(modifier = Modifier.height(16.dp))

        CastDetails(movie)

        Spacer(Modifier.height(72.dp))
    }

}

@Composable
fun Header(movie: DetailsItem?) {
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
            text = "User Score: " + movie?.score.toString() + "%",
            modifier = Modifier.padding(start = 16.dp, top = 16.dp),
            color = Color.White
        )

        Text(
            text = movie?.title.toString(),
            modifier = Modifier.padding(start = 16.dp, top = 40.dp),
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = movie?.releaseDate.toString(),
            modifier = Modifier.padding(start = 16.dp, top = 80.dp),
            color = Color.White,
            fontSize = 16.sp
        )

        Text(
            text = movie?.genre.toString(),
            modifier = Modifier.padding(start = 16.dp, top = 104.dp),
            color = Color.White
        )
    }

}

@Composable
fun ActorCard(item: CastMember, modifier: Modifier) {

    Card(
        elevation = 4.dp,
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .size(
                width = 126.dp,//dimensionResource(id = R.dimen.movie_card_width),
                height = 209.dp//dimensionResource(id = R.dimen.movie_card_height)
            ),
        shape = RoundedCornerShape(8.dp),
    ) {
        Column {
            Image(
                painter = rememberAsyncImagePainter(item.image),
                contentDescription = null,
                modifier = Modifier
                    .height(152.dp)
                    .width(126.dp),
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = item.name,
                modifier = Modifier.padding(start = 8.dp),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = item.role,
                modifier = Modifier.padding(start = 8.dp),
                fontSize = 12.sp,
            )

        }
    }

}

@Composable
fun Overview(movie: DetailsItem?) {
    Text(
        text = "Overview",
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        modifier = Modifier.padding(start = 16.dp, top = 8.dp)
    )
    Spacer(modifier = Modifier.height(8.dp))

    Text(text = movie?.overview ?: "", modifier = Modifier.padding(start = 16.dp))
}

@Composable
fun CastList(movie: DetailsItem?) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = dimensionResource(id = R.dimen.home_movies_list_content_padding))
    ) {
        if (movie != null) {
            items(movie.directors) {
                DirectorPart(
                    item = it,
                    modifier = Modifier.padding(
                        bottom = dimensionResource(id = R.dimen.micro_spacing),
                        end = dimensionResource(id = R.dimen.micro_spacing)
                    )
                )
            }
        }

    }
}

@Composable
fun CastDetails(movie: DetailsItem?) {
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
        if (movie != null) {
            items(movie.cast) {
                ActorCard(
                    item = it,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

    }
}


@Composable
fun DirectorPart(item: CastMember, modifier: Modifier) {
    Column {
        Text(text = item.name, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(4.dp))
        Text(text = item.role)
    }
}
