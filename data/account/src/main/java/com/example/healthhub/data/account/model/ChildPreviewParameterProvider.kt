package com.example.healthhub.data.account.model

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class ChildPreviewParameterProvider : PreviewParameterProvider<Child> {
    override val values = sequenceOf(
        Child(
            id = 2,
            surname = "Semenciuc",
            firstname = "Raluca",
            cnp = "123456789",
            dateOfBirth = "05-11-2030",
            fatherSurname = "Semenciuc",
            fatherFirstname = "Cosmin",
            motherSurname = "Semenciuc",
            motherFirstName = "Oana"
        )
    )
}
