package com.yackson.personapi.service.impl;

import com.yackson.personapi.model.World;
import com.yackson.personapi.model.input.WorldInput;
import com.yackson.personapi.service.IWorldService;
import com.yackson.personapi.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WorldServiceImpl implements IWorldService {
    private WorldInput worldInput;
    private World world = new World();

    public WorldServiceImpl() {
        buildFile();
    }

    private void buildFile() {
        this.worldInput = FileUtil.loadWorldInput();
        log.info("Building the output file");
        worldInput.getCityInputs().forEach(world::addNew);
        FileUtil.saveWorldOutput(world);
    }
}
