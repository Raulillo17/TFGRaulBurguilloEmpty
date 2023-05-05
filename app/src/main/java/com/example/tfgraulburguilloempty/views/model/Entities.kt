package com.example.tfgraulburguilloempty.views.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Team (
    val id: Long,
    val name: String,
    val conference: Conference,
    val record: String,
    @SerializedName("teamLogoUrl")
    val teamLogoURL: String,

    val dateLastUpdated: String
) : Serializable



data class Player (
    val id: Long,
    val firstName: String? = null,
    val lastName: String? = null,
    val team: String? = null,
    val position: Position? = null,
    val dateOfBirth: String? = null,
    val weight: String? = null,
    val jerseyNumber: String? = null,
    val age: String? = null,
    val careerPoints: Double,
    val careerBlocks: Double,
    val carrerAssists: Double,
    val careerRebounds: Double,
    val careerTurnovers: Double,
    val careerPercentageThree: Double,
    val careerPercentageFreethrow: Double,
    val careerPercentageFieldGoal: Double,

    @SerializedName("headShotUrl")
    val headShotURL: String? = null,
    val dateLastUpdated: String? = null
)  : Serializable


enum class Position(val value: String) {
    @SerializedName("Center") Center("Center"),
    @SerializedName("Forward") Forward("Forward"),
    @SerializedName("Guard") Guard("Guard"),
    @SerializedName("Point Guard") PointGuard("Point Guard"),
    @SerializedName("Power Forward") PowerForward("Power Forward"),
    @SerializedName("Shooting Guard") ShootingGuard("Shooting Guard"),
    @SerializedName("Small Forward") SmallForward("Small Forward");
}


enum class Conference(val value: String) {
    @SerializedName("Eastern") Eastern("Eastern"),
    @SerializedName("Western") Western("Western");
}

data class Respuesta(
    val jugadores:List<Player>,
    val equipos:List<Team>,
): Serializable