package com.nikoarap.gametime.networking.transforming.transformers

interface BaseTransformer<T, S> {
    fun fromDTO(dto: T): S
}