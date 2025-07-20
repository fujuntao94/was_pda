package com.sobuy.pda.core.utils

import android.util.Base64
import java.security.KeyFactory
import java.security.PublicKey
import java.security.spec.X509EncodedKeySpec
import javax.crypto.Cipher

object RSAUtils {
    private const val ALGORITHM = "RSA"
    private const val TRANSFORMATION = "RSA/ECB/PKCS1Padding"
    private const val KEY_SIZE = 2048 // For key length, it is recommended to use a minimum of 2048 bits

    /**
     * Load public key from Base64 string
     */
    private fun loadPublicKey(publicKeyBase64: String): PublicKey {
        val keyBytes = Base64.decode(publicKeyBase64, Base64.DEFAULT)
        val keySpec = X509EncodedKeySpec(keyBytes)
        val keyFactory = KeyFactory.getInstance(ALGORITHM)
        return keyFactory.generatePublic(keySpec)
    }

    /**
     * Encrypt data using public key (support external incoming public key string)
     */
    fun encrypt(plainText: String, publicKeyBase64: String): String {
        val publicKey = loadPublicKey(publicKeyBase64)
        return encrypt(plainText, publicKey)
    }

    /**
     * Encrypt data using public key (internal overload method)
     */
    private fun encrypt(plainText: String, publicKey: PublicKey): String {
        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.ENCRYPT_MODE, publicKey)

        // Block cipher (processing data larger than the key length)
        val inputBytes = plainText.toByteArray(Charsets.UTF_8)
        val maxBlockSize = (KEY_SIZE / 8) - 11 // RSA maximum encryption block size (PKCS1Padding)
        val outputStream = java.io.ByteArrayOutputStream()

        var currentPosition = 0
        while (currentPosition < inputBytes.size) {
            val blockSize = minOf(maxBlockSize, inputBytes.size - currentPosition)
            val encryptedBlock = cipher.doFinal(inputBytes, currentPosition, blockSize)
            outputStream.write(encryptedBlock)
            currentPosition += blockSize
        }

        val encryptedBytes = outputStream.toByteArray()
        outputStream.close()

        return Base64.encodeToString(encryptedBytes, Base64.DEFAULT)
    }
}