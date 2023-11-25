package hadi.co.sample.api.utils;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SampleSubDto implements Serializable {
    private String sample1;
    private String sample2;
}
