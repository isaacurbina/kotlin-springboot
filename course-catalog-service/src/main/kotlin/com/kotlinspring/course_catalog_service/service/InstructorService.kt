package com.kotlinspring.course_catalog_service.service

import com.kotlinspring.course_catalog_service.dto.InstructorDTO
import com.kotlinspring.course_catalog_service.entity.Instructor
import com.kotlinspring.course_catalog_service.repository.InstructorRepository
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class InstructorService(
	val instructorRepository: InstructorRepository
) {

	fun createInstructor(instructorDTO: InstructorDTO): InstructorDTO {
		val instructorEntity = instructorDTO.let {
			Instructor(
				id = it.id,
				name = it.name
			)
		}
		instructorRepository.save(instructorEntity)

		return instructorEntity.let {
			InstructorDTO(
				id = it.id,
				name = it.name
			)
		}
	}

	fun findByInstructorId(instructorId: Int): Optional<Instructor> {
		return instructorRepository.findById(instructorId)
	}
}
