package challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class QuoteServiceImpl implements QuoteService {

	@Autowired
	private QuoteRepository repository;
	final Random random = new Random();

	@Override
	public Quote getQuote() {
		Integer max =repository.findMaxId();
		return repository.findById(random.nextInt(max)).orElseThrow(RuntimeException::new);
	}

	@Override
	public Quote getQuoteByActor(String actor) {
		List<Quote> byActor = repository.findByActor(actor);
		return byActor.get(random.nextInt(byActor.size()));
	}

}
