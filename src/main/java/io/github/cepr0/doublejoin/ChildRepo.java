package io.github.cepr0.doublejoin;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChildRepo extends JpaRepository<Child, Integer> {
}
