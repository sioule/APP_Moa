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
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.moa.R
import com.mobile.moa.databinding.FragmentAssetBinding
import com.mobile.moa.databinding.FragmentAssetListBinding
import com.mobile.moa.model.Account
import com.mobile.moa.model.Balance
import com.mobile.moa.model.UserInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AssetListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AssetListFragment : Fragment() {
    lateinit var binding: FragmentAssetListBinding

    var user_seq_no = "" // TODO user_seq_no 불러오기
    var bank_tran_id = "" // TODO 은행거래고유번호 생성
    lateinit var userData: UserInfo
    lateinit var balanceData: ArrayList<Balance>
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
        val call: Call<UserInfo> = ServiceCreator.service.getUserInfo(user_seq_no)

        call.enqueue(object : Callback<UserInfo> {
            override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                if (response.isSuccessful) {
                    userData = response.body()!!
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
            val call: Call<Balance> = ServiceCreator.service.getAccountBalance(bank_tran_id, fintech_use_num, tran_dtime)

            call.enqueue(object : Callback<Balance> {
                override fun onResponse(call: Call<Balance>, response: Response<Balance>) {
                    if (response.isSuccessful) {
                        balanceData.add(response.body()!!)
                    } else {
                        Toast.makeText(context, "실패", Toast.LENGTH_SHORT).show()
                    }
                    setAdapter()
                }

                override fun onFailure(call: Call<Balance>, t: Throwable) {
                    //Toast.makeText(context, "서버 연결 실패", Toast.LENGTH_SHORT).show()
                }

            })
        }
    }

    // 계좌 내역
    private fun setAdapter(){
        val manageAdapter = AccountAdapter(balanceData)

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