package matej.tejkogames.api.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import matej.tejkogames.models.general.Yamb;
import matej.tejkogames.models.yamb.enums.YambType;

public interface YambRepository extends JpaRepository<Yamb, UUID> {

    public List<Yamb> findAllByTypeAndUserId(UUID id, YambType type);

}
