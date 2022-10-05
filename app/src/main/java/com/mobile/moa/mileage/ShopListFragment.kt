package com.mobile.moa.mileage

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.moa.databinding.FragmentShopListBinding

class ShopListFragment : Fragment(), ShopView {

    lateinit var binding: FragmentShopListBinding
    private var shopService = ShopService()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShopListBinding.inflate(inflater, container, false)
        shopService.setShopView(this)

        // 어댑터
        binding.shopListRv.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.shopListRv.setHasFixedSize(true)

        getShopList()

        return binding.root
    }

    private fun getShopList() {
        if (getJwt() == null) {
            Toast.makeText(activity, "로그인이 필요한 서비스입니다.", Toast.LENGTH_SHORT).show()
        }
        else {
            shopService.getShopListMember(getMemberId(), getJwt()!!)
        }
    }

    //500미터 이내로 수정
    override fun onShopListSuccess(shopList: List<ShopResponse>) {
        val shopAdapter = ShopListRAdapter(shopList)
        binding.shopListRv.adapter = shopAdapter.apply {
            this.setMyItemClickListener(object : ShopListRAdapter.MyItemClickListener {
                override fun onClick(position: Int) {
//                    val dialog = CustomDialog(context!!)
//                    dialog.scrap()
//                    dialog.setOnClickListener(object : CustomDialog.OnDialogClickListener {
//                        override fun onClicked(flag: Boolean) {
//                            if (flag) {
//                                addScrap()
//                            }
//                        }
//                    })
//                }
//                    binding.putSchoolEt.setText(school.userSchool)
                        var dialog = AlertDialog.Builder(context!!)
                        dialog.setTitle("스크랩 하시겠습니까?")

                        fun toast() {
                            Toast.makeText(activity, "스크랩에 추가 되었습니다.", Toast.LENGTH_SHORT).show()
                            addScrap(shopList[position])
                        }

                        var dialogListener = DialogInterface.OnClickListener { p0, p1 ->
                            when (p1) {
                                DialogInterface.BUTTON_POSITIVE -> toast()
                            }
                        }

                        dialog.setPositiveButton("네", dialogListener)
                        dialog.setNegativeButton("아니요", null)
                        dialog.show()
                    }
//                }
//            })
            })
        }
    }

    private fun addScrap(shop: ShopResponse) {
        if (getJwt() == null) {
            Toast.makeText(activity, "로그인이 필요한 서비스입니다.", Toast.LENGTH_SHORT).show()
        }
        else {
            shopService.addScrap(getMemberId(), getJwt()!!, shop)
        }
    }


    override fun onShopListFailure() {
        Log.d("shop-fragment", "fail")
    }

    //스크랩 성공
    override fun onAddScrapSuccess() {
        Toast.makeText(activity, "스크랩 페이지에서 확인이 가능합니다.", Toast.LENGTH_SHORT).show()
    }

    private fun getMemberId(): Long {
        val memberId = activity?.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return memberId!!.getLong("memberId", 0)
    }

    private fun getJwt(): String? {
        val jwt = activity?.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return jwt!!.getString("jwt", null)
    }

}