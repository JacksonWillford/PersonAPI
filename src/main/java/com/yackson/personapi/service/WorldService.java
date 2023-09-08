package com.yackson.personapi.service;

import com.yackson.personapi.model.input.WorldInput;
import com.yackson.personapi.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WorldService {
    private WorldInput worldInput;
    public WorldService() {
        this.worldInput = FileUtil.loadWorldInput();
    }
}
