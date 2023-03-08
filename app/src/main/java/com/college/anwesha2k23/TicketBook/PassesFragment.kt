package com.college.anwesha2k23.TicketBook

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import app.rive.runtime.kotlin.RiveAnimationView
import com.college.anwesha2k23.MainActivity
import com.college.anwesha2k23.R
import com.college.anwesha2k23.databinding.FragmentElitePassBinding
import com.college.anwesha2k23.databinding.FragmentPassesBinding
import com.college.anwesha2k23.databinding.FragmentProPassBinding
import java.lang.Math.abs


class PassesFragment : Fragment() {

    private lateinit var binding: FragmentPassesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPassesBinding.inflate(inflater, container, false)
        val view = binding.root

        val viewPager = binding.viewPager
        val adapter = PassesPagerAdapter(this)
        viewPager.adapter = adapter

        viewPager.setPageTransformer(PassesPageTransformer())

        val dotsIndicator =binding.dotsIndicator
        dotsIndicator.setViewPager2(viewPager)

        return view
    }

    // Custom PagerAdapter to return the appropriate fragment for each page
    private inner class PassesPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> ElitePassFragment()
                else -> ProPassFragment()
            }
        }
    }
    private inner class PassesPageTransformer : ViewPager2.PageTransformer {
        override fun transformPage(page: View, position: Float) {
            val absPosition = abs(position)
            val scaleFactor = if (absPosition > 1) 0f else 1 - absPosition
        }
    }

}



class ElitePassFragment : Fragment() {

    private lateinit var binding: FragmentElitePassBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentElitePassBinding.inflate(inflater, container, false)
        val view = binding.root

        val riveAnimationView = view.findViewById<RiveAnimationView>(R.id.animation_view)
        riveAnimationView.play()

        val button = binding.cilcker
        button.setOnClickListener {
            val dialog = Dialog(requireContext())
            dialog.setContentView(R.layout.redirection_layout)
            val imageView = dialog.findViewById<ImageView>(R.id.redirect)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()
            imageView.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(requireActivity().getString(R.string.elite_pass)))
                startActivity(intent)
                dialog.dismiss()
            }
        }

        return view
    }
}

class ProPassFragment : Fragment() {
    private lateinit var binding: FragmentProPassBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProPassBinding.inflate(inflater, container, false)
        val view = binding.root

        val riveAnimationView = view.findViewById<RiveAnimationView>(R.id.animation_view)
        riveAnimationView.play()

        // Add OnClickListener to handle clicks on the pass
        val button = binding.cilcker
        button.setOnClickListener {
            val dialog = Dialog(requireContext())
            dialog.setContentView(R.layout.redirection_layout)
            val imageView = dialog.findViewById<ImageView>(R.id.redirect)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()
            imageView.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(requireActivity().getString(R.string.pro_pass)))
                startActivity(intent)
                dialog.dismiss()
            }
        }
        return view
    }
}
// Redirect user to another screen or perform
