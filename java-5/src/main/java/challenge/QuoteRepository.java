package challenge;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuoteRepository extends JpaRepository<Quote,Integer> {

    List<Quote> findByActor(String actor);

    @Query("select max(id) from Quote")
    Integer findMaxId();
}

