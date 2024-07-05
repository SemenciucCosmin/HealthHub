package com.example.healthhub.feature.account.model

import androidx.annotation.StringRes
import com.example.healthhub.ui.catalog.R

enum class AccountDialog(
    @StringRes val titleRes: Int,
    @StringRes val messageRes: Int
) {
    CHANGE_EMAIL(
        titleRes = R.string.lbl_email,
        messageRes = R.string.lbl_change_email
    ),
    CHANGE_PASSWORD(
        titleRes = R.string.lbl_password,
        messageRes = R.string.lbl_change_password
    ),
    SIGN_OUT(
        titleRes = R.string.lbl_sign_out,
        messageRes = R.string.lbl_sign_out_dialog_message
    ),
}