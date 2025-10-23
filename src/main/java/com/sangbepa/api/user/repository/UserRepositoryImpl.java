package com.sangbepa.api.user.repository;

import org.springframework.stereotype.Repository;
import com.sangbepa.api.user.domain.UserDTO;
import com.sangbepa.api.user.domain.UserEntity;
import com.sangbepa.api.user.domain.UserVO;
import com.sangbepa.api.common.domain.Messenger;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * User Repository 구현체 - CRUD + 기존 기능 구현
 * 메모리 저장소 방식 (연습용)
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    // 메모리 저장소 (DB 대신 사용)
    private final List<UserEntity> database = new ArrayList<>();
    private final AtomicInteger idGenerator = new AtomicInteger(1);

    /**
     * CREATE - 사용자 저장
     */
    @Override
    public Messenger save(UserDTO dto) {
        System.out.println("\n[Repository] save() 호출");

        try {
            // DTO → Entity 변환
            UserEntity entity = new UserEntity(
                    dto.getPassengerId() != null ? dto.getPassengerId() : idGenerator.getAndIncrement(),
                    dto.getSurvived(),
                    dto.getPclass(),
                    dto.getName(),
                    dto.getSex(),
                    dto.getAge(),
                    dto.getSibSp(),
                    dto.getParch(),
                    dto.getTicket(),
                    dto.getFare(),
                    dto.getCabin(),
                    dto.getEmbarked());

            database.add(entity);
            System.out.println("[Repository] ✓ 저장 완료 - ID: " + entity.getPassengerId());

            Messenger messenger = new Messenger();
            messenger.setCode(0);
            messenger.setMessage("사용자 저장 완료 - ID: " + entity.getPassengerId());
            return messenger;
        } catch (Exception e) {
            System.out.println("[Repository] ✗ 저장 실패: " + e.getMessage());
            Messenger messenger = new Messenger();
            messenger.setCode(-1);
            messenger.setMessage("저장 실패: " + e.getMessage());
            return messenger;
        }
    }

    /**
     * READ - 전체 조회
     */
    @Override
    public Messenger findAll() {
        System.out.println("\n[Repository] findAll() 호출");
        System.out.println("[Repository] 총 " + database.size() + "명 조회");

        Messenger messenger = new Messenger();
        messenger.setCode(0);
        messenger.setMessage("전체 조회 완료 - 총 " + database.size() + "명");
        return messenger;
    }

    /**
     * READ - ID로 조회
     */
    @Override
    public Messenger findById(Integer id) {
        System.out.println("\n[Repository] findById(" + id + ") 호출");

        for (UserEntity entity : database) {
            if (entity.getPassengerId().equals(id)) {
                System.out.println("[Repository] ✓ 조회 완료 - " + entity.getName());
                Messenger messenger = new Messenger();
                messenger.setCode(0);
                messenger.setMessage("조회 완료 - " + entity.getName());
                return messenger;
            }
        }

        System.out.println("[Repository] ✗ ID를 찾을 수 없음");
        Messenger messenger = new Messenger();
        messenger.setCode(1);
        messenger.setMessage("ID " + id + "를 찾을 수 없습니다");
        return messenger;
    }

    /**
     * UPDATE - 사용자 수정
     */
    @Override
    public Messenger update(Integer id, UserDTO dto) {
        System.out.println("\n[Repository] update(" + id + ") 호출");

        for (int i = 0; i < database.size(); i++) {
            if (database.get(i).getPassengerId().equals(id)) {
                UserEntity updatedEntity = new UserEntity(
                        id,
                        dto.getSurvived(),
                        dto.getPclass(),
                        dto.getName(),
                        dto.getSex(),
                        dto.getAge(),
                        dto.getSibSp(),
                        dto.getParch(),
                        dto.getTicket(),
                        dto.getFare(),
                        dto.getCabin(),
                        dto.getEmbarked());
                database.set(i, updatedEntity);
                System.out.println("[Repository] ✓ 수정 완료");

                Messenger messenger = new Messenger();
                messenger.setCode(0);
                messenger.setMessage("수정 완료 - ID: " + id);
                return messenger;
            }
        }

        System.out.println("[Repository] ✗ ID를 찾을 수 없음");
        Messenger messenger = new Messenger();
        messenger.setCode(1);
        messenger.setMessage("ID " + id + "를 찾을 수 없습니다");
        return messenger;
    }

    /**
     * DELETE - 사용자 삭제
     */
    @Override
    public Messenger delete(Integer id) {
        System.out.println("\n[Repository] delete(" + id + ") 호출");

        boolean removed = database.removeIf(entity -> entity.getPassengerId().equals(id));

        if (removed) {
            System.out.println("[Repository] ✓ 삭제 완료");
            Messenger messenger = new Messenger();
            messenger.setCode(0);
            messenger.setMessage("삭제 완료 - ID: " + id);
            return messenger;
        } else {
            System.out.println("[Repository] ✗ ID를 찾을 수 없음");
            Messenger messenger = new Messenger();
            messenger.setCode(1);
            messenger.setMessage("ID " + id + "를 찾을 수 없습니다");
            return messenger;
        }
    }

    /**
     * BULK - CSV 데이터 터미널 출력 (기존 메서드 구현)
     * Controller의 saveALL()에서 호출하는 메서드
     */
    @Override
    public Messenger printPassengerInfo(List<UserDTO> passengers) {
        System.out.println("\n[Repository] printPassengerInfo() 호출");
        System.out.println("==========================================");
        System.out.println("  받은 데이터: " + passengers.size() + "개의 DTO");
        System.out.println("==========================================\n");

        for (int i = 0; i < passengers.size(); i++) {
            UserDTO dto = passengers.get(i);

            // DTO → VO 변환 (읽기 전용)
            UserVO vo = new UserVO(
                    dto.getPassengerId(),
                    dto.getSurvived(),
                    dto.getPclass(),
                    dto.getName(),
                    dto.getSex(),
                    dto.getAge(),
                    dto.getSibSp(),
                    dto.getParch(),
                    dto.getTicket(),
                    dto.getFare(),
                    dto.getCabin(),
                    dto.getEmbarked());

            // 터미널 출력 (VO 사용)
            System.out.println("┌─────────────────────────────────────────┐");
            System.out.println("│  👤 승객 #" + (i + 1) + " 정보");
            System.out.println("├─────────────────────────────────────────┤");
            System.out.println("│  ID        : " + vo.getPassengerId());
            System.out.println("│  이름      : " + vo.getName());
            System.out.println("│  성별      : " + vo.getSex());
            System.out.println("│  나이      : " + (vo.getAge() != null ? vo.getAge() + "세" : "알 수 없음"));
            System.out.println("│  생존 여부 : " + (vo.getSurvived() == 1 ? "✓ 생존" : "✗ 사망"));
            System.out.println("│  등급      : " + vo.getPclass() + "등석");
            System.out.println("│  요금      : $" + (vo.getFare() != null ? vo.getFare() : "0"));
            System.out.println("│  티켓      : " + vo.getTicket());
            System.out.println(
                    "│  객실      : " + (vo.getCabin() != null && !vo.getCabin().isEmpty() ? vo.getCabin() : "미배정"));
            System.out.println("│  형제자매  : " + vo.getSibSp());
            System.out.println("│  부모자녀  : " + vo.getParch());
            System.out.println("│  탑승 항구 : " + vo.getEmbarked());
            System.out.println("└─────────────────────────────────────────┘\n");
        }

        System.out.println("==========================================");
        System.out.println("[Repository] ✓ " + passengers.size() + "명 출력 완료");
        System.out.println("==========================================\n");

        // Messenger 생성 및 반환
        Messenger messenger = new Messenger();
        messenger.setCode(0);
        messenger.setMessage("Repository: " + passengers.size() + "명의 승객 정보를 성공적으로 출력했습니다");
        return messenger;
    }

    /**
     * 전체 데이터 삭제 (테스트용)
     */
    public void deleteAll() {
        System.out.println("[Repository] 전체 데이터 삭제");
        database.clear();
        idGenerator.set(1);
    }
}
