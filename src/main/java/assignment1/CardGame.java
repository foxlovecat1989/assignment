package assignment1;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


//1. Playing card game
//   Design a simple playing card game that allows multiple players to join.
//   Each player would receive an amount of cards dispatched by a dealer.
//   Each turn, every player plays a card and the player with the biggest card wins.
//   The game ends when there is a player who is unable to play a card.
//   The player who wins the most is the final winner.
//   Notes
//   ○ The number of players is configurable.
//   ○ Use OOP to implement the game.
//   ○ The history of the game should be printed out when the game is finished.
//   ○ Include unit tests for all the code.

@Getter
public class CardGame {
    private final Dealer dealer;
    private final Referee referee;
    private final Reporter reporter;
    private final List<Player> players;

    public CardGame(Dealer dealer, Referee referee, Reporter reporter, List<Player> players) {
        this.dealer = dealer;
        this.referee = referee;
        this.reporter = reporter;
        this.players = players;
    }

    public static void main(String[] args) {
        // 玩家數量由 environment variable args[0] 設定
        int numberOfPlayers = Integer.parseInt(args[0]);
        List<Player> players = IntStream.rangeClosed(1, numberOfPlayers)
                .mapToObj(num -> new Player("Player " + num, UUID.randomUUID()))
                .collect(Collectors.toList());
        new CardGame(new Dealer(), new Referee(), new Reporter(), players).start();
    }

    public void start() {
        int numberOfPlayers = this.players.size();
        // 取得牌
        List<Card> cards = getCards();
        // 取得局數
        int numberOfRounds = getNumberOfRounds(numberOfPlayers, cards);
        // 洗牌
        Collections.shuffle(cards);
        // dealer取牌
        dealer.receiveCard(cards);
        // dealer發牌給各個玩家
        int numCardsPerPlayer = getNumberOfRounds(players.size(), getCards());
        IntStream.range(0, numCardsPerPlayer).forEach(num ->
                players.forEach(player -> {
                    Card card = dealer.drawCard();
                    player.receiveCard(card);
                }));
        // 開局
        List<RoundRecord> records = play(numberOfRounds, players);
        // 總結得分
        referee.summaryPoints(records);
        // 總結名次
        List<PlayerRank> playerRanks = summaryRank(players);
        // 公布名次
        reporter.printResult(playerRanks);
    }

    private List<RoundRecord> play(int numberOfRounds, List<Player> players) {
        return IntStream.rangeClosed(1, numberOfRounds)
                .mapToObj(round -> {
                    System.out.println("第 " + round + " 局 - start");
                    List<RoundRecord.Record> records = players.stream()
                            .map(player -> {
                                Card currentCard = player.playCard();
                                System.out.println(
                                        player.getName()
                                                + " 出了一張 "
                                                + currentCard.getSuit()
                                                + ":"
                                                + currentCard.getRank());

                                return RoundRecord.Record.builder()
                                        .player(player)
                                        .card(currentCard)
                                        .build();
                            })
                            .collect(Collectors.toList());

                    Player winner = referee.getWinner(records);

                    return RoundRecord.builder()
                            .records(records)
                            .numberOfPlayers(players.size())
                            .winner(winner)
                            .round(round)
                            .build();
                })
                .collect(Collectors.toList());
    }

    private List<PlayerRank> summaryRank(List<Player> players) {
        return players.stream()
                .collect(Collectors.groupingBy(Player::getPoints)).entrySet().stream()
                .map(entry -> PlayerRank.builder()
                        .points(entry.getKey())
                        .players(entry.getValue())
                        .build())
                .collect(Collectors.toList());
    }

    private List<Card> getCards() {
        return Arrays.stream(Rank.values())
                .map(rank -> Arrays.stream(Suit.values())
                        .map(suit -> Card.builder()
                                .rank(rank)
                                .suit(suit)
                                .build())
                        .collect(Collectors.toList()))
                .collect(Collectors.toList()).stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private int getNumberOfRounds(int numberOfPlayers, List<Card> cards) {
        return cards.size() / numberOfPlayers;
    }
}





