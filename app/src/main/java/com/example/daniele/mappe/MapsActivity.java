package com.example.daniele.mappe;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;

    private String provider;
    private LocationManager locationManager = null;
    private Location location;


    private double[][] metroLatLng = {
            {41.842605, 12.586417},
            {41.849413, 12.573865},
            {41.853718, 12.567500},
            {41.856627, 12.562661},
            {41.859695, 12.557168},
            {41.862052, 12.552640},
            {41.863602, 12.548070},
            {41.865977, 12.536015},
            {41.869716, 12.529470},
            {41.874709, 12.522983},
            {41.877750, 12.519013},
            {41.882153, 12.513857},
            {41.885605, 12.509565},
            {41.890301, 12.506915},
            {41.894510, 12.504437},
            {41.902188, 12.495825},
            {41.903606, 12.488969},
            {41.906547, 12.483055},
            {41.912708, 12.476386},
            {41.911453, 12.466481},
            {41.909273, 12.458206},
            {41.907695, 12.447519},
            {41.902767, 12.441284},
            {41.899112, 12.434394},
            {41.900477, 12.426216},
            {41.906268, 12.414909}
    } ;

    private String[] metro = {"Anagnina","Cinecittà","Subaugusta","Giulio Agricola", "Lucio Sestio","Numidio Quadrato",
    "Porta furba", "Arco di Travertino", "Colli albani", "Furio Camillo", "Ponte lungo", "Re di Roma", "San Giovanni",
    "Manzoni", "Vittorio Emanuele", "Repubblica", "Barberini", "Spagna", "Flaminio", "Lepanto", "Ottaviano", "Cipro",
    "Valle Aurelia", "Baldo degli Ubaldi", "Cornelia", "Battistini"};
   /* [
            ['Laurentina',41.827162, 12.480893],
            ['Eur Fermi',41.828661, 12.470947],
            ['Eur Palasport',41.830140, 12.466044],
            ['Eur Magliana',41.839589, 12.463351],
            ['Marconi',41.848780, 12.475571],
            ['Basilica San Paolo',41.855952, 12.478375],
            ['Garbatella',41.866512, 12.483178],
            ['Piramide',41.875660, 12.482084],
            ['Circo Massimo',41.883472, 12.487878],
            ['Colosseo',41.891339, 12.491419],
            ['Cavour',41.895051, 12.493818],
            ['Termini',41.901031, 12.500149],
            ['Castro pretorio',41.906191, 12.505501],
            ['Policlinico',41.908685, 12.512212],
            ['Metro bologna',41.913575, 12.520845],
            ['Tiburtina',41.910090, 12.530265],
            ['Quintiliani',41.914697, 12.539023],
            ['Monti tiburtini',41.915942, 12.547606],
            ['Pietralata',41.914936, 12.554912],
            ['Santa maria del soccorso',41.915231, 12.561274],
            ['Ponte mammolo',41.920883, 12.565233],
            ['Rebibbia',41.925669, 12.572687],
            ['S.Agnese/Annibaliano',41.923543, 12.516087],
            ['Libia',41.931661, 12.519252],
            ['Conca d oro',41.940368, 12.528511],
            ['Jonio',41.947263, 12.527696],
            ['Pantano',41.865612, 12.707644],
            ['Graniti',41.865723, 12.697735],
            ['Finocchio',41.865490, 12.687775],
            ['Bolognetta',41.865234, 12.680946],
            ['Borghesiana',41.864661, 12.666595],
            ['Fontana candida',41.864858, 12.658159],
            ['Grotte Celoni',41.862739, 12.646094],
            ['Torre Gaia',41.864091, 12.635692],
            ['Torre Angela',41.864240, 12.625675],
            ['Torrenova',41.863335, 12.616862],
            ['Giardinetti',41.863935, 12.610237],
            ['Torre Maura',41.867315, 12.592484],
            ['Torre spaccata',41.869288, 12.586540],
            ['Alessandrino',41.871405, 12.578343],
            ['Parco di Centocelle',41.874441, 12.568322],
            ['Mirti',41.881265, 12.566388],
            ['Gardenie',41.886161, 12.562021],
            ['Teano',41.889638, 12.551296],
            ['Malatesta',41.887398, 12.540540],
            ['Pigneto',41.888597, 12.528213],
            ['Lodi',41.886938, 12.518767]
            ];
*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        location = locationManager.getLastKnownLocation(provider);
        if (location != null) {
            System.out.println("Provider " + provider + " has been selected.");
            onLocationChanged(location);
        } else {
            //latituteField.setText("Location not available");
            //longitudeField.setText("Location not available");
        }

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));





        while(location == null){

        }
        Location loc = new Location(metro[0]);
        loc.setLatitude(metroLatLng[0][0]);
        loc.setLongitude(metroLatLng[0][1]);
        double distance = location.distanceTo(loc);
        String locStr = metro[0];
        double lat = metroLatLng[0][0];
        double lng = metroLatLng[0][1];
        //float dist = Location.distanceBetween(location.getLatitude(),location.getLongitude(),metroLatLng[0][0],metroLatLng[0][1]);
        for (int i = 0; i < metroLatLng.length; i++){
            mMap.addMarker(new MarkerOptions().position(new LatLng(metroLatLng[i][0],metroLatLng[i][1])).title(metro[i]));
            loc = new Location(metro[i]);
            loc.setLatitude(metroLatLng[i][0]);
            loc.setLongitude(metroLatLng[i][1]);
            double tempDistance = location.distanceTo(loc);
            if(tempDistance < distance){
                locStr = metro[i];
                lat = metroLatLng[i][0];
                lng = metroLatLng[i][1];
            }
        }
        Toast.makeText(this, "La stazione più vicina è "+ locStr, Toast.LENGTH_SHORT).show();
        TextView testo = (TextView) findViewById(R.id.textView);
        testo.setText("La stazione più vicina è "+ locStr);

        //if(location != null){
            //Toast.makeText(this, "location = nonNull = "+ location, Toast.LENGTH_SHORT).show();
        //}
        LatLng roma = new LatLng(location.getLatitude(), location.getLongitude());
        mMap.addMarker(new MarkerOptions().position(roma).title("Marker in Rome").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(roma));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(15));
        //LatLng near = new LatLng(lat, lng);
        //LatLngBounds bounds = new LatLngBounds(roma, roma).including(near);
        //bounds.including(near);
        //mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 0));
        //mMap.setLatLngBoundsForCameraTarget(new LatLngBounds(roma,near));

    }

    /* Request updates at startup */
    @Override
    protected void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(provider, 400, 1, this);
    }

    /* Remove the locationlistener updates when Activity is paused */
    @Override
    protected void onPause() {
        super.onPause();
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.removeUpdates(this);
    }


    @Override
    public void onLocationChanged(Location location) {
        double lat = (location.getLatitude());
        double lng = (location.getLongitude());
        //latituteField.setText(String.valueOf(lat));
        //longitudeField.setText(String.valueOf(lng));
        Toast.makeText(this, "Lat = "+ lat+ " Long = "+ lng, Toast.LENGTH_SHORT).show();
        TextView testo2 = (TextView) findViewById(R.id.textView2);
        TextView testo3 = (TextView) findViewById(R.id.textView3);
        testo2.setText("Lat = "+lat);
        testo3.setText("Lng = "+lng);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(this, "Enabled new provider " + provider, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(this, "Disabled provider " + provider,
                Toast.LENGTH_SHORT).show();
    }







}
