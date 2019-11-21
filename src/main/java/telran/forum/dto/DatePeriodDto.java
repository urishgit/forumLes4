package telran.forum.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Getter
@JsonFormat(pattern = "yyyy-MM-dd")
public class DatePeriodDto {

	LocalDate dateFrom;
	LocalDate dateTo;
}
