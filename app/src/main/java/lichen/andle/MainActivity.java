/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package lichen.andle;

import java.util.concurrent.Callable;

import com.andle.Andle;
import com.andle.internal.Task;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private StringBuilder builder = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mTextView = (TextView) findViewById(R.id.text);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testAndle();
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void testAndle() {
        Andle andle = Andle.getInstance();
        Task<Boolean> t1 = new Task<Boolean>(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                String s = "test1\t";
                Log.d("www", s);
                builder.append(s);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTextView.setText(builder.toString());
                    }
                });
                Thread.sleep(1000);
                return true;
            }
        }, new Task.TaskCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean o) {
                String s = "test1 回调 success = "+o +"\n";
                builder.append(s);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTextView.setText(builder.toString());
                    }
                });

                Log.d("www", s);
            }

            @Override
            public void onError(int error, String msg) {
                String s = "test1 回调 error\t";
                builder.append(s);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTextView.setText(builder.toString());
                    }
                });
                Log.d("www", s);
            }
        });
        Task<Boolean> t2 = new Task<Boolean>(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                String s = "test1\t";
                Log.d("www", s);
                builder.append(s);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTextView.setText(builder.toString());
                    }
                });
                Thread.sleep(1000);
                return true;
            }
        }, new Task.TaskCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean o) {
                String s = "test1 回调 success = "+o +"\n";
                builder.append(s);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTextView.setText(builder.toString());
                    }
                });

                Log.d("www", s);
            }

            @Override
            public void onError(int error, String msg) {
                String s = "test1 回调 error\t";
                builder.append(s);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTextView.setText(builder.toString());
                    }
                });
                Log.d("www", s);
            }
        });
        andle.add(t1);
        andle.add(t2);
        andle.add(t1);
        andle.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
