package edu.temple.assignment3

import android.media.Image
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ItemViewModel() : ViewModel() {
    // item's in list have both a description and a image resource
    var itemImage = MutableLiveData<Image>()

    fun getItemImage() : LiveData<Image> {
        // cannot modify color here
        return itemImage
    }

    fun changeItemImage(_itemResource : Image){
        itemImage.value = _itemResource
    }

}