package org.freedu.usercontactmvvm.viewUi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.freedu.usercontactmvvm.R
import org.freedu.usercontactmvvm.databinding.ItemLayoutBinding
import org.freedu.usercontactmvvm.room.User

class MyRecyclerViewAdapter(
    private val usersList: List<User>,
    private val clickListener: (User) -> Unit
) : RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding:ItemLayoutBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_layout, parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(usersList[position], clickListener)
    }

}

class MyViewHolder(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(user: User, clickListener: (User) -> Unit) {
        binding.nameTxt.text = user.name
        binding.emailTxt.text = user.email

        binding.layoutId.setOnClickListener {
            clickListener(user)
        }
    }
}