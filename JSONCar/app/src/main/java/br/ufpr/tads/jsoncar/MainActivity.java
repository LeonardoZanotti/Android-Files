package br.ufpr.tads.jsoncar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView carInfo = findViewById(R.id.carInfo);
        try {
            carInfo.setText(addData().toString());
            carInfo.setText(carInfo.getText() + "\n" + addObject());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private JSONObject addData() throws JSONException {
        Car car = new Car(1, "Celta", "AAA1234");
        JSONObject jsonCar = new JSONObject();
        jsonCar.put("id", car.getId());
        jsonCar.put("model", car.getModel());
        jsonCar.put("sign", car.getSign());
        return jsonCar;
    }

    private String addObject() throws JSONException {
        Car car = new Car(2, "Celta", "AAA1234");
        JSONObject jsonCar = new JSONObject();
        jsonCar.put("car", car);
        return jsonCar.toString();
    }
}