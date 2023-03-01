package assignment1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.mockito.Mockito.*;

public class CardGameTest {
    @Mock
    private Referee referee;
    @Mock
    private Dealer dealer;
    @Mock
    private Reporter reporter;
    @Mock
    private Player player1;
    @Mock
    private Player player2;
    @Mock
    private Player player3;
    @Mock
    private Player player4;
    @Captor
    private ArgumentCaptor<List<Card>> argumentOfCards;
    @Captor
    private ArgumentCaptor<List<RoundRecord>> argumentOfRecords;
    @Captor
    private ArgumentCaptor<List<PlayerRank>> argumentOfPlayerRanks;

    private CardGame underTest;

    @BeforeEach
    public void setUp() {
        // GIVEN
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testConstructor() {
        // WHEN
        List<Player> players = List.of(player1, player2, player3, player4);
        underTest = new CardGame(dealer, referee, reporter, players);
        // THEN
        Assertions.assertSame(dealer, underTest.getDealer());
        Assertions.assertSame(referee, underTest.getReferee());
        Assertions.assertSame(reporter, underTest.getReporter());
        Assertions.assertSame(players, underTest.getPlayers());
    }

    @Test
    void testStart() {
        // GIVEN
        List<Player> players = List.of(player1, player2, player3, player4);
        int numberOfPlayers = players.size();

        List<Card> cards = Arrays.asList(
                Card.builder().rank(Rank.TWO).suit(Suit.CLUBS).build(),
                Card.builder().rank(Rank.THREE).suit(Suit.CLUBS).build(),
                Card.builder().rank(Rank.FOUR).suit(Suit.CLUBS).build(),
                Card.builder().rank(Rank.FIVE).suit(Suit.CLUBS).build(),
                Card.builder().rank(Rank.SIX).suit(Suit.CLUBS).build(),
                Card.builder().rank(Rank.SEVEN).suit(Suit.CLUBS).build(),
                Card.builder().rank(Rank.EIGHT).suit(Suit.CLUBS).build(),
                Card.builder().rank(Rank.NINE).suit(Suit.CLUBS).build(),
                Card.builder().rank(Rank.TEN).suit(Suit.CLUBS).build(),
                Card.builder().rank(Rank.JACK).suit(Suit.CLUBS).build(),
                Card.builder().rank(Rank.QUEEN).suit(Suit.CLUBS).build(),
                Card.builder().rank(Rank.KING).suit(Suit.CLUBS).build(),
                Card.builder().rank(Rank.ACE).suit(Suit.CLUBS).build(),
                Card.builder().rank(Rank.TWO).suit(Suit.DIAMONDS).build(),
                Card.builder().rank(Rank.THREE).suit(Suit.DIAMONDS).build(),
                Card.builder().rank(Rank.FOUR).suit(Suit.DIAMONDS).build(),
                Card.builder().rank(Rank.FIVE).suit(Suit.DIAMONDS).build(),
                Card.builder().rank(Rank.SIX).suit(Suit.DIAMONDS).build(),
                Card.builder().rank(Rank.SEVEN).suit(Suit.DIAMONDS).build(),
                Card.builder().rank(Rank.EIGHT).suit(Suit.DIAMONDS).build(),
                Card.builder().rank(Rank.NINE).suit(Suit.DIAMONDS).build(),
                Card.builder().rank(Rank.TEN).suit(Suit.DIAMONDS).build(),
                Card.builder().rank(Rank.JACK).suit(Suit.DIAMONDS).build(),
                Card.builder().rank(Rank.QUEEN).suit(Suit.DIAMONDS).build(),
                Card.builder().rank(Rank.KING).suit(Suit.DIAMONDS).build(),
                Card.builder().rank(Rank.ACE).suit(Suit.DIAMONDS).build(),
                Card.builder().rank(Rank.TWO).suit(Suit.HEARTS).build(),
                Card.builder().rank(Rank.THREE).suit(Suit.HEARTS).build(),
                Card.builder().rank(Rank.FOUR).suit(Suit.HEARTS).build(),
                Card.builder().rank(Rank.FIVE).suit(Suit.HEARTS).build(),
                Card.builder().rank(Rank.SIX).suit(Suit.HEARTS).build(),
                Card.builder().rank(Rank.SEVEN).suit(Suit.HEARTS).build(),
                Card.builder().rank(Rank.EIGHT).suit(Suit.HEARTS).build(),
                Card.builder().rank(Rank.NINE).suit(Suit.HEARTS).build(),
                Card.builder().rank(Rank.TEN).suit(Suit.HEARTS).build(),
                Card.builder().rank(Rank.JACK).suit(Suit.HEARTS).build(),
                Card.builder().rank(Rank.QUEEN).suit(Suit.HEARTS).build(),
                Card.builder().rank(Rank.KING).suit(Suit.HEARTS).build(),
                Card.builder().rank(Rank.ACE).suit(Suit.HEARTS).build(),
                Card.builder().rank(Rank.TWO).suit(Suit.SPADES).build(),
                Card.builder().rank(Rank.THREE).suit(Suit.SPADES).build(),
                Card.builder().rank(Rank.FOUR).suit(Suit.SPADES).build(),
                Card.builder().rank(Rank.FIVE).suit(Suit.SPADES).build(),
                Card.builder().rank(Rank.SIX).suit(Suit.SPADES).build(),
                Card.builder().rank(Rank.SEVEN).suit(Suit.SPADES).build(),
                Card.builder().rank(Rank.EIGHT).suit(Suit.SPADES).build(),
                Card.builder().rank(Rank.NINE).suit(Suit.SPADES).build(),
                Card.builder().rank(Rank.TEN).suit(Suit.SPADES).build(),
                Card.builder().rank(Rank.JACK).suit(Suit.SPADES).build(),
                Card.builder().rank(Rank.QUEEN).suit(Suit.SPADES).build(),
                Card.builder().rank(Rank.KING).suit(Suit.SPADES).build(),
                Card.builder().rank(Rank.ACE).suit(Suit.SPADES).build());

        int numberOfRounds = cards.size() / numberOfPlayers;

        when(player1.getName()).thenReturn("Player 1");
        when(player2.getName()).thenReturn("Player 2");
        when(player3.getName()).thenReturn("Player 3");
        when(player4.getName()).thenReturn("Player 4");

        doNothing().when(dealer).receiveCard(argumentOfCards.capture());

        doNothing().when(player1).receiveCard(Mockito.any(Card.class));
        doNothing().when(player2).receiveCard(Mockito.any(Card.class));
        doNothing().when(player3).receiveCard(Mockito.any(Card.class));
        doNothing().when(player4).receiveCard(Mockito.any(Card.class));

        when(dealer.drawCard()).thenReturn(cards.get(0), cards.get(1), cards.get(2), cards.get(3), cards.get(4),
                cards.get(5), cards.get(6), cards.get(7), cards.get(8), cards.get(9), cards.get(10),
                cards.get(11), cards.get(12), cards.get(13), cards.get(14), cards.get(15), cards.get(16),
                cards.get(17), cards.get(18), cards.get(19), cards.get(20),
                cards.get(21), cards.get(22), cards.get(23), cards.get(24), cards.get(25), cards.get(26),
                cards.get(27), cards.get(28), cards.get(29), cards.get(30),
                cards.get(31), cards.get(32), cards.get(33), cards.get(34), cards.get(35), cards.get(36),
                cards.get(37), cards.get(38), cards.get(39), cards.get(40),
                cards.get(41), cards.get(42), cards.get(43), cards.get(44), cards.get(45), cards.get(46),
                cards.get(47), cards.get(48), cards.get(49), cards.get(50),
                cards.get(51));

        when(player1.playCard())
                .thenReturn(cards.get(0), cards.get(4), cards.get(8), cards.get(12), cards.get(16), cards.get(20),
                        cards.get(24), cards.get(28), cards.get(32), cards.get(36), cards.get(40), cards.get(44),
                        cards.get(48));
        when(player2.playCard())
                .thenReturn(cards.get(1), cards.get(5), cards.get(9), cards.get(13), cards.get(17), cards.get(21),
                        cards.get(25), cards.get(29), cards.get(33), cards.get(37), cards.get(41), cards.get(45),
                        cards.get(49));
        when(player3.playCard())
                .thenReturn(cards.get(2), cards.get(6), cards.get(10), cards.get(14), cards.get(18), cards.get(22),
                        cards.get(26), cards.get(30), cards.get(34), cards.get(38), cards.get(42), cards.get(46),
                        cards.get(50));
        when(player4.playCard())
                .thenReturn(cards.get(3), cards.get(7), cards.get(11), cards.get(15), cards.get(19), cards.get(23),
                        cards.get(27), cards.get(31), cards.get(35), cards.get(39), cards.get(43), cards.get(47),
                        cards.get(51));

        when(referee.getWinner(anyList())).thenReturn(player4, player4, player4, player4, player4, player4, player4,
                player4, player4, player4, player4, player4, player4);

        AtomicInteger cardIndex = new AtomicInteger(0);
        List<RoundRecord> roundRecords = IntStream.rangeClosed(1, numberOfRounds)
                .mapToObj(round -> {
                    List<RoundRecord.Record> records = players.stream()
                            .map(player -> RoundRecord.Record.builder()
                                    .player(player)
                                    .card(cards.get(cardIndex.getAndIncrement()))
                                    .build())
                            .collect(Collectors.toList());

                    return RoundRecord.builder()
                            .round(round)
                            .winner(player4)
                            .records(records)
                            .numberOfPlayers(numberOfPlayers)
                            .build();
                })
                .collect(Collectors.toList());

        List<PlayerRank> playerRanks = players.stream()
                .map(player -> PlayerRank.builder()
                        .points(player.getPoints())
                        .players(players)
                        .build())
                .collect(Collectors.toList());


        doNothing().when(referee).summaryPoints(argumentOfRecords.capture());
        doNothing().when(reporter).printResult(argumentOfPlayerRanks.capture());

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        underTest = new CardGame(dealer, referee, reporter, players);

        // WHEN
        underTest.start();

        // THEN
        verify(dealer, times(1)).receiveCard(anyList());
        verify(dealer, times(cards.size())).drawCard();
        verify(referee, times(numberOfRounds)).getWinner(anyList());
        verify(referee, times(1)).summaryPoints(anyList());
        verify(reporter, times(1)).printResult(anyList());

        verify(player1, times(numberOfRounds)).receiveCard(any(Card.class));
        verify(player2, times(numberOfRounds)).receiveCard(any(Card.class));
        verify(player3, times(numberOfRounds)).receiveCard(any(Card.class));
        verify(player4, times(numberOfRounds)).receiveCard(any(Card.class));

        verify(player1, times(numberOfRounds)).playCard();
        verify(player2, times(numberOfRounds)).playCard();
        verify(player3, times(numberOfRounds)).playCard();
        verify(player4, times(numberOfRounds)).playCard();


        IntStream.range(0, argumentOfCards.getValue().size()).forEach(index ->
                Assertions.assertTrue(cards.contains(argumentOfCards.getValue().get(index))));
        Assertions.assertEquals(cards.size(), argumentOfCards.getValue().size());

        IntStream.range(0, argumentOfRecords.getValue().size()).forEach(index -> {
            Assertions.assertEquals(
                    roundRecords.get(index).getRound(), argumentOfRecords.getValue().get(index).getRound());
            Assertions.assertEquals(
                    roundRecords.get(index).getWinner(), argumentOfRecords.getValue().get(index).getWinner());
            Assertions.assertEquals(
                    roundRecords.get(index).getNumberOfPlayers(),
                    argumentOfRecords.getValue().get(index).getNumberOfPlayers());
            List<RoundRecord.Record> recordsOfExpected = roundRecords.get(index).getRecords();
            List<RoundRecord.Record> recordsOfActual = argumentOfRecords.getValue().get(index).getRecords();

            IntStream.range(0, recordsOfActual.size()).forEach(indexOfRecord -> {
                Assertions.assertEquals(
                        recordsOfExpected.get(indexOfRecord).getPlayer(),
                        recordsOfActual.get(indexOfRecord).getPlayer());
                Assertions.assertEquals(
                        recordsOfExpected.get(indexOfRecord).getCard().getValue(),
                        recordsOfActual.get(indexOfRecord).getCard().getValue());
            });
        });


        IntStream.range(0, argumentOfPlayerRanks.getValue().size()).forEach(index -> {
            Assertions.assertEquals(
                    playerRanks.get(index).getPlayers(), argumentOfPlayerRanks.getValue().get(index).getPlayers());
            Assertions.assertEquals(
                    playerRanks.get(index).getPoints(), argumentOfPlayerRanks.getValue().get(index).getPoints());
        });


        Assertions.assertTrue(outputStream.toString().contains("第 1 局 - start\n" +
                "Player 1 出了一張 CLUBS:TWO\n" +
                "Player 2 出了一張 CLUBS:THREE\n" +
                "Player 3 出了一張 CLUBS:FOUR\n" +
                "Player 4 出了一張 CLUBS:FIVE\n" +
                "第 2 局 - start\n" +
                "Player 1 出了一張 CLUBS:SIX\n" +
                "Player 2 出了一張 CLUBS:SEVEN\n" +
                "Player 3 出了一張 CLUBS:EIGHT\n" +
                "Player 4 出了一張 CLUBS:NINE\n" +
                "第 3 局 - start\n" +
                "Player 1 出了一張 CLUBS:TEN\n" +
                "Player 2 出了一張 CLUBS:JACK\n" +
                "Player 3 出了一張 CLUBS:QUEEN\n" +
                "Player 4 出了一張 CLUBS:KING\n" +
                "第 4 局 - start\n" +
                "Player 1 出了一張 CLUBS:ACE\n" +
                "Player 2 出了一張 DIAMONDS:TWO\n" +
                "Player 3 出了一張 DIAMONDS:THREE\n" +
                "Player 4 出了一張 DIAMONDS:FOUR\n" +
                "第 5 局 - start\n" +
                "Player 1 出了一張 DIAMONDS:FIVE\n" +
                "Player 2 出了一張 DIAMONDS:SIX\n" +
                "Player 3 出了一張 DIAMONDS:SEVEN\n" +
                "Player 4 出了一張 DIAMONDS:EIGHT\n" +
                "第 6 局 - start\n" +
                "Player 1 出了一張 DIAMONDS:NINE\n" +
                "Player 2 出了一張 DIAMONDS:TEN\n" +
                "Player 3 出了一張 DIAMONDS:JACK\n" +
                "Player 4 出了一張 DIAMONDS:QUEEN\n" +
                "第 7 局 - start\n" +
                "Player 1 出了一張 DIAMONDS:KING\n" +
                "Player 2 出了一張 DIAMONDS:ACE\n" +
                "Player 3 出了一張 HEARTS:TWO\n" +
                "Player 4 出了一張 HEARTS:THREE\n" +
                "第 8 局 - start\n" +
                "Player 1 出了一張 HEARTS:FOUR\n" +
                "Player 2 出了一張 HEARTS:FIVE\n" +
                "Player 3 出了一張 HEARTS:SIX\n" +
                "Player 4 出了一張 HEARTS:SEVEN\n" +
                "第 9 局 - start\n" +
                "Player 1 出了一張 HEARTS:EIGHT\n" +
                "Player 2 出了一張 HEARTS:NINE\n" +
                "Player 3 出了一張 HEARTS:TEN\n" +
                "Player 4 出了一張 HEARTS:JACK\n" +
                "第 10 局 - start\n" +
                "Player 1 出了一張 HEARTS:QUEEN\n" +
                "Player 2 出了一張 HEARTS:KING\n" +
                "Player 3 出了一張 HEARTS:ACE\n" +
                "Player 4 出了一張 SPADES:TWO\n" +
                "第 11 局 - start\n" +
                "Player 1 出了一張 SPADES:THREE\n" +
                "Player 2 出了一張 SPADES:FOUR\n" +
                "Player 3 出了一張 SPADES:FIVE\n" +
                "Player 4 出了一張 SPADES:SIX\n" +
                "第 12 局 - start\n" +
                "Player 1 出了一張 SPADES:SEVEN\n" +
                "Player 2 出了一張 SPADES:EIGHT\n" +
                "Player 3 出了一張 SPADES:NINE\n" +
                "Player 4 出了一張 SPADES:TEN\n" +
                "第 13 局 - start\n" +
                "Player 1 出了一張 SPADES:JACK\n" +
                "Player 2 出了一張 SPADES:QUEEN\n" +
                "Player 3 出了一張 SPADES:KING\n" +
                "Player 4 出了一張 SPADES:ACE"));
    }
}













