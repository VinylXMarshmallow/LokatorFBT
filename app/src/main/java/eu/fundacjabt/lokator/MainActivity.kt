package eu.fundacjabt.lokator


import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import android.os.Build
import eu.fundacjabt.lokator.ui.theme.LokatorTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Button
import androidx.compose.ui.Alignment
import androidx.core.app.ActivityCompat


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.TIRAMISU){
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.POST_NOTIFICATIONS, Manifest.permission.ACCESS_FINE_LOCATION),
            0
        )
        setContent {
            LokatorTheme {
                Column(modifier=Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Button(onClick = {
                    Intent(applicationContext,RunningService::class.java).also {
                            it.action=RunningService.Actions.START.toString()
                            startService(it)
                        }
                    }) {
                        Text(text = "Start w tle :D")

                    }
                    Button(onClick = {
                        Intent(applicationContext,RunningService::class.java).also {
                            it.action=RunningService.Actions.STOP.toString()
                            startService(it)
                        }
                    }) {
                        Text(text = "Stop w tle :D")

                    }

                }


            }


        }


    }
}







}



