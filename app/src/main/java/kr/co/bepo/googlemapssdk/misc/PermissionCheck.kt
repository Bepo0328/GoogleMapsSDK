package kr.co.bepo.googlemapssdk.misc

class PermissionCheck {
//    private fun checkLocationPermission() {
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
//            == PackageManager.PERMISSION_GRANTED
//        ) {
//            mMap.isMyLocationEnabled = true
//            Toast.makeText(this, "Already Enabled", Toast.LENGTH_SHORT).show()
//        } else{
//            requestPermission()
//        }
//    }
//
//    private fun requestPermission() {
//        ActivityCompat.requestPermissions(
//            this,
//            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
//            1
//        )
//    }
//
//    @SuppressLint("MissingPermission")
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (requestCode != 1) {
//            return
//        }
//
//        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            Toast.makeText(this, "Granted!", Toast.LENGTH_SHORT).show()
//            mMap.isMyLocationEnabled = true
//        }
//        else {
//            Toast.makeText(this, "We need your permission!", Toast.LENGTH_SHORT).show()
//        }
//    }
}