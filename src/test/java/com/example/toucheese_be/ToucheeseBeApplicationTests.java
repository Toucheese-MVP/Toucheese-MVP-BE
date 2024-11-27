package com.example.toucheese_be;

import com.example.toucheese_be.domain.studio.entity.Concept;
import com.example.toucheese_be.domain.studio.repository.ConceptRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DataJpaTest
class ToucheeseBeApplicationTests {

/*
	@Autowired
	ConceptRepository conceptRepository;

	@Sql("/data.sql")
	@Test
	void getAllConcepts(){
		List<Concept> concept = conceptRepository.findAll();

		assertThat(concept.size()).isEqualTo(1);
	}
*/
}
