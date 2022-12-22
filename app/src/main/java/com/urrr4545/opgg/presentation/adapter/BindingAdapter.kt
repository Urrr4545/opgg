package com.urrr4545.opgg.presentation.adapter

import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.urrr4545.opgg.Constants
import com.urrr4545.opgg.R
import com.urrr4545.opgg.TimeValue
import com.urrr4545.opgg.domain.model.League

@BindingAdapter(
    requireAll = false,
    value = ["imgUrl", "isCircle"]
)
fun ImageView.setCircleImg(
    imgUrl: String?,
    isCircle: Boolean = false,
) {
    if (imgUrl != null) {
        Glide.with(context).clear(this)
        Glide.with(this).load(imgUrl).let { request ->
            if (isCircle) {
                request.circleCrop()
            }
            request.into(this)
        }
    }
}

@BindingAdapter(
    "imgPosStr"
)
fun ImageView.setPositionImg(
    posStr : String?
) {
    var resource= when(posStr) {
        "TOP" -> R.drawable.icon_lol_top
        "JNG" -> R.drawable.icon_lol_jng
        "MID" -> R.drawable.icon_lol_mid
        "BOT" -> R.drawable.icon_lol_bot
        "SUP" -> R.drawable.icon_lol_sup
        else -> R.drawable.icon_lol_top
    }

    Glide.with(context).clear(this)
    Glide.with(this).load(resource).let { request ->
        request.into(this)
    }
}

@BindingAdapter(
    "scoreRate"
)
fun TextView.setScoreRate(
    league: League
) {
    this.text = "${league.wins}승 ${league.losses}패 (${((league.wins * 100) / (league.wins + league.losses))}%)"
}

@BindingAdapter(
    "isWin"
)
fun LinearLayout.setWinBg(
    isWin: Boolean
) {
    setBackgroundColor(
        if (isWin) {
            context.resources.getColor(R.color.blue, null)
        } else {
            context.resources.getColor(R.color.red, null)
        }
    )
}

@BindingAdapter(
    "isWin"
)
fun TextView.setWin(
    isWin: Boolean
) {
    this.text =
        if (isWin) {
            "승"
        } else {
            "패"
        }
}

@BindingAdapter(
    "gameLength"
)
fun TextView.setGameLength(
    gameLength: Long
) {
    val min = gameLength.toInt() / 60
    val sec = gameLength.toInt() % 60
    this.text = String.format("%02d:%02d", min, sec)
}

@BindingAdapter(
    "opScoreBadge"
)
fun TextView.setOpScoreBadge(
    opScoreBadge: String
) {
    //todo when(opScoreBadge) custom background resource
    if(opScoreBadge.isEmpty()){
        this.setBackgroundResource(0)
    }
    this.text = "${opScoreBadge}"
}

@BindingAdapter(
    "largestMultiKillString"
)
fun TextView.setLargestMultiKillString(
    largestMultiKillString: String
) {
    //todo when(opScoreBadge) custom background resource
    if(largestMultiKillString.isEmpty()){
        this.setBackgroundResource(0)
    }
    this.text = "${largestMultiKillString}"
}

@BindingAdapter(
    "createDate"
)
fun TextView.setCreateDate(
    createDate: Long
) {
    val curTime = System.currentTimeMillis()
    var diffTime = (curTime- createDate*1000) / 1000
    var msg: String? = null
    if(diffTime < TimeValue.SEC.value )
        msg= "방금 전"
    else {
        for (i in TimeValue.values()) {
            diffTime /= i.value
            if (diffTime < i.maximum) {
                msg="${diffTime}${i.msg}"
                break
            }
        }
    }
    this.text = "${msg}"
}

