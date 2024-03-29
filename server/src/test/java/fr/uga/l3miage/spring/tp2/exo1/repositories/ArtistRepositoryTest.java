package fr.uga.l3miage.spring.tp2.exo1.repositories;

import fr.uga.l3miage.spring.tp2.exo1.models.ArtistEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import static fr.uga.l3miage.exo1.enums.GenreMusical.*;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        properties = "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect")
public class ArtistRepositoryTest {
    @Autowired
    private ArtistRepository artistRepository;

    @Test
    void testRequestFindAllByGenre(){
        //given
        ArtistEntity artistEntity1 = ArtistEntity.builder().name("Manal")
                .biography("Née le 27 Octobre 2004").genreMusical(VARIETY).build();

        ArtistEntity artistEntity2 = ArtistEntity.builder().name("Ahmed")
                .biography("Née le 12 décembre 2002, a gagné 2 oscars").genreMusical(CLASSIC).build();

        ArtistEntity artistEntity3 = ArtistEntity.builder().name("Ayman")
                .biography("Née le 10 décembre 2003, a gagné 2 oscars").genreMusical(VARIETY).build();

        artistRepository.save(artistEntity1);
        artistRepository.save(artistEntity2);
        artistRepository.save(artistEntity3);


        //when
        int numberOfVarietyGenre = artistRepository.countByGenreMusicalEquals(VARIETY);

        //then
        assertThat(numberOfVarietyGenre).isEqualTo(2);
    }
}
