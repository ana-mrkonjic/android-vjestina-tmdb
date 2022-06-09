package agency.five.tmdb.ui

import agency.five.tmdb.R
import agency.five.tmdb.database.CastMemb
import agency.five.tmdb.database.DetailsItem
import agency.five.tmdb.remote.CastMember
import agency.five.tmdb.remote.CrewMember
import agency.five.tmdb.remote.DetailsResponse
import agency.five.tmdb.remote.MembersResponse
import agency.five.tmdb.viewmodel.DetailsViewModel
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
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

    val members = viewModel.getCast(movieId).collectAsState(initial = null).value

    if (members != null) {
        for(c in members.crew) {
            println(c.name)
        }
    }


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
                DetailsContent(movie = movie, members = members)
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
fun DetailsContent(movie: DetailsResponse, members: MembersResponse?) {

    Column(Modifier.verticalScroll(rememberScrollState())) {

        Header(movie)

        Spacer(modifier = Modifier.height(8.dp))

        Overview(movie)

        Spacer(modifier = Modifier.height(16.dp))

        CastList(members)

        Spacer(modifier = Modifier.height(16.dp))

        CastDetails(members)

        Spacer(Modifier.height(72.dp))
    }

}

@Composable
fun Header(movie: DetailsResponse?) {
    Box(modifier = Modifier) {
        Image(
            painter =rememberAsyncImagePainter(movie?.imageUrl),
            modifier = Modifier
                .fillMaxSize()
                .height(304.dp)
                .width(363.dp),
            contentDescription = "Movie image",
            contentScale = ContentScale.Crop
        )

        Text(
            text = "User Score: " + movie?.score.toString(),
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
            modifier = Modifier.padding(start = 16.dp, top = 104.dp),
            color = Color.White,
            fontSize = 16.sp
        )

        Text(
            text = movie?.genres?.get(0)?.name.toString(),
            modifier = Modifier.padding(start = 16.dp, top = 128.dp),
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
                text = item.character,
                modifier = Modifier.padding(start = 8.dp),
                fontSize = 12.sp,
            )

        }
    }

}

@Composable
fun Overview(movie: DetailsResponse?) {
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
fun CastList(members: MembersResponse?) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = dimensionResource(id = R.dimen.micro_spacing))
    ) {
        if (members != null) {
            items(members.crew) {
                DirectorPart(
                    item = it,
                    modifier = Modifier.padding(
                        bottom = dimensionResource(id = R.dimen.micro_spacing),
                        //end = dimensionResource(id = R.dimen.micro_spacing)
                    )
                )
            }
        }

    }
    /*Column(
        modifier = Modifier.padding(8.dp),
        verticalArrangement = Arrangement.Top
    ) {
        var index = 0
        val crewList = members?.crew
        if (crewList != null) {
            for(i in 0..1) {
                Row(modifier = Modifier.padding(8.dp),horizontalArrangement = Arrangement.Center){
                    for(i in 0..3) {
                        if(index < 6) {
                            DirectorPart(
                                item = crewList[index++],
                                modifier = Modifier.padding(
                                    bottom = dimensionResource(id = R.dimen.micro_spacing),
                                    end = dimensionResource(id = R.dimen.micro_spacing)
                                )
                            )
                        }

                    }


                }
            }
        }

    }*/
}

@Composable
fun CastDetails(members: MembersResponse?) {
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
        if (members != null) {
            items(members.cast) {
                ActorCard(
                    item = it,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

    }
}


@Composable
fun DirectorPart(item: CrewMember, modifier: Modifier) {
    Column(modifier = Modifier.padding(8.dp), horizontalAlignment = Alignment.Start) {
        Text(text = item.name, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(4.dp))
        Text(text = item.job)
    }
}
