package com.sobuy.pda.feature.login.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sobuy.pda.R
import com.sobuy.pda.core.network.Resource
import com.sobuy.pda.core.utils.RSAUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlin.text.isBlank
import com.sobuy.pda.feature.login.data.model.LoginResponse
import com.sobuy.pda.feature.login.data.repository.LoginRepository

class LoginViewModel : ViewModel() {
    private val _base64Image = MutableSharedFlow<String>()
    val base64Image: Flow<String> = _base64Image
    private var uuid: String = ""
    fun captchaImage() {
        viewModelScope.launch {
            val response = LoginRepository.captchaImageApi()
            if (response.code == 200) {
                val base64Image = "data:image/png;base64,${response.img}"
                _base64Image.emit(base64Image)
                uuid = response.uuid
            }
        }
    }

    private val _savePasswordEnabled = MutableLiveData(false)
    val savePasswordEnabled: LiveData<Boolean> = _savePasswordEnabled
    fun toggleSavePassword() {
        _savePasswordEnabled.value = !_savePasswordEnabled.value!!
    }

    val userName = MutableLiveData<String>("")
    val password = MutableLiveData<String>("")
    val code = MutableLiveData<String>("")



    //
    private val _passwordError = MutableLiveData<String?>()
    val passwordError: LiveData<String?> = _passwordError

    // 登录结果
    private val _loginResult = MutableLiveData<Resource<LoginResponse>>()
    val loginResult: LiveData<Resource<LoginResponse>> = _loginResult


    // 验证并执行登录
    fun validateAndLogin() {
//        val emailValid = validateUserName(userName.value ?: "")
//        val passwordValid = validatePassword(password.value ?: "")
//
//        if (emailValid && passwordValid) {
        performLogin()
//        }
    }

    // 执行登录请求
    private fun performLogin() {
        viewModelScope.launch {
            _loginResult.value = Resource.Loading()
            val password = RSAUtils.encrypt(password.value!!, publicKey)
            val result = LoginRepository.loginApi(
                username = userName.value ?: "",
                password = password,
                uuid = uuid,
                language = "en_US",
                code = code.value ?: ""
            )
            _loginResult.value = result
        }
    }

    // 验证密码
    private fun validatePassword(password: String): Boolean {
        return if (password.isBlank()) {
            _passwordError.value = "密码不能为空"
            false
        } else {
            _passwordError.value = null
            true
        }
    }

    private fun validateUserName(userName: String): Boolean {
        return if (userName.isBlank()) {
            _passwordError.value = "用户名不能为空"
            false
        } else if (userName.length < 6) {
            _passwordError.value = "密码长度至少为6位"
            false
        } else {
            _passwordError.value = null
            true
        }
    }

    //
    private var publicKey: String = ""
    fun publicKey() {
        viewModelScope.launch {
            val response = LoginRepository.publicKeyApi()
            if (response.data!!.code == 200) {
                publicKey = response.data.data.publicKey
            }
        }
    }
}