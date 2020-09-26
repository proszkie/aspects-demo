package com.proszkie.aspectsdemo.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proszkie.aspectsdemo.domain.Language;
import com.proszkie.aspectsdemo.domain.LanguagesRepository;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AspectsDemoApplicationTests implements WithAssertions {

	@LocalServerPort
	private int port;

	final RestTemplate restTemplate = new RestTemplate();
	final ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	LanguagesRepository languagesRepository;

	final Language DUMMY_LANGUAGE = new Language(UUID.randomUUID(), "DUMMY");

	@Test
	void contextLoads() throws JsonProcessingException {
	    // Given
		final String url = "http://localhost:" + port + "/translate";
		final List<WordDto> exampleWords = WordsDtoFixture.exampleWordsDtoList(DUMMY_LANGUAGE.getId());
		thereIsALanguageInDbWithId(DUMMY_LANGUAGE);

		// When
		final ResponseEntity<String> response = restTemplate.postForEntity(url, exampleWords, String.class);
		final WordsWithTranslationsDto wordsWithTranslations = objectMapper.readValue(response.getBody(), WordsWithTranslationsDto.class);

		// Then
	    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	    assertThat(wordsWithTranslations.getWordsWithTranslations()).hasSize(exampleWords.size());
	}

	private void thereIsALanguageInDbWithId(final Language language) {
	    languagesRepository.save(language);
	}

}
