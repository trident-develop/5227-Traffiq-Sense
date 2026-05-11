package com.eyecon.glo.event

sealed interface TVEvent {
    data object OpenGame : TVEvent
}