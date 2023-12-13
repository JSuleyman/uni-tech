package com.example.unitech.utility;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@Data
@Component
public class SessionManager {
    static final String SEPERATOR = "__";
    public static final String DEFAULT_LANG = "AZ";

    String userId;
    String currentUserId;
    String currentUserName;
    String currentThreadId;
    String currentRoleId;
    String lang = DEFAULT_LANG;
    String token;
}
