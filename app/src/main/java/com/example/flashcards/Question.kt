package com.example.flashcards

data class Question(
    val statement: String,
    val isHack: Boolean,
    val explanation: String
)

object QuestionRepository {
    val questions = listOf(
        Question(
            "Putting a wet phone in a bowl of dry rice will fix it.",
            false,
            "Rice is actually inefficient at absorbing moisture inside a phone and can leave dust and starch behind."
        ),
        Question(
            "You can use a banana peel to polish leather shoes.",
            true,
            "The potassium and oils in banana peels can actually help clean and shine leather!"
        ),
        Question(
            "Rechargeable batteries last longer if you keep them in the freezer.",
            false,
            "Cold temperatures can actually damage batteries and lead to corrosion."
        ),
        Question(
            "Using a binder clip can help you organize and stand up your phone.",
            true,
            "Binder clips are surprisingly versatile for creating DIY phone stands."
        ),
        Question(
            "Adding salt to water makes it boil significantly faster.",
            false,
            "You would need a massive amount of salt to make any noticeable difference in boiling time."
        )
    )
}
