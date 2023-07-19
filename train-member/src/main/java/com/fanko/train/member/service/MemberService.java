package com.fanko.train.member.service;

import cn.hutool.core.collection.CollUtil;
import com.fanko.train.member.domain.Member;
import com.fanko.train.member.domain.MemberExample;
import com.fanko.train.member.mapper.MemberMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    @Resource
    private MemberMapper memberMapper;
    public int count(){
        return Math.toIntExact(memberMapper.countByExample(null));
    }

    public long register(String mobile){
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileNotEqualTo(mobile);
        List<Member> list = memberMapper.selectByExample(memberExample);
        if (CollUtil.isNotEmpty(list)){
//            return list.get(0).getId();
            throw new RuntimeException("手机号已注册");
        }
        Member member = new Member();
        member.setId(System.currentTimeMillis());
        member.setMobile(mobile);
        memberMapper.insert(member);
        return member.getId();
    }
}