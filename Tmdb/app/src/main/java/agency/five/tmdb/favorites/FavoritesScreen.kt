package agency.five.tmdb.favorites

import agency.five.tmdb.R
import agency.five.tmdb.extensions.gridItems
import agency.five.tmdb.favorites.FavoritesViewModel
import agency.five.tmdb.home.BottomBar
import agency.five.tmdb.home.MovieItem
import agency.five.tmdb.home.MyTopAppBar
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberImagePainter


@ExperimentalMaterialApi
@Composable
fun FavoritesScreen(viewModel: FavoritesViewModel) {

    val movies = viewModel.items

/*    val scaffoldState: ScaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                elevation = 16.dp
            ) {
                MyTopAppBar()
            }
        },

        bottomBar = { BottomBar() }
    )
    {

        FavoritesContent(viewModel, movies, Modifier)

    }*/

    FavoritesContent(viewModel, movies, Modifier)
}



@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FavoritesContent(viewModel: FavoritesViewModel, movies: List<MovieItem>, modifier: Modifier) {
    Column(modifier = Modifier) {
        Text(
            modifier = Modifier.padding(vertical=8.dp, horizontal = 24.dp), text = "Favorites", style = MaterialTheme.typography.h6.copy(
                color = colorResource(id = R.color.text_blue),
                fontSize = 24.sp
            )
        )
        FavoritesScreenContent(viewModel, movies, Modifier.padding(top = 8.dp, bottom = 32.dp))
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
@ExperimentalMaterialApi
fun FavoritesScreenContent(viewModel: FavoritesViewModel, movies: List<MovieItem>, modifier: Modifier) {

//    var favoriteMovieItems by remember {
//        mutableStateOf(
//            listOf(
//                MovieItem(
//                    id = 1,
//                    title = "Title 1",
//                    overview = "Content 1",
//                    imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
//                ),
//                MovieItem(
//                    id = 2,
//                    title = "Title 2",
//                    overview = "Content 2",
//                    imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
//                ),
//                MovieItem(
//                    id = 3,
//                    title = "Title 3",
//                    overview = "Content 3",
//                    imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
//                ),
//                MovieItem(
//                    id = 4,
//                    title = "Title 1",
//                    overview = "Content 1",
//                    imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
//                ),
//                MovieItem(
//                    id = 5,
//                    title = "Title 2",
//                    overview = "Content 2",
//                    imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
//                ),
//                MovieItem(
//                    id = 6,
//                    title = "Title 3",
//                    overview = "Content 3",
//                    imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
//                ),
//                MovieItem(
//                    id = 7,
//                    title = "Title 1",
//                    overview = "Content 1",
//                    imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
//                ),
//                MovieItem(
//                    id = 8,
//                    title = "Title 2",
//                    overview = "Content 2",
//                    imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
//                ),
//                MovieItem(
//                    id = 9,
//                    title = "Title 3",
//                    overview = "Content 3",
//                    imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
//                ),
//                MovieItem(
//                    id = 10,
//                    title = "Title 1",
//                    overview = "Content 1",
//                    imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
//                ),
//                MovieItem(
//                    id = 11,
//                    title = "Title 2",
//                    overview = "Content 2",
//                    imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
//                ),
//                MovieItem(
//                    id = 12,
//                    title = "Title 3",
//                    overview = "Content 3",
//                    imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
//                ),
//                MovieItem(
//                    id = 13,
//                    title = "Title 2",
//                    overview = "Content 2",
//                    imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
//                ),
//                MovieItem(
//                    id = 14,
//                    title = "Title 3",
//                    overview = "Content 3",
//                    imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
//                )
//            )
//        )
//    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        contentPadding = PaddingValues(top = 8.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        //state = scrollState
    ) {

        //titleSection("Favorites", modifier = Modifier.padding(horizontal = 16.dp))
        favoritesSection(favoriteMovies = movies, viewModel = viewModel)


    }
}

//var favoriteMovieItems =
//    listOf(
//        MovieItem(
//            id = 1,
//            title = "Title 1",
//            overview = "Content 1",
//            imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
//        ),
//        MovieItem(
//            id = 2,
//            title = "Title 2",
//            overview = "Content 2",
//            imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
//        ),
//        MovieItem(
//            id = 3,
//            title = "Title 3",
//            overview = "Content 3",
//            imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
//        ),
//        MovieItem(
//            id = 4,
//            title = "Title 1",
//            overview = "Content 1",
//            imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
//        ),
//        MovieItem(
//            id = 5,
//            title = "Title 2",
//            overview = "Content 2",
//            imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
//        ),
//        MovieItem(
//            id = 6,
//            title = "Title 3",
//            overview = "Content 3",
//            imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
//        ),
//        MovieItem(
//            id = 7,
//            title = "Title 1",
//            overview = "Content 1",
//            imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
//        ),
//        MovieItem(
//            id = 8,
//            title = "Title 2",
//            overview = "Content 2",
//            imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
//        ),
//        MovieItem(
//            id = 9,
//            title = "Title 3",
//            overview = "Content 3",
//            imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
//        ),
//        MovieItem(
//            id = 10,
//            title = "Title 1",
//            overview = "Content 1",
//            imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
//        ),
//        MovieItem(
//            id = 11,
//            title = "Title 2",
//            overview = "Content 2",
//            imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
//        ),
//        MovieItem(
//            id = 12,
//            title = "Title 3",
//            overview = "Content 3",
//            imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
//        ),
//        MovieItem(
//            id = 13,
//            title = "Title 2",
//            overview = "Content 2",
//            imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
//        ),
//        MovieItem(
//            id = 14,
//            title = "Title 3",
//            overview = "Content 3",
//            imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
//        )
//    )


@OptIn(ExperimentalFoundationApi::class)
@ExperimentalMaterialApi
fun LazyListScope.favoritesSection(favoriteMovies: List<MovieItem>, viewModel: FavoritesViewModel
) {
    gridItems(
        data = favoriteMovies,
        columnCount = 3,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        FavoriteMovieCard(
            viewModel = viewModel,
            item = it,
            onMovieItemClick = {},
            modifier = androidx.compose.ui.Modifier.padding(horizontal = dimensionResource(id = R.dimen.micro_spacing))
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
            painter = rememberImagePainter(item.imageUrl),
            contentDescription = null,
            modifier = Modifier
                .size(
                    width = 120.dp,//dimensionResource(id = R.dimen.movie_card_width),
                    height = 176.dp//dimensionResource(id = R.dimen.movie_card_height)
                )
                .clip(RoundedCornerShape(8.dp/*dimensionResource(id = R.dimen.small_spacing)*/)),
            contentScale = ContentScale.Crop
        )

        FavoriteShape2(
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
fun FavoriteShape2(
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
        FavoriteButton2(viewModel = viewModel, item = item, modifier = Modifier.padding(8.dp))
    }
}

@Composable
fun FavoriteButton2(
    viewModel: FavoritesViewModel,
    modifier: Modifier = Modifier,
    item: MovieItem,
    color: Color = Color.White//Color(0xffE91E63)
) {

    var isFavorite  by remember { mutableStateOf(false) }

    IconToggleButton(
        checked = isFavorite,
        onCheckedChange = {
            isFavorite = !isFavorite

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


//@ExperimentalMaterialApi
//@Preview
//@Composable
//fun FavoritesScreenPreview() = TmdbTheme {
//   // FavoritesScreen()
//}
