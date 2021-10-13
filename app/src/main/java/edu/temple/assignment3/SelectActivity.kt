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

        supportActionBar?.title = getString(R.string.select_activity)

        val signArray = getImages()

        val signRecyclerView = findViewById<RecyclerView>(R.id.signRecyclerView)
        //val signImageView = findViewById<ImageView>(R.id.signImageView)
        val signTextView = findViewById<TextView>(R.id.signTextView)

        signRecyclerView.layoutManager = GridLayoutManager(this, 3)

        // View.OnClickListener is created in the activity
        // and then passed to adapter
        // This allows the onClick() callback
        // to have access to the activity's members
        val onClickListener = View.OnClickListener {
            val itemPosition = signRecyclerView.getChildAdapterPosition(it)
            //signImageView.setImageResource(signArray[itemPosition].resourceId)
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
        val myArray: Array<String> = this.resources.getStringArray(R.array.item_Array)
        return arrayOf(
            Item( myArray[0], R.drawable.asl_a),
            Item( myArray[1], R.drawable.asl_b),
            Item( myArray[2], R.drawable.asl_c),
            Item( myArray[3], R.drawable.asl_d),
            Item( myArray[4], R.drawable.asl_e),
            Item( myArray[5], R.drawable.asl_f),
            Item( myArray[6], R.drawable.asl_g),
            Item( myArray[7], R.drawable.asl_h),
            Item( myArray[8], R.drawable.asl_i),
            Item( myArray[9], R.drawable.asl_j),
            Item( myArray[10], R.drawable.asl_k),
            Item( myArray[11], R.drawable.asl_l),
            Item( myArray[12], R.drawable.asl_m),
            Item( myArray[13], R.drawable.asl_n),
            Item( myArray[14], R.drawable.asl_o),
            Item( myArray[15], R.drawable.asl_p),
            Item( myArray[16], R.drawable.asl_q),
            Item( myArray[17], R.drawable.asl_r),
            Item( myArray[18], R.drawable.asl_s),
            Item( myArray[19], R.drawable.asl_t),
            Item( myArray[20], R.drawable.asl_u),
            Item( myArray[21], R.drawable.asl_v),
            Item( myArray[22], R.drawable.asl_w),
            Item( myArray[23], R.drawable.asl_x),
            Item( myArray[24], R.drawable.asl_y),
            Item( myArray[25], R.drawable.asl_z),
        )
    }
}