package com.muramsyah.gitsid.todoapp.ui.add

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.*
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.muramsyah.gitsid.todoapp.R
import com.muramsyah.gitsid.todoapp.data.source.remote.response.TodoItem
import com.muramsyah.gitsid.todoapp.databinding.FragmentAddUpdateBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*


@AndroidEntryPoint
class AddUpdateFragment : Fragment() {

    companion object {
        const val EXTRA_DATA = "extra_data"
        private val TAG = AddUpdateFragment::class.java.simpleName
    }

    private var _binding: FragmentAddUpdateBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AddUpdateViewModel by viewModels()

    private var todoData: TodoItem? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddUpdateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        todoData = arguments?.getParcelable(EXTRA_DATA)

        val arrayStatus = context?.resources!!.getStringArray(R.array.status)

        val arrayAdapter = ArrayAdapter(
            requireContext(),
            R.layout.support_simple_spinner_dropdown_item,
            arrayStatus
        )
        binding.spnStatus.adapter = arrayAdapter

        if (todoData != null) {
            binding.edtTitle.setText(todoData?.title)
            binding.edtCatatan.setText(todoData?.catatan)

            binding.fab.setOnClickListener {
                doAction(1)
            }
        } else {
            binding.fab.setImageResource(R.drawable.ic_add)

            binding.fab.setOnClickListener {
              doAction(2)
            }
        }

        binding.btnDelete.setOnClickListener {
            doAction(3)
        }

        binding.btnBack.setOnClickListener {
            it.findNavController().popBackStack()
        }

    }

    private fun doAction(action: Int) {
        var title = binding.edtTitle.text.toString().trim()
        var catatan = binding.edtCatatan.text.toString().trim()
        var status = binding.spnStatus.selectedItem.toString().trim().lowercase()

        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault())
        val tanggal = simpleDateFormat.format(Date())

        if (TextUtils.isEmpty(title)) {
            title = "Empty"
        }

        if (TextUtils.isEmpty(catatan)) {
            catatan = "Empty"
        }

        if (TextUtils.isEmpty(status)) {
            status = "Biasa"
        }

        when (action) {
            // Update
            1 -> {
                viewModel.updateTodo(todoData?.id!!, title, catatan, tanggal.toString(), status)
                    .observe(viewLifecycleOwner, {
                        if (it.status == 200) {
                            Toast.makeText(context, "Berhasil melakukan update", Toast.LENGTH_LONG)
                                .show()
                        } else {
                            Toast.makeText(context, "Gagal melakukan update", Toast.LENGTH_LONG)
                                .show()
                        }
                    })
            }
            // Add
            2 -> {
                viewModel.addTodo(title, catatan, tanggal, status).observe(viewLifecycleOwner, {
                    if (it.status == 200) {
                        Toast.makeText(context, "Berhasil menambahkan data", Toast.LENGTH_LONG)
                            .show()
                    } else {
                        Toast.makeText(context, "Gagal menambahkan data", Toast.LENGTH_LONG).show()
                    }
                })
            }
            //Delete
            3 -> {
                viewModel.deleteTodo(todoData?.id!!).observe(viewLifecycleOwner, {
                    if (it.status == 200) {
                        view?.findNavController()?.popBackStack()
                    } else {
                        Toast.makeText(context, "Gagal menghapus todo", Toast.LENGTH_LONG).show()
                    }
                })
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}