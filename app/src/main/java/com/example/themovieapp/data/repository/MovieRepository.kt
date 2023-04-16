package com.example.themovieapp.data.repository

import com.example.themovieapp.data.remote.MovieApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(private val movieApi: MovieApi) {
}