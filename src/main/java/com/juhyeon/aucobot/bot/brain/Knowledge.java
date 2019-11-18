package com.juhyeon.aucobot.bot.brain;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "knowledge")
public class Knowledge {
    /*
    *
    * @type
    * notice | schedule | guide
    *
     */
    private int code;
    private String type;
    private String message;

    public static final String NOTICE_TYPE = "notice";
    public static final String SCHEDULE_TYPE = "schedule";
    public static final String GUIDE_TYPE = "guide";

}
