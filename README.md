import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public Date convertStringToDate(String dateString) throws ParseException {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    return formatter.parse(dateString);
}

xxx

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface MyEntityRepository extends JpaRepository<MyEntity, Long> {
    List<MyEntity> findByDateAndBranchCodeAndId(LocalDate date, String branchCode, Long id);
}

xxx
import java.util.List;

public class MyService {

    private final MyEntityRepository myEntityRepository;

    public MyService(MyEntityRepository myEntityRepository) {
        this.myEntityRepository = myEntityRepository;
    }

    public List<MyEntity> findRecords(String dateString, String branchCode, Long id) {
        Date date = convertStringToDate(dateString);
        return myEntityRepository.findByDateAndBranchCodeAndId(date, branchCode, id);
    }
}

xxx

import java.util.List;

public class MyService {

    private final MyEntityRepository myEntityRepository;

    public MyService(MyEntityRepository myEntityRepository) {
        this.myEntityRepository = myEntityRepository;
    }

    public List<MyEntity> findRecords(String dateString, String branchCode, Long id) {
        LocalDate date = convertStringToDate(dateString);
        return myEntityRepository.findByDateAndBranchCodeAndId(date, branchCode, id);
    }
}