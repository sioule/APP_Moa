package com.mobile.moa.asset

import com.google.gson.annotations.SerializedName

data class AssetResponse (
/*{
    "api_tran_id": "11eb545d-693e-4296-b4b4-ccc62e511889",
    "rsp_code": "A0000",
    "rsp_message": "",
    "api_tran_dtm": "20220802140940848",
    "bank_tran_id": "M202200591U4BC142326",
    "bank_tran_date": "20190101",
    "bank_code_tran": "097",
    "bank_rsp_code": "000",
    "bank_rsp_message": "",
    "fintech_use_num": "120220059188941035897424",
    "balance_amt": "50000000",
    "available_amt": "10000000",
    "account_type": "1",
    "product_name": "알뜰자유적립",
    "bank_name": "KB국민은행",
    "savings_bank_name": "",
    "account_issue_date": "20220802",
    "maturity_date": "20280809",
    "last_tran_date": "00000000"
}*/
    @SerializedName("available_amt") val available_amt: String
    )