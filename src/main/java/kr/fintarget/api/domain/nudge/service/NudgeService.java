package kr.fintarget.api.domain.nudge.service;
import kr.fintarget.api.domain.nudge.dto.NudgeResponse;
import kr.fintarget.api.domain.nudge.entity.Nudge;
import kr.fintarget.api.domain.nudge.repository.NudgeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class NudgeService {
    private final NudgeRepository nudgeRepository;
    public Map<String, Object> getNudges(String userId) {
        List<Nudge> nudges = nudgeRepository.findByUserIdOrderByCreatedAtDesc(userId);
        long unreadCount = nudgeRepository.countByUserIdAndIsRead(userId, false);
        return Map.of(
                "nudges", nudges.stream().map(NudgeResponse::from).collect(Collectors.toList()),
                "unreadCount", unreadCount
        );
    }
    @Transactional
    public void markAsRead(String userId, String nudgeId) {
        Nudge nudge = nudgeRepository.findById(nudgeId)
                .orElseThrow(() -> new IllegalArgumentException("넛지를 찾을 수 없습니다."));
        if (!nudge.getUserId().equals(userId)) {
            throw new SecurityException("접근 권한이 없습니다.");
        }
        nudge.markAsRead();
    }
}
