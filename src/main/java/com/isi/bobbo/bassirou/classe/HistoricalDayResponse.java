package com.isi.bobbo.bassirou.classe;

import com.isi.bobbo.bassirou.entity.SearchItem;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistoricalDayResponse {

    private Long id;
    private String date;
    private String dayOfWeek;
    private String searchDate;
    private List<SearchItem> searchItems;
}
