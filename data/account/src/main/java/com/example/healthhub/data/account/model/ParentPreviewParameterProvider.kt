package com.example.healthhub.data.account.model

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class ParentPreviewParameterProvider : PreviewParameterProvider<Parent> {
    override val values = sequenceOf(
        Parent(
            id = 1,
            email = "cosminsemenciuc@gmail.com",
            cnp = "123456789",
            series = "SV",
            lastname = "Cosmin",
            firstname = "Semenciuc",
            nationality = "Romania",
            dateOfBirth = "05-11-2000",
            gender = "Masculine"
        )
    )
}
