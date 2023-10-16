package com.example.demo.api

import openapi.api.AnnouncementApi
import openapi.model.AnnouncementResponse
import openapi.model.AnnouncementsByPageResponse
import openapi.model.CreateAnnouncementRequest
import openapi.model.UpdateAnnouncementRequest
import org.springframework.http.ResponseEntity

class Test : AnnouncementApi {
    override fun createAnnouncement(
        authorization: String,
        createAnnouncementRequest: CreateAnnouncementRequest
    ): ResponseEntity<Unit> {
        return super.createAnnouncement(authorization, createAnnouncementRequest)
    }

    override fun deleteAnnouncement(id: Int, authorization: String): ResponseEntity<Unit> {
        return super.deleteAnnouncement(id, authorization)
    }

    override fun findAllAnnouncementByPage(
        authorization: String,
        page: Int,
        size: Int
    ): ResponseEntity<AnnouncementsByPageResponse> {
        return super.findAllAnnouncementByPage(authorization, page, size)
    }

    override fun findAnnouncementById(id: Int, authorization: String): ResponseEntity<AnnouncementResponse> {
        return super.findAnnouncementById(id, authorization)
    }

    override fun updateAnnouncement(
        id: Int,
        authorization: String,
        updateAnnouncementRequest: UpdateAnnouncementRequest
    ): ResponseEntity<Unit> {
        return super.updateAnnouncement(id, authorization, updateAnnouncementRequest)
    }
}
