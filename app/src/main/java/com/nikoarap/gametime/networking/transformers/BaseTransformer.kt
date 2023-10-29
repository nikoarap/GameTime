package com.nikoarap.gametime.networking.transformers

/**
 * Interface for transforming data transfer objects (DTOs) to domain objects (models).
 *
 * @param T The type of the data transfer object (DTO).
 * @param S The type of the domain object (model).
 */
interface BaseTransformer<T, S> {
    fun fromDTO(dto: T): S
}