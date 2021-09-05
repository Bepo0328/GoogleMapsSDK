package kr.co.bepo.googlemapssdk

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.Polyline
import com.google.android.gms.maps.model.PolylineOptions
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kr.co.bepo.googlemapssdk.databinding.ActivityMapsBinding
import kr.co.bepo.googlemapssdk.databinding.CustomInfoWindowBinding
import kr.co.bepo.googlemapssdk.misc.CameraAndViewport
import kr.co.bepo.googlemapssdk.misc.CustomInfoAdapter
import kr.co.bepo.googlemapssdk.misc.TypeAndStyle

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnPolylineClickListener {

    private lateinit var mMap: GoogleMap
    private val binding: ActivityMapsBinding by lazy { ActivityMapsBinding.inflate(layoutInflater) }

    private val losAngeles = LatLng(34.04692127928215, -118.24748421830992)
    private val newYork = LatLng(40.71614203933524, -74.00440676650565)
    private val madrid = LatLng(40.639871895206674, -3.5627974558481665)
    private val panama = LatLng(8.457442357239337, -79.93696458060398)

    private val typeAndStyle: TypeAndStyle by lazy { TypeAndStyle() }
    private val cameraAndViewport: CameraAndViewport by lazy { CameraAndViewport() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.map_types_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        typeAndStyle.setMapType(item, mMap)
        return super.onOptionsItemSelected(item)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val losAngelesMarker =
            mMap.addMarker(
                MarkerOptions()
                    .position(losAngeles)
                    .title("Marker in Los Angeles")
                    .snippet("Some random text")
            )

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(losAngeles, 10f))
        mMap.uiSettings.apply {
            isZoomControlsEnabled = true
        }

        typeAndStyle.setMapStyle(mMap, this)

        mMap.setOnPolylineClickListener(this)

        lifecycleScope.launch {
            addPolyline()
        }
    }

    private suspend fun addPolyline() {
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

    override fun onPolylineClick(polyline: Polyline) {
        Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()
    }
}