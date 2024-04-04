package com.ncba.miniapp.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class FetchToken {
    private String token;
    private String version;
}


