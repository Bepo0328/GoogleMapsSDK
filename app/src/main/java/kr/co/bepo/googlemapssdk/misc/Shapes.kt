package kr.co.bepo.googlemapssdk.misc

import android.graphics.Color
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolygonOptions
import com.google.android.gms.maps.model.PolylineOptions
import kotlinx.coroutines.delay
import kr.co.bepo.googlemapssdk.R

class Shapes {

    private val losAngeles = LatLng(34.04692127928215, -118.24748421830992)
    private val newYork = LatLng(40.71614203933524, -74.00440676650565)
    private val madrid = LatLng(40.639871895206674, -3.5627974558481665)
    private val panama = LatLng(8.457442357239337, -79.93696458060398)

    private val p0 = LatLng(34.61111283456, -119.61055187107762)
    private val p1 = LatLng(34.599014993181534, -117.15922754262057)
    private val p2 = LatLng(33.550677353674885, -117.14616252288198)
    private val p3 = LatLng(33.54387186558186, -119.81469280436997)

    private val p00 = LatLng(34.36342923763002, -118.82828381410476)
    private val p01 = LatLng(34.33646281801516, -117.87780362812079)
    private val p02 = LatLng(33.86857373492017, -117.8435079513069)
    private val p03 = LatLng(33.80888822068028, -118.82665068663746)

    suspend fun addPolyline(mMap: GoogleMap) {
        val polyline = mMap.addPolyline(
            PolylineOptions().apply {
                add(losAngeles, newYork, madrid)
                width(5f)
                color(Color.BLUE)
                geodesic(true)
                clickable(true)
            }
        )

        delay(5_000)

        val newList = listOf<LatLng>(
            losAngeles, panama, madrid
        )

        polyline.points = newList
    }

    fun addPolygon(mMap: GoogleMap) {
        val polygon = mMap.addPolygon(
            PolygonOptions().apply {
                add(p0, p1, p2, p3)
                fillColor(R.color.black)
                strokeColor(R.color.black)
                addHole(listOf(p00, p01, p02, p03))
            }
        )
    }
}