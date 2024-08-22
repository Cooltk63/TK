import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Date;
import java.util.Optional;

public interface MyEntityRepository extends JpaRepository<MyEntity, Long> {
    Optional<MyEntity> findByAssetsdateAndBranchCodeAndId(Date assetsdate, String branchCode, Long id);
}




xxx

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MyService {

    @Autowired
    private MyEntityRepository myEntityRepository;

    @Transactional
    public void updateRecord(String dateString, String branchCode, Long id, List<String> dataList) throws ParseException {
        // Convert String to Date
        Date assetsdate = convertStringToDate(dateString);

        // Retrieve the entity
        Optional<MyEntity> optionalEntity = myEntityRepository.findByAssetsdateAndBranchCodeAndId(assetsdate, branchCode, id);
        
        if (optionalEntity.isPresent()) {
            MyEntity entity = optionalEntity.get();

            // Update the entity fields using dataList (you will need to map dataList to entity fields)
            entity.setField1(dataList.get(0));  // Example field update
            entity.setField2(dataList.get(1));  // Another field update
            // Update more fields as needed

            // Save the updated entity
            myEntityRepository.save(entity);
        } else {
            // Handle the case where the entity was not found
            throw new RuntimeException("Entity not found for the given criteria");
        }
    }

    // Date conversion method
    public Date convertStringToDate(String dateString) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.parse(dateString);
    }
}