package agency.five.tmdb.home


import agency.five.tmdb.R
import agency.five.tmdb.Router
import agency.five.tmdb.Screen
import agency.five.tmdb.home.HomeViewModel
import agency.five.tmdb.home.MovieItem
import agency.five.tmdb.navigation.MainScreen
import agency.five.tmdb.navigation.NavRoutes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.getViewModel

@ExperimentalMaterialApi
@Composable
fun HomeScreen(viewModel: HomeViewModel, navController: NavController) {



/*
    val scaffoldState: ScaffoldState = rememberScaffoldState()

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
        ScreenContent(viewModel = viewModel, movies = viewModel.movieList, categories = viewModel.getCategories(), modifier = Modifier.padding(vertical = 24.dp))
    }*/

    ScreenContent(viewModel = viewModel, navController = navController, movies = viewModel.movieList, categories = viewModel.getCategories(), modifier = Modifier.padding(vertical = 24.dp))

}

@Composable
fun MyTopAppBar() {
    Row(
        Modifier
            .background(colorResource(id = R.color.primary_blue))
            .fillMaxWidth()
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_tmdb_title),
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxSize(),
            contentDescription = "App title image",
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun ScreenContent(
    viewModel: HomeViewModel,
    navController: NavController,
    modifier: Modifier = Modifier,
    movies: List<MovieItem>,
    categories: List<String>
) {

    val textState = remember { mutableStateOf(TextFieldValue("")) }

    LazyColumn(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally//.padding(vertical = 64.dp),
        //contentPadding = PaddingValues(horizontal = 16.dp)
    ) {


        items(count = 1) {
            //TextSearchBar(value = "", label = "Search", onValueChanged = {})
            SearchView(textState)
            viewModel.categoryMap["What's popular"]?.let { it1 -> ScreenSection(viewModel = viewModel, navController = navController, movies = movies, categories = it1, modifier = Modifier, title = "What's popular") }
            viewModel.categoryMap["Free to Watch"]?.let { it1 -> ScreenSection(viewModel = viewModel, navController = navController,movies = movies, categories = it1, modifier = Modifier, title = "Free to watch") }
            viewModel.categoryMap["Trending"]?.let { it1 -> ScreenSection(viewModel = viewModel, navController = navController,movies = movies, categories = it1, modifier = Modifier, title = "Trending") }
            Spacer(modifier = Modifier.padding(vertical = 24.dp))

        }


    }


}

@ExperimentalMaterialApi
@Composable
fun ScreenSection(
    viewModel: HomeViewModel,
    navController: NavController,
    modifier: Modifier = Modifier,
    title: String,
    movies: List<MovieItem>,
    categories: List<String>
    ) {



/*
    var movieItems by remember {
        mutableStateOf(
            listOf(
                MovieItem(
                    id = 1,
                    title = "Title 1",
                    overview = "Content 1",
                    imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
                ),
                MovieItem(
                    id = 2,
                    title = "Title 2",
                    overview = "Content 2",
                    imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
                ),
                MovieItem(
                    id = 3,
                    title = "Title 3",
                    overview = "Content 3",
                    imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
                )
            )
        )
    }*/

    //var favoriteMovies = mutableListOf<MovieItem>()

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

        var moviess = remember {
            viewModel.getCategoryMovies("Streaming")
            mutableStateOf(viewModel.categoryMovies/*viewModel.getCategoryMovies("Streaming")*/) }
        println("Filmovi moviess su: ")
        for(i in viewModel.categoryMovies) println("Film je: "+i.id+" "+i.title)

        Categories(
            categories = categories,
            modifier = Modifier.padding(vertical = 16.dp),
            onCategoryItemClick = {clickedCategory ->
                println("Clicked category: "+clickedCategory)
                val mm = viewModel.getCategoryMovies(clickedCategory)
                println("Filmovi kategorije "+clickedCategory+ "su: ")
                for(m in viewModel.categoryMovies) println("Film je: "+m.id+" "+m.title)
                moviess.value = viewModel.categoryMovies
            })
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        if (movies.isNotEmpty()) {
            MoviesList(
                viewModel = viewModel,
                navController = navController,
                modifier = Modifier,
                onMovieItemClick = {
                    println("Movie Clicked")
                    NavRoutes.Details.route/*clickedMovie -> viewModel.addFavoriteMovie(clickedMovie)*/ },
                movieItems = movies//moviess.value
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


//
//@Composable
//fun TopBar() {
//    Box(modifier = Modifier
//        .background(Color(0xff546e7a))
//        .fillMaxSize()) {
//
//        TopAppBar(
//            //title = { Text(text = "Android Vjestina", color = MaterialTheme.colors.primary) },
//            navigationIcon = {
//                Icon(
//                    painter = painterResource(R.drawable.ic_tmdb_title),
//                    contentDescription = "Content description for visually impaired"
//                )
//
//            },
//            backgroundColor = colorResource(R.color.primary_blue),
//            elevation = 10.dp
//
//        )
//
//    }
//}


@ExperimentalMaterialApi
@Composable
fun MovieCard(
    viewModel: HomeViewModel,
    navController: NavController,
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

        FavoriteShape(
            viewModel = viewModel,
            item = item,
            modifier = Modifier.padding(
                start = 8.dp,//dimensionResource(id = R.dimen.small_spacing),
                top = 8.dp//dimensionResource(id = R.dimen.small_spacing)
            )
        )
    }

}





@ExperimentalMaterialApi
@Composable
private fun MoviesList(
    viewModel: HomeViewModel,
    navController : NavController,
    modifier: Modifier = Modifier,
    onMovieItemClick: (MovieItem) -> Unit = {},
    movieItems: List<MovieItem>
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = dimensionResource(id = R.dimen.home_movies_list_content_padding))
    ) {
        items(movieItems) {
            MovieCard(
                viewModel = viewModel,
                navController = navController,
                item = it,
                onMovieItemClick = {println("Movie Clicked2")
                                   navController.navigate(NavRoutes.Details.route)},
                modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.micro_spacing), end = dimensionResource(id = R.dimen.micro_spacing))
            )
        }

    }
}

@Composable
private fun Categories(
    modifier: Modifier = Modifier,
    onCategoryItemClick: (String) -> Unit = {},
    categories: List<String>
) {
    val selectedIndex = remember { mutableStateOf(0) }
    LazyRow(
        modifier = Modifier,
        //contentPadding = PaddingValues(horizontal = 32.dp)
    ) {
        var position = 0
        items(categories) {
            Category(
                index = position++,
                item = it,
                onCategoryItemClick = {onCategoryItemClick(it)},
                modifier = Modifier
            )
        }

    }
}

@Composable
private fun Category(
    index : Int,
    modifier: Modifier = Modifier,
    item: String,
    clicked: Boolean = false,
    onCategoryItemClick:  () -> Unit = {},
) {
    Column {
       Text(text= item, modifier = Modifier
           .padding(horizontal = 16.dp)
           .clickable {
               onCategoryItemClick()
               println("Clickkkk")
           }, color = if(index == 0) Color.Black else Color.Gray)




        if (clicked) {
            Divider(color = Color.Blue, thickness = 2.dp)
        }

    }
}

@Composable
fun FavoriteShape(
    viewModel: HomeViewModel,
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
        FavoriteButton(viewModel = viewModel, item = item, modifier = Modifier.padding(8.dp))
    }
}

@Composable
fun FavoriteButton(
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier,
    item: MovieItem,
    color: Color = Color.White
) {

    var isFavorite by remember { mutableStateOf(false) }

    IconToggleButton(
        checked = isFavorite,
        onCheckedChange = {
            isFavorite = !isFavorite
            if(isFavorite) {
                /*viewModel.addFavoriteMovie(item)*/
            } else {
                /*viewModel.removeFavoriteMovie(item)*/
            }
//            viewModel.getFavoriteMovies()
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



@Composable
fun BottomBar() {
    val selectedIndex = remember { mutableStateOf(0) }

    BottomNavigation(elevation = 10.dp, backgroundColor = Color.White) {
        BottomNavigationItem(icon = {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "",
                tint = if (selectedIndex.value == 1) colorResource(id = R.color.grey) else colorResource(id = R.color.black)
                //if(selectedIndex == 1) colorResource(id = R.color.grey)
            )
        },
            label = { Text(text = "Home", color = if (selectedIndex.value == 1) colorResource(id = R.color.grey) else colorResource(id = R.color.black)) },
            selected = (selectedIndex.value == 0),
            onClick = {
                selectedIndex.value = 0
            })

        BottomNavigationItem(icon = {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "",
                tint = if (selectedIndex.value == 1) colorResource(id = R.color.black) else colorResource(id = R.color.grey)
            )
        },
            label = { Text(text = "Favorites", color = if (selectedIndex.value == 1) colorResource(id = R.color.black) else colorResource(id = R.color.grey)) },
            selected = (selectedIndex.value == 1),
            onClick = {
                selectedIndex.value = 1
            })

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

/*@Preview
@ExperimentalMaterialApi
@Composable
fun MovieCardPreview() {
    MovieCard(

        modifier = Modifier,
        item = MovieItem(
            1,
            "Peaky blinders",
            "series about..",
            "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg"
        ),
        onMovieItemClick = {})
}*/

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

/*@ExperimentalMaterialApi
@Preview
@Composable
fun ScreenContentPreview() {
    ScreenContent()
}*/

//@ExperimentalMaterialApi
//@Preview
//@Composable
//fun ScreenSectionPreview() {
//    ScreenSection(modifier = Modifier, movies = title= "naslov")
//}

//@ExperimentalMaterialApi
//@Preview
//@Composable
//fun HomeScreenPreview() {
//    HomeScreen()
//}


/*@ExperimentalMaterialApi
@Preview
@Composable
fun MoviesListPreview() {
    MoviesList(
        movieItems = listOf(
            MovieItem(
                id = 1,
                title = "Title 1",
                overview = "Content 1",
                imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
            ),
            MovieItem(
                id = 2,
                title = "Title 2",
                overview = "Content 2",
                imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
            ),
            MovieItem(
                id = 3,
                title = "Title 3",
                overview = "Content 3",
                imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
            )
        ),
        modifier = Modifier.padding(4.dp),
        onMovieItemClick = {}

    )
}*/
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
        label = {Text(text = "Search")},
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

