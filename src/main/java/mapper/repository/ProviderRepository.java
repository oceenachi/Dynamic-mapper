package mapper.repository;

import mapper.model.ProviderFields;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends MongoRepository<ProviderFields, String> {

}
