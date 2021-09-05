package kr.co.bepo.googlemapssdk.misc

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import kr.co.bepo.googlemapssdk.R
import kr.co.bepo.googlemapssdk.databinding.CustomInfoWindowBinding

class CustomInfoAdapter(context: Context): GoogleMap.InfoWindowAdapter {

    private val contentView =
        (context as Activity).layoutInflater.inflate(R.layout.custom_info_window, null)

    override fun getInfoWindow(marker: Marker): View {
        renderViews(marker, contentView)
        return contentView
    }

    override fun getInfoContents(marker: Marker): View {
        renderViews(marker, contentView)
        return contentView
    }

    private fun renderViews(marker: Marker, contentView: View) {
        val title = marker.title
        val description = marker.snippet

        val titleTextView = contentView.findViewById<TextView>(R.id.titleTextView)
        titleTextView.text = title

        val descriptionTextView = contentView.findViewById<TextView>(R.id.descriptionTextView)
        descriptionTextView.text = description
    }
}