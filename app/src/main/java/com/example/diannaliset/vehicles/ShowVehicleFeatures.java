package com.example.diannaliset.vehicles;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.diannaliset.vehicles.handlerDB.Vehicle;

public class ShowVehicleFeatures extends Activity {
    private LinearLayout linearLayout;
    Canvas canvas;
    Vehicle vehicle;
    Spinner spinnerTire,spinnerCapo,spinnerDoor;
    int colorTire=0,colorCapo=0,colorDoor=0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_vehicles_features);

        Spinner spinnerTire =  findViewById(R.id.spinner_tire);
        Spinner spinnerCapo =  findViewById(R.id.spinner_baul);
        Spinner spinnerDoor =  findViewById(R.id.spinner_door);
        vehicle=new Vehicle();

        colorTire=getIntent().getExtras().getInt("tireColor");
        colorCapo=getIntent().getExtras().getInt("capoColor");
        colorDoor=getIntent().getExtras().getInt("doorColor");


        linearLayout=findViewById(R.id.cavas_view);
        linearLayout.addView(new Panel(this));



    }

    public void addColors(){


    }



    public int getColorTire() {
        return colorTire;
    }

    public void setColorTire(int colorTire) {
        this.colorTire = colorTire;
    }

    public int getColorCapo() {
        return colorCapo;
    }

    public void setColorCapo(int colorCapo) {
        this.colorCapo = colorCapo;
    }

    public int getColorDoor() {
        return colorDoor;
    }

    public void setColorDoor(int colorDoor) {
        this.colorDoor = colorDoor;
    }

    class Panel extends View {
        Context context;
        public Panel(Context context) {
            super(context);
            this.context=context;
            addColors();

        }

        @Override
        public void onDraw(Canvas canva) {
            super.onDraw(canva);
            canvas=canva;
            drawTire();
            drawCapoAndBaul();
            drawableDoor();
            this.invalidate();


        }

        public void drawTire( ){

            Paint pincel=new Paint();
            pincel.setColor(colorTire);
            pincel.setStrokeWidth(100);
            pincel.setTypeface(Typeface.MONOSPACE);

            Paint pincel2=new Paint();
            pincel2.setColor(Color.BLACK);
            pincel2.setTextSize(20);

            Paint pincel3=new Paint();
            pincel3.setARGB(150,0,100,70);
            pincel3.setStrokeWidth(15);
            pincel3.setStrokeMiter(10);


            canvas.drawText("color de llantas",50,130,pincel2);
            canvas.drawCircle(150, 200, 55, pincel);
            canvas.drawCircle(150,200,30,pincel3);

        }

        public void drawCapoAndBaul(){
            Paint pincel1= new Paint();
            pincel1.setColor(colorCapo);
            pincel1.setStrokeWidth(10);

            Paint pincel2=new Paint();
            pincel2.setColor(Color.BLACK);
            pincel2.setTextSize(20);

            canvas.drawText("color de Capó y Baúl",50,350,pincel2);
            float coordinates[]={100,400,100,420,100,420,250,420,250,420,250,360,250,360,100,400};
            canvas.drawLines(coordinates,pincel1);

        }

        public void drawableDoor(){
            Paint pincel1= new Paint();
            pincel1.setColor(colorDoor);
            pincel1.setStrokeWidth(10);

            Paint pincel2=new Paint();
            pincel2.setColor(Color.BLACK);
            pincel2.setTextSize(20);

            canvas.drawText("color de Puerta",50,570,pincel2);
            float coordinates[]={100,590,100,700,100,700,250,700,250,700,250,580,250,580,120,580,120,580,100,590};
            canvas.drawLines(coordinates,pincel1);

        }



    }
}
