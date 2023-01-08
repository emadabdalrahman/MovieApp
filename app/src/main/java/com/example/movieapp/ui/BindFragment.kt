package com.example.movieapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import java.lang.reflect.Method
import java.lang.reflect.ParameterizedType

/*
 for view binding and data binding
 */
@Suppress("UNCHECKED_CAST")
abstract class BindFragment<T : ViewBinding> : Fragment() {

    private var binding: T? = null

    val bind: T
        get() {
            if (binding == null) throw Exception("you can use bind after onCreateView been called")
            return binding as T
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bind = inflateMethod().invoke(null, inflater, container, false) as T?
        this.binding = bind
        return bind?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun bindClass(): Class<T> {
        val parameterizedType = this::class.java.genericSuperclass as ParameterizedType
        val bindingClass = parameterizedType.actualTypeArguments.last()
        return bindingClass as Class<T>
    }

    private fun inflateMethod(): Method {
        val inflate = bindClass().getDeclaredMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.java
        )
        inflate.isAccessible = true
        return inflate
    }
}
