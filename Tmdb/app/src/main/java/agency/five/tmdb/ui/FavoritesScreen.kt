package agency.five.tmdb.ui

import agency.five.tmdb.R
import agency.five.tmdb.extensions.gridItems
import agency.five.tmdb.repository.MovieItem
import agency.five.tmdb.viewmodel.FavoritesViewModel
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel


@ExperimentalMaterialApi
@Composable
fun FavoritesScreen(onFavoriteClick: (Int) -> Unit) {

    val viewModel = getViewModel<FavoritesViewModel>()

    val movies = viewModel.getFavoriteMovies().collectAsState(initial = emptyList()).value

    FavoritesContent(viewModel, movies, Modifier, onFavoriteClick)
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FavoritesContent(
    viewModel: FavoritesViewModel,
    movies: List<MovieItem>,
    modifier: Modifier,
    onFavoriteClick: (Int) -> Unit
) {
    Column(modifier = Modifier) {
        Text(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 24.dp),
            text = "Favorites",
            style = MaterialTheme.typography.h6.copy(
                color = colorResource(id = R.color.text_blue),
                fontSize = 24.sp
            )
        )
        FavoritesScreenContent(
            viewModel,
            movies,
            Modifier.padding(top = 8.dp, bottom = 32.dp),
            onFavoriteClick
        )
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
@ExperimentalMaterialApi
fun FavoritesScreenContent(
    viewModel: FavoritesViewModel,
    movies: List<MovieItem>,
    modifier: Modifier,
    onFavoriteClick: (Int) -> Unit
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        contentPadding = PaddingValues(top = 8.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {

        favoritesSection(
            favoriteMovies = movies,
            viewModel = viewModel,
            onFavoriteClick = onFavoriteClick
        )


    }
}


@OptIn(ExperimentalFoundationApi::class)
@ExperimentalMaterialApi
fun LazyListScope.favoritesSection(
    favoriteMovies: List<MovieItem>,
    viewModel: FavoritesViewModel,
    onFavoriteClick: (Int) -> Unit = {}
) {
    gridItems(
        data = favoriteMovies,
        columnCount = 3,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(horizontal = 16.dp)
    ) { movie ->
        FavoriteMovieCard(
            viewModel = viewModel,
            item = movie,
            onMovieItemClick = { onFavoriteClick(movie.id) },
            modifier = Modifier
                .padding(horizontal = dimensionResource(id = R.dimen.micro_spacing))
                .clickable {
                    onFavoriteClick(movie.id)
                }
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun FavoriteMovieCard(
    viewModel: FavoritesViewModel,
    modifier: Modifier = Modifier,
    item: MovieItem,
    onMovieItemClick: () -> Unit = {}
) {
    Box(modifier = modifier.clickable { onMovieItemClick() }) {
        Image(
            painter = rememberAsyncImagePainter(item.imageUrl),
            contentDescription = null,
            modifier = Modifier
                .size(
                    width = 120.dp,//dimensionResource(id = R.dimen.movie_card_width),
                    height = 176.dp//dimensionResource(id = R.dimen.movie_card_height)
                )
                .clip(RoundedCornerShape(8.dp/*dimensionResource(id = R.dimen.small_spacing)*/)),
            contentScale = ContentScale.Crop
        )

        FavShape(
            viewModel = viewModel,
            item = item,
            modifier = Modifier.padding(
                start = 8.dp,//dimensionResource(id = R.dimen.small_spacing),
                top = 8.dp//dimensionResource(id = R.dimen.small_spacing)
            )
        )
    }

}

@Composable
fun FavShape(
    viewModel: FavoritesViewModel,
    modifier: Modifier = Modifier,
    item: MovieItem
) {
    Surface(
        shape = CircleShape,
        modifier = Modifier
            .padding(6.dp)
            .size(32.dp),
        color = Color(0x77000000)
    ) {
        FavButton(
            viewModel = viewModel,
            item = item,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun FavButton(
    viewModel: FavoritesViewModel,
    modifier: Modifier = Modifier,
    item: MovieItem,
    color: Color = Color.White
) {

    var isFavorite by remember { mutableStateOf(true) }
    val scope = rememberCoroutineScope()


    IconToggleButton(
        checked = isFavorite,
        onCheckedChange = {

                isFavorite = !isFavorite
                if (isFavorite) {
                    scope.launch {
                        viewModel.addFavoriteMovie(item)
                    }
                } else {
                    scope.launch {
                        viewModel.removeFavoriteMovie(item)
                    }

                }
        }
    ) {
        Icon(
            tint = color,
            modifier = modifier.graphicsLayer {
                scaleX = 1.3f
                scaleY = 1.3f
            },
            imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            contentDescription = null
        )
    }

}
