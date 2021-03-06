package de.fra.uas.AdvProBE.db.repositorys;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import de.fra.uas.AdvProBE.db.entitys.Business;

//Interface which is used to get DB commands and create custom ones
public interface BusinessRepository extends MongoRepository<Business, String> {
	// Finds and returns a Business in case it exists
	public Optional<Business> findBusinessById(String id);
	public Optional<Business> findByName(String name);
 
	// Custom Query`s
	@Query("{'city': ?0}")
	List<Business> findByCity(String city);

	@Query("{'state': ?0}")
	List<Business> findByState(String state);

	@Query("{'city': ?0, 'stars': 5, 'isOpen' : true}")
	List<Business> findBestByCity(String city);

	@Query("{'state': ?0, 'stars': 5,  'isOpen' : true}")
	List<Business> findBestByState(String state);

	@Query("{'stars': 5,  'isOpen' : true, 'reviewCount' : {$gt : 100}}")
	List<Business> findBestTotal();

	@Query("{'city': ?0,  'name' : ?1}")
	Optional<Business> findByCityAndName(String city, String name);

	@Query(value = "{'city': {$regex: ?0, $options: 'i'}}", count = true)
	public Integer countFetchedDocumentsForBusinessCity(String city);
}
