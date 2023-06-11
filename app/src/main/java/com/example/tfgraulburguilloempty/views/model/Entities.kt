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
) : Serializable


data class Player (
    val id: Long,
    val firstName: String? = null,
    val lastName: String? = null,
    val team: String? = null,
    val position: String? = null,
    val dateOfBirth: String? = null,
    val height: String? = null,
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


enum class Height(val value: String) {
    @SerializedName("5' 10\"") The510("5' 10\""),
    @SerializedName("5' 11\"") The511("5' 11\""),
    @SerializedName("5' 9\"") The59("5' 9\""),
    @SerializedName("6' 0\"") The60("6' 0\""),
    @SerializedName("6' 1\"") The61("6' 1\""),
    @SerializedName("6' 10\"") The610("6' 10\""),
    @SerializedName("6' 11\"") The611("6' 11\""),
    @SerializedName("6' 2\"") The62("6' 2\""),
    @SerializedName("6' 3\"") The63("6' 3\""),
    @SerializedName("6' 4\"") The64("6' 4\""),
    @SerializedName("6' 5\"") The65("6' 5\""),
    @SerializedName("6' 6\"") The66("6' 6\""),
    @SerializedName("6' 7\"") The67("6' 7\""),
    @SerializedName("6' 8\"") The68("6' 8\""),
    @SerializedName("6' 9\"") The69("6' 9\""),
    @SerializedName("7' 0\"") The70("7' 0\""),
    @SerializedName("7' 1\"") The71("7' 1\""),
    @SerializedName("7' 2\"") The72("7' 2\""),
    @SerializedName("7' 3\"") The73("7' 3\""),
    @SerializedName("7' 4\"") The74("7' 4\""),
    @SerializedName("7' 6\"") The76("7' 6\"");
}


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

data class Jugador(
    val lastName: String? = null,
    val careerPoints: Double = 0.0,
    val careerRebounds: Double = 0.0,
    val carrerAssists: Double = 0.0,
    val headShotURL: String? = null,
    val team: String? = null,
)

data class Respuesta(
    val jugadores:List<Player>,
    val equipos:List<Team>,
    val jugadoresFav:List<Jugador>
): Serializable