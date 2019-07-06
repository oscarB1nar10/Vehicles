package com.example.diannaliset.vehicles.handlerDBR;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.diannaliset.vehicles.handlerDB.Vehicle;
import com.example.diannaliset.vehicles.handlerDB.VehicleDB_Helper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.StringTokenizer;
/*
    This class provide us to connected to remote data base through (Volley)
 */
public class SyncUpWhitRemoteDBR {

    private int numOfsynchronization=0;
    private Vehicle vehicle;
    private ArrayList<Vehicle> arrayVehicle;
    private Context context;
    private VehicleDB_Helper vehicleDB_helper;
    private RequestQueue requestQueue;
    private JsonObjectRequest jsonObjectReques;

    public SyncUpWhitRemoteDBR(Context context){
        vehicle=new Vehicle();
        this.context=context;
        arrayVehicle=new ArrayList<Vehicle>();
        requestQueue = Volley.newRequestQueue(context);
    }

    /*
        This method allow us send a GET request, after send a data set that
        the remote data base will store.
     */
    public void syncUp(){

         vehicleDB_helper=new VehicleDB_Helper(context);
         vehicleDB_helper.openDB();
         arrayVehicle=vehicleDB_helper.readData();//the data set to add
         vehicleDB_helper.closeDB();

        for(int i=0; i<arrayVehicle.size(); i++){//each data record
            vehicle=arrayVehicle.get(i);
            String url="http://192.168.0.23/vehicles/insertVehicle.php?plaque="+removeBlancks(vehicle.getPlaque())+"&brand="+removeBlancks(vehicle.getBrand())+"&" +
                        "model="+removeBlancks(vehicle.getModel())+"&numOfDoors="+removeBlancks(vehicle.getNumOfDoors()+"&typeOfVehicle="+removeBlancks(vehicle.getTypeOfVehicle()));

            System.out.println("Typer of vehicle without blanks: "+removeBlancks(vehicle.getTypeOfVehicle()));

            jsonObjectReques= new JsonObjectRequest(Request.Method.GET,url,null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    JSONArray arrayAso=null;

                    try {
                        arrayAso=response.getJSONArray("vehicle");
                        JSONObject jsonObject=arrayAso.getJSONObject(0);
                        checkResults(jsonObject.optString("vehicle"));
                        System.out.println("El valor optenido es: "+jsonObject.optString("vehicle"));
                    } catch (JSONException e) {
                        Toast.makeText(context, "Error de tipo..."+e.getMessage(), Toast.LENGTH_LONG).show();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });


            jsonObjectReques.setRetryPolicy(new RetryPolicy() {
                @Override
                public int getCurrentTimeout() {
                    return (DefaultRetryPolicy.DEFAULT_TIMEOUT_MS);
                }

                @Override
                public int getCurrentRetryCount() {
                    return 0;
                }

                @Override
                public void retry(VolleyError error) throws VolleyError {       }
            });
            requestQueue.add(jsonObjectReques);

        }


    }

    public String removeBlancks(String string){
        String result="";
        StringTokenizer stringTokenizer=new StringTokenizer(string, " ");

            while(stringTokenizer.hasMoreTokens()){
                  result+=stringTokenizer.nextToken();
            }
        return result;
    }


    public void checkResults(String string){

        if(!string.equals("WRONG")){
            numOfsynchronization++;
        }
    }

    public int getNumOfsynchronization() {
        return numOfsynchronization;
    }
}
