package assignment1;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
public class PlayerRank {
    private final Integer points;
    private final List<Player> players;
}
