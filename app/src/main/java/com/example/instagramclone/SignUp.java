package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import com.example.instagramclone.R;
import com.example.instagramclone.SignUpLoginActivity;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private EditText name, punch_speed, punch_power, kick_power, kick_speed;
    private Button Enter,btnGetAllData,btnSwitchActivity;
    private TextView txtGetData;

    private String allKickBoxer;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        punch_speed = (EditText) findViewById(R.id.punchSpeed);
        punch_power = (EditText) findViewById(R.id.punch_power);
        kick_power = (EditText) findViewById(R.id.kickPower);
        kick_speed = (EditText) findViewById(R.id.kickSpeed);
        name = (EditText) findViewById(R.id.name);
        Enter = (Button) findViewById(R.id.Enter);
        txtGetData=(TextView) findViewById(R.id.txtGetData);
        btnGetAllData=(Button) findViewById(R.id.btnGetAllData);
        btnSwitchActivity= (Button)findViewById(R.id.btnSwitchActivity);
        Enter.setOnClickListener(SignUp.this);

        btnGetAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ParseQuery<ParseObject> kickBoxer = ParseQuery.getQuery("KickBoxer");
                kickBoxer.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        allKickBoxer="";
                        if(e==null){
                            if(objects.size() >0){
                                for(ParseObject kickBoxer : objects){
                                    allKickBoxer+=kickBoxer.get("name")+"-"+kickBoxer.get("power_kick")+"\n";
                                }
                                FancyToast.makeText(SignUp.this, allKickBoxer, Toast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();

                            }else{
                                FancyToast.makeText(SignUp.this, "False is"+ e.getMessage(), Toast.LENGTH_SHORT, FancyToast.ERROR, true).show();

                            }
                        }
                    }
                });
            }
        });


        txtGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("KickBoxer");
                parseQuery.getInBackground("fGmndlM74r", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if(object!=null && e==null){
                            txtGetData.setText(object.get("name")+"-"+ "Kick Power:"+object.get("power_kick"));
                        }
                    }
                });
            }
        });
        btnSwitchActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(SignUp.this, SignUpLoginActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override

    public void onClick(View v) {
        try {

            final ParseObject kickboxer = new ParseObject("KickBoxer");
            kickboxer.put("name", name.getText().toString());
            kickboxer.put("punch_speed", Integer.parseInt(punch_speed.getText().toString()));
            kickboxer.put("punch_power", Integer.parseInt(punch_power.getText().toString()));
            kickboxer.put("power_kick", Integer.parseInt(kick_power.getText().toString()));
            kickboxer.put("kick_speed", Integer.parseInt(kick_speed.getText().toString()));


            kickboxer.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        FancyToast.makeText(SignUp.this, kickboxer.get("name") + "is add sucessfull", Toast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                    } else {
                        FancyToast.makeText(SignUp.this, kickboxer.get("name") + "is add False because " + e.getMessage(), Toast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                    }

                }
            });
        } catch (Exception x) {
            FancyToast.makeText(SignUp.this, "False " + x.getMessage(), Toast.LENGTH_SHORT, FancyToast.ERROR, false).show();

        }

    }
}
