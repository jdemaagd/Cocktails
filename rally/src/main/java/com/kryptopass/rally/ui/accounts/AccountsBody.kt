package com.kryptopass.rally.ui.accounts

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.kryptopass.rally.R
import com.kryptopass.rally.data.Account
import com.kryptopass.rally.ui.components.AccountRow
import com.kryptopass.rally.ui.components.StatementBody

@Composable
fun AccountsBody(accounts: List<Account>) {
    StatementBody(
        items = accounts,
        amounts = { account -> account.balance },
        colors = { account -> account.color },
        amountsTotal = accounts.map { account -> account.balance }.sum(),
        circleLabel = stringResource(R.string.total),
        rows = { account ->
            AccountRow(
                name = account.name,
                number = account.number,
                amount = account.balance,
                color = account.color
            )
        }
    )
}
