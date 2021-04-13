package com.example.image_picker.presentation

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.image_picker.databinding.ViewImagePickerBinding
import java.io.ByteArrayOutputStream
import android.Manifest.permission.CAMERA
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.widget.Toast
import com.example.core.extensions.sharedViewModel
import com.example.core.helper.EventObserver
import com.example.image_picker.R
import com.example.image_picker.di.closeImagePickerScope
import com.example.image_picker.di.getOrCreateImagePickerScope
import com.example.image_picker.domain.model.ImageDestiny
import com.example.image_picker.domain.model.ImagePath

private const val REQUEST_CODE_CAMERA_PERMISSION = 1
private const val REQUEST_CODE_CAMERA = 2
private const val REQUEST_CODE_GALLERY = 3

class ImagePickerDialog : DialogFragment() {

    private lateinit var imagePath: ImagePath
    private lateinit var onFinishSaveImage: (String?) -> Unit

    private val imagePickerViewModel by lazy {
        getOrCreateImagePickerScope().sharedViewModel<ImagePickerViewModel>(this)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?) = buildDialog(
        ViewImagePickerBinding.inflate(layoutInflater).apply {
            setUpListeners(this)
            setUpObservers()
        }.root
    )

    override fun onDestroyView() {
        super.onDestroyView()
        closeImagePickerScope()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            data?.run {
                handleImageResult(requestCode, this)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CODE_CAMERA_PERMISSION) {
            pickImageFromCamera()
        }
    }

    private fun buildDialog(view: View): Dialog = requireContext().run {
        val builder = AlertDialog.Builder(this)
        builder.setView(view)
        builder.create().apply {
            setView(view)
        }
    }

    private fun setUpObservers() {
        imagePickerViewModel?.saveError?.observe(viewLifecycleOwner, EventObserver {
            finishProcessWithError()
        })

        imagePickerViewModel?.saveFinish?.observe(viewLifecycleOwner, EventObserver { url ->
            finishProcess(url)
        })
    }

    private fun finishProcess(url: String) {
        onFinishSaveImage(url)
    }

    private fun finishProcessWithError() {
        onFinishSaveImage(null)
    }

    private fun showErrorToast() {
        Toast.makeText(context, R.string.save_error, Toast.LENGTH_SHORT).show()
    }

    private fun setUpListeners(binding: ViewImagePickerBinding) = with(binding) {
        btnPickFromCamera.setOnClickListener {
            imagePickerViewModel?.onClickToPick(PickType.CAMERA, imagePath)
            checkCameraPermission()
            dismiss()
        }

        btnPickFromGallery.setOnClickListener {
            imagePickerViewModel?.onClickToPick(PickType.GALLERY, imagePath)
            pickImageFromGallery()
            dismiss()
        }
    }

    private fun handleImageResult(requestCode: Int, data: Intent) {
        if (requestCode == REQUEST_CODE_CAMERA) {
            data.extras?.get("data")?.takeIf { it is Bitmap }?.run {
                prepareImage(this as Bitmap)
            }
        } else if (requestCode == REQUEST_CODE_GALLERY) {
            data.data?.run {
                prepareImage(getBitmapFromUri(this))
            }
        }
    }

    private fun getBitmapFromUri(uri: Uri): Bitmap {
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
            MediaStore.Images.Media.getBitmap(requireContext().contentResolver, uri)
        } else {
            val source = ImageDecoder.createSource(requireActivity().contentResolver, uri)
            ImageDecoder.decodeBitmap(source)
        }
    }

    private fun prepareImage(bitmap: Bitmap) {
        ByteArrayOutputStream().apply {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, this)
            imagePickerViewModel?.onImagePrepared(this.toByteArray())
        }
    }

    private fun checkCameraPermission() {
        if (checkSelfPermission(requireContext(), CAMERA) != PERMISSION_GRANTED) {
            requestPermissions(arrayOf(CAMERA), REQUEST_CODE_CAMERA_PERMISSION)
        } else {
            pickImageFromCamera()
        }
    }

    private fun pickImageFromCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, REQUEST_CODE_CAMERA)
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK).apply { type = "image/*" }
        startActivityForResult(intent, REQUEST_CODE_GALLERY)
    }

    companion object {
        fun show(
            manager: FragmentManager,
            imagePath: ImagePath,
            onFinishSaveImage: (String?) -> Unit,
        ) {
            ImagePickerDialog().apply {
                this.imagePath = imagePath
                this.onFinishSaveImage = onFinishSaveImage
            }.show(manager, ImagePickerDialog::class.java.name)
        }
    }
}