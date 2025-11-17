package com.extrime.myrandomusers.presentation.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {
    var gender by remember { mutableStateOf("") }
    var nationality by remember { mutableStateOf("") }
    var genderExpanded by remember { mutableStateOf(false) }
    var nationalityExpanded by remember { mutableStateOf(false) }

    val genders = listOf("", "male", "female")
    val nationalities = listOf("", "US", "GB", "CA", "AU", "DE", "FR", "BR", "JP", "KR", "CN")

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "Random User Generator",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 32.dp),
                    color = MaterialTheme.colorScheme.primary
                )

                // Gender dropdown
                ExposedDropdownMenuBox(
                    expanded = genderExpanded,
                    onExpandedChange = { genderExpanded = !genderExpanded }
                ) {
                    OutlinedTextField(
                        value = gender.ifEmpty { "Any" },
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Gender") },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = genderExpanded)
                        },
                        modifier = Modifier.fillMaxWidth().menuAnchor()
                    )

                    ExposedDropdownMenu(
                        expanded = genderExpanded,
                        onDismissRequest = { genderExpanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Any") },
                            onClick = {
                                gender = ""
                                genderExpanded = false
                            }
                        )
                        genders.filter { it.isNotEmpty() }.forEach { selectionOption ->
                            DropdownMenuItem(
                                text = { Text(selectionOption.replaceFirstChar { it.uppercase() }) },
                                onClick = {
                                    gender = selectionOption
                                    genderExpanded = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Nationality dropdown
                ExposedDropdownMenuBox(
                    expanded = nationalityExpanded,
                    onExpandedChange = { nationalityExpanded = !nationalityExpanded }
                ) {
                    OutlinedTextField(
                        value = nationality.ifEmpty { "Any" },
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Nationality") },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = nationalityExpanded)
                        },
                        modifier = Modifier.fillMaxWidth().menuAnchor()
                    )

                    ExposedDropdownMenu(
                        expanded = nationalityExpanded,
                        onDismissRequest = { nationalityExpanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Any") },
                            onClick = {
                                nationality = ""
                                nationalityExpanded = false
                            }
                        )
                        nationalities.filter { it.isNotEmpty() }.forEach { selectionOption ->
                            DropdownMenuItem(
                                text = { Text(selectionOption) },
                                onClick = {
                                    nationality = selectionOption
                                    nationalityExpanded = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Info card
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Информация:",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "• Выберите фильтры или оставьте пустыми для случайного выбора")
                        Text(text = "• Приложение работает офлайн с использованием кэшированных данных.")
                        Text(text = "• Поддержка фильтрации по полу и национальности.")
                    }
                }
            }

            // Generate button
            Button(
                onClick = {
                    // Если не выбран пол и/или национальность то берем на рандом
                    gender = gender.ifEmpty { listOf("male", "female").random() }
                    nationality = nationality.ifEmpty { nationalities.filter { it.isNotEmpty() }.random() }
                    navController.navigate("userList/${gender}/${nationality}")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                Text(
                    text = "GENERATE USERS",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}