package _veryday.Sched.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ScheduleCategory category;

    private String title;

    private String note;

    private LocalDate date; // 단발성 일정

    private String repeatType; // daily, weekly, monthly, yearly

    private String repeatValue; // weekly: MONDAY,THURSDAY

}