package com.example.finalproject.repository

import com.example.finalproject.model.Event
import com.example.finalproject.service_api.EventService
import java.io.IOException

interface EventRepository {
    suspend fun getEvent(): List<Event>
    suspend fun insertEvent(event: Event)
    suspend fun updateEvent(id_event: String, event: Event)
    suspend fun deleteEvent(id_event: String)
    suspend fun getEventById(id_event: String): Event
}

class NetworkEventRepository(private val eventService: EventService) : EventRepository {
    override suspend fun insertEvent(event: Event) {
        eventService.insertEvent(event)
    }

    override suspend fun updateEvent(id_event: String, event: Event) {
        eventService.updateEvent(id_event, event)
    }

    override suspend fun deleteEvent(id_event: String) {
        try {
            val response = eventService.deleteEvent(id_event)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete event. HTTP status code: ${response.code()}")
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getEvent(): List<Event> = eventService.getEvent()
    override suspend fun getEventById(id_event: String): Event = eventService.getEventById(id_event)
}