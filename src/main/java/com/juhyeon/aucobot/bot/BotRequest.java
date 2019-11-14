package com.juhyeon.aucobot.bot;

import lombok.*;
import org.thymeleaf.util.StringUtils;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BotRequest {
    private String issueNumber;
    private String author;
    private String title;
    private String body;

    public boolean checkValidation() {
        return !StringUtils.isEmpty(title) && !StringUtils.isEmpty(body);
    }

}
