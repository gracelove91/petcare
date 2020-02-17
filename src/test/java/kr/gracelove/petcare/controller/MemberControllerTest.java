package kr.gracelove.petcare.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.gracelove.petcare.controller.dto.MemberJoinRequestDto;
import kr.gracelove.petcare.domain.common.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void create() throws Exception {
        MemberJoinRequestDto joinRequestDto = MemberJoinRequestDto.builder()
                .loginId("grace")
                .password("password")
                .password2("password")
                .address(new Address("서울시", "관악구", "봉천동", "123-123번지"))
                .email("govlmo91@gmail.com")
                .phoneNumber("010-3349-9440")
                .build();

        mockMvc.perform(post("/api/v1/members")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(joinRequestDto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @Test
    void create_duplicate_loginId() throws Exception {
        MemberJoinRequestDto joinRequestDto1 = MemberJoinRequestDto.builder()
                .loginId("grace")
                .password("password")
                .password2("password")
                .address(new Address("서울시", "관악구", "봉천동", "123-123번지"))
                .email("govlmo91@gmail.com")
                .phoneNumber("010-3349-9440")
                .build();

        MemberJoinRequestDto joinRequestDto2 = MemberJoinRequestDto.builder()
                .loginId("grace")
                .password("password")
                .password2("password")
                .address(new Address("서울시", "관악구", "봉천동", "123-123번지"))
                .email("govlmo91@gmail.com")
                .phoneNumber("010-3349-9440")
                .build();

        mockMvc.perform(post("/api/v1/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(joinRequestDto1)))
                .andDo(print())
                .andExpect(status().isCreated());

        mockMvc.perform(post("/api/v1/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(joinRequestDto2)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void getMember_member_not_found_exception() throws Exception {
        mockMvc.perform(get("/api/v1/members/{id}", 999L))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}