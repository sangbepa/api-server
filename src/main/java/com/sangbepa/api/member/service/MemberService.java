package com.sangbepa.api.member.service;

import com.sangbepa.api.member.domain.MemberDTO;

public interface MemberService {
    public void save(MemberDTO MemberDTO);
    public void update(MemberDTO MemberDTO);
    public void delete(MemberDTO MemberDTO);
    // command 
    public void findById(MemberDTO MemberDTO);
    public void findAll(MemberDTO MemberDTO);
    // query

}
