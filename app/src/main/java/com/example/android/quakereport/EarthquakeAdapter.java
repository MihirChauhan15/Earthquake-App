package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.android.quakereport.Earthquake;
import com.example.android.quakereport.R;

import java.util.ArrayList;
import java.util.Date;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {


    public EarthquakeAdapter(@NonNull Context context, @NonNull ArrayList<Earthquake> earthquakes) {
        super(context,0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView=convertView;
        if(listItemView==null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.eathqauke_list_item,parent,false);

        }
        Earthquake currentEarthquake = getItem(position);

        TextView magnitude = (TextView)listItemView.findViewById(R.id.magnitude);
        double mag = currentEarthquake.getmMagnitude();
        magnitude.setText(currentEarthquake.formatMagnitude(mag));

        GradientDrawable magnitude_background = (GradientDrawable)magnitude.getBackground();
        magnitude_background.setColor(ContextCompat.getColor(getContext(), currentEarthquake.getMagnitudeColor(currentEarthquake.getmMagnitude())));


        TextView location = (TextView)listItemView.findViewById(R.id.locationOffset);
        TextView cityName = (TextView)listItemView.findViewById(R.id.primaryLocation);
        String string = currentEarthquake.getmCityName();

        if(string.contains("of")){
            int end = string.indexOf("of")+2;

            location.setText(string.substring(0,end));
            cityName.setText(string.substring(end,string.length()));
        }

        long mDate = currentEarthquake.getmDate();
        Date dateObject = new Date(mDate);
        TextView date = (TextView)listItemView.findViewById(R.id.date);
        date.setText(currentEarthquake.formatDate(dateObject));

        TextView time = (TextView)listItemView.findViewById(R.id.time);
        time.setText(currentEarthquake.formatTime(dateObject));

        return listItemView;
    }


}

