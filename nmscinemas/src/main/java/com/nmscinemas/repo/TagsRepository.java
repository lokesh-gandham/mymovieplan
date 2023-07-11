package com.nmscinemas.repo;

import org.springframework.data.jpa.repository.JpaRepository;


import com.nmscinemas.entities.tags;

public interface TagsRepository extends JpaRepository<tags,Long> {
	void deleteTagById(Long id);

}
