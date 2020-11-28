package com.example.rosseti.application

enum class CategoryOfDigitalTransf(val id: Int, val description: String) {
    TechProcess(0, "Управление технологическим процессом. Цифровая сеть"),
    ExtraServices(1, "Дополнительные сервисы"),
    NotApplicable(2, "не относится"),
    DigitalManagement(3, "Цифровое управление компанией"),
    InfoSec(4, "Комплексная система информационной безопасности");

    companion object {
        fun getById(id: Int): CategoryOfDigitalTransf? = values().find { it.id == id }
    }
}