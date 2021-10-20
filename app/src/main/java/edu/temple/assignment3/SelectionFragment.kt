package edu.temple.assignment3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.ViewModelProvider

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class SelectionFragment(_array: Array<Item>) : Fragment() {
    lateinit var spinner : Spinner
    lateinit var layout : View

    // fragment is passed array, makes view from Item at index
    val itemArray = _array
    var startIndex : Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        // state management
        super.onCreate(savedInstanceState)
        startIndex = arguments?.getInt("startupIndex", 0)
        if( arguments != null){ startIndex = requireArguments().getInt("startIndex")}
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        layout = inflater.inflate(R.layout.fragment_selection, container, false)
        spinner = layout.findViewById(R.id.spinner)
        //set up with items from ItemArray
        spinner.adapter = ArrayAdapter(
            requireActivity(),
            android.R.layout.simple_spinner_dropdown_item,
            requireContext().resources.getStringArray(R.array.item_Array)
        )
        val itemVM = ViewModelProvider(requireActivity()).get(ItemViewModel::class.java)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                (requireActivity() as Interface1).itemSelected(p2, itemArray)
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
        return layout
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changeItemDesc(startIndex!!)
        changeItemImage(startIndex!!)
    }

    // activity-->fragment communication
    public fun changeItemImage(index:Int){
        spinner.setSelection(index)
    }
    public fun changeItemDesc(index:Int){

    }
    /*fun changeColor(index: Int?) {
        if (index != null) {
            spinner.setSelection(index)
        }
    }*/

    companion object {
        fun getInstance(_array:Array<Item>): SelectionFragment {
            // call an instance of fragment, provided by fragment
            val fragmentInst = SelectionFragment(_array)
            val bundle : Bundle = Bundle()
            bundle.putInt("startIndex", 1)
            fragmentInst.arguments = bundle
            return fragmentInst
        }
    }

    interface Interface1 {
        fun itemSelected(_index:Int, _array:Array<Item>):Item
    }
}