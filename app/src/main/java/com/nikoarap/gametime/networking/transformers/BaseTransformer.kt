package com.nikoarap.gametime.networking.transformers

interface BaseTransformer<T, S> {
    fun fromDTO(dto: T): S
}