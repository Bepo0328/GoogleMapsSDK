package kr.co.bepo.googlemapssdk.misc

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.*
import kr.co.bepo.googlemapssdk.R

class Overlays {

    private val losAngeles = LatLng(34.04692127928215, -118.24748421830992)
    private val losAngelesBounds = LatLngBounds(
        LatLng(33.91195972596177, -118.76195050322201),
        LatLng(34.38361514748239, -118.15495586666819)
    )

    fun addGroundOverlay(mMap: GoogleMap): GroundOverlay? {
        return mMap.addGroundOverlay(
            GroundOverlayOptions().apply {
                positionFromBounds(losAngelesBounds)
                image(BitmapDescriptorFactory.fromResource(R.drawable.custom_marker))
            }
        )
    }

    fun addGroundOverlayWithTag(mMap: GoogleMap): GroundOverlay? {
        val groundOverlay =  mMap.addGroundOverlay(
            GroundOverlayOptions().apply {
                positionFromBounds(losAngelesBounds)
                image(BitmapDescriptorFactory.fromResource(R.drawable.custom_marker))
            }
        )
        groundOverlay?.tag = "My Tag"
        return groundOverlay
    }
}