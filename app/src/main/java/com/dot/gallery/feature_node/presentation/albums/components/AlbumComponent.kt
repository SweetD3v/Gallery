package com.dot.gallery.feature_node.presentation.albums.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.dot.gallery.feature_node.domain.model.Album
import com.dot.gallery.ui.theme.Dimens
import com.dot.gallery.ui.theme.Shapes
import java.io.File

@Composable
fun AlbumComponent(
    album: Album,
    onItemClick: (Album) -> Unit,
) {
    Box(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.surface,
            )
            .padding(horizontal = 8.dp),
    ) {
        Column{
            AlbumImage(album = album, onItemClick)
            Text(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .padding(horizontal = 16.dp),
                text = album.label,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Text(
                modifier = Modifier
                    .padding(top = 2.dp, bottom = 16.dp)
                    .padding(horizontal = 16.dp),
                text = "${album.count} ${if (album.count == 1.toLong()) "item" else "items"}",
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

@Composable
fun AlbumImage(album: Album, onItemClick: (Album) -> Unit) {
    SubcomposeAsyncImage(
        modifier = Modifier
            .aspectRatio(1f)
            .size(Dimens.Album())
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outlineVariant,
                shape = Shapes.large
            )
            .clip(Shapes.large)
            .clickable {
                onItemClick(album)
            },
        model = File(album.pathToThumbnail),
        contentDescription = album.label,
        contentScale = ContentScale.Crop,
        onError = {
            it.result.throwable.printStackTrace()
        }
    )
}