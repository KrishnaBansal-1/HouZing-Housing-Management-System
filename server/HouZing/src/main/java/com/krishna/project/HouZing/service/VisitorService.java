package com.krishna.project.HouZing.service;

import com.krishna.project.HouZing.repository.VisitorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VisitorService {

    private final VisitorRepository visitorRepository;


}
