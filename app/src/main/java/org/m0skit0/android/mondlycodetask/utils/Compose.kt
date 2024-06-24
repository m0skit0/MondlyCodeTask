package org.m0skit0.android.mondlycodetask.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.imageLoader

@Composable
fun coilImageLoader(): ImageLoader = LocalContext.current.imageLoader
