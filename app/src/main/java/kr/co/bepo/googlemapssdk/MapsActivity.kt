package kr.co.bepo.googlemapssdk

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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kr.co.bepo.googlemapssdk.databinding.ActivityMapsBinding
import kr.co.bepo.googlemapssdk.misc.CameraAndViewport
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
        mMap.addMarker(MarkerOptions().position(losAngeles).title("Marker in Los Angeles"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(losAngeles, 10f))
        mMap.uiSettings.apply {
            isZoomControlsEnabled = true
        }
        typeAndStyle.setMapStyle(mMap, this)

        lifecycleScope.launch {
            delay(4_000L)

            // 3f 만큼 카메라 줌
//            mMap.moveCamera(CameraUpdateFactory.zoomBy(3f))

            // newYork 으로 카메라 이동
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(newYork))

            // (-100f,200f) 만큼 카메라 이동
//            mMap.moveCamera(CameraUpdateFactory.scrollBy(-100f, 200f))

            // 경계 중앙(zoom:10f) 으로 이동
//            mMap.moveCamera(
//                CameraUpdateFactory.newLatLngZoom(
//                    cameraAndViewport.melbourneBounds.center,
//                    10f
//                )
//            )

            // 경계 지정 후 이동
//            mMap.moveCamera(
//                CameraUpdateFactory.newLatLngBounds(
//                    cameraAndViewport.melbourneBounds, 0
//                )
//            )

            // 경계 이상으로 이동 불가
//            mMap.setLatLngBoundsForCameraTarget(cameraAndViewport.melbourneBounds)

            // 애니메이션 효과를 사용하여 카메라 이동
//            mMap.animateCamera(
//                CameraUpdateFactory.newLatLngBounds(
//                    cameraAndViewport.melbourneBounds,
//                    100
//                ), 2_000, null
//            )

            // 애니메이션 효과를 사용하여 15f 만큼 카메라 줌
//            mMap.animateCamera(CameraUpdateFactory.zoomTo(15f), 2_000, null)

            // 애니메이션 효과를 사용하여 (200f,0f) 만큼 카메라 이동
//            mMap.animateCamera(CameraUpdateFactory.scrollBy(200f, 0f), 2_000, null)

            // 애니메이션 효과가 끝나거나 취소됐을 때 행동
            mMap.animateCamera(
                CameraUpdateFactory.newCameraPosition(cameraAndViewport.losAngeles),
                2_000, object : GoogleMap.CancelableCallback {
                    override fun onFinish() {
                        Toast.makeText(this@MapsActivity, "Finished", Toast.LENGTH_SHORT).show()
                    }

                    override fun onCancel() {
                        Toast.makeText(this@MapsActivity, "Canceled", Toast.LENGTH_SHORT).show()
                    }
                }
            )

        }

    }
}