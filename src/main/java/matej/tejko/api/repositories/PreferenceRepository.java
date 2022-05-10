package matej.tejko.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import matej.tejko.models.general.Preference;

public interface PreferenceRepository extends JpaRepository<Preference, UUID> {

}
