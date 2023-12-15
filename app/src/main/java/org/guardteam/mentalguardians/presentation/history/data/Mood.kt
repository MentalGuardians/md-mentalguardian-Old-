package org.guardteam.mentalguardians.presentation.history.data

enum class Mood {
    Good,
    Bad;

    companion object {
        fun byNameIgnoreCaseOrNull(input: String): Mood {
            return entries.first { it.name.equals(input, true) }
        }
    }
}