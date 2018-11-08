package mistyhippo.github.com.activitylifecyclestate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final String EXTRA_MESSAGE = "mistyhippo.github.com.activitylifecyclestate";
    private EditText mMessageEditText;
    public static final int TEXT_REQUEST = 1;
    private TextView myReplyHeadTextView;
    private TextView myReplyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialize all the view variables
        mMessageEditText = findViewById(R.id.editText_main);
        myReplyHeadTextView = findViewById(R.id.text_header_reply);
        myReplyTextView = findViewById(R.id.text_message_reply);
        //Restore the state
        if(savedInstanceState != null){
            boolean isVisible = savedInstanceState.getBoolean("reply_invisible");

            if(isVisible){
                myReplyHeadTextView.setVisibility(View.VISIBLE);
                myReplyTextView.setText(savedInstanceState.getString("reply_text"));
                myReplyTextView.setVisibility(View.VISIBLE);
            }
        }
        Log.d(LOG_TAG, "--------");
        Log.d(LOG_TAG, "onCreate");
    }
    @Override
    public void onStart(){
        super.onStart();
        Log.d(LOG_TAG, "onStart");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }

    @Override
    public void onRestart(){
        super.onRestart();
        Log.d(LOG_TAG, "onRestart");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(LOG_TAG, "onResume");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        if(myReplyHeadTextView.getVisibility() == View.VISIBLE){
            outState.putBoolean("reply_visible", true);
            outState.putString("reply_text", myReplyTextView.getText().toString());
        }
    }



    public void launchSecondActivity(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        String message = mMessageEditText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivityForResult(intent,TEXT_REQUEST);
        Log.d(LOG_TAG, "Button clicked!");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == TEXT_REQUEST){
            if(resultCode == RESULT_OK){
                String reply = data.getStringExtra(SecondActivity.EXTRA_REPLY);
                myReplyHeadTextView.setVisibility(View.VISIBLE);
                myReplyTextView.setText(reply);
                myReplyTextView.setVisibility(View.VISIBLE);
            }
        }
    }
}
