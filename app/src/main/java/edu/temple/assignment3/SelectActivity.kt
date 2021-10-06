package edu.temple.assignment3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SelectActivity: AppCompatActivity() {
    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode == RESULT_OK) {
            // do something when intent of launcher returns data
            // a data object is returned
            Log.d("Response data", it.data?.getStringExtra("activity is happening").toString())
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.select_activity)

        supportActionBar?.title = "Test"

        val signArray = getImages()

        val signRecyclerView = findViewById<RecyclerView>(R.id.signRecyclerView)
        val signImageView = findViewById<ImageView>(R.id.signImageView)
        val signTextView = findViewById<TextView>(R.id.signTextView)

        signRecyclerView.layoutManager = GridLayoutManager(this, 3)

        // View.OnClickListener is created in the activity
        // and then passed to adapter
        // This allows the onClick() callback
        // to have access to the activity's members
        val onClickListener = View.OnClickListener {
            val itemPosition = signRecyclerView.getChildAdapterPosition(it)
            signImageView.setImageResource(signArray[itemPosition].resourceId)
            signTextView.text = signArray[itemPosition].description

            val launchIntent = Intent(this, DisplayActivity::class.java) //created a kotlin class out of a java class, this will be repeated regularly
            launchIntent.putExtra("signItem", signArray[itemPosition])

            launcher.launch(launchIntent)
            // we expect data to come back from launchIntent, check logcat
        }

        signRecyclerView.adapter = ImageAdapter(signArray, onClickListener)
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putAll(outPersistentState)
    }

    private fun getImages(): Array<Item> {
        return arrayOf(
            Item( "ASL Letter A", R.drawable.asl_a),
            Item( "ASL Letter B", R.drawable.asl_b),
            Item( "ASL Letter C", R.drawable.asl_c),
            Item( "ASL Letter D", R.drawable.asl_d),
            Item( "ASL Letter E", R.drawable.asl_e),
            Item( "ASL Letter F", R.drawable.asl_f),
            Item( "ASL Letter G", R.drawable.asl_g),
            Item( "ASL Letter H", R.drawable.asl_h),
            Item( "ASL Letter I", R.drawable.asl_i),
            Item( "ASL Letter J", R.drawable.asl_j),
            Item( "ASL Letter K", R.drawable.asl_k),
            Item( "ASL Letter L", R.drawable.asl_l),
            Item( "ASL Letter M", R.drawable.asl_m),
            Item( "ASL Letter N", R.drawable.asl_n),
            Item( "ASL Letter O", R.drawable.asl_o),
            Item( "ASL Letter P", R.drawable.asl_p),
            Item( "ASL Letter Q", R.drawable.asl_q),
            Item( "ASL Letter R", R.drawable.asl_r),
            Item( "ASL Letter S", R.drawable.asl_s),
            Item( "ASL Letter T", R.drawable.asl_t),
            Item( "ASL Letter U", R.drawable.asl_u),
            Item( "ASL Letter V", R.drawable.asl_v),
            Item( "ASL Letter W", R.drawable.asl_w),
            Item( "ASL Letter X", R.drawable.asl_x),
            Item( "ASL Letter Y", R.drawable.asl_y),
            Item( "ASL Letter Z", R.drawable.asl_z),
        )
    }
}