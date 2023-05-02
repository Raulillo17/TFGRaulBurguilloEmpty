package com.example.tfgraulburguilloempty.views.model

import java.io.Serializable


data class Welcome (
    val player: Player
)


data class Player (
    val country: PlayerCountry,
    val dateOfBirthTimestamp: Long,
    val height: Long,
    val id: Long,
    val jerseyNumber: String,
    val name: String,
    val position: String,
    val shirtNumber: Long,
    val shortName: String,
    val slug: String,
    val team: Team,
    val userCount: Long
) : Serializable


data class PlayerCountry (
    val alpha2: String,
    val name: String
) : Serializable


data class Team (
    val country: PlayerCountry,
    val disabled: Boolean,
    val gender: String,
    val id: Long,
    val name: String,
    val nameCode: String,
    val national: Boolean,
    val shortName: String,
    val slug: String,
    val type: Long,
    val userCount: Long
) : Serializable



