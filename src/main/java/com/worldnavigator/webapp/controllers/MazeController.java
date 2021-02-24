package com.worldnavigator.webapp.controllers;

import static java.util.stream.Collectors.toList;

import com.worldnavigator.webapp.dto.MazeTemplateInfo;
import com.worldnavigator.webapp.mappers.GameMapper;
import com.worldnavigator.webapp.repositories.MazeTemplateRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mazes")
public class MazeController {

    private final GameMapper gameMapper;

    private final MazeTemplateRepository mazeTemplateRepository;

    @Autowired
    public MazeController(GameMapper gameMapper, MazeTemplateRepository mazeTemplateRepository) {
        this.gameMapper = gameMapper;
        this.mazeTemplateRepository = mazeTemplateRepository;
    }

    @GetMapping
    public List<MazeTemplateInfo> list() {
        return mazeTemplateRepository.findAll()
                .stream()
                .map(gameMapper::toMazeTemplateInfo)
                .collect(toList());
    }
}
