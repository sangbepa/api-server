package com.sangbepa.api.member.controller;

import com.sangbepa.api.member.domain.MemberDTO;
import com.sangbepa.api.member.service.MemberService;
import org.springframework.stereotype.Controller;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    
    @PostMapping("") // 결국 ("")은 이름을 남겨주는 것.- id가 없는 상태 - 아직 id에 id가 없다.
    public void save(@RequestBody MemberDTO MemberDTO) {
       
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @PutMapping("/{id}") // 결국 ("")은 이름을 남겨주는 것.- id가 없는 상태
    public void update(@RequestBody MemberDTO MemberDTO) {
       
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @DeleteMapping("/{id}") // db에서 생성된 id - html에서 입력한 id가 아니다.
    public void delete(@PathVariable Long id) {
       
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @GetMapping("/id/{id}") // 결국 ("")은 이름을 남겨주는 것.
    public void findById(@PathVariable Long id) {
       
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @GetMapping("all")
    public void findAll() {
       
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

}
