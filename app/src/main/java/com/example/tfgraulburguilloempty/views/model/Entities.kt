package com.example.tfgraulburguilloempty.views.model

import java.io.Serializable


data class Team (
    val id: Long,
    val name: String,
    val nickname: String,
    val code: String,
    val city: String,
    val logo: String? = null,
    val allStar: Boolean,
    val nbaFranchise: Boolean,
): Serializable


data class Respuesta (
    val results: Long,
    val response: List<Team>
): Serializable



