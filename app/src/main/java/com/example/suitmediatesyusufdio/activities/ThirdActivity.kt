package com.example.suitmediatesyusufdio.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.suitmediatesyusufdio.R
import com.example.suitmediatesyusufdio.adapters.UserAdapter
import com.example.suitmediatesyusufdio.models.User
import com.example.suitmediatesyusufdio.network.ApiClient
import kotlinx.coroutines.launch

class ThirdActivity : AppCompatActivity() {

    private lateinit var rvUsers: RecyclerView
    private lateinit var adapter: UserAdapter
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var ivBack: ImageView

    private val users = mutableListOf<User>()
    private var currentPage = 1
    private var isLoading = false
    private var isLastPage = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_three)

        rvUsers = findViewById(R.id.rvUsers)
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        ivBack = findViewById(R.id.ivBack)

        adapter = UserAdapter(users) { user ->
            val intent = Intent().apply {
                putExtra("selected_name", "${user.first_name} ${user.last_name}")
            }
            setResult(RESULT_OK, intent)
            finish()
        }

        rvUsers.layoutManager = LinearLayoutManager(this)
        rvUsers.adapter = adapter

        swipeRefreshLayout.setOnRefreshListener {
            currentPage = 1
            users.clear()
            isLastPage = false
            fetchUsers()
        }

        ivBack.setOnClickListener { finish() }

        rvUsers.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(rv: RecyclerView, dx: Int, dy: Int) {
                if (!rv.canScrollVertically(1) && !isLoading && !isLastPage) {
                    currentPage++
                    fetchUsers()
                }
            }
        })

        fetchUsers()
    }

    private fun fetchUsers() {
        isLoading = true
        swipeRefreshLayout.isRefreshing = true

        lifecycleScope.launch {
            try {
                val response = ApiClient.instance.getUsers(currentPage)
                val newUsers = response.body()?.data ?: emptyList()
                if (newUsers.isEmpty()) isLastPage = true
                users.addAll(newUsers)
                adapter.notifyDataSetChanged()
            } catch (e: Exception) {
                Toast.makeText(this@ThirdActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            } finally {
                isLoading = false
                swipeRefreshLayout.isRefreshing = false
            }
        }
    }
}
