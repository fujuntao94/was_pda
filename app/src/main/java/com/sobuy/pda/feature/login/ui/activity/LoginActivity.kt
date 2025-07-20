package com.sobuy.pda.feature.login.ui.activity

import android.app.Activity
import com.sobuy.pda.R
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.sobuy.pda.core.base.activity.BaseViewModelActivity
import com.sobuy.pda.feature.login.ui.fragment.LostPasswordFragment
import com.sobuy.pda.feature.login.data.model.LoginResponse
import com.sobuy.pda.core.network.Resource
import com.sobuy.pda.databinding.ActivityLoginBinding
import com.sobuy.pda.feature.login.ui.viewmodel.LoginViewModel
import com.sobuy.pda.core.utils.Base64ImageLoader
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LoginActivity : BaseViewModelActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {
    private val viewModel: LoginViewModel by viewModels()
    private var imageLoadJob: Job? = null

    override fun initViews() {
        super.initViews()
    }

    override fun initDatum() {
        super.initDatum()
        setupViewModel()
        observeCaptchaImage()
        observeLoginResult()

        viewModel.captchaImage()
        viewModel.publicKey()
    }

    override fun initListeners() {
        super.initListeners()

        binding.apply {
            submitButton.setOnClickListener {
                viewModel?.validateAndLogin()
            }

            savePassword.setOnClickListener {
                viewModel?.toggleSavePassword()
            }

            lostPassword.setOnClickListener {
                navigateToLostPassword()
//                LostPasswordFragment.show(supportFragmentManager)
            }

            verificationCode.setOnClickListener {
                viewModel?.captchaImage()
            }
        }

    }

    private fun setupViewModel() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        // 观察保存密码状态
        viewModel.savePasswordEnabled.observe(this) { isEnabled ->
            binding.circleView.isSelected = isEnabled
        }
    }

    private fun observeCaptchaImage() {
        imageLoadJob = lifecycleScope.launch {
            viewModel.base64Image.collectLatest { base64Image ->
                Base64ImageLoader.loadImageAsync(binding.verificationCode, base64Image)
            }
        }
    }

    private fun observeLoginResult() {
        viewModel.loginResult.observe(this) { result ->
            when (result) {
                is Resource.Success -> navigateToMainScreen(3000)
                is Resource.Error -> handleLoginError(result)
                is Resource.Loading -> handleLoadingState(result)
            }
        }
    }

    private fun navigateToLostPassword() {
        LostPasswordFragment.Companion.show(supportFragmentManager)
    }
    fun Activity.showCustomToast(
        message: String,
        duration: Int = Toast.LENGTH_SHORT,
        init: Toast.() -> Unit = {}
    ) {
        val toast = Toast.makeText(this, message, duration)
        toast.init()  // 应用自定义设置
        toast.show()
    }
    private fun navigateToMainScreen(duration: Int = Toast.LENGTH_SHORT) {
        showCustomToast("juzhong") {
            view = layoutInflater.inflate(R.layout.custom_toast, null)
        }
//        startActivityAfterFinishThis(MainActivity::class.java)
    }

    private fun handleLoginError(result: Resource.Error<*>) {
        // 显示错误消息
//        showToast(result.message ?: "登录失败")
        // 刷新验证码
        viewModel.captchaImage()
    }

    private fun handleLoadingState(isLoading: Resource.Loading<LoginResponse>) {
        // 显示/隐藏加载指示器
//        binding.progressBar.visibility = if (isLoading) android.view.View.VISIBLE else android.view.View.GONE
    }


    companion object {
        const val TAG = "LoginActivity"
    }
}

