package com.mobile.moa.model

// TODO 필요없는 데이터 삭제
data class UserInfo(
    val api_tran_id: String,
    val api_tran_dtm: String,
    val rsp_code: String,
    val rsp_message: String,
    val user_seq_no: String,
    val user_ci: String,
    val user_name: String,
    val res_cnt: String,
    val res_list: List<Account>, // 계좌 정보
    val inquiry_card_cnt: String,
    val inquiry_card_list: List<InquiryCard>,
)

data class InquiryCard(
    val bank_code_std: String,
    val member_bank_code: String,
    val inquiry_agree_dtime: String,
)

data class Account(
    val fintech_use_num: String, // 핀테크 이용번호
    val account_alias: String,
    val bank_code_std: String,
    val bank_code_sub: String,
    val bank_name: String, // 은행 이름
    val account_num_masked: String,
    val account_holder_name: String,
    val account_holder_type: String,
    val acount_type: String,
    val inquiry_agree_yn: String,
    val inquiry_agree_dtimeval : String,
    val transfer_agree_yn: String,
    val transfer_agree_dtime: String,
    val payer_num: String,
)

data class Balance(
    val api_tran_id: String,
    val rsp_code: String,
    val rsp_message: String,
    val api_tran_dtm: String,
    val bank_tran_id: String,
    val bank_tran_date: String,
    val bank_code_tran: String,
    val bank_rsp_code: String,
    val bank_rsp_message: String,
    val fintech_use_num: String,
    val balance_amt: String, // 잔액
    val available_amt: String,
    val account_type: String,
    val product_name: String, // 통장 이름
    val bank_name: String, // 은행 이름
    val savings_bank_name: String,
    val account_issue_date: String,
    val maturity_date: String,
    val last_tran_date: String,

)