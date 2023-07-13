package com.isi.bobbo.bassirou.classe;

import com.isi.bobbo.bassirou.entity.SearchItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DayResponse {

    private Long id;
    private String date;
    private String dayOfWeek;
    private String searchDate;
    private List<SearchItem> searchItems;
}
