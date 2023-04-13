package com.example.ondemandsample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest

private const val packageName = "com.example.dynamicfeature"
private const val onDemandClassname = "$packageName.onDemandActivity"
private lateinit var manager: SplitInstallManager

class MainActivity2 : AppCompatActivity() {

    private val moduleOnDemand by lazy { getString(R.string.title_dynamicfeature) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        manager = SplitInstallManagerFactory.create(this)

        var lButton = findViewById<Button>(R.id.gotoFeature)

        lButton.setOnClickListener {
            openOnDemand()
        }
    }

    private fun openOnDemand(){
        if(manager.installedModules.contains(moduleOnDemand)){
            Toast.makeText(this, "Module ${moduleOnDemand} is Already installed", Toast.LENGTH_SHORT).show()
            launchActivity(onDemandClassname)
        }else{
            Toast.makeText(this, "OnDemand module is not installed", Toast.LENGTH_SHORT).show()
            val request = SplitInstallRequest.newBuilder()
                .addModule(moduleOnDemand)
                .build()

            manager.startInstall(request)
                .addOnCompleteListener {
                    Toast.makeText(this, "Module ${moduleOnDemand} installed", Toast.LENGTH_SHORT).show()
                    launchActivity(onDemandClassname)
                }
                .addOnSuccessListener {
                    Toast.makeText(this, "Loading ${moduleOnDemand}", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Error Loading ${moduleOnDemand}", Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun launchActivity(className: String) {
        Intent().setClassName(packageName, className)
            .also {
                startActivity(it)
            }
    }
}