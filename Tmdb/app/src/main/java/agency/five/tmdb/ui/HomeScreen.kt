package agency.five.tmdb.ui


import agency.five.tmdb.R
import agency.five.tmdb.remote.MovieResponse
import agency.five.tmdb.remote.MoviesResponse
import agency.five.tmdb.repository.MovieItem
import agency.five.tmdb.viewmodel.HomeViewModel
import agency.five.tmdb.viewmodel.MovieCategoryViewState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@ExperimentalMaterialApi
@Composable
fun HomeScreen(onMovieCardClick: (Int) -> Unit) {

    val viewModel = getViewModel<HomeViewModel>()

    ScreenContent(
        viewModel = viewModel,
        onMovieCardClick = onMovieCardClick,
        modifier = Modifier.padding(vertical = 24.dp)
    )

}

@Composable
fun MyTopAppBar() {
    Image(
        painter = painterResource(id = R.drawable.ic_tmdb_title),
        modifier = Modifier
            .background(colorResource(id = R.color.primary_blue))
            .fillMaxWidth()
            .height(56.dp)
            .padding(vertical = 8.dp)
            .fillMaxHeight(),
        contentDescription = "App title image",
    )
}

@ExperimentalMaterialApi
@Composable
fun ScreenContent(
    viewModel: HomeViewModel,
    onMovieCardClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {

    val textState = remember { mutableStateOf(TextFieldValue("")) }

    LazyColumn(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        items(count = 1) {
            SearchView(textState)

            viewModel.getWhatsPopular()
                .collectAsState(initial = null).value?.let { popularCategory ->
                    ScreenSection(
                        viewModel = viewModel,
                        onMovieCardClick = onMovieCardClick,
                        movies = popularCategory.movies,
                        category = "What's popular",
                        categories = viewModel.getTabs("What's popular"),
                        modifier = Modifier,
                        title = "What's popular"
                    )
                }

            ScreenSection(
                viewModel = viewModel,
                onMovieCardClick = onMovieCardClick,
                movies = viewModel.getFreeToWatch().collectAsState(
                    initial = null
                ).value?.movies ?: MoviesResponse(emptyList<MovieResponse>()),
                category = "Free to Watch",
                categories = viewModel.getTabs("Free to Watch"),
                modifier = Modifier,
                title = "Free to watch"
            )


            ScreenSection(
                viewModel = viewModel,
                onMovieCardClick = onMovieCardClick,
                movies = viewModel.getTrending().collectAsState(
                    initial = MovieCategoryViewState(
                        emptyList(),
                        0,
                        MoviesResponse(emptyList<MovieResponse>()),
                    )
                ).value.movies,
                category = "Trending",
                categories = viewModel.getTabs("Trending"),
                modifier = Modifier,
                title = "Trending"
            )

            Spacer(modifier = Modifier.padding(vertical = 24.dp))
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun ScreenSection(
    viewModel: HomeViewModel,
    onMovieCardClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    title: String,
    movies: MoviesResponse,
    category: String,
    categories: List<String>
) {

    Column(
        modifier = Modifier.padding(vertical = 24.dp),
    ) {

        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily(Font(R.font.proxima_nova_font))
        )

        Categories(
            category = category,
            categories = categories,
            modifier = Modifier.padding(vertical = 16.dp),
            onCategoryItemClick = { categoryIndex ->
                viewModel.selectCategory(category, categoryIndex)
                viewModel.getCategoryMovies(category)
            })

        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        if (movies.movies.isNotEmpty()) {
            MoviesList(
                viewModel = viewModel,
                onMovieCardClick = onMovieCardClick,
                modifier = Modifier,
                movieItems = movies
            )
        }
    }
}

@Composable
fun TextSearchBar(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    onDoneActionClick: () -> Unit = {},
    onClearClick: () -> Unit = {},
    onFocusChanged: (FocusState) -> Unit = {},
    onValueChanged: (String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth(.9f)
            .clip(RoundedCornerShape(24.dp))
            .onFocusChanged { onFocusChanged(it) },
        value = value,
        onValueChange = { query ->
            onValueChanged(query)
        },
        label = { Text(text = label) },
        textStyle = MaterialTheme.typography.subtitle1,
        singleLine = true,
        leadingIcon = {
            IconButton(onClick = { onClearClick() }) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
            }
        },
        trailingIcon = {
            IconButton(onClick = { onClearClick() }) {
                Icon(imageVector = Icons.Filled.Clear, contentDescription = "Clear")
            }
        },
        keyboardActions = KeyboardActions(onDone = { onDoneActionClick() }),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Text
        )
    )
}


@ExperimentalMaterialApi
@Composable
fun MovieCard(
    viewModel: HomeViewModel,
    onMovieCardClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    item: MovieResponse,
) {
    Box(modifier = modifier.clickable {
        onMovieCardClick()
    }
    ) {
        Image(
            painter = rememberImagePainter(item.imageUrl),
            contentDescription = null,
            modifier = Modifier
                .size(
                    width = 120.dp,//dimensionResource(id = R.dimen.movie_card_width),
                    height = 176.dp//dimensionResource(id = R.dimen.movie_card_height)
                )
                .clip(RoundedCornerShape(8.dp/*dimensionResource(id = R.dimen.small_spacing)*/)),
            contentScale = ContentScale.Crop,
        )

        FavoriteShape(
            viewModel = viewModel,
            item = item,
            modifier = Modifier.padding(
                start = 8.dp,
                top = 8.dp
            )
        )
    }

}


@ExperimentalMaterialApi
@Composable
private fun MoviesList(
    viewModel: HomeViewModel,
    onMovieCardClick: (Int) -> Unit = {},
    modifier: Modifier = Modifier,
    movieItems: MoviesResponse
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = dimensionResource(id = R.dimen.home_movies_list_content_padding))
    ) {
        items(movieItems.movies) { movie ->
            MovieCard(
                viewModel = viewModel,
                onMovieCardClick = { onMovieCardClick(movie.id) },
                item = movie,
                modifier = Modifier.padding(
                    bottom = dimensionResource(id = R.dimen.micro_spacing),
                    end = dimensionResource(id = R.dimen.micro_spacing)
                )
            )
        }
    }
}

@Composable
private fun Categories(
    modifier: Modifier = Modifier,
    onCategoryItemClick: (Int) -> Unit = {},
    category: String,
    categories: List<String>
) {
    var selectedIndex by remember { mutableStateOf(0) }
    LazyRow(
        modifier = Modifier,
    ) {
        items(categories) {
            Category(
                index = categories.indexOf(it),
                item = it,
                onCategoryItemClick = {
                    selectedIndex = categories.indexOf(it)
                    onCategoryItemClick(selectedIndex)
                },
                modifier = Modifier,
                color = if (categories.indexOf(it) == selectedIndex) Color.Black else Color.Gray
            )
        }

    }
}

@Composable
private fun Category(
    index: Int,
    modifier: Modifier = Modifier,
    item: String,
    clicked: Boolean = false,
    onCategoryItemClick: () -> Unit = {},
    color: Color,
) {

    Column {
        Text(
            text = item,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .clickable {
                    onCategoryItemClick()
                },
            color = color,
        )

        if (clicked) {
            Divider(color = Color.Blue, thickness = 2.dp)
        }

    }
}

@Composable
fun FavoriteShape(
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier,
    item: MovieResponse
) {
    Surface(
        shape = CircleShape,
        modifier = Modifier
            .padding(6.dp)
            .size(32.dp),
        color = Color(0x77000000)
    ) {
        FavoriteButton(
            viewModel = viewModel,
            item = item,
            modifier = Modifier.padding(8.dp),
        )
    }
}

@Composable
fun FavoriteButton(
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier,
    item: MovieResponse,
    color: Color = Color.White
) {

    var isFavorite by remember { mutableStateOf(false) }
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


@Preview
@ExperimentalMaterialApi
@Composable
fun MovieItemPreview() {
    MovieItem(
        1,
        "Peaky blinders",
        "series about..",
        "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg"
    )
}


@Preview
@Composable
fun TextSearchBarPreview() {
    TextSearchBar(
        modifier = Modifier,
        value = "",
        label = "Search",
        onDoneActionClick = {},
        onClearClick = {},
        onFocusChanged = {},
        onValueChanged = {})
}

@Composable
fun SearchView(state: MutableState<TextFieldValue>) {
    TextField(
        value = state.value,
        onValueChange = { value ->
            state.value = value
        },
        modifier = Modifier
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp)),
        textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
        label = { Text(text = "Search") },
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "",
                modifier = Modifier
                    .padding(16.dp)
                    .size(24.dp)
            )
        },
        trailingIcon = {
            if (state.value != TextFieldValue("")) {
                IconButton(
                    onClick = {
                        state.value =
                            TextFieldValue("") // Remove text from TextField when you press the 'X' icon
                    }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(16.dp)
                            .size(24.dp)
                    )
                }
            }
        },
        singleLine = true,
        shape = RectangleShape, // The TextFiled has rounded corners top left and right by default
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
            cursorColor = Color.White,
            leadingIconColor = Color.Black,
            trailingIconColor = Color.Black,
            backgroundColor = colorResource(id = R.color.search_grey),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Preview(showBackground = true)
@Composable
fun SearchViewPreview() {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    SearchView(textState)
}

@Preview
@Composable
fun CategoriesPreview() {
    Categories(
        category = "What's popular",
        categories = listOf(
            "Streaming", "On TV", "Rent"
        ),
        modifier = Modifier.padding(4.dp),
        onCategoryItemClick = {}
    )
}

@Preview
@Composable
fun MyTopAppBarPreview() {
    MyTopAppBar()
}

