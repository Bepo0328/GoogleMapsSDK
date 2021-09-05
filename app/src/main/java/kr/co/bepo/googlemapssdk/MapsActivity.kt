package kr.co.bepo.googlemapssdk

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kr.co.bepo.googlemapssdk.databinding.ActivityMapsBinding
import kr.co.bepo.googlemapssdk.databinding.CustomInfoWindowBinding
import kr.co.bepo.googlemapssdk.misc.CameraAndViewport
import kr.co.bepo.googlemapssdk.misc.CustomInfoAdapter
import kr.co.bepo.googlemapssdk.misc.TypeAndStyle

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private val binding: ActivityMapsBinding by lazy { ActivityMapsBinding.inflate(layoutInflater) }

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

        val losAngeles = LatLng(34.04692127928215, -118.24748421830992)
        val newYork = LatLng(40.71614203933524, -74.00440676650565)
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

        mMap.setInfoWindowAdapter(CustomInfoAdapter(this))

        typeAndStyle.setMapStyle(mMap, this)
    }
}