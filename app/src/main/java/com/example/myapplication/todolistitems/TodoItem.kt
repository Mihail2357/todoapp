package com.example.myapplication.todolistitems

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TodoItem(
  item: Item,
  modifier: Modifier = Modifier,
  onDeleteItem: (Int) -> Unit,
  onCheckItem: (Int, Boolean) -> Unit,
) {
  Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically
  ) {
    Column(
      modifier = Modifier.weight(1f),
      verticalArrangement = Arrangement.Center
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = item.title,
          fontSize = 20.sp,
          fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(onClick = { onDeleteItem(item.id) })
        {
          Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "Delete"
          )
        }
      }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = item.description)
    }
    Checkbox(
      checked = item.isChecked,
      onCheckedChange = { isChecked -> onCheckItem(item.id, isChecked)
      }
    )
  }
}