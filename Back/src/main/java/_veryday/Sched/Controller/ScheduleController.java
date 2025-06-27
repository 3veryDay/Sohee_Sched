package _veryday.Sched.Controller;

import _veryday.Sched.Entity.Schedule;
import _veryday.Sched.Entity.ScheduleCategory;
import _veryday.Sched.Repository.ScheduleCategoryRepository;
import _veryday.Sched.Repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleCategoryRepository categoryRepository;

    @GetMapping
    public List<Schedule> getSchedules(@RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date);
        return scheduleRepository.findByDate(localDate);
    }

    @PostMapping
    public Schedule createSchedule(@RequestBody Schedule schedule) {
        // 카테고리 ID만 들어온 경우 처리
        ScheduleCategory category =
                categoryRepository.findById(schedule.getCategory().getId())
                        .orElseThrow(() -> new RuntimeException("카테고리 없음"));

        schedule.setCategory(category);
        return scheduleRepository.save(schedule);
    }

    @PutMapping("/{id}")
    public Schedule updateSchedule(@PathVariable Long id, @RequestBody Schedule updated) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));
        schedule.setTitle(updated.getTitle());
        schedule.setNote(updated.getNote());
        schedule.setDate(updated.getDate());
        schedule.setRepeatType(updated.getRepeatType());
        schedule.setRepeatValue(updated.getRepeatValue());
        return scheduleRepository.save(schedule);
    }

    @DeleteMapping("/{id}")
    public void deleteSchedule(@PathVariable Long id) {
        scheduleRepository.deleteById(id);
    }
}