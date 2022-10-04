package com.mobile.moa.asset

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.moa.R
import com.mobile.moa.databinding.FragmentAssetBinding
import com.mobile.moa.databinding.FragmentAssetListBinding
import com.mobile.moa.model.Account
import com.mobile.moa.model.Balance
import com.mobile.moa.model.Goal
import com.mobile.moa.model.UserInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class AssetListFragment : Fragment() {
    lateinit var binding: FragmentAssetListBinding
    lateinit var user_seq_no: String
    lateinit var bank_tran_id: String

    var access_info = this.requireActivity().getSharedPreferences("access_token", AppCompatActivity.MODE_PRIVATE)
    var access_token = access_info.getString("access_info", "")

    lateinit var userData: UserInfo
    lateinit var balanceData: ArrayList<Balance>
    lateinit var testList: ArrayList<Balance>
    var memberId = 1.toLong() // TODO 멤버 아이디 불러오기

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAssetListBinding.inflate(inflater, container, false)

        getAccount()
        getAccountBalance()
        getMileage()
        getCash()

        return binding.root
    }

    // 사용자 정보 조회 (계좌 정보)
    private fun getAccount() {
        bank_tran_id = "M202201889" + "U000000" + (Random().nextInt(900) + 100)
        val call: Call<UserInfo> = ServiceCreator.service.getUserInfo(access_token!!, user_seq_no)

        call.enqueue(object : Callback<UserInfo> {
            override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                if (response.isSuccessful) {
                    userData = response.body()!!
                    user_seq_no = response.body()!!.user_seq_no
                } else {
                    Toast.makeText(context, "실패", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<UserInfo>, t: Throwable) {
                //Toast.makeText(context, "서버 연결 실패", Toast.LENGTH_SHORT).show()

            }

        })

    }

    // 계좌 잔액 조회
    @RequiresApi(Build.VERSION_CODES.O)
    private fun getAccountBalance() {
        // 계좌별 잔액 조회
        for (i in 0..userData.res_list.size) {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss")
            val tran_dtime = current.format(formatter).toString()

            val fintech_use_num = userData.res_list.get(i).fintech_use_num.toString()
            val call: Call<Balance> = ServiceCreator.service.getAccountBalance(access_token!!, bank_tran_id, fintech_use_num, tran_dtime)

            call.enqueue(object : Callback<Balance> {
                override fun onResponse(call: Call<Balance>, response: Response<Balance>) {
                    if (response.isSuccessful) {
                        balanceData.add(response.body()!!)

                    } else {
                        Toast.makeText(context, "실패", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Balance>, t: Throwable) {
                    //Toast.makeText(context, "서버 연결 실패", Toast.LENGTH_SHORT).show()
                }

            })
        }

        testList = ArrayList<Balance>()
        val list1= Balance("", "", "", "", "", "", "", "", "", "", "51245", "", "", "mini", "카카오뱅크", "", "", "", "")
        val list2= Balance("", "", "", "", "", "", "", "", "", "", "3000", "", "", "저축통장", "신한은행", "", "", "", "")

        testList.add(list1)
        testList.add(list2)
        setAdapter()
    }

    // 계좌 내역
    private fun setAdapter(){
        val manageAdapter = AccountAdapter(testList)

        binding.assetBankbookList.layoutManager = LinearLayoutManager(context)
        binding.assetBankbookList.adapter = manageAdapter
        manageAdapter.setItemClickListener(object: AccountAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                // TODO
            }
        })
    }

    // 마일리지 조회
    private fun getMileage() {
        val call: Call<AssetResponse> = ServiceCreator.service.getMileage(memberId)

        call.enqueue(object : Callback<AssetResponse> {
            override fun onResponse(call: Call<AssetResponse>, response: Response<AssetResponse>) {
                if (response.isSuccessful) {
                    binding.mileageTv.setText("원") // TODO 마일리지 잔액 연결
                } else {
                    Toast.makeText(context, "실패", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<AssetResponse>, t: Throwable) {
                Toast.makeText(context, "서버 연결 실패", Toast.LENGTH_SHORT).show()
            }

        })
    }

    // 현금 조회
    private fun getCash() {
        val call: Call<AssetResponse> = ServiceCreator.service.getCash(memberId)

        call.enqueue(object : Callback<AssetResponse> {
            override fun onResponse(call: Call<AssetResponse>, response: Response<AssetResponse>) {
                if (response.isSuccessful) {
                    binding.cashTv.setText("원") // TODO 현금 잔액 연결
                } else {
                    Toast.makeText(context, "실패", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<AssetResponse>, t: Throwable) {
                Toast.makeText(context, "서버 연결 실패", Toast.LENGTH_SHORT).show()
            }

        })
    }

}