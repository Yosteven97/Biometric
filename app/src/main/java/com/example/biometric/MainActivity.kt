package com.example.biometric

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.fragment.app.FragmentActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var biometricPrompt: BiometricPrompt? = null
    private var promptDialogInfo: BiometricPrompt.PromptInfo? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        /**
         *  Tasks are guaranteed to execute
         * sequentially, and no more than one task will be active at any
         * given time.
         */
        val executor = Executors.newSingleThreadExecutor()


        val activity: FragmentActivity = this
        biometricPrompt = BiometricPrompt(
            activity,
            executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                }

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                }
            })

        promptDialogInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Login.")
            .setSubtitle("Login to App.")
            .setDescription("Another Way for Enjoying Login App.")
            .setNegativeButtonText("Cancel")
            .build()

        buttonLogin?.setOnClickListener(this@MainActivity)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.buttonLogin -> {
                promptDialogInfo?.let { biometricPrompt?.authenticate(it) }
            }
        }
    }
}
