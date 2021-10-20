package edu.temple.assignment3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView


class MainActivity: AppCompatActivity(), SelectionFragment.Interface1 {
    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode == RESULT_OK) {
            // do something when intent of launcher returns data
            // a data object is returned
            Log.d("Response data", it.data?.getStringExtra("activity is happening").toString())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        supportActionBar?.title = getString(R.string.select_activity)

        val signArray = getImages()

        val signTextView = findViewById<TextView>(R.id.signTextView)

        supportFragmentManager.beginTransaction()
            .add(R.id.selectionContainer, SelectionFragment.getInstance(signArray))
            .add(R.id.displayContainer, DisplayFragment())
            .commit()

        ViewModelProvider(this)
            .get(ItemViewModel::class.java)
            .getItemImage()
            .observe(this, {
                if(findViewById<View>(R.id.displayContainer) == null)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.displayContainer, DisplayFragment())
                        .addToBackStack(null)
                        .commit()
            })
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

    override fun itemSelected(_index:Int, _array:Array<Item>) : Item{
        return _array[_index]
    }
}