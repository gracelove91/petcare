package kr.gracelove.petcare.service;

public class MemberNotFoundException extends RuntimeException {
    public MemberNotFoundException(String loginId) {
        super("존재하지않는 사용자입니다.  : " +loginId);
    }
}
