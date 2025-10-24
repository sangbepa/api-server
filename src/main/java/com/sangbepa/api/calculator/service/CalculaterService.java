package com.sangbepa.api.calculator.service;

import org.springframework.stereotype.Service;
import com.sangbepa.api.calculator.domain.CalculaterDTO;

@Service
public interface CalculaterService {

    CalculaterDTO calculate(CalculaterDTO dto);
}
