package kr.gracelove.petcare.service;

public class DuplicateLoginId extends RuntimeException {
    public DuplicateLoginId(String loginId) {
        super("중복된 아이디입니다." +loginId);
    }
}
