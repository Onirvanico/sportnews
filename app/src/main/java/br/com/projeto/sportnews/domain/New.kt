package br.com.projeto.sportnews.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class New(

    @PrimaryKey
    val id: Int,
    val title: String,
    val description: String,
    val image: String,
    val link: String,
    val favorited: Boolean
    )