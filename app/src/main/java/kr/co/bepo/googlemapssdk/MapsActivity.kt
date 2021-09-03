package kr.co.bepo.googlemapssdk

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import kr.co.bepo.googlemapssdk.databinding.ActivityMapsBinding
import kr.co.bepo.googlemapssdk.misc.CameraAndViewport
import kr.co.bepo.googlemapssdk.misc.TypeAndStyle

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnMarkerDragListener {

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
//        val losAngeles2 = LatLng(34.125037184477044, -118.38286807008632)
        val newYork = LatLng(40.71614203933524, -74.00440676650565)
        val losAngelesMarker =
            mMap.addMarker(MarkerOptions()
                .position(losAngeles)
                .title("Marker in Los Angeles")
                // 드래그 허용
//                .draggable(true)
                // Marker 색상(ORANGE) 변경
//                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
                // Marker 색상(HSL 색상표) 변경
//                .icon(BitmapDescriptorFactory.defaultMarker(134f))
                // Marker 아이콘 변경
//                .icon(BitmapDescriptorFactory.fromResource(R.drawable.custom_marker))
                // Marker 아이콘 변경 후 색상(Black)도 함께 변경
//                .icon(fromVectorToBitmap(R.drawable.ic_android, Color.parseColor("#000000")))
                // Marker 투명도 적용
//                .alpha(0.5f)
                // Marker 회전 적용
//                .rotation(90f)
                // Marker 가 같이 회전 하지 않음
//                .flat(true)
                .snippet("Some random text")
        )

//        val losAngelesMarker2 =
//            mMap.addMarker(MarkerOptions()
//                .position(losAngeles2)
//                .title("Marker in Los Angeles")
//                .snippet("Some random text")
//                // Marker 가 겹쳤을 때 우선순위 개념
//                .zIndex(1f)
//            )

        losAngelesMarker?.tag = "Restaurant"
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(losAngeles, 10f))
        mMap.uiSettings.apply {
            isZoomControlsEnabled = true
        }
        typeAndStyle.setMapStyle(mMap, this)

//        lifecycleScope.launch {
//            delay(4_000L)
//        }

//        onMapClicked()
//        onMapLongClicked()

//        mMap.setOnMarkerClickListener(this)
//        mMap.setOnMarkerDragListener(this)
    }

    // 짧은 클릭 시 호출
    private fun onMapClicked() {
        mMap.setOnMapClickListener {
            Toast.makeText(
                this,
                "Single Click",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    // 오래 클릭 시 호출
    private fun onMapLongClicked() {
        mMap.setOnMapLongClickListener {
            mMap.addMarker(MarkerOptions().position(it).title("New Marker"))
        }
    }


    // Marker 클릭 시 호출
    override fun onMarkerClick(marker: Marker): Boolean {
        Log.d("Marker", marker.tag as String)
        mMap.animateCamera(CameraUpdateFactory.zoomTo(17f), 2_000, null)
        marker.showInfoWindow()
        return true
    }

    // Marker 드래그 시 호출
    override fun onMarkerDragStart(marker: Marker) {
        Log.d("Marker", "Start")
    }

    override fun onMarkerDrag(marker: Marker) {
        Log.d("Marker","Drag")
    }

    override fun onMarkerDragEnd(marker: Marker) {
        Log.d("Marker", "End")
    }

    // Vector Xml 을 Bitmap 으로 변경
    private fun fromVectorToBitmap(id: Int, color: Int): BitmapDescriptor {
        val vectorDrawable: Drawable? = ResourcesCompat.getDrawable(resources, id, null)
        if (vectorDrawable == null) {
            Log.d("MapsActivity", "Resource not found.")
            return BitmapDescriptorFactory.defaultMarker()
        }
        val bitmap = Bitmap.createBitmap(
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        vectorDrawable.setBounds(0, 0, canvas.width, canvas.height)
        DrawableCompat.setTint(vectorDrawable, color)
        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }
}