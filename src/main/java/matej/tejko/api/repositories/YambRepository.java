package matej.tejko.api.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import matej.tejko.models.yamb.Yamb;
import matej.tejko.models.yamb.enums.YambType;

public interface YambRepository extends JpaRepository<Yamb, UUID> {

    public List<Yamb> findAllByTypeAndUserId(UUID id, YambType type);

}
