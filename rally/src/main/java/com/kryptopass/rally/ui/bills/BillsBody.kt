package com.kryptopass.rally.ui.bills

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.kryptopass.rally.R
import com.kryptopass.rally.data.Bill
import com.kryptopass.rally.ui.components.BillRow
import com.kryptopass.rally.ui.components.StatementBody

@Composable
fun BillsBody(bills: List<Bill>) {
    StatementBody(
        items = bills,
        amounts = { bill -> bill.amount },
        colors = { bill -> bill.color },
        amountsTotal = bills.map { bill -> bill.amount }.sum(),
        circleLabel = stringResource(R.string.due),
        rows = { bill ->
            BillRow(bill.name, bill.due, bill.amount, bill.color)
        }
    )
}
