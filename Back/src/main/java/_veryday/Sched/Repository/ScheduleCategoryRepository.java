package _veryday.Sched.Repository;


import _veryday.Sched.Entity.ScheduleCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleCategoryRepository extends JpaRepository<ScheduleCategory, Long> {
}