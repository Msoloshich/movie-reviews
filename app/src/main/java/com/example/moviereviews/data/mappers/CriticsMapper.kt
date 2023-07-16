package com.example.moviereviews.data.mappers

import com.example.moviereviews.data.database.CriticsDbModel
import com.example.moviereviews.data.network.model.critic.CriticResultDto
import com.example.moviereviews.domain.entity.Critic

class CriticsMapper {
    fun mapCriticsDtoToDbModel(dto: CriticResultDto): CriticsDbModel =
        with(dto) {
            CriticsDbModel(
                bio = bio,
                photoUrl = multimedia?.resource?.src,
                name = displayName,
                status = status,
            )
        }

    fun mapCriticsDbModelToEntity(model: CriticsDbModel): Critic =
        with(model) {
            Critic(
                bio,
                photoUrl,
                name,
                status,
            )
        }
}